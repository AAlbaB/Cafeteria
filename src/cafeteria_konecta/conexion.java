/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cafeteria_konecta;

import java.sql.*;

/**
 *
 * @author Andrés Alba
 * @Prueba técnica KONECTA
 */
public class conexion {
    private static Connection cnx = null;
    
    public static Connection abrir() throws ClassNotFoundException, SQLException{
        if(cnx == null){
            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bDCafeteria","root","");
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException(e.getMessage());
            }catch (SQLException e) {
                throw new SQLException(e.getCause());
            }
            
        }
        return cnx;
    }
    
    public static void cerrar() throws SQLException{
        if(cnx != null){
            cnx.close();
        }
    }

}
