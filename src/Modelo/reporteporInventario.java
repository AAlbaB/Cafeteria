/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Andrés Alba
 * @Prueba técnica KONECTA
 */
public class reporteporInventario {
    private String nombreProducto;
    private int cantidadInventario;

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the cantidadInventario
     */
    public int getCantidadInventario() {
        return cantidadInventario;
    }

    /**
     * @param cantidadInventario the cantidadInventario to set
     */
    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }
}
