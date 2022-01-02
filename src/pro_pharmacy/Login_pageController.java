
package pro_pharmacy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Login_pageController implements Initializable {

    static Connection c=null;
    static PreparedStatement st=null;
    static Statement stt=null;
    static String query;
    static ResultSet r=null;
    
    @FXML
    private JFXButton  login;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    @FXML
    private AnchorPane Sidepanle;

    @FXML
    private AnchorPane icon;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;
    
    @FXML
    private Label ErrorMsg;
 ///   Image image  , String nfirstName , String nusername , String Nlastname , String nphone , String nState
    
    @FXML
    void login_button(ActionEvent event) {
        
        ////   auser name is => assh and password is => 111
        
        
        String check=login();
        String nfirstName= excute_firstName();
        Image image = excute_image();
        String Ngender = excute_gender();
        String nphone = excute_phone();
        String nState = excute_state();
        String Nlastname = excute_lasttName();
        String  npasswprd = password.getText().toString();
        String nusername = username.getText().toString();
        if(check == "admin"){
            
            try {
                ErrorMsg.setText("");
                FXMLLoader loader= new FXMLLoader(getClass().getResource("pharmacist.fxml"));
                Parent root1= (Parent) loader.load();
                // get controll
                PharmacistController ph = loader.getController();
                // send data
                ph.send_info(image , nfirstName , nusername , npasswprd , Nlastname , nphone , nState , Ngender);
                
                Stage st= new Stage();
                st.setTitle("Pharmacy");
                st.setResizable(false);
                Image icon = new Image(getClass().getResourceAsStream("icon20.png"));
                st.getIcons().add(icon);
                st.setScene(new Scene(root1));
                st.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if(check=="user"){
            try {
                ErrorMsg.setText("");
                FXMLLoader loader= new FXMLLoader(getClass().getResource("User.fxml"));
                Parent root1= (Parent) loader.load();
                
                UserController us= loader.getController();
                
                us.send_info(image , nfirstName , nusername , npasswprd , Nlastname , nphone , nState , Ngender);
                
                Stage st2= new Stage();
                st2.setTitle("Pharmacy");
                Image icon = new Image(getClass().getResourceAsStream("icon20.png"));
                st2.getIcons().add(icon);
                st2.setResizable(false);
                st2.setScene(new Scene(root1));
                st2.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            ErrorMsg.setAlignment(Pos.CENTER);
            ErrorMsg.setText("Your username or password isn't correct !!");
        }
        password.setText("");
        username.setText("");
    }
    
    public void animate(Circle c,float i){
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            0.0, 0.0,
            0.0, -18.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0});
        
        PathTransition trans= new PathTransition();
        trans.setNode(c);
        trans.setDelay(Duration.seconds(i));
        trans.setDuration(Duration.seconds(1));
        trans.setPath(polyline);
        
        RotateTransition rt = new RotateTransition(Duration.seconds(2));
        rt.setNode(c);
        rt.setByAngle(360);
        rt.setAutoReverse(true);
        rt.play();
        
        ParallelTransition pt=new ParallelTransition(trans,rt);
        pt.setCycleCount(ParallelTransition.INDEFINITE);
        pt.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animate(circle1,0);
        animate(circle2, (float) 0.2);
        animate(circle3,(float) 0.4);
        
    }    
    public Login_pageController(){
            c=connectionH2.conDB();
    }
    
    public String login(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select state from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            if(r.getString("state").equalsIgnoreCase("admin"))
                back="admin";
            else if(r.getString("state").equalsIgnoreCase("user"))
                back="user";
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }
    
    public String excute_firstName(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select  firstname from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            back=r.getString("firstname");
            return back;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }
    public Image excute_image(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        Image imge =null;
        try {
            query="select photo from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            Blob blob= r.getBlob("photo");
            InputStream is = blob.getBinaryStream();
            imge = new Image(is);
            return imge;
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return imge;
    }
    
    public String excute_lasttName(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select  lastname from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            back=r.getString("lastname");
            return back;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }
    
    public String excute_phone(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select  phone from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            back=r.getString("phone");
            return back;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }
    
    public String excute_state(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select  state from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            back=r.getString("state");
            if (back.equalsIgnoreCase("admin"))
                back="Pharmacist";
            return back;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }
    public String excute_gender(){
        String user_password = password.getText().toString();
        String user_username = username.getText().toString();
        String back = "c++";
        try {
            query="select gender from user where password='"+user_password+"' and username ='"+user_username+"'";
            stt=c.createStatement();
            r=stt.executeQuery(query);
            r.next();
            back=r.getString("gender");
            return back;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return back;
    }  
}
