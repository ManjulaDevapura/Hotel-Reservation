
package hotel.mgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author manjula
 */
public class Connect {
    
    Connection conn=null;
    public static Connection ConnecrDb(){
        
        try{
            
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
       
         return conn;
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
