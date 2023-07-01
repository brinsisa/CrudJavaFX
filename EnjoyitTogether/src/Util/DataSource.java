
package Util;

import java.sql.*;

/**
 *
 * @author a
 */
public class DataSource {
    
     private String url = "jdbc:mysql://localhost:3306/enjoyit_together";
    private String login = "root";
    private String pwd = "";
    private Connection connection;
    private static DataSource instance;

    private DataSource() {
        try {
            connection = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static DataSource getInstance(){
        if(instance==null)
            instance=new DataSource();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
