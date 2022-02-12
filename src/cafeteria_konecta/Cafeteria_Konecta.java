/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria_konecta;

import Controlador.Controler;
import Modelo.*;
import Vista.frmCafeteria;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres Alba
 * @Prueba KONECTA
 */
public class Cafeteria_Konecta {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        frmCafeteria gui = new frmCafeteria();
        Controler controler = new Controler(gui);
        gui.setTitle("Gestión de Cafeteria");
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        /*Producto producto1 = new Producto("Dulces", "Quipitos", 1200, 2, "Mecato", 10, "2021-02-01");
        System.out.println(producto1.calcularTiempo());*/
    }
}
