import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class employeeController implements Initializable {

    @FXML
    private AnchorPane InventoryForm;

    @FXML
    private AnchorPane MatiralsForm;

    @FXML
    private Label WelcomeName;

    @FXML
    private Button buyBtn;

    @FXML
    private Button historyBtn;

    @FXML
    private AnchorPane historyForm;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private Label idSelect;

    @FXML
    private ImageView imgSelect;

    @FXML
    private Button inventoryBtn;

    @FXML
    private TableView<Product> inventoryTable;

    @FXML
    private TableColumn<Product, String> liimitCol;

    @FXML
    private Button mateialsBtn;

    @FXML
    private TableView<SuplierOrder> materialTable;

    @FXML
    private TableColumn<SuplierOrder, String> sm_IdCol;

    @FXML
    private TableColumn<SuplierOrder, String> sm_eIdCol;

    @FXML
    private TableColumn<SuplierOrder, String> sm_midCol;

    @FXML
    private TableColumn<SuplierOrder, String> sm_quCol;

    @FXML
    private TableColumn<SuplierOrder, String> sm_sIdCol;

    @FXML
    private TilePane materialTilePane;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private Label nameSelect;

    @FXML
    private AnchorPane orderForm;

    @FXML
    private TableView<Forder> orderTable;

    @FXML
    private TableColumn<Forder, String> orderQCol;

    @FXML
    private TableColumn<Forder, String> orderPriceCol;

    @FXML
    private TableColumn<Forder, String> orderIDCOl;

    @FXML
    private TableColumn<Forder, String> orderDateCol;

    @FXML
    private TableColumn<Forder, String> OrderCoustimerCol;

    @FXML
    private Button ordersBtn;

    @FXML
    private TableColumn<Product, String> priceCol;

    @FXML
    private Label priceSelect;

    @FXML
    private TableColumn<Product, String> quantitiyCol;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button signoutBtn;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private ComboBox<String> supplierCombo;

    @FXML
    private TableView<Forder> historyTable;

    @FXML
    private TableColumn<Forder, String> hs_taken;

    @FXML
    private TableColumn<Forder, String> hs_orderPrice;

    @FXML
    private TableColumn<Forder, String> hs_orderId;

    @FXML
    private TableColumn<Forder, String> hs_orderDate;

    @FXML
    private TableColumn<Forder, String> hs_orderCustomerId;

    @FXML
    private TableColumn<Forder, String> hs_OrderQuantity;

    @FXML
    private Label TotalAmountDash;

    @FXML
    private Label mySalaryDash;

    @FXML
    private Label totalProductsDash;

    @FXML
    private AnchorPane dashForm;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ImageView menuImage;

    @FXML
    private Label timeLable;

    private Connection connect;
    private ResultSet result;
    private ResultSet result1;
    private PreparedStatement prepare;
    private PreparedStatement prepare1;
    private Statement statement;
    private Statement statement1;

    ArrayList<Product> arr = new ArrayList<>();
    ArrayList<Product> Tabearr = new ArrayList<>();
    ArrayList<Material> Marr = new ArrayList<>();
    ArrayList<String> suppliersName = new ArrayList<>();
    ArrayList<Supplier> suppliersList = new ArrayList<>();
    public MaterialMyListener myListener;
    public Material selecedMaterial;
    CoustomerController cc = new CoustomerController();
    RotateTransition rotateTransition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotateTransition = new RotateTransition(Duration.seconds(0.5), menuImage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLable.setText(currentTime.format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        getChartData();
        getTotalAmount();
        Tabearr = getData();
        Marr.addAll(getMaterial());
        suppliersList = getSuppliers();
        supplierCombo.getItems().addAll(FXCollections.observableArrayList(suppliersName));
        supplierCombo.getSelectionModel().select(0);
        inventoryTable.getItems().addAll(FXCollections.observableArrayList(Tabearr));
        WelcomeName.setText(Account.username);
        mySalaryDash.setText(String.valueOf(getEmpSalary()));
        totalProductsDash.setText(String.valueOf(cc.getData().size()));
        orderIDCOl.setCellValueFactory(new PropertyValueFactory<>("Onumber"));
        orderQCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        OrderCoustimerCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        hs_orderId.setCellValueFactory(new PropertyValueFactory<>("Onumber"));
        hs_OrderQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        hs_orderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        hs_orderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        hs_orderCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        hs_taken.setCellValueFactory(new PropertyValueFactory<>("taken"));

        sm_IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sm_sIdCol.setCellValueFactory(new PropertyValueFactory<>("Supplier_id"));
        sm_midCol.setCellValueFactory(new PropertyValueFactory<>("Material_id"));
        sm_eIdCol.setCellValueFactory(new PropertyValueFactory<>("Employee_id"));
        sm_quCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("Pname"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Sprice"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        quantitiyCol.setCellValueFactory(new PropertyValueFactory<>("Pquantity"));
        liimitCol.setCellValueFactory(new PropertyValueFactory<>("Plimit"));

        dashForm.setVisible(true);
        InventoryForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        historyForm.setVisible(false);

        Tabearr.clear();
        Tabearr.addAll(getData());
        inventoryTable.getItems().clear();
        inventoryTable.getItems().addAll(FXCollections.observableArrayList(Tabearr));

        materialTable.setItems(FXCollections.observableArrayList(getMatTable()));

        // historyTable.setItems(FXCollections.observableArrayList(getHis()));

        SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        value.setValue(1);
        spinner.setValueFactory(value);

        if (Marr.size() > 0) {
            setChosenMaterial(Marr.get(0));
        }
        myListener = new MaterialMyListener() {
            @Override
            public void onClickListener(Material material) {
                setChosenMaterial(material);
                selecedMaterial = material;
                idSelect.setText(selecedMaterial.getMid());
                nameSelect.setText(selecedMaterial.getMname());
                imgSelect.setImage(new Image(selecedMaterial.getMurl()));
                priceSelect.setText(selecedMaterial.getMprice());
            }
        };
    }

    public ArrayList<Product> getData() {
        Tabearr.clear();
        ArrayList<Product> x = new ArrayList<>();
        String str = "select id,pname,quantity,Sellingcost,warehouse_limit from products";

        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(str);

            while (result.next()) {
                String pid = String.valueOf(result.getInt(1));
                String pname = String.valueOf(result.getString(2));
                String quantity = String.valueOf(result.getInt(3));
                String sellingPrice = String.valueOf(result.getDouble(4));
                String WhareHouse = String.valueOf(result.getInt(5));
                if (Integer.valueOf(quantity) == 0) {
                    continue;
                }

                Product product = new Product();
                product.setPid(pid);
                product.setPname(pname);
                product.setPquantity(quantity);
                product.setSprice(sellingPrice);
                product.setPlimit(WhareHouse);

                x.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public void inventoryAction() {
        dashForm.setVisible(false);
        InventoryForm.setVisible(true);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        historyForm.setVisible(false);

        Tabearr.clear();
        Tabearr.addAll(getData());
        inventoryTable.getItems().clear();
        inventoryTable.getItems().addAll(FXCollections.observableArrayList(Tabearr));
    }

    public void buyAction() {
        cartController cr = new cartController();
        double orderPrice = Double.parseDouble(selecedMaterial.getMprice()) * spinner.getValue();
        alertMassege alret = new alertMassege();

        if (cr.getFirstAdmin() < orderPrice) {
            alret.errorMassage("Error, Please check your budget...");
            return;
        }

        String empID = " ";
        connect = dataBase.connectDb();

        String getEmpID = "select * from employee where empName=?";
        try {
            prepare = connect.prepareStatement(getEmpID);
            System.out.println(Account.username);
            prepare.setString(1, Account.username);
            result = prepare.executeQuery();

            if (result.next()) {
                empID = String.valueOf(result.getInt(1));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String getSuppliersID = " ";
        for (int i = 0; i < suppliersList.size(); i++) {
            if (supplierCombo.getValue().equals(suppliersList.get(i).name)) {
                getSuppliersID = suppliersList.get(i).id;
            }
        }
        String getMattID = " ";

        getMattID = selecedMaterial.getMid();

        try {
            if (result.next()) {
                empID = String.valueOf(result.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            statement = connect.createStatement();

            String insertOrder = "INSERT INTO Supplier_Order "
                    + "(supplier_id,material_id,employee_id,quantity)"
                    + "VALUES (?,?,?,?)";

            prepare = connect.prepareStatement(insertOrder);
            prepare.setString(1, getSuppliersID);
            prepare.setString(2, getMattID);
            prepare.setString(3, empID);
            prepare.setString(4, String.valueOf(spinner.getValue()));

            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String Mquantity = "update material  SET quantity=? where Mname = ?";
        int newQuantity = Integer.parseInt(selecedMaterial.getMquantity()) + spinner.getValue();
        try {
            prepare = connect.prepareStatement(Mquantity);
            double newBudgut = cr.getFirstAdmin() - orderPrice;

            prepare = connect.prepareStatement(Mquantity);
            prepare.setString(1, String.valueOf(newQuantity));
            prepare.setString(2, selecedMaterial.getMname());
            prepare.executeUpdate();
            Account.budget = String.valueOf(newBudgut);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        materialTable.getItems().clear();
        materialTable.setItems(FXCollections.observableArrayList(getMatTable()));

        String AdminBudget = "update Users  SET budget=? where U_role = 'Admin'";

        try {
            prepare = connect.prepareStatement(AdminBudget);
            double newBudgut = cr.getFirstAdmin() - orderPrice;

            prepare = connect.prepareStatement(AdminBudget);
            prepare.setString(1, String.valueOf(newBudgut));
            prepare.executeUpdate();
            Account.budget = String.valueOf(newBudgut);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<Product> getTableData() {
        ArrayList<Product> x = new ArrayList<>();
        String str = "select id,pname,quantity,Sellingcost,warehouse_limit from products";
        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(str);

            while (result.next()) {
                String pid = String.valueOf(result.getInt(1));
                String pname = String.valueOf(result.getString(2));
                String quantity = String.valueOf(result.getInt(3));
                String sellingPrice = String.valueOf(result.getDouble(4));
                String WhareHouse = String.valueOf(result.getInt(5));

                Product product = new Product();
                product.setPid(pid);
                product.setPname(pname);
                product.setPquantity(quantity);
                product.setSprice(sellingPrice);
                product.setPlimit(WhareHouse);

                x.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public ArrayList<Material> getMaterial() {
        ArrayList<Material> marr = new ArrayList<>();

        String getMaterial = "SELECT * FROM Material";
        connect = dataBase.connectDb();

        try {
            statement = connect.createStatement();

            result = statement.executeQuery(getMaterial);

            while (result.next()) {
                String mid = String.valueOf(result.getInt(1));
                String mname = String.valueOf(result.getString(2));
                String mprice = String.valueOf(result.getDouble(3));
                String mquantity = String.valueOf(result.getInt(4));
                String murl = String.valueOf(result.getString(5));

                Material material = new Material();
                material.setMid(mid);
                material.setMname(mname);
                material.setMprice(mprice);
                material.setMquantity(mquantity);
                material.setMurl(murl);
                marr.add(material);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marr;
    }

    private void setChosenMaterial(Material material) {
        nameSelect.setText(material.getMname());
        idSelect.setText(material.getMid());
        Image image = new Image(material.getMurl());
        imgSelect.setImage(image);
    }

    public void materalAction() {
        dashForm.setVisible(false);
        InventoryForm.setVisible(false);
        MatiralsForm.setVisible(true);
        orderForm.setVisible(false);
        historyForm.setVisible(false);

        Marr.clear();
        materialTilePane.getChildren().clear();
        Marr.addAll(getMaterial());
        // materialTable.setItems(FXCollections.observableArrayList(Marr));

        try {
            for (int i = 0; i < Marr.size(); i++) {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("materialCard.fxml"));
                AnchorPane anchorPane = fxmlLoader1.load();

                materialController materialController = fxmlLoader1.getController();
                materialController.setData(Marr.get(i));

                materialTilePane.getChildren().add(anchorPane); // (child,column,row)

                materialTilePane.setHgap(10);
                materialTilePane.setVgap(10);
                scrollPane.setFitToWidth(true);

                materialTilePane.setMargin(anchorPane, new Insets(10));

                final Material material = Marr.get(i);
                anchorPane.setOnMouseClicked(event -> {
                    if (myListener != null) {
                        myListener.onClickListener(material);
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> arr = new ArrayList<>();
        String str = "select id,sname,phone from Supplier";
        suppliersName.clear();
        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(str);

            while (result.next()) {
                String sid = String.valueOf(result.getInt(1));
                String sName = String.valueOf(result.getString(2));
                String sPhone = String.valueOf(result.getString(3));

                Supplier supplier = new Supplier();
                supplier.id = sid;
                supplier.name = sName;
                supplier.phone = sPhone;

                arr.add(supplier);
                suppliersName.add(sName);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arr;
    }

    public ArrayList<SuplierOrder> getMatTable() {
        ArrayList<SuplierOrder> arr = new ArrayList<>();

        String getMaterial = "SELECT * FROM Supplier_Order";
        connect = dataBase.connectDb();

        try {
            statement = connect.createStatement();

            result = statement.executeQuery(getMaterial);

            while (result.next()) {
                String id = String.valueOf(result.getInt(1));
                String supplier_id = String.valueOf(result.getString(2));
                String material_id = String.valueOf(result.getDouble(3));
                String employee_id = String.valueOf(result.getInt(4));
                String quantity = String.valueOf(result.getString(5));

                SuplierOrder so = new SuplierOrder();
                so.setId(id);
                so.setSupplier_id(supplier_id);
                so.setMaterial_id(material_id);
                so.setEmployee_id(employee_id);
                so.setQuantity(quantity);

                arr.add(so);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void orderForm() {
        dashForm.setVisible(false);
        InventoryForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(true);
        historyForm.setVisible(false);
        orderTable.getItems().clear();
        orderTable.setItems(FXCollections.observableArrayList(getF_Order()));
    }

    public void historyForm() {
        dashForm.setVisible(false);
        InventoryForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        historyForm.setVisible(true);
        historyTable.getItems().clear();
        historyTable.setItems(FXCollections.observableArrayList(getHisOrder()));

    }

    public void signoutAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.setScene(scene);
            stage.show();
            signoutBtn.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
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
            stage.setTitle("Login");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Forder> getF_Order() {
        ArrayList<Forder> res = new ArrayList<>();
        String getFOrder = "select * from F_Order where taken='false'";
        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(getFOrder);

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

    public void acceptAction() {
        String updateOrder = "update F_Order SET taken=true, customer_id=? where Onumber = ?;";

        PreparedStatement prepare2;
        try {
            prepare2 = connect.prepareStatement(updateOrder);
            prepare2.setString(1, String.valueOf(getEmpId()));
            prepare2.setString(2, String.valueOf(orderTable.getSelectionModel().getSelectedItem().getOnumber()));
            prepare2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String inserOrderLine = "INSERT INTO emp_history (order_id ,employee_id)VALUES (?,?)";
        int empId = getempId();
        PreparedStatement lineStmt;
        try {
            lineStmt = connect.prepareStatement(inserOrderLine);
            lineStmt.setString(1, orderTable.getSelectionModel().getSelectedItem().getOnumber());
            lineStmt.setString(2, String.valueOf(empId));
            lineStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderTable.getItems().clear();
        orderTable.setItems(FXCollections.observableArrayList(getF_Order()));
    }

    public int getempId() {
        int customerId = 0;

        String gitCid = "select id from employee where empName =? ";
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

    public ArrayList<Forder> getHisOrder() {
        ArrayList<Forder> res = new ArrayList<>();
        String getFOrder = "select * from F_Order where taken=1 and Onumber=?";
        connect = dataBase.connectDb();
        try {
            prepare = connect.prepareStatement(getFOrder);


            for(int i=0;i< getOrderId().size();i++){
            prepare.setString(1, String.valueOf(getOrderId().get(i)));

            
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
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void getTotalAmount() {
        cartController cr = new cartController();
        TotalAmountDash.setText(String.valueOf(cr.getFirstAdmin()));
    }

    public double getEmpSalary() {
        String getEmpSalary = "SELECT salary FROM Employee WHERE empName=?";
        double salary = 0;
        try {
            connect = dataBase.connectDb();
            prepare = connect.prepareStatement(getEmpSalary);
            prepare.setString(1, Account.username);

            result = prepare.executeQuery();

            if (result.next()) {
                salary = result.getDouble(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return salary;
    }

    public void dashAction() {
        dashForm.setVisible(true);
        InventoryForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        historyForm.setVisible(false);
        getChartData();
    }

    public void getChartData() {
        String chartSql = "SELECT Odate, SUM(price) FROM f_order where customer_id=? GROUP BY Odate ORDER BY TIMESTAMP(Odate) asc";

        connect = dataBase.connectDb();
        try {
            XYChart.Series<String, Number> chartData = new XYChart.Series();
            prepare = connect.prepareStatement(chartSql);
            prepare.setString(1, String.valueOf(getEmpId()));
            result = prepare.executeQuery();

            while (result.next()) {
                chartData.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            barChart.getData().clear();

            barChart.getData().add(new XYChart.Series());

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    for (XYChart.Data<String, Number> data : chartData.getData()) {
                                        barChart.getData().get(0).getData().add(data);
                                    }
                                }
                            }));
            timeline.play();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int getEmpId() {
        int empId = 0;
        String getEmpId = "select id from employee where empName =?";

        try (Connection connect = dataBase.connectDb();
                PreparedStatement prepare = connect.prepareStatement(getEmpId)) {

            prepare.setString(1, Account.username);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    empId = result.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empId;
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

    public ArrayList<Integer>  getOrderId() {
        ArrayList<Integer> res = new ArrayList();
        String getOId = "select order_id from emp_history where employee_id =?";

        try (Connection connect = dataBase.connectDb();
                PreparedStatement prepare = connect.prepareStatement(getOId)) {

            prepare.setString(1, String.valueOf(getEmpId()));
            try (ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                 
                    res.add(result.getInt(1));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

}