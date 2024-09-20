import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CoustomerController implements Initializable {

    @FXML
    private Label WelcomeName;

    @FXML
    private Button cartBtn;

    @FXML
    private AnchorPane historyForm;

    @FXML
    private TableView<Forder> historyTable;

    @FXML
    private TilePane tilePane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView selectImg;

    @FXML
    private Button signoutBtn;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Button addToCart;

    @FXML
    private Label nameSelect;

    @FXML
    private Label priceSelect;

    @FXML
    private AnchorPane productsForm;

    @FXML
    private TableColumn<Forder, String> qCol;

    @FXML
    private TableColumn<Forder, String> priceCol;

    @FXML
    private TableColumn<Forder, String> oidCol;

    @FXML
    private TableColumn<Forder, String> coustomerCol;

    @FXML
    private TableColumn<Forder, String> dateCol;

    @FXML
    private Label timeLable;

    @FXML
    private ImageView menuImage;
    
    @FXML
    private Button productsBtn;

    private java.sql.Connection connect;
    private ResultSet result;
    private ResultSet result1;
    private PreparedStatement prepare;
    private PreparedStatement prepare1;
    private Statement statement;
    private Statement statement1;
    private MyListener myListener;

    ArrayList<Product> arr = new ArrayList<>();
    Product selecteProduct;

    static ArrayList<Product> cartItems = new ArrayList<>();
    RotateTransition rotateTransition;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arr.clear();
        rotateTransition = new RotateTransition(Duration.seconds(0.5), menuImage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLable.setText(currentTime.format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        productsBtn.fire();
        productsForm.setVisible(true);
        historyForm.setVisible(false);
        WelcomeName.setText(Account.username);
        oidCol.setCellValueFactory(new PropertyValueFactory<>("Onumber"));
        qCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        coustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        value.setValue(1);
        spinner.setValueFactory(value);

        arr = getData();
    }

    public ArrayList<Product> getData() {
        ArrayList<Product> x = new ArrayList<>();
        String str = "select id,pname,Productioncost,Sellingcost,warehouse_limit,imgUrl,materialRate from products";
        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(str);

            while (result.next()) {
                String pid = String.valueOf(result.getInt(1));
                String pname = String.valueOf(result.getString(2));
                String productinCost = String.valueOf(result.getDouble(3));
                String sellingPrice = String.valueOf(result.getDouble(4));
                String WhareHouse = String.valueOf(result.getInt(5));
                String imgUrl = String.valueOf(result.getString(6));
                Double materialRate = (result.getDouble(7));

                Product product = new Product();
                product.setPid(pid);
                product.setPname(pname);
                product.setPprice(productinCost);
                product.setSprice(sellingPrice);
                product.setPimage(imgUrl);
                product.setPlimit(WhareHouse);
                product.setReqMaterial(materialRate);
                x.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    private void setChosenProduct(Product product) {
        nameSelect.setText(product.getPname());
        priceSelect.setText(product.getSprice());
        Image image = new Image(product.getPimage());
        selectImg.setImage(image);
    }

    public void historyAction() {
        productsForm.setVisible(false);
        historyForm.setVisible(true);
        historyTable.getItems().clear();
        historyTable.setItems(FXCollections.observableArrayList(getF_Order()));
    }

    public void productAction() {
        productsForm.setVisible(true);
        historyForm.setVisible(false);
        arr.clear();
        tilePane.getChildren().clear();
        arr.addAll(getData());

        if (arr.size() > 0) {
            setChosenProduct(arr.get(0));
            selecteProduct=arr.get(0);
            myListener = new MyListener() {

                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                    selecteProduct = product;
                    selecteProduct.setPquantity(product.Pquantity);
                }
            };

            try {
                for (int i = 0; i < arr.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("productsCard.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    productController itemController = fxmlLoader.getController();
                    itemController.setData(arr.get(i), myListener);

                    tilePane.getChildren().add(anchorPane); // (child,column,row)

                    tilePane.setHgap(10);
                    tilePane.setVgap(10);
                    scrollPane.setFitToWidth(true);

                    tilePane.setMargin(anchorPane, new Insets(10));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void exitAction() {
        System.exit(0);
    }

    public void myProfileAction() {
        AnchorPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("My Profile");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signoutAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.show();
            signoutBtn.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToCart() {
        adminController ad = new adminController();
        ArrayList<Product> check = ad.getTableData();
        alertMassege alert = new alertMassege();
        if (!exist(check, selecteProduct)) {
            alert.errorMassage("Error, The Product is not avliable in the inventory...");
            return;
        }

        for (int i = 0; i < check.size(); i++) {
            if (check.get(i).getPname().equals(selecteProduct.getPname())) {
                if (Integer.parseInt(check.get(i).getPquantity()) < spinner.getValue()) {
                    alert.errorMassage("Error,This quantity is not available ...");
                    return;
                }
            }
        }

        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getPname().equals(selecteProduct.Pname)) {
                int quantity = spinner.getValue() + Integer.parseInt(cartItems.get(i).getPquantity());
                cartItems.get(i).setPquantity(String.valueOf(quantity));
                return;
            }
        }
        selecteProduct.setPquantity(String.valueOf(spinner.getValue()));
        cartItems.add(selecteProduct);
    }

    public void cartAction() {
        AnchorPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("cart.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("My Cart");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(ArrayList<Product> arr, Product selecteProduct) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getPid().equals(selecteProduct.getPid())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Forder> getF_Order() {
        ArrayList<Forder> res = new ArrayList<>();
        String getFOrder = "select * from F_Order where customer_id=?";
        connect = dataBase.connectDb();
        try {
            prepare = connect.prepareStatement(getFOrder);
            prepare.setString(1, String.valueOf(getCosId()));

            result = prepare.executeQuery();

            while (result.next()) {
                String Onumber = String.valueOf(result.getInt(1));
                String quantity = String.valueOf(result.getInt(2));
                String date = String.valueOf(result.getDate(3));
                String price = String.valueOf(result.getDouble(4));
                String customer_id = String.valueOf(result.getInt(5));
                String taken = String.valueOf(result.getBoolean(6));

                Forder Forder = new Forder();
                Forder.setOnumber(Onumber);
                Forder.setQuantity(quantity);
                Forder.setDate(date);
                Forder.setPrice(price);
                Forder.setCustomer_id(customer_id);
                Forder.setTaken(taken);

                res.add(Forder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getCosId1() {
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

    public int getCosId() {
        int customerId = 0;
        String gitCid = "select id from Customers where Cname =?";

        try (Connection connect = dataBase.connectDb();
                PreparedStatement prepare = connect.prepareStatement(gitCid)) {

            prepare.setString(1, Account.username);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    customerId = result.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customerId;
    }

    public void mShowAction() {
        Image img = new Image("img\\close.png");
        menuImage.setImage(img);
        // rotateTransition.setByAngle(90);
        // rotateTransition.playFromStart();
       
    }

    public void mHiddenAction() {
        Image img = new Image("img\\menu.png");
        menuImage.setImage(img);
        // rotateTransition.setByAngle(-90);
        // rotateTransition.playFromStart();
    
    }

}
