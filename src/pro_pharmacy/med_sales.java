
package pro_pharmacy;

   

public class med_sales {
    private String sale_name;
    private String sale_type;
    private String sale_shelf;
    private String sale_quantity;
    private String sale_price;
    private String expiry;

    public med_sales(String sale_name, String sale_type, String sale_shelf, String expiry ,String sale_quantity, String sale_price) {
        this.sale_name = sale_name;
        this.sale_type = sale_type;
        this.sale_shelf = sale_shelf;
        this.sale_quantity = sale_quantity;
        this.sale_price = sale_price;
        this.expiry = expiry;
    }

    public String getSale_name() {
        return sale_name;
    }

    public void setSale_name(String sale_name) {
        this.sale_name = sale_name;
    }

    public String getSale_type() {
        return sale_type;
    }

    public void setSale_type(String sale_type) {
        this.sale_type = sale_type;
    }

    public String getSale_shelf() {
        return sale_shelf;
    }

    public void setSale_shelf(String sale_shelf) {
        this.sale_shelf = sale_shelf;
    }

    public String getSale_quantity() {
        return sale_quantity;
    }

    public void setSale_quantity(String sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    
}
