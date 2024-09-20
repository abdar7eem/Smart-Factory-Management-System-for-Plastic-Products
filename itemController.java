import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class itemController {

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

      Product product;
    
    public void setData(Product product) {
        this.product = product;
        name.setText(product.getPname());
        price.setText(product.getPprice());
        quantity.setText(product.getPquantity());
        Image image = new Image(product.getPimage());
        img.setImage(image);
    }

}
