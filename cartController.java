import java.net.URL;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class cartController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button clearAll;

    @FXML
    private Label myBudget;

    @FXML
    private Button submit;

    @FXML
    private Label cartTotalPrice;

    private Connection connect;
    private ResultSet result;
    private ResultSet result1;
    private PreparedStatement prepare, prepare1, prepare3;
    private Statement statement;
    private Statement statement1;

    ArrayList<Product> arr = new ArrayList<>();

    // CoustomerController cc=new CoustomerController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myBudget.setText(Account.budget);
        arr.clear();
        vbox.getChildren().clear();
        getData();
        cartTotalPrice.setText(String.valueOf(getTotal()));
        try {
            for (int i = 0; i < arr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemCart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemController itemController = fxmlLoader.getController();
                itemController.setData(arr.get(i));

                vbox.getChildren().add(anchorPane); 

                scrollPane.setFitToWidth(true);

                vbox.setMargin(anchorPane, new Insets(10));
                myBudget.setText(Account.budget);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getData() {
        arr = CoustomerController.cartItems;
    }

    public void clearAllAction() {
        arr.clear();
        vbox.getChildren().clear();
    }

    public void submitCart() {
        alertMassege alret = new alertMassege();
        adminController ad = new adminController();
        ArrayList<Product> tb = ad.getTableData();
        int id = 0;
        try {
            connect = dataBase.connectDb();
            Statement statement = connect.createStatement();

            String inserOrder = "INSERT INTO F_Order(Odate,customer_id)VALUES (?, ?)";
            PreparedStatement prepare = connect.prepareStatement(inserOrder);
            LocalDate date = LocalDate.now();
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();

            prepare.setString(1, date.toString());
            prepare.setString(2, String.valueOf(getCosId()));
            prepare.executeUpdate();

            String gitOid = "SELECT Onumber FROM F_order WHERE Onumber=(SELECT MAX(Onumber) FROM F_order)";
            int orderId = 0;
            PreparedStatement prepare0 = connect.prepareStatement(gitOid);
            result = prepare0.executeQuery();

            if (result.next()) { // Move cursor to the first row
                orderId = result.getInt(1);
            }

            String inserOrderLine = "INSERT INTO Order_Line (order_id,product_id,quantity)VALUES (?,?,?)";

            int OrderQuantity = 0, OrderPrice = 0;
            for (int i = 0; i < arr.size(); i++) {
                PreparedStatement lineStmt = connect.prepareStatement(inserOrderLine);
                lineStmt.setInt(1, orderId);
                lineStmt.setString(2, arr.get(i).Pid);
                lineStmt.setString(3, arr.get(i).Pquantity);
                lineStmt.executeUpdate();

                OrderQuantity += Integer.parseInt(arr.get(i).Pquantity);
                OrderPrice += Double.parseDouble(arr.get(i).Sprice) * Double.parseDouble(arr.get(i).getPquantity());
            }

            if (Double.parseDouble(Account.budget) < OrderPrice) {
                alret.errorMassage("Error, Please check your budget...");
                return;
            }

            String updateOrder = "update F_Order  SET quantity=?,price=? where Onumber =?";

            PreparedStatement prepare2 = connect.prepareStatement(updateOrder);
            prepare2.setString(1, String.valueOf(OrderQuantity));
            prepare2.setString(2, String.valueOf(OrderPrice));
            prepare2.setString(3, String.valueOf(orderId));
            prepare2.executeUpdate();

            for (int i = 0; i < tb.size(); i++) {
                for (int j = 0; j < arr.size(); j++) {
                    if (tb.get(i).getPid().equals(arr.get(j).getPid())) {
                        String UpdateQ = "Update Products set quantity=? where id=?";
                        try {
                            PreparedStatement prepare13 = connect.prepareStatement(UpdateQ);
                            int quantity = Integer.parseInt(tb.get(i).getPquantity())
                                    - Integer.parseInt(arr.get(j).getPquantity());

                            prepare13.setString(1, String.valueOf(quantity));
                            prepare13.setString(2, String.valueOf(tb.get(i).getPid()));
                            prepare13.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            String updateBudgut = "update Users SET budget=? where email =?";

            try {
                prepare2 = connect.prepareStatement(updateBudgut);
                double newBudgut = Double.parseDouble(Account.budget) - OrderPrice;
                System.out.println(newBudgut);

                System.out.println(OrderPrice);
                prepare2.setString(1, String.valueOf(newBudgut));
                prepare2.setString(2, String.valueOf(Account.email));
                prepare2.executeUpdate();
                Account.budget = String.valueOf(newBudgut);
                alret.successMessage("Purchase succeful");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String AdminBudget = "update Users  SET budget=? where U_role = 'Admin'";

            try {
                prepare3 = connect.prepareStatement(AdminBudget);
                double newBudgut = getFirstAdmin() + OrderPrice;

                prepare3 = connect.prepareStatement(AdminBudget);
                prepare3.setString(1, String.valueOf(newBudgut));
                prepare3.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            arr.clear();
            vbox.getChildren().clear();
            myBudget.setText(Account.budget);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public double getFirstAdmin() {
        String getAdmin = "SELECT budget FROM users WHERE U_role = 'Admin' LIMIT 1";
        double budget = 0;
        try {
            connect = dataBase.connectDb();
            prepare = connect.prepareStatement(getAdmin);
            result = prepare.executeQuery();

            if (result.next()) {
                budget = result.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budget;
    }

    public String getAdminBalance() {
        String valueBalance = "";
        String AdminBalance = "select budget from users where U_role = admin";
        try {
            connect = dataBase.connectDb();
            prepare3 = connect.prepareStatement(AdminBalance);
            ResultSet st = prepare3.executeQuery();
            if (st.next()) {
                valueBalance = String.valueOf(st.getInt(1));
            }
            return valueBalance;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return valueBalance;
    }

    public int getCosId() {
        int customerId = 0;

        String gitCid = "select id from Customers where Cname =? ";
        try {
            prepare = connect.prepareStatement(gitCid);
            prepare.setString(1, Account.username);
            result = prepare.executeQuery();

            if (result.next()) {
                customerId = result.getInt(1);
            }
            return customerId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerId;
    }

    public double getTotal() {
        int OrderPrice = 0;
        for (int i = 0; i < arr.size(); i++) {
            OrderPrice += Double.parseDouble(arr.get(i).Sprice) * Double.parseDouble(arr.get(i).getPquantity());
        }
        return OrderPrice;
    }

}
