import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class alertMassege {
	private Alert alert;

	public void errorMassage(String message) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error Massage");
		alert.setContentText(message);
		alert.showAndWait();
	}

	public void successMessage(String message) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Success Message");
		alert.setContentText(message);
		alert.showAndWait();
	}

}
