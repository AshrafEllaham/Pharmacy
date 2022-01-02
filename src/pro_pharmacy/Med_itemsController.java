
package pro_pharmacy;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Med_itemsController {
    
     private medicine_item item; 
    
    public void setData(medicine_item item22) {
        Rectangle clip = new Rectangle(
                med_image.getFitWidth(), med_image.getFitHeight()
        );
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        med_image.setClip(clip);
        
        item=item22;
        med_name.setText(item22.getName());
        med_price.setText(item22.getPrice());
        med_image.setImage(item22.getImage());
        if(item22.getQuantity().equalsIgnoreCase("0")){
            med_quantity.setTextFill(Color.web("#d90429"));
            med_quantity.setText("Out Of Stock");
        }else
            med_quantity.setText(item22.getQuantity());
        if(item22.getExpiry().equalsIgnoreCase("Expired")){
            med_expiry.setTextFill(Color.web("#d90429"));
            med_expiry.setText("Expired");
        }else
            med_expiry.setText(item22.getExpiry());
        
        
    }
    
    @FXML
    AnchorPane pan1;
    
    @FXML
    private ImageView med_image;

    @FXML
    Label med_name;

    @FXML
    private Label med_price;

    @FXML
    private Label med_expiry;

    @FXML
    private Label med_quantity;
    
}
