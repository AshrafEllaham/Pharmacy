
package pro_pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connectionH2 {
    
    public static Connection conDB(){
            
        try {
            try {

               Class.forName("org.h2.Driver");
               Connection con = DriverManager.getConnection("jdbc:h2:./database/pharmacy;IFEXISTS=TRUE" ,"admin",""); ///  ./database/pharmacy;IFEXISTS=TRUE        jdbc:h2:~/test./pharmacy
               System.out.println("connected...");
                return con;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
}
