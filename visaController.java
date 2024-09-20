
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class visaController implements Initializable {

    @FXML
    private TextField visaTf;

    @FXML
    private Label myBalance;

    private Connection connect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myBalance.setText(String.valueOf(Account.budget));
    }

    public void addamountAction() {
        alertMassege alret = new alertMassege();
        connect = dataBase.connectDb();
        if (!Account.U_role.equalsIgnoreCase("Admin")) {
            try {
                String updateBudgut = "update Users  SET budget=? where email =?";

                PreparedStatement prepare2;

                prepare2 = connect.prepareStatement(updateBudgut);
                double newBudgut = Double.parseDouble(Account.budget) + Double.parseDouble(visaTf.getText());
                prepare2.setString(1, String.valueOf(newBudgut));
                prepare2.setString(2, String.valueOf(Account.email));
                prepare2.executeUpdate();
                Account.budget = String.valueOf(newBudgut);
                alret.successMessage(
                        "Amount add Successfully , " + "You add : " + visaTf.getText() + " $ to your Account./n"
                                + "Your new Budgut is " + newBudgut + "$");
                myBalance.setText(String.valueOf(Account.budget));

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            if (Account.U_role.equalsIgnoreCase("Admin")) {
                try {
                    String updateBudgut = "update Users  SET budget=? where U_role ='Admin'";

                    PreparedStatement prepare2;

                    prepare2 = connect.prepareStatement(updateBudgut);
                    double newBudgut = Double.parseDouble(Account.budget) + Double.parseDouble(visaTf.getText());
                    prepare2.setString(1, String.valueOf(newBudgut));
                    prepare2.executeUpdate();
                    Account.budget = String.valueOf(newBudgut);
                    alret.successMessage(
                            "Amount add Successfully , " + "You add : " + visaTf.getText() + " $ to your Account./n"
                                    + "Your new Budgut is " + newBudgut + "$");
                    myBalance.setText(String.valueOf(Account.budget));

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
