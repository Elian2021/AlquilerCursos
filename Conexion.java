/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author elian
 */
public class Conexion {
    public static Connection obtener_connexio_BD() {
        
        Connection conexion = null;
        String servidor = "jdbc:mysql://localhost:3306/alquiler";
        String bbdd = "";
        String user = "root";
        String password = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection(servidor + bbdd, user, password);
        } catch (Exception e){
            e.printStackTrace();
            return conexion;
        }
        return conexion;
    }    
    
}
