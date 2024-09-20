import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class adminController implements Initializable {

    @FXML
    private AnchorPane InventoryForm;

    @FXML
    private AnchorPane MatiralsForm;

    @FXML
    private Label WelcomeName;

    @FXML
    private ImageView addBtn;

    @FXML
    private Button addInventoryBtn;

    @FXML
    private TextField addNameP;

    @FXML
    private TextField addPprice;

    @FXML
    private TextField addSprice;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button employeeBtn;

    @FXML
    private Label idSelect;

    @FXML
    private Label quantitySelect;

    @FXML
    private Label pPriceSelect;

    @FXML
    private Label sPriceSelect;

    @FXML
    private ImageView imgSelect;

    @FXML
    private ImageView importImg;

    @FXML
    private Button improtBtn;

    @FXML
    private Button inventoryBtn;

    @FXML
    private TableView<Product> inventoryTable;

    @FXML
    private Button mateialsBtn;

    @FXML
    private TilePane materialTilePane;

    @FXML
    private Label nameSelect;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button productBtn;

    @FXML
    private AnchorPane productForm;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private Button signoutBtn;

    @FXML
    Spinner<Integer> spinner;

    @FXML
    private TilePane tilePane;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> liimitCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, String> priceCol;

    @FXML
    private TableColumn<Product, String> quantitiyCol;

    @FXML
    private Spinner<Integer> addSpinner;

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> mIdCol;

    @FXML
    private TableColumn<Material, String> mNameCol;

    @FXML
    private TableColumn<Material, String> mPriceCol;

    @FXML
    private TableColumn<Material, String> mQuantityCol;

    @FXML
    private TableColumn<Forder, String> supplierCoustomerIDCol;

    @FXML
    private TableColumn<Forder, String> supplierMatirelIDCol;

    @FXML
    private TableColumn<Forder, String> supplierOrderIDCol;

    @FXML
    private TableColumn<Forder, String> supplierSupplierIDCol;

    @FXML
    private TableColumn<Forder, String> coustomerCoustomerIDCol;

    @FXML
    private TableColumn<Forder, String> coustomerOrderDateCol;

    @FXML
    private TableColumn<Forder, String> coustomerOrderIDCol;

    @FXML
    private TableColumn<Forder, String> coustomerOrderPriceCol;

    @FXML
    private TableView<Forder> coustomerOrderTable;

    @FXML
    private TableColumn<Forder, String> coustomerQuantityIDCol;

    @FXML
    private TableView<SuplierOrder> SupplierOrderTable;

    @FXML
    private AnchorPane orderForm;

    @FXML
    private AnchorPane EmployeeForm;

    @FXML
    private TextField addEmpEmail;

    @FXML
    private TextField addEmpID;

    @FXML
    private TextField addEmpName;

    @FXML
    private TextField addEmpPhone;

    @FXML
    private TextField addEmpSalary;

    @FXML
    private ComboBox<String> addEmpCombo;

    @FXML
    private TextField addEmpPass;

    @FXML
    private TextField addEmpAdress;

    @FXML
    private TextField addEmpAge;

    @FXML
    private ComboBox<String> addEmpFavoriteCombo;

    @FXML
    private TableColumn<Employee, String> EmpAgeCol;

    @FXML
    private TableColumn<Employee, String> EmpEmailCol;

    @FXML
    private TableColumn<Employee, String> EmpIdCol;

    @FXML
    private TableColumn<Employee, String> EmpNameCol;

    @FXML
    private TableColumn<Employee, String> EmpPhoneCol;

    @FXML
    private TableView<SuplierOrder> smTable;

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
    private TableColumn<Employee, String> EmpRoleCol;

    @FXML
    private TableColumn<Employee, String> EmpSalaryCol;

    @FXML
    private TableView<Employee> EmployeeTable;

    @FXML
    private Spinner<Integer> addReq;

    @FXML
    public Label TotalAmountDash;

    @FXML
    private Label totalEmpDash;

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

    static String welcomeMassege;
    

    ArrayList<Product> arr = new ArrayList<>();
    ArrayList<Product> Tabearr = new ArrayList<>();
    ArrayList<Material> Marr = new ArrayList<>();
    ArrayList<Employee> EmpArr = new ArrayList<>();

    String[] roleArr = { "Driver", "Machine Worker", "Factory Worker" };
    String[] colroArr = { "Blue", "Green", "Red" };

    private Connection connect;
    private ResultSet result;
    private ResultSet result1;
    private PreparedStatement prepare;
    private PreparedStatement prepare1;
    private Statement statement;
    private Statement statement1;

    private MyListener myListener;
    RotateTransition rotateTransition;

    Product selectedProduct = null;
    File file;
    FileChooser fm;
    CoustomerController cc = new CoustomerController();
    
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
        addEmpCombo.getItems().addAll(roleArr);
        addEmpFavoriteCombo.getItems().addAll(colroArr);
        InventoryForm.setVisible(false);
        productForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        dashForm.setVisible(true);
        EmployeeForm.setVisible(false);
        WelcomeName.setText(welcomeMassege);
        totalProductsDash.setText(String.valueOf(cc.getData().size()));
        getTotalAmount();
        totalEmpDash.setText(String.valueOf(getEmployeTable().size()));

        sm_IdCol.setCellValueFactory(new PropertyValueFactory<>("Supplier_id"));
        sm_midCol.setCellValueFactory(new PropertyValueFactory<>("Material_id"));
        sm_eIdCol.setCellValueFactory(new PropertyValueFactory<>("Employee_id"));
        sm_quCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("Pname"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Sprice"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        quantitiyCol.setCellValueFactory(new PropertyValueFactory<>("Pquantity"));
        liimitCol.setCellValueFactory(new PropertyValueFactory<>("Plimit"));

        mIdCol.setCellValueFactory(new PropertyValueFactory<>("Mid"));
        mNameCol.setCellValueFactory(new PropertyValueFactory<>("Mname"));
        mPriceCol.setCellValueFactory(new PropertyValueFactory<>("Mprice"));
        mQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Mquantity"));

        coustomerOrderIDCol.setCellValueFactory(new PropertyValueFactory<>("Onumber"));
        coustomerQuantityIDCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        coustomerOrderDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        coustomerOrderPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        coustomerCoustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        EmpAgeCol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        EmpIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        EmpNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        EmpPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Contact_info"));
        EmpRoleCol.setCellValueFactory(new PropertyValueFactory<>("E_role"));
        EmpSalaryCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        Tabearr.addAll(getTableData());
        inventoryTable.setItems(FXCollections.observableArrayList(Tabearr));
        SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        value.setValue(1);
        spinner.setValueFactory(value);
        SpinnerValueFactory<Integer> addValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        addValue.setValue(1);
        addSpinner.setValueFactory(addValue);

        SpinnerValueFactory<Integer> NewValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        NewValue.setValue(1);
        addReq.setValueFactory(NewValue);
        arr = getData();
        Marr.addAll(getMaterial());

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

    public ArrayList<Employee> getEmployeTable() {
        ArrayList<Employee> x = new ArrayList<>();
        String str = "select * from Employee";
        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            result = statement.executeQuery(str);

            while (result.next()) {
                String eid = String.valueOf(result.getInt(1));
                String ename = String.valueOf(result.getString(2));
                String phone = String.valueOf(result.getString(3));
                String age = String.valueOf(result.getInt(4));
                String salary = String.valueOf(result.getDouble(5));
                String Role = String.valueOf(result.getString(6));

                Employee emp = new Employee();
                emp.setAge(age);
                emp.setContact_info(phone);
                emp.setName(ename);
                emp.setE_role(Role);
                emp.setSalary(salary);
                emp.setId(eid);
                x.add(emp);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public void inventoryAction() {
        InventoryForm.setVisible(true);
        productForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        dashForm.setVisible(false);
        EmployeeForm.setVisible(false);

        Tabearr.clear();
        Tabearr.addAll(getTableData());
        inventoryTable.setItems(FXCollections.observableArrayList(Tabearr));
    }

    private void setChosenProduct(Product product) {
        nameSelect.setText(product.getPname());
        idSelect.setText(product.getPid());
        Image image = new Image(product.getPimage());
        imgSelect.setImage(image);
        quantitySelect.setText(product.getPlimit());
        sPriceSelect.setText(product.getSprice());
        pPriceSelect.setText(product.getPprice());
    }

    public void productAction() {
        InventoryForm.setVisible(false);
        productForm.setVisible(true);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        dashForm.setVisible(false);
        EmployeeForm.setVisible(false);

        arr.clear();
        tilePane.getChildren().clear();
        arr.addAll(getData());

        if (arr.size() > 0) {
            setChosenProduct(arr.get(0));
            selectedProduct=arr.get(0);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                    selectedProduct = product;
                    addNameP.setText(product.getPname());
                    addPprice.setText(product.getPprice());
                    addSprice.setText(product.getSprice());

                    selectedProduct.setPid(idSelect.getText());
                    selectedProduct.setPlimit(String.valueOf(addSpinner.getValue()));
                    importImg.setImage(new Image(product.getPimage()));

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void materalAction() {
        InventoryForm.setVisible(false);
        productForm.setVisible(false);
        MatiralsForm.setVisible(true);
        orderForm.setVisible(false);
        dashForm.setVisible(false);
        EmployeeForm.setVisible(false);

        Marr.clear();
        materialTilePane.getChildren().clear();
        Marr.addAll(getMaterial());
        materialTable.setItems(FXCollections.observableArrayList(Marr));
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
                scrollPane1.setFitToWidth(true);

                materialTilePane.setMargin(anchorPane, new Insets(10));
            }
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

    public void importAction() {
        Stage stage = new Stage();
        fm = new FileChooser();
        fm.setTitle("Select Photo");
        ExtensionFilter filter = new ExtensionFilter("All Pic ", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fm.getExtensionFilters().addAll(filter);
        file = fm.showOpenDialog(stage);
        importImg.setImage(new Image(file.toURI().toString()));
    }

    public void LoadItem() {
        alertMassege alret = new alertMassege();

        double q = selectedProduct.reqMaterial * spinner.getValue();

        for (int i = 0; i < Marr.size(); i++) {
            Marr.get(i).getMquantity();
            if (Double.parseDouble(Marr.get(i).getMquantity()) > q) {

                for (int j = 0; j < Tabearr.size(); j++) {
                    if (Tabearr.get(j).getPid().equals(idSelect.getText())) {
                        int quantity = Integer.parseInt(Tabearr.get(j).getPquantity()) + spinner.getValue();
                        Tabearr.get(j).setPquantity(String.valueOf(quantity));

                    }
                }

                Marr.get(i).setMquantity(String.valueOf(Double.parseDouble(Marr.get(i).getMquantity()) - q));
                selectedProduct.setPquantity(String.valueOf(spinner.getValue()));
                selectedProduct.setPid(idSelect.getText());
                Tabearr.add(selectedProduct);

                String UpdateQ = "Update Material set quantity=? where id=?";
                try {
                    prepare = connect.prepareStatement(UpdateQ);
                    prepare.setString(1, String.valueOf(Marr.get(i).getMquantity()));
                    prepare.setString(2, String.valueOf(Marr.get(i).getMid()));
                    prepare.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alret.errorMassage("No enough Material");
            }

        }
        inventoryTable.getItems().clear();
        inventoryTable.setItems(FXCollections.observableArrayList(Tabearr));
        String str = "Update products set quantity=? where id=?";
        if (connect == null) {
            alret.errorMassage("ًWrong Username");
        } else {

            String Quant = String.valueOf(spinner.getValue());

            try {
                prepare = connect.prepareStatement(str);
                prepare.setString(1, Quant);
                prepare.setString(2, selectedProduct.getPid());
                int x = prepare.executeUpdate();

                if (x > 0) {

                } else {
                    alret.errorMassage("Wrong Username/password");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void AddProduct() {
        alertMassege alret = new alertMassege();

        for (int i = 0; i < Tabearr.size(); i++) {
            if (Tabearr.get(i).getPname().equalsIgnoreCase(addNameP.getText())) {
                alret.errorMassage(" The Product is al ready adedd...");
                return;
            }
        }

        connect = dataBase.connectDb();
        try {
            statement = connect.createStatement();

            String productData = "INSERT INTO Products "
                    + "(Pname,Productioncost,Sellingcost, Warehouse_limit,imgUrl,materialRate)"
                    + "VALUES (?,?,?,?,?,?)";

            prepare = connect.prepareStatement(productData);

            prepare.setString(1, addNameP.getText());
            prepare.setString(2, addPprice.getText());
            prepare.setString(3, addSprice.getText());
            prepare.setString(4, String.valueOf(addSpinner.getValue()));
            if (file != null) {
                prepare.setString(5, file.toURI().toString());
            }
            prepare.setString(6, String.valueOf(addReq.getValue()));

            prepare.executeUpdate();

            prepare = connect.prepareStatement(productData);

        } catch (Exception e) {
            alret.errorMassage("Error,Enter the empty field...");
        }

        // String str="Insert into Products "
        arr.clear();
        arr = getData();
        tilePane.getChildren().clear();

        if (arr.size() > 0) {
            setChosenProduct(arr.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                    selectedProduct = product;
                    selectedProduct.setPid(idSelect.getText());
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void AddEmployee() {
        alertMassege alret = new alertMassege();
        if (addEmpName.getText().isBlank() || addEmpPass.getText().isBlank() || addEmpPhone.getText().isBlank()
                || addEmpEmail.getText().isBlank() || addEmpAdress.getText().isBlank()
                || addEmpCombo.getSelectionModel().getSelectedItem() == null
                || addEmpFavoriteCombo.getSelectionModel().getSelectedItem() == null
                || addEmpSalary.getText().isBlank()
                || addEmpAge.getText().isBlank()) {
            alret.errorMassage("Incorrect Data...");
        } else if (addEmpPass.getText().length() < 8) {
            alret.errorMassage("Invalid Password,at least 8 characters needed");

        } else if (addEmpEmail.getText().length() < 8) {
            alret.errorMassage("Invalid Eamil,at least 8 characters needed");

        }

        else {
            try {
                // Check if addEmpAge is a valid number
                int age = Integer.parseInt(addEmpAge.getText());
                if (age < 18) { // Assuming age should be at least 18
                    alret.errorMassage("Invalid Age, must be at least 18");
                    return;
                }
            } catch (NumberFormatException e) {
                alret.errorMassage("Invalid Age, please enter a valid number");
                return;
            }

            try {
                // Check if addEmpSalary is a valid number
                double salary = Double.parseDouble(addEmpSalary.getText());
                if (salary < 0) { // Assuming salary should be a positive number
                    alret.errorMassage("Invalid Salary, must be a positive number");
                    return;
                }
            } catch (NumberFormatException e) {
                alret.errorMassage("Invalid Salary, please enter a valid number");
                return;
            }

            String checkUserName = "SELECT * FROM Users WHERE username = '" + addEmpName.getText() + "'";
            String checkaddEmail = "SELECT * FROM Users WHERE username = '" + addEmpEmail.getText() + "'";

            try {

                connect = dataBase.connectDb();
                statement = connect.createStatement();
                result = statement.executeQuery(checkUserName);

                statement1 = connect.createStatement();
                result1 = statement1.executeQuery(checkUserName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (result.next()) {
                    alret.errorMassage(addEmpName.getText() + " is already taken...");

                } else if (result1.next()) {
                    alret.errorMassage(addEmpEmail.getText() + " is already taken...");

                }

                else {
                    try {
                        statement = connect.createStatement();

                        String employeeData = "INSERT INTO Employee"
                                + "(empName,contact_info,age,salary,E_role)"
                                + "VALUES (?,?,?,?,?)";

                        String employeeUserData = "INSERT INTO Users "
                                + "(email ,username,U_password,addrees,phone,U_role,favoriteColor)"
                                + "VALUES (?,?,?,?,?,?,?)";

                        prepare1 = connect.prepareStatement(employeeUserData);
                        prepare1.setString(1, addEmpEmail.getText());
                        prepare1.setString(2, addEmpName.getText());
                        prepare1.setString(3, addEmpPass.getText());
                        prepare1.setString(4, addEmpAdress.getText());
                        prepare1.setString(5, addEmpPhone.getText());
                        prepare1.setString(6, "Employee");
                        prepare1.setString(7, addEmpFavoriteCombo.getSelectionModel().getSelectedItem());

                        prepare1.executeUpdate();

                        prepare = connect.prepareStatement(employeeData);
                        prepare.setString(1, addEmpName.getText());
                        prepare.setString(2, addEmpPhone.getText());
                        prepare.setString(3, addEmpAge.getText());
                        prepare.setString(4, addEmpSalary.getText());
                        prepare.setString(5, addEmpCombo.getSelectionModel().getSelectedItem());

                        prepare.executeUpdate();

                        EmpArr.clear();
                        EmpArr.addAll(getEmployeTable());
                        EmployeeTable.getItems().clear();
                        EmployeeTable.getItems().addAll(EmpArr);

                    } catch (SQLException e) {
                        alret.errorMassage(addEmpEmail.getText() + " is already taken...");
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void updateProd() {
        alertMassege alret = new alertMassege();
        int id = Integer.parseInt(selectedProduct.getPid());
        String str = "Update products set pname =?,Productioncost=?,sellingcost=?,Warehouse_limit=?,imgUrl=? where ?=id";

        try {
            prepare = connect.prepareStatement(str);
            prepare.setString(1, addNameP.getText());
            prepare.setString(2, addPprice.getText());
            prepare.setString(3, addSprice.getText());
            prepare.setString(4, String.valueOf(addSpinner.getValue()));
            prepare.setString(5, file.toURI().toString());
            prepare.setString(6, String.valueOf(idSelect.getText()));

            prepare.executeUpdate();

            arr.clear();
            arr = getData();
            tilePane.getChildren().clear();

            for (int i = 0; i < arr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("productsCard.fxml"));
                AnchorPane anchorPane;
                try {
                    anchorPane = fxmlLoader.load();

                    productController itemController = fxmlLoader.getController();
                    itemController.setData(arr.get(i), myListener);

                    tilePane.getChildren().add(anchorPane); // (child,column,row)

                    tilePane.setHgap(10);
                    tilePane.setVgap(10);
                    scrollPane.setFitToWidth(true);

                    tilePane.setMargin(anchorPane, new Insets(10));
                    setChosenProduct(arr.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            alret.successMessage("Product Upadted Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteProd() {
        alertMassege alret = new alertMassege();
        int id = Integer.parseInt(selectedProduct.getPid());

        String str = "DELETE from Products where ID =?";
        try {
            prepare = connect.prepareStatement(str);
            prepare.setString(1, String.valueOf(idSelect.getText()));

            prepare.executeUpdate();

            arr.clear();
            arr = getData();
            tilePane.getChildren().clear();

            for (int i = 0; i < arr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("productsCard.fxml"));
                AnchorPane anchorPane;
                try {
                    anchorPane = fxmlLoader.load();

                    productController itemController = fxmlLoader.getController();
                    itemController.setData(arr.get(i), myListener);

                    tilePane.getChildren().add(anchorPane);

                    tilePane.setHgap(10);
                    tilePane.setVgap(10);
                    scrollPane.setFitToWidth(true);

                    tilePane.setMargin(anchorPane, new Insets(10));
                    setChosenProduct(arr.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            alret.successMessage("Product Upadted Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OrderAction() {
        InventoryForm.setVisible(false);
        productForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(true);
        dashForm.setVisible(false);
        EmployeeForm.setVisible(false);

        smTable.getItems().clear();
        smTable.setItems(FXCollections.observableArrayList(getMatSupTable()));

        coustomerOrderTable.getItems().clear();
        coustomerOrderTable.setItems(FXCollections.observableArrayList(getF_Order()));
    }

    public void EmployeeAction() {
        InventoryForm.setVisible(false);
        productForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        dashForm.setVisible(false);
        EmployeeForm.setVisible(true);

        EmpArr.clear();
        EmpArr.addAll(getEmployeTable());
        EmployeeTable.getItems().clear();
        EmployeeTable.getItems().addAll(EmpArr);
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
            stage.show();
            stage.setTitle("My profile");
            stage.getIcons().addAll(new Image("img\\Logo.png"));
            stage.setResizable(false);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent arg0) {
                    getTotalAmount();
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<SuplierOrder> getMatSupTable() {
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

    public ArrayList<Forder> getF_Order() {
        ArrayList<Forder> res = new ArrayList<>();
        String getFOrder = "select * from F_Order";
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

    public void getTotalAmount() {
        cartController cr = new cartController();
        TotalAmountDash.setText(String.valueOf(cr.getFirstAdmin()));
    }

    public void dashAction() {
        getTotalAmount();
        getChartData();
        InventoryForm.setVisible(false);
        productForm.setVisible(false);
        MatiralsForm.setVisible(false);
        orderForm.setVisible(false);
        dashForm.setVisible(true);
        EmployeeForm.setVisible(false);
    }

    public void getChartData() {
        String chartSql = "SELECT Odate, SUM(price) FROM f_order GROUP BY Odate ORDER BY TIMESTAMP(Odate) asc";

        connect = dataBase.connectDb();
        try {
            XYChart.Series<String, Number> chartData = new XYChart.Series();
            prepare = connect.prepareStatement(chartSql);
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
