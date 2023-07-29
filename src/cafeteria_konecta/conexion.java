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

    public static Connection abrir() {
        if (cnx == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String jdbcUrl = " ";
                cnx = DriverManager.getConnection(jdbcUrl);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println(e.getCause());
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }
}
