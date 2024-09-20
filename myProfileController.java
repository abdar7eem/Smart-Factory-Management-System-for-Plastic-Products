import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class myProfileController implements Initializable {

    @FXML
    private Label myAdress;

    @FXML
    private Label myColor;

    @FXML
    private Label myEmail;

    @FXML
    private Label myName;

    @FXML
    private Label myPasswd;

    @FXML
    private Label myPhone;

    @FXML
    private Label myType;

    @FXML
    private Button visaBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myName.setText(Account.username);
        myEmail.setText(Account.email);
        myAdress.setText(Account.addrees);
        myColor.setText(Account.favoriteColor);
        myPasswd.setText(Account.U_password);
        myPhone.setText(Account.phone);
        myType.setText(Account.U_role);
        if (Account.U_role.equalsIgnoreCase("Employee")) {
            visaBtn.setDisable(true);
        }
    }

    public void visaAction() {
        AnchorPane root;
        try {
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("visaScreen.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
