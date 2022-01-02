
package pro_pharmacy;

import javafx.scene.image.Image;


public class medicine_item {
    private String Name;
    private String price;
    private String quantity;
    private String expiry;
    private Image image;

    public medicine_item(String Name, String price, String quantity, String expiry, Image image) {
        this.Name = Name;
        this.price = price;
        this.quantity = quantity;
        this.expiry = expiry;
        this.image = image;
    }

    medicine_item() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
}
