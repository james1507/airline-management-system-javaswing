/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author conne
 */
public class ConnectSQL {
    protected Connection con;
    
    public ConnectSQL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=qlmb";
            String s = System.getProperty("os.name");
            if(s.contains("Windows")){
                con = DriverManager.getConnection(url, "sa", "123456");
            }else{
                con = DriverManager.getConnection(url, "sa", "Password1234");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
