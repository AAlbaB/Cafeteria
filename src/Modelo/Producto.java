/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Andrés Alba
 * @Prueba técnica KONECTA
 */
public class Producto {
    int id_producto;
    private String nombre;
    private String referencia;
    private int precio;
    private int peso;
    private String categoria;
    private int inventario;
    private LocalDate fechaCreacion;
    private long diasInventario;
    private int ventaProducto;

    public Producto(int id_producto, String nombre, int inventario, int ventaProducto){
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.inventario = inventario;
        this.ventaProducto = ventaProducto;
    }
    
    public Producto(String nombre, String referencia, int precio, int peso, String categoria, int inventario, String fechaCreacion) {
        this.nombre = nombre;
        this.referencia = referencia;
        this.precio = precio;
        this.peso = peso;
        this.categoria = categoria;
        this.inventario = inventario;
        this.fechaCreacion = this.calcularFecha(fechaCreacion);
    }

    public Producto(int id_producto, String nombre, String referencia, int precio, int peso, String categoria, int inventario, String fechaCreacion, long diasInventario) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.referencia = referencia;
        this.precio = precio;
        this.peso = peso;
        this.categoria = categoria;
        this.inventario = inventario;
        this.fechaCreacion = this.calcularFecha(fechaCreacion);
        this.diasInventario = diasInventario;
    }
    
    public Producto(int id_producto, String nombre, String referencia, int precio, int peso, String categoria, int inventario, String fechaCreacion) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.referencia = referencia;
        this.precio = precio;
        this.peso = peso;
        this.categoria = categoria;
        this.inventario = inventario;
        this.fechaCreacion = this.calcularFecha(fechaCreacion);
    }

    /**
     * @return the id_producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the inventario
     */
    public int getInventario() {
        return inventario;
    }

    /**
     * @param inventario the inventario to set
     */
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return this.fechaCreacion.toString();
    }

    public long calcularTiempo() {
        LocalDate fecha_actual = LocalDate.now();
        long diffDias = ChronoUnit.DAYS.between(this.fechaCreacion, fecha_actual);
        return diffDias;
    }

    private LocalDate calcularFecha(String fecha) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fecha, fmt);
    }

    /**
     * @return the diasInventario
     */
    public long getDiasInventario() {
        return diasInventario;
    }

    /**
     * @param diasInventario the diasInventario to set
     */
    public void setDiasInventario(long diasInventario) {
        this.diasInventario = diasInventario;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", Nombre=" + nombre + ", Referencia=" + referencia + ", Precio=" + precio + '}';
    }

    /**
     * @return the ventaProducto
     */
    public int getVentaProducto() {
        return ventaProducto;
    }

    /**
     * @param ventaProducto the ventaProducto to set
     */
    public void setVentaProducto(int ventaProducto) {
        this.ventaProducto = ventaProducto;
    }
    
}
