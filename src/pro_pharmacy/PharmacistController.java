package pro_pharmacy;

//   select sum (price) from medicine 

// Toolkit.getDefaultToolkit().beep();
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.layout.Region;


public class PharmacistController implements Initializable {
        

    private AutoCompletionBinding<String> autocompletion;
    static Connection c = null;
    static PreparedStatement st = null;
    static Statement stt = null;
    static String query;
    static ResultSet r = null;
    private String picture_path = null;
    private String med_picture_path = null;
    private Image mod_image;
    private String mod_firstName;
    private String mod_username;
    private String mod_password;
    private String mod_lastname;
    private String mod_phone;
    private String mod_State;
    private String mod_gender;
    private File fl22 = null;
    private File fl_med_modify = null;
    private File fl33 = null;
    private int index = -1;
    boolean flag = false;
    boolean med_flag = false;
    private String xusername;
    private String xpassword;
    private LocalDate exp_sale;
    private List<medicine_item> items = new ArrayList<>();
    private Image med_image_x;
    private List<medicine_item> items_info = new ArrayList<>();
    
    

    public void send_info(Image image, String nfirstName, String nusername, String npassword, String Nlastname, String nphone, String nState, String Ngender) {
        show_name.setText(nfirstName);
        user_home_pic.setImage(image);
        user_profile_pic.setImage(image);
        pro_first_name.setText(nfirstName);
        pro_gender.setText(Ngender);
        pro_phone.setText(nphone);
        pro_password.setText(npassword);
        pro_state.setText(nState);
        pro_username.setText(nusername);
        pro_last_name.setText(Nlastname);
        Circle clip = new Circle(40, 40, 40);
        user_home_pic.setClip(clip);
        Rectangle clip2 = new Rectangle(
                user_profile_pic.getFitWidth(), user_profile_pic.getFitHeight());
        clip2.setArcWidth(30);
        clip2.setArcHeight(30);
        user_profile_pic.setClip(clip2);

        //////////
        mod_image = image;
        mod_firstName = nfirstName;
        mod_username = nusername;
        mod_password = npassword;
        mod_lastname = Nlastname;
        mod_phone = nphone;
        mod_State = nState;
        mod_gender = Ngender;
    }

    public PharmacistController() {
        c = connectionH2.conDB();
    }

    /////////////////////////////////////
    @FXML
    private JFXProgressBar prog_tesssssssssssssssssss;
    
    @FXML
    private Label system_info_msg;
    
    @FXML
    private ScrollPane view_info_scroll;

    @FXML
    private GridPane view_info_grid;
    
    @FXML
    private JFXButton medcine_cancle;
    
    @FXML
    private JFXButton medcine_add;
    
    @FXML
    private JFXButton delete_medicine;
    
    @FXML
    private JFXButton log_out;

    @FXML
    private JFXButton Exit_app;
    
    @FXML
    private JFXButton my_profile;
    
    @FXML
    private JFXButton view_users;
    
    @FXML
    private JFXButton add_user;
    
    @FXML
    private JFXButton add_medicine;
    
    @FXML
    private JFXButton Search;
    
    @FXML
    private JFXButton viewMedicine;
    
    @FXML
    private JFXButton dashboard;
    
    @FXML
    private JFXComboBox<String> view_all;
    
    @FXML
    private ScrollPane view_med_scroll;

    @FXML
    private GridPane view_med_grid;
    
    @FXML
    private JFXButton expiry_month_but;
    
    @FXML
    private JFXButton out_of_stock_but;
    
    @FXML
    private JFXButton clear_sales;
    
    @FXML
    private TableView<med_sales> table_med_sales;

    @FXML
    private TableColumn<med_sales, String> table_med_name;

    @FXML
    private TableColumn<med_sales, String> table_med_type;

    @FXML
    private TableColumn<med_sales, String> table_med_shelf;

    @FXML
    private TableColumn<med_sales, String> table_med_quantity;

    @FXML
    private TableColumn<med_sales, String> table_med_price;
    
    @FXML
    private TableColumn<med_sales, String> table_med_expiry;
    
    @FXML
    private JFXButton total_sales;
    
    @FXML
    private JFXButton Browse_med;
    
    @FXML
    private JFXButton Delete_user;
    
    @FXML
    private JFXButton modify_user;
    
    @FXML
    private JFXButton browse;
    
    @FXML
    private JFXButton cancle;
    
    @FXML
    private JFXButton add_user_enter;
    
    @FXML
    private JFXButton update_profile;
    
    @FXML
    private JFXButton Search_med_button;
    
    @FXML
    private JFXButton modify_medOK;
    
    @FXML
    private Label quant_sale_label;
    
    @FXML
    private JFXTextField quantity_sale;
    
    @FXML
    private JFXButton Sale_med_Button;
    
    @FXML
    private JFXButton Sale_med_ButtonOk;

    @FXML
    private JFXButton modify_med_Button;
    
    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXPasswordField confirm_password;

    @FXML
    private JFXPasswordField password_enter;
    
    @FXML
    private JFXTextField first_name;

    @FXML
    private JFXTextField last_name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField username_enter;

    @FXML
    private JFXComboBox<String> com;
    
    @FXML
    private TextField Search_here;

    @FXML
    private ImageView Search_photo;

    @FXML
    private Label search_med_name;

    @FXML
    private Label search_med_type;

    @FXML
    private Label search_med_price;

    @FXML
    private Label search_med_quantity;

    @FXML
    private Label search_med_shelf;

    @FXML
    private Label search_med_expiry;

    @FXML
    private Label search_med_using;

    @FXML
    private Label Search_med_msg;

    @FXML
    private Label add_medcine_msg;

    @FXML
    private AnchorPane pan2_med;
    
    @FXML
    private JFXDatePicker med_expiry;

    @FXML
    private JFXComboBox<String> med_type;

    @FXML
    private JFXTextField med_name;

    @FXML
    private JFXTextField med_price;

    @FXML
    private JFXTextField med_quantity;

    @FXML
    private JFXTextField med_shelf;

    @FXML
    private ImageView medicne_photo;

    @FXML
    private TextArea med_using;

    @FXML
    private TextField med_photo_path;

    @FXML
    private Label delete_user_msg;

    @FXML
    private TableView<users_info> user_table;

    @FXML
    private TableColumn<users_info, String> table_firstName;

    @FXML
    private TableColumn<users_info, String> table_lastName;

    @FXML
    private TableColumn<users_info, String> table_phone;

    @FXML
    private TableColumn<users_info, String> table_state;

    @FXML
    private TableColumn<users_info, String> table_gender;

    @FXML
    private TableColumn<users_info, String> table_username;

    @FXML
    private TableColumn<users_info, String> table_password;

    @FXML
    private TabPane bigTab;

    @FXML
    private Label show_name;

    @FXML
    private Label num_medcicine;

    @FXML
    private Label num_expiry;

    @FXML
    private Label num_out;

    @FXML
    private Label num_order;

    @FXML
    private Label num_sales;

    @FXML
    private AnchorPane add_user_pan;

    @FXML
    private ImageView user_photo;

    @FXML
    private TextField loadPhoto;

    @FXML
    private Label add_user_error;

    @FXML
    private ImageView user_profile_pic;

    @FXML
    private Label pro_first_name;

    @FXML
    private Label pro_phone;

    @FXML
    private Label pro_username;

    @FXML
    private Label pro_state;

    @FXML
    private Label pro_last_name;

    @FXML
    private Label pro_gender;

    @FXML
    private Label pro_password;

    @FXML
    private Button Modify_medicine;

    @FXML
    private ImageView user_home_pic;

    @FXML
    private ProgressIndicator stock_pi;

    ////////////////////
    @FXML
    void delete_medicineAction(ActionEvent event) {
        String name_med_del = med_name.getText().toString();
        try {
            PreparedStatement ps = c.prepareStatement("delete from medicine where name = ?");
            ps.setString(1, name_med_del);
            ps.executeUpdate();
            clear_add_medcine();
            add_medcine_msg.setTextFill(Color.web("#137547"));
            add_medcine_msg.setText("medicine deleted");
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void view_allAction(ActionEvent event) {
        
        if(view_all.getValue().equalsIgnoreCase("All")){
            view_all_medicine();
        }else if(view_all.getValue().equalsIgnoreCase("Liquid/Syrup")){
            getData_special("Liquid/Syrup");
            view_all_medicine_special("Liquid/Syrup");
        }else if(view_all.getValue().equalsIgnoreCase("Tablets")){
            getData_special("Tablets");
            view_all_medicine_special("Tablets");
        }else if(view_all.getValue().equalsIgnoreCase("Capsules")){
            getData_special("Capsules");
            view_all_medicine_special("Capsules");
        }else if(view_all.getValue().equalsIgnoreCase("lozenges")){
            getData_special("lozenges");
            view_all_medicine_special("lozenges");
        }else if(view_all.getValue().equalsIgnoreCase("Injections")){
            getData_special("Injections");
            view_all_medicine_special("Injections");
        }else if(view_all.getValue().equalsIgnoreCase("Spray")){
            getData_special("Spray");
            view_all_medicine_special("Spray");
        }else if(view_all.getValue().equalsIgnoreCase("Drops")){
            getData_special("Drops");
            view_all_medicine_special("Drops");
        }else if(view_all.getValue().equalsIgnoreCase("Topical Medicine")){
            getData_special("Topical Medicine");
            view_all_medicine_special("Topical Medicine");
        }
         
    }
    
    @FXML
    void out_of_stockAction(ActionEvent event) {
        system_info_msg.setText("Out of stock medicines");
        int numx = view_info_medicine_stock();
        stock_pi.setStyle("-fx-accent: green;");
        stock_pi.setProgress((double)numx/get_medcine_number());
        prog_tesssssssssssssssssss.setProgress((double)numx/get_medcine_number());
        bigTab.getSelectionModel().select(8);
    }
    
    @FXML
    void expiry_monthAction(ActionEvent event) {
         system_info_msg.setText("Expired medicines");
        int nn = view_info_medicine_expierd();
        stock_pi.setStyle("-fx-accent: green;");
        stock_pi.setProgress((double)nn/get_medcine_number());
        prog_tesssssssssssssssssss.setProgress((double)nn/get_medcine_number());
        bigTab.getSelectionModel().select(8);
    }
    
    @FXML
    void clear_salesAction(ActionEvent event) {
        try {
            PreparedStatement ps = c.prepareStatement("delete from out_of_stock");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view_sales_method();
        num_sales.setText(""+get_sales_number());
        num_order.setText(""+get_order_number());
    }
    
    @FXML
    void total_salesAction(ActionEvent event) {
        view_sales_method();
        bigTab.getSelectionModel().select(2);
    }
    
    @FXML
    void modify_medOKAction(ActionEvent event) throws FileNotFoundException{
        search_med_expiry.setTextFill(Color.web("#000000"));
        search_med_quantity.setTextFill(Color.web("#000000"));
        
        String medicne_Name = med_name.getText().toString();
        String medicne_Quantity = med_quantity.getText().toString();
        String medicne_Shelf = med_shelf.getText().toString();
        String medicne_Using = med_using.getText().toString();
        String medcine_pic_path = med_photo_path.getText().toString();
        double medicne_Price = Double.parseDouble(med_price.getText());

        String medicine_Type = med_type.getValue().toString();
        String medicine_Expiry = med_expiry.getValue().toString();

        LocalDate exp = LocalDate.now();
        LocalDate exp2 = med_expiry.getValue();
        
        if (exp.isAfter(exp2)) {
            Search_med_msg.setText("please choose valid expiry date...");
            Toolkit.getDefaultToolkit().beep();
        }else{
            if(med_photo_path.getText().equalsIgnoreCase("")){
                try {
                     PreparedStatement ps = c.prepareStatement("update medicine set name=? ,type= ? ,price = ? ,quantity = ?,shelf = ? ,expiryDate = ? ,feeding = ? where name = ?");
                     ps.setString(1,medicne_Name);
                     ps.setString(2,medicine_Type);
                     ps.setDouble(3,medicne_Price);
                     ps.setString(4,medicne_Quantity);
                     ps.setString(5,medicne_Shelf);
                     ps.setString(6,medicine_Expiry);
                     ps.setString(7,medicne_Using);
                     ps.setString(8,medicne_Name);
                     ps.execute();
                     System.out.println("updated");
                     
                    TextFields.bindAutoCompletion(Search_here, get_medcine_names());
                    bigTab.getSelectionModel().select(3);
                    Search_med_msg.setTextFill(Color.web("#137547"));
                    Search_med_msg.setText("Medicine Updated");
                    
                    ///
                    search_med_name.setText(medicne_Name);
                    search_med_type.setText(medicine_Type);
                    search_med_price.setText(medicne_Price+"");
                    
                    search_med_shelf.setText(medicne_Shelf);
                    search_med_expiry.setText(medicine_Expiry);
                    search_med_using.setText(medicne_Using);
                    
                    if(medicne_Quantity.equalsIgnoreCase("0")){
                        search_med_quantity.setTextFill(Color.web("#d90429"));
                        search_med_quantity.setText(medicne_Quantity);
                    }else search_med_quantity.setText(medicne_Quantity);

                    LocalDate localDate1011 = LocalDate.now();
                    LocalDate con_date1011 = LocalDate.parse( medicine_Expiry);
                    if(localDate1011.isAfter(con_date1011) || localDate1011.equals(con_date1011)){
                        search_med_expiry.setTextFill(Color.web("#d90429"));
                        search_med_expiry.setText(medicine_Expiry);
                    }else search_med_expiry.setText(medicine_Expiry);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                     PreparedStatement ps = c.prepareStatement("update medicine set name=? ,type= ? ,price = ? ,quantity= ? ,shelf = ? ,expiryDate = ? ,feeding = ?, image = ? where name = ?");
                     InputStream is_sale22 = new FileInputStream(new File(med_picture_path));
                     ps.setString(1,medicne_Name);
                     ps.setString(2,medicine_Type);
                     ps.setDouble(3,medicne_Price);
                     ps.setString(4,medicne_Quantity);
                     ps.setString(5,medicne_Shelf);
                     ps.setString(6,medicine_Expiry);
                     ps.setString(7,medicne_Using);
                     ps.setBlob(8,is_sale22);
                     ps.setString(9, medicne_Name);
                     ps.execute();
                     System.out.println("updated");
                     TextFields.bindAutoCompletion(Search_here, get_medcine_names());
                    bigTab.getSelectionModel().select(3);
                    Search_med_msg.setTextFill(Color.web("#137547"));
                    Search_med_msg.setText("Medicine Updated");
                    
                    ////
                    search_med_name.setText(medicne_Name);
                    search_med_type.setText(medicine_Type);
                    search_med_price.setText(medicne_Price+"");
                    search_med_shelf.setText(medicne_Shelf);
                    search_med_using.setText(medicne_Using);
                    
                    if(medicne_Quantity.equalsIgnoreCase("0")){
                        search_med_quantity.setTextFill(Color.web("#d90429"));
                        search_med_quantity.setText(medicne_Quantity);
                    }else search_med_quantity.setText(medicne_Quantity);

                    LocalDate localDate1011 = LocalDate.now();
                    LocalDate con_date1011 = LocalDate.parse( medicine_Expiry);
                    if(localDate1011.isAfter(con_date1011) || localDate1011.equals(con_date1011)){
                        search_med_expiry.setTextFill(Color.web("#d90429"));
                        search_med_expiry.setText(medicine_Expiry);
                    }else search_med_expiry.setText(medicine_Expiry);
                    
                    Image image_med_33 = new Image(fl33.toURI().toString());
                    Search_photo.setImage(image_med_33);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @FXML
    void Sale_med_ButtonOkAction(ActionEvent event) {
       int yFormuser = Integer.parseInt(quantity_sale.getText().toString());   
       int xlocal = Integer.parseInt(search_med_quantity.getText().toString()); 
       double Xprice = Double.parseDouble(search_med_price.getText().toString()); 
       double totl_price= Xprice * yFormuser;
       if(yFormuser>xlocal){
            Search_med_msg.setTextFill(Color.web("#d90429"));
            Search_med_msg.setText("the Quantity isn't enough...");
            Toolkit.getDefaultToolkit().beep(); 
       }else if(expired_medicine_name(search_med_name.getText())==true){
            Search_med_msg.setTextFill(Color.web("#d90429"));
            Search_med_msg.setText("Medicine is Expired...");
            Toolkit.getDefaultToolkit().beep(); 
       }else{
           try {
               PreparedStatement ps = c.prepareStatement("update medicine set quantity = quantity - ? where name = ?");
               ps.setInt(1, yFormuser);
               ps.setString(2,search_med_name.getText());
               ps.execute();
               
           } catch (SQLException ex) {
               Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           try {
               PreparedStatement ps = c.prepareStatement("insert into out_of_stock ( name , type , shelf , expiry ,price ,quantity) values (? , ? , ? , ? ,? ,?) ");
               ps.setString(1, search_med_name.getText());
               ps.setString(2,search_med_type.getText());
               ps.setString(3,search_med_shelf.getText());
               ps.setString(4,search_med_expiry.getText());
               ps.setDouble(5,totl_price);
               ps.setInt(6, yFormuser);
               ps.executeUpdate();
               System.out.println("Sold...");
           } catch (SQLException ex) {
               Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
           }
           Search_med_msg.setText("Successfully sold");
           Search_med_msg.setTextFill(Color.web("#137547"));
           search_med_quantity.setText(""+(xlocal-yFormuser));
           quantity_sale.setText("0");
           quant_sale_label.setVisible(false);
           quantity_sale.setVisible(false);
           Sale_med_ButtonOk.setVisible(false);
       }
       
    }
    
    @FXML
    void Sale_med_ButtonAction(ActionEvent event) {
        quant_sale_label.setVisible(true);
        quantity_sale.setVisible(true);
        Sale_med_ButtonOk.setVisible(true);
    }
    
    @FXML
    void modify_med_ButtonAction(ActionEvent event) {
        med_name.setText(search_med_name.getText());
        med_quantity.setText(search_med_quantity.getText());
        med_shelf.setText(search_med_shelf.getText());
        med_using.setText(search_med_using.getText());
        med_price.setText(search_med_price.getText());
        med_expiry.setValue(exp_sale);
        med_type.setValue(search_med_type.getText());
        medicne_photo.setImage(excute_image(search_med_name.getText()));
        modify_medOK.setVisible(true);
        delete_medicine.setVisible(true);
        medcine_add.setVisible(false);
        medcine_cancle.setVisible(false);
        bigTab.getSelectionModel().select(4);
    }

    @FXML
    void Search_med_buttonAction(ActionEvent event) { 
        search_med_expiry.setTextFill(Color.web("#000000"));
        search_med_quantity.setTextFill(Color.web("#000000"));
        
        Rectangle clip22 = new Rectangle(
                Search_photo.getFitWidth(), Search_photo.getFitHeight()
        );
        clip22.setArcWidth(30);
        clip22.setArcHeight(30);
        Search_photo.setClip(clip22);
        String med_name_s = Search_here.getText().toString();
        if(Search_here.getText().equalsIgnoreCase("")){
            Search_med_msg.setText("please enter name of medicicne...");
            Toolkit.getDefaultToolkit().beep();
        }else if (!check_medicine(med_name_s)) {
            clear_search_medicine();
            Search_med_msg.setText("The medicine is not found...");
            Toolkit.getDefaultToolkit().beep();
        } else {
            Search_med_msg.setText("");
            try {
                PreparedStatement ps = c.prepareStatement("select * from medicine where name= ?");
                ps.setString(1, med_name_s);
                r = ps.executeQuery();
                r.next();
                search_med_name.setText(r.getString("name"));
                search_med_type.setText(r.getString("type"));
                search_med_price.setText(r.getString("price"));
                
                if(r.getString("quantity").equalsIgnoreCase("0")){
                    search_med_quantity.setTextFill(Color.web("#d90429"));
                    search_med_quantity.setText(r.getString("quantity"));
                }else search_med_quantity.setText(r.getString("quantity"));

                LocalDate localDate1011 = LocalDate.now();
                LocalDate con_date1011 = LocalDate.parse( r.getString("expiryDate"));
                if(localDate1011.isAfter(con_date1011) || localDate1011.equals(con_date1011)){
                    search_med_expiry.setTextFill(Color.web("#d90429"));
                    search_med_expiry.setText(""+r.getDate("expiryDate"));
                }else search_med_expiry.setText(""+r.getDate("expiryDate"));
                
                search_med_shelf.setText(r.getString("shelf"));
                search_med_using.setText(r.getString("feeding"));
                Blob blob = r.getBlob("image");
                InputStream is = blob.getBinaryStream();
                Image imge33 = new Image(is);
                Search_photo.setImage(imge33);
                Sale_med_Button.setVisible(true);
                modify_med_Button.setVisible(true);
                
                exp_sale= LocalDate.parse(r.getString("expiryDate"));

            } catch (SQLException ex) {
                Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void medcine_addAction(ActionEvent event) {
        String medicne_Name = med_name.getText().toString();
        String medicne_Quantity = med_quantity.getText().toString();
        String medicne_Shelf = med_shelf.getText().toString();
        String medicne_Using = med_using.getText().toString();
        String medcine_pic_path = med_photo_path.getText().toString();
        

        String medicine_Type = med_type.getValue().toString();
        String medicine_Expiry = med_expiry.getValue().toString();

        LocalDate exp = LocalDate.now();
        LocalDate exp2 = med_expiry.getValue();

        if (medcine_pic_path.equalsIgnoreCase("")) {
            add_medcine_msg.setText("please choose medicine's image...");
            Toolkit.getDefaultToolkit().beep();                      /// 
        } else if (medicne_Name.equalsIgnoreCase("") ||  med_price.getText().toString().equalsIgnoreCase("") || medicne_Quantity.equalsIgnoreCase("") || medicne_Shelf.equalsIgnoreCase("") || medicne_Using.equalsIgnoreCase("")) {
            add_medcine_msg.setText("You must fill all fields...");
            Toolkit.getDefaultToolkit().beep();
        } else if (check_medicine(medicne_Name)) {
            add_medcine_msg.setText("the medicine is already exist...");
            Toolkit.getDefaultToolkit().beep();
        } else if (exp.isAfter(exp2)) {
            add_medcine_msg.setText("please choose valid expiry date...");
            Toolkit.getDefaultToolkit().beep();
        } else {
            add_medcine_msg.setText("");
            double medicne_Price = Double.parseDouble(med_price.getText());
            try {
                PreparedStatement ps = c.prepareStatement("insert into medicine( name , type , price , quantity , expiryDate , shelf , image , feeding ) values (? , ?, ?, ?, ?, ?, ?, ?)");
                InputStream is = new FileInputStream(new File(med_picture_path));
                ps.setString(1, medicne_Name);
                ps.setString(2, medicine_Type);
                ps.setDouble(3, medicne_Price);
                ps.setString(4, medicne_Quantity);
                ps.setString(5, medicine_Expiry);
                ps.setString(6, medicne_Shelf);
                ps.setBlob(7, is);
                ps.setString(8, medicne_Using);
                ps.executeUpdate();
                
                System.out.println("done..");
                clear_add_medcine();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            add_medcine_msg.setTextFill(Color.web("#137547"));
            add_medcine_msg.setText("Medicine successfully added");
        }
    }

    @FXML
    void medcine_cancleAction(ActionEvent event) {
        clear_add_medcine();
    }

    @FXML
    void Browse_medAction(ActionEvent event) {
        add_medcine_msg.setText("");
        Rectangle clip22 = new Rectangle(
                medicne_photo.getFitWidth(), medicne_photo.getFitHeight()
        );
        clip22.setArcWidth(30);
        clip22.setArcHeight(30);
        medicne_photo.setClip(clip22);

        FileChooser fileChooser2 = new FileChooser();

//        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser2.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        fileChooser2.setTitle("Open Resource File");
        Stage stage = (Stage) pan2_med.getScene().getWindow();
        File file2 = fileChooser2.showOpenDialog(stage);
        fl33 = file2;
        med_picture_path = file2.getAbsolutePath();
        if (file2 != null) {
            med_photo_path.setText(file2.getAbsolutePath());
            Image image = new Image(file2.toURI().toString());
            medicne_photo.setImage(image);
            med_flag = true;
        } else {
            System.out.println("file is not valid");
        }
    }

    @FXML
    void browseAction(ActionEvent event) {
        add_user_error.setText("");
        Rectangle clip = new Rectangle(
                user_photo.getFitWidth(), user_photo.getFitHeight()
        );
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        user_photo.setClip(clip);

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) add_user_pan.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        fl22 = file;
        picture_path = file.getAbsolutePath();
        if (file != null) {
            loadPhoto.setText(file.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            user_photo.setImage(image);
            flag = true;
        } else {
            System.out.println("file is not valid");
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @FXML
    void viewMedicineAction(ActionEvent event) {
        
        
        bigTab.getSelectionModel().select(6);
        view_all.getSelectionModel().selectFirst();
        view_all_medicine();
    }

    @FXML
    void my_profileAction(ActionEvent event) {
        bigTab.getSelectionModel().select(5);
    }

    @FXML
    void add_medicineAction(ActionEvent event) {
        med_type.getSelectionModel().selectFirst();
        med_expiry.setValue(LocalDate.now());
        modify_medOK.setVisible(false);
        delete_medicine.setVisible(false);
        medcine_add.setVisible(true);
        medcine_cancle.setVisible(true);
        clear_add_medcine();
        add_medcine_msg.setText("");
        add_medcine_msg.setTextFill(Color.web("#d90429"));
        bigTab.getSelectionModel().select(4);
    }

    @FXML
    void SearchAction(ActionEvent event) {
        Search_med_msg.setText("");
        TextFields.bindAutoCompletion(Search_here, get_medcine_names());
        clear_search_medicine();
        bigTab.getSelectionModel().select(3);
    }

    @FXML
    void add_userAction(ActionEvent event) {
        clrear_add_user();
        add_user_error.setText("");
        update_profile.setVisible(false);
        add_user_enter.setVisible(true);
        flag = false;
        
        com.getSelectionModel().selectFirst();
        bigTab.getSelectionModel().select(1);
    }

    @FXML
    void view_usersAction(ActionEvent event) {
        view_user_method();
        index=-1;
        Delete_user.setVisible(false);
        bigTab.getSelectionModel().select(7);
    }

    @FXML
    void Delete_userAction(ActionEvent event) {
        try {
            if (index <= -1) {
                delete_user_msg.setTextFill(Color.web("#d90429"));
                delete_user_msg.setText("please choose user...");
                Toolkit.getDefaultToolkit().beep();
            } else {
                PreparedStatement ps = c.prepareStatement("delete from user where username = ? and password = ? ");
                ps.setString(1, xusername);
                ps.setString(2, xpassword);
                ps.execute();
                view_user_method();
                delete_user_msg.setTextFill(Color.web("#137547"));
                delete_user_msg.setText("User deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void getSelected(MouseEvent event) {
        
        delete_user_msg.setText("");
        index = user_table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            Delete_user.setVisible(false);
            return;
        }
        Delete_user.setVisible(true);
        xusername = table_username.getCellData(index).toString();
        xpassword = table_password.getCellData(index).toString();
    }

    @FXML
    void dashboardAction(ActionEvent event) {
        get_medcine_names();
        num_medcicine.setText(""+get_medcine_number());
        num_expiry.setText(""+view_info_medicine_expierd());
        num_out.setText(view_info_medicine_stock()+"");
        num_sales.setText(""+get_sales_number());
        num_order.setText(""+get_order_number());
        bigTab.getSelectionModel().select(0);
    }

    @FXML
    void Exit_appAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void modifyUser_Action(ActionEvent event) {

        if (mod_gender.equalsIgnoreCase("male")) {
            male.setSelected(true);
            female.setSelected(false);
        } else {
            male.setSelected(false);
            female.setSelected(true);
        }

        if (mod_State.equalsIgnoreCase("user")) {
            com.getSelectionModel().selectFirst();
        } else {
            com.getSelectionModel().selectLast();
        }
        
        first_name.setText(mod_firstName);
        last_name.setText(mod_lastname);
        phone.setText(mod_phone);
        username_enter.setText(mod_username);
        password_enter.setText(mod_password);
        confirm_password.setText(mod_password);
        loadPhoto.setText("");
        user_photo.setImage(mod_image);
        Rectangle clip = new Rectangle(
                user_photo.getFitWidth(), user_photo.getFitHeight()
        );
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        user_photo.setClip(clip);
        update_profile.setVisible(true);
        add_user_enter.setVisible(false);
        add_user_error.setText("");
        flag = false;
        bigTab.getSelectionModel().select(1);
    }

    @FXML
    void log_outAction(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void add_user_enterQAction(ActionEvent event) {

        String user_first_name = first_name.getText().toString();
        String user_last_name = last_name.getText().toString();
        String user_phone = phone.getText().toString();
        String user_user_name = username_enter.getText().toString();
        String user_password = password_enter.getText().toString();
        String user_confirm_password = confirm_password.getText().toString();
        String user_state = com.getValue().toString();
        String usre_gender = "male";
        if (female.isSelected()) {
            usre_gender = "female";
        }
        if (user_state == "pharmacist") {
            user_state = "admin";
        }

        if (flag == false) {
            add_user_error.setTextFill(Color.web("#d90429"));
            add_user_error.setText("please enter your profile picture...");
            Toolkit.getDefaultToolkit().beep();
        } else if (user_first_name.equalsIgnoreCase("") || user_last_name.equalsIgnoreCase("") || user_phone.equalsIgnoreCase("") || user_user_name.equalsIgnoreCase("") || user_password.equalsIgnoreCase("") || user_confirm_password.equalsIgnoreCase("") || user_state.equalsIgnoreCase("") || (female.isSelected() == false && male.isSelected() == false)) {
            add_user_error.setTextFill(Color.web("#d90429"));
            add_user_error.setText("You must fill all fields...");
            Toolkit.getDefaultToolkit().beep();
        } else if (!user_confirm_password.equals(user_password)) {
            add_user_error.setTextFill(Color.web("#d90429"));
            add_user_error.setText("Passwords do not match...");
            password_enter.setText("");
            confirm_password.setText("");
            Toolkit.getDefaultToolkit().beep();
        } else {
            add_user_error.setText("");
            String check = login(user_password, user_user_name);
            String check_pass = check_password(user_password);

            if (check == "admin" || check == "user") {
                add_user_error.setTextFill(Color.web("#d90429"));
                add_user_error.setText("the user is already exist...");
                Toolkit.getDefaultToolkit().beep();
            } else if (check_pass == "admin" || check_pass == "user") {
                add_user_error.setTextFill(Color.web("#d90429"));
                add_user_error.setText("Passwords are reserved...");
                password_enter.setText("");
                confirm_password.setText("");
                Toolkit.getDefaultToolkit().beep();
            } else {

                try {
                    PreparedStatement ps = c.prepareStatement("insert into user( password , username , gender , phone , state , firstname , lastname , photo ) values ('" + user_password + "' , '" + user_user_name + "' , '" + usre_gender + "' , '" + user_phone + "' , '" + user_state + "' ,'" + user_first_name + "' , '" + user_last_name + "' , ?)");
                    InputStream is = new FileInputStream(new File(picture_path));
                    ps.setBlob(1, is);
                    ps.executeUpdate();
                    System.out.println("done................");
                    clrear_add_user();
                    flag = false;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                add_user_error.setTextFill(Color.web("#137547"));
                add_user_error.setText("User successfully added");

            }

        }
    }

    @FXML
    void cancleAction(ActionEvent event) {
        clrear_add_user();
        add_user_error.setText("");
    }

    @FXML
    void update_profileAction(ActionEvent event) {

        String user_first_name = first_name.getText().toString();
        String user_last_name = last_name.getText().toString();
        String user_phone = phone.getText().toString();
        String user_user_name = username_enter.getText().toString();
        String user_password = password_enter.getText().toString();
        String user_confirm_password = confirm_password.getText().toString();
        String user_state = com.getValue().toString();
        String usre_gender = "male";
        if (female.isSelected()) {
            usre_gender = "female";
        }
        if (user_state == "pharmacist") {
            user_state = "admin";
        }

        if (!user_confirm_password.equals(user_password)) {
            add_user_error.setTextFill(Color.web("#d90429"));
            add_user_error.setText("Passwords do not match...");
            password_enter.setText("");
            confirm_password.setText("");
            Toolkit.getDefaultToolkit().beep();
        } else {

            String check = login(user_password, user_user_name);
            String check_pass = check_password(user_password);
            if ((check_pass == "admin" || check_pass == "user") && (!mod_password.equalsIgnoreCase(user_password))) {
                add_user_error.setTextFill(Color.web("#d90429"));
                add_user_error.setText("Passwords are reserved...");
                password_enter.setText("");
                confirm_password.setText("");
                Toolkit.getDefaultToolkit().beep();
            } else {
                if (flag == true) {
                    try {
                        PreparedStatement ps = c.prepareStatement("update user set username ='" + user_user_name + "' , password ='" + user_password + "' , state ='" + user_state + "',  gender ='" + usre_gender + "' , phone ='" + user_phone + "' , firstname ='" + user_first_name + "' , lastname ='" + user_last_name + "' , photo = ?  where password = '" + mod_password + "' ");
                        InputStream is = new FileInputStream(new File(picture_path));
                        ps.setBlob(1, is);
                        ps.executeUpdate();
                        System.out.println("updtae................");
                        flag = false;

                        Image image33 = new Image(fl22.toURI().toString());
                        show_name.setText(user_first_name);
                        user_profile_pic.setImage(image33);
                        user_home_pic.setImage(image33);
                        pro_first_name.setText(user_first_name);
                        pro_last_name.setText(user_last_name);
                        pro_gender.setText(usre_gender);
                        pro_phone.setText(user_phone);
                        pro_password.setText(user_password);
                        pro_username.setText(user_user_name);
                        if (user_state.equalsIgnoreCase("admin")) {
                            pro_state.setText("Pharmacist");
                        } else {
                            pro_state.setText(user_state);
                        }
                        //// modify data
                        mod_image = image33;
                        mod_firstName = user_first_name;
                        mod_username = user_user_name;
                        mod_password = user_password;
                        mod_lastname = user_last_name;
                        mod_phone = user_phone;
                        mod_State = pro_state.getText();
                        mod_gender = usre_gender;
                        /////

                        bigTab.getSelectionModel().select(5);
                        clrear_add_user();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        PreparedStatement ps = c.prepareStatement("update user set username = ? , password = ? , state = ?, gender = ? , phone = ? , firstname = ? , lastname = ?  where password = '" + mod_password + "' ");
                        ps.setString(1, user_user_name);
                        ps.setString(2, user_password);
                        ps.setString(3, user_state);
                        ps.setString(4, usre_gender);
                        ps.setString(5, user_phone);
                        ps.setString(6, user_first_name);
                        ps.setString(7, user_last_name);
                        ps.executeUpdate();
                        System.out.println("updtae................");
                        flag = false;

                        show_name.setText(user_first_name);
                        pro_first_name.setText(user_first_name);
                        pro_last_name.setText(user_last_name);
                        pro_gender.setText(usre_gender);
                        pro_phone.setText(user_phone);
                        pro_password.setText(user_password);
                        pro_username.setText(user_user_name);
                        if (user_state.equalsIgnoreCase("admin")) {
                            pro_state.setText("Pharmacist");
                        } else {
                            pro_state.setText(user_state);
                        }
                        //// modify data
                        mod_firstName = user_first_name;
                        mod_username = user_user_name;
                        mod_password = user_password;
                        mod_lastname = user_last_name;
                        mod_phone = user_phone;
                        mod_State = pro_state.getText();
                        mod_gender = usre_gender;
                        /////
                        bigTab.getSelectionModel().select(5);
                        clrear_add_user();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                add_user_error.setTextFill(Color.web("#137547"));
                add_user_error.setText("User successfully updated");
            }
        }
    }

    public void clrear_add_user() {
        male.setSelected(false);
        female.setSelected(false);
        first_name.setText("");
        last_name.setText("");
        phone.setText("");
        username_enter.setText("");
        password_enter.setText("");
        confirm_password.setText("");
        com.getSelectionModel().selectFirst();
        loadPhoto.setText("");
        user_photo.setImage(null);
        File file_picture = new File("C:\\java\\Pro_pharmacy\\src\\photos\\33.png");
        Image image_pic = new Image(file_picture.toURI().toString());
        user_photo.setImage(image_pic);
    }

    public void clear_add_medcine() {
        add_medcine_msg.setText("");
        med_name.setText("");
        med_photo_path.setText("");
        med_price.setText("");
        med_quantity.setText("");
        med_shelf.setText("");
        med_using.setText("");
        med_expiry.setValue(LocalDate.now());
        med_type.getSelectionModel().selectFirst();
        File file_picture12 = new File("C:\\java\\Pro_pharmacy\\src\\photos\\ico22.png");
        Image image_pic22 = new Image(file_picture12.toURI().toString());
        medicne_photo.setImage(image_pic22);
    }
    
    public void clear_search_medicine(){
        Sale_med_ButtonOk.setVisible(false);
        quant_sale_label.setVisible(false);
        quantity_sale.setVisible(false);
        Sale_med_Button.setVisible(false);
        modify_med_Button.setVisible(false);
        Search_here.setText("");
        Search_med_msg.setText("");
        search_med_name.setText("");
        search_med_type.setText("");
        search_med_price.setText("");
        search_med_quantity.setText("");
        search_med_shelf.setText("");
        search_med_using.setText("");
        search_med_expiry.setText("");
        File file_picture44 = new File("C:\\java\\Pro_pharmacy\\src\\photos\\ico22.png");
        Image image_pic15 = new Image(file_picture44.toURI().toString());
        Search_photo.setImage(image_pic15);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        com.getItems().addAll("user", "pharmacist");
        get_medcine_names();
        num_medcicine.setText(""+get_medcine_number());
        num_expiry.setText(""+view_info_medicine_expierd());
        num_out.setText(view_info_medicine_stock()+"");
        num_sales.setText(""+get_sales_number());
        num_order.setText(""+get_order_number());
        
        med_type.getItems().addAll("Liquid/Syrup", "Tablets", "Capsules", "lozenges", "Injections", "Spray", "Drops", "Topical Medicine");
        view_all.getItems().addAll("All","Liquid/Syrup", "Tablets", "Capsules", "lozenges", "Injections", "Spray", "Drops", "Topical Medicine");
        stock_pi.setProgress(0);
        bigTab.getSelectionModel().select(0);
        
        
    }

    public String login(String user_password, String user_username) {
        String back = "c++";
        try {
            query = "select state from user where password='" + user_password + "' and username ='" + user_username + "'";
            stt = c.createStatement();
            r = stt.executeQuery(query);
            r.next();
            if (r.getString("state").equalsIgnoreCase("admin")) {
                back = "admin";
            } else if (r.getString("state").equalsIgnoreCase("user")) {
                back = "user";
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }

    public String check_password(String user_password_c) {
        String back = "c++";
        try {
            query = "select state from user where password='" + user_password_c + "' ";
            stt = c.createStatement();
            r = stt.executeQuery(query);
            r.next();
            if (r.getString("state").equalsIgnoreCase("admin")) {
                back = "admin";
            } else if (r.getString("state").equalsIgnoreCase("user")) {
                back = "user";
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }

    public boolean check_medicine(String name) {
        try {
            query = "select name from medicine where name='" + name + "' ";
            stt = c.createStatement();
            r = stt.executeQuery(query);
            r.next();
            if (r.getString("name").equalsIgnoreCase(name)) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void view_user_method() {
        delete_user_msg.setText("");
        ObservableList list22 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = c.prepareStatement("select * from user");
            r = ps.executeQuery();

            while (r.next()) {
                String chState = r.getString("state");
                if (r.getString("state").equalsIgnoreCase("admin")) {
                    chState = "pharmacist";
                }
                list22.add(new users_info(r.getString("firstname"), r.getString("lastname"), r.getString("gender"), r.getString("phone"), chState, r.getString("username"), r.getString("password")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_gender.setCellValueFactory(new PropertyValueFactory<users_info, String>("gender"));
        table_phone.setCellValueFactory(new PropertyValueFactory<users_info, String>("phone"));
        table_state.setCellValueFactory(new PropertyValueFactory<users_info, String>("state"));
        table_firstName.setCellValueFactory(new PropertyValueFactory<users_info, String>("First_name"));
        table_lastName.setCellValueFactory(new PropertyValueFactory<users_info, String>("Last_name"));
        table_username.setCellValueFactory(new PropertyValueFactory<users_info, String>("username"));
        table_password.setCellValueFactory(new PropertyValueFactory<users_info, String>("password"));
        user_table.setItems(list22);
    }
    
    public void view_sales_method() {
        
        ObservableList list33 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = c.prepareStatement("select * from out_of_stock");
            r = ps.executeQuery();

            while (r.next()) {
                list33.add(new med_sales(r.getString("name"), r.getString("type"), r.getString("shelf"), r.getString("expiry"),r.getString("quantity") , r.getString("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_med_name.setCellValueFactory(new PropertyValueFactory<med_sales, String>("sale_name"));
        table_med_type.setCellValueFactory(new PropertyValueFactory<med_sales, String>("sale_type"));
        table_med_shelf.setCellValueFactory(new PropertyValueFactory<med_sales, String>("sale_shelf"));
        table_med_expiry.setCellValueFactory(new PropertyValueFactory<med_sales, String>("expiry"));
        table_med_quantity.setCellValueFactory(new PropertyValueFactory<med_sales, String>("sale_quantity"));
        table_med_price.setCellValueFactory(new PropertyValueFactory<med_sales, String>("sale_price"));
        
        table_med_sales.setItems(list33);
    }
    
    public Set get_medcine_names(){
        Set<String> list= new HashSet<>();
        try {
            PreparedStatement ps = c.prepareStatement("select name from medicine");
            r = ps.executeQuery();
            while (r.next()) {
                list.add(r.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int get_medcine_number(){
        int num=0;
        try {
            PreparedStatement ps = c.prepareStatement("select name from medicine");
            r = ps.executeQuery();
            while (r.next()) {
                num++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
    
    public int get_order_number(){
        int num1=0;
        try {
            PreparedStatement ps = c.prepareStatement("select name from out_of_stock");
            r = ps.executeQuery();
            while (r.next()) {
               num1++;
            }
            return num1;
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num1;
    }
    
    public double get_sales_number(){
        double num1=0;
        try {
            PreparedStatement ps = c.prepareStatement("select sum (price) from out_of_stock");
            r = ps.executeQuery();
            r.next();
             num1 =r.getInt("sum(price)");
             
             return num1;
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num1;
    }
    
    public Image excute_image(String name){
        Image imge =null;
        try {
            query="select image from medicine where name ='"+name+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            Blob blob= r.getBlob("image");
            InputStream is_sale = blob.getBinaryStream();
            imge = new Image(is_sale);
            return imge;
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return imge;
    }

    private List<medicine_item> getData() {
        LocalDate localDate45 = LocalDate.now();
        LocalDate firstNext12 = localDate45.plusMonths(1).withDayOfMonth(1);
        
        List<medicine_item> items_fun = new ArrayList<>();        
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select * from medicine");
            r = ps.executeQuery();
            
            while(r.next()){
                LocalDate con_date11 = LocalDate.parse( r.getString("expiryDate"));
                String Date_med= r.getString("expiryDate");
                if(firstNext12.isAfter(con_date11)){
                       Date_med = "Expired";
                }
                Blob blob= r.getBlob("image");
                InputStream is66 = blob.getBinaryStream();
                Image imge66 = new Image(is66);
                items_fun.add(new medicine_item(r.getString("name"), r.getString("price"), r.getString("quantity"), Date_med, imge66));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items_fun;
    }
    
    private List<medicine_item> getData_special(String type) {
        LocalDate localDate45 = LocalDate.now();
        LocalDate firstNext12 = localDate45.plusMonths(1).withDayOfMonth(1);
        
        List<medicine_item> items_fun = new ArrayList<>();        
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select * from medicine where type = ?");
            ps.setString(1, type);
            r = ps.executeQuery();
            
            while(r.next()){
                LocalDate con_date11 = LocalDate.parse( r.getString("expiryDate"));
                String Date_med= r.getString("expiryDate");
                if(firstNext12.isAfter(con_date11)){
                       Date_med = "Expired";
                }
                Blob blob= r.getBlob("image");
                InputStream is66 = blob.getBinaryStream();
                Image imge66 = new Image(is66);
                items_fun.add(new medicine_item(r.getString("name"), r.getString("price"), r.getString("quantity"), Date_med, imge66));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items_fun;
    }
    
    private void view_all_medicine(){
        items.clear();
        view_med_grid.getChildren().clear();
        
        items.addAll(getData());
        int column = 0;
        int row = 1;
        try {  
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("med_items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Med_itemsController itemController = fxmlLoader.getController();
                
                itemController.setData(items.get(i));
                
                 if (column == 3) {
                    column = 0;
                    row++;
                 }

                view_med_grid.add(anchorPane, column++, row); //(child,column,row)
                
                //set grid width
                view_med_grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                view_med_grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                view_med_grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                view_med_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                view_med_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                view_med_grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void view_all_medicine_special(String type){
        items.clear();
        view_med_grid.getChildren().clear();
        
        items.addAll(getData_special(type));
        int column = 0;
        int row = 1;
        try {  
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("med_items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Med_itemsController itemController = fxmlLoader.getController();
                
                itemController.setData(items.get(i));
                
                   if (column == 3) {
                    column = 0;
                    row++;
                   }

                   view_med_grid.add(anchorPane, column++, row); //(child,column,row)
                
                //set grid width
                view_med_grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                view_med_grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                view_med_grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                view_med_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                view_med_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                view_med_grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     
    private boolean expired_medicine_name (String name){
        LocalDate localDate22 = LocalDate.now();
        LocalDate firstNext = localDate22.plusMonths(1).withDayOfMonth(1);
        
        try {
            PreparedStatement ps = c.prepareStatement("select expiryDate from medicine where name = ?");
            ps.setString(1, name);
            r = ps.executeQuery();
            r.next();
            LocalDate con_date = LocalDate.parse( r.getString("expiryDate"));
            if(firstNext.isAfter(con_date)){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int view_info_medicine_stock(){
        items_info.clear();
        view_info_grid.getChildren().clear();
        items_info.addAll(getData_info_stock());
        int column = 0;
        int row = 1;
        int num2=0;
        
        try {  
            for (int i = 0; i < items_info.size(); i++) {
                FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("med_items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Med_itemsController itemController = fxmlLoader.getController();
                
                itemController.setData(items_info.get(i));
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                view_info_grid.add(anchorPane, column++, row); //(child,column,row)
                
                //set grid width
                view_info_grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                view_info_grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                view_info_grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                view_info_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                view_info_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                view_info_grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
                num2++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return num2;
    }
    
    private List<medicine_item> getData_info_stock() {
        LocalDate localDate45 = LocalDate.now();
        LocalDate firstNext12 = localDate45.plusMonths(1).withDayOfMonth(1);
        
        List<medicine_item> items_func = new ArrayList<>();        
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select * from medicine where quantity = 0");
            r = ps.executeQuery();
            
            while(r.next()){
                LocalDate con_date11 = LocalDate.parse( r.getString("expiryDate"));
                String Date_med= r.getString("expiryDate");
                if(firstNext12.isAfter(con_date11)){
                       Date_med = "Expired";
                }
                Blob blob= r.getBlob("image");
                InputStream is66 = blob.getBinaryStream();
                Image imge66 = new Image(is66);
                items_func.add(new medicine_item(r.getString("name"), r.getString("price"), r.getString("quantity"), Date_med, imge66));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items_func;
    }
    
    private int view_info_medicine_expierd(){
        items_info.clear();
        view_info_grid.getChildren().clear();
        items_info.addAll(getData_info_expierd());
        int column = 0;
        int row = 1;
        int number=0;
        try {  
            for (int i = 0; i < items_info.size(); i++) {
                FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("med_items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Med_itemsController itemController = fxmlLoader.getController();
                
                itemController.setData(items_info.get(i));
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                view_info_grid.add(anchorPane, column++, row); //(child,column,row)
                
                //set grid width
                view_info_grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                view_info_grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                view_info_grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                view_info_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                view_info_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                view_info_grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                number++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
    
    private List<medicine_item> getData_info_expierd() {
        LocalDate localDate45 = LocalDate.now();
        LocalDate firstNext12 = localDate45.plusMonths(1).withDayOfMonth(1);
        
        List<medicine_item> items_func = new ArrayList<>();        
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select * from medicine");
            r = ps.executeQuery();
            
            while(r.next()){
                LocalDate con_date11 = LocalDate.parse( r.getString("expiryDate"));
                String Date_med= r.getString("expiryDate");
                if(firstNext12.isAfter(con_date11)){
                    Date_med = "Expired";
                    Blob blob= r.getBlob("image");
                    InputStream is66 = blob.getBinaryStream();
                    Image imge66 = new Image(is66);
                    items_func.add(new medicine_item(r.getString("name"), r.getString("price"), r.getString("quantity"), Date_med, imge66));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items_func;
    }
}
