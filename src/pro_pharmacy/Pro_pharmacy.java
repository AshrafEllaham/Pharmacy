
package pro_pharmacy;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Pro_pharmacy extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("icon20.png"));
        stage.getIcons().add(icon);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.resizableProperty().setValue(false);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
