/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Andrés Alba
 * @Prueba técnica KONECTA
 */
public class Cafeteria {

    private ArrayList<Producto> producto;

    public Cafeteria() {
        this.producto = new ArrayList<>();
    }

    /**
     * La cafetería ingresa un producto
     *
     * @param conexion
     * @param producto
     * @return
     * @throws java.sql.SQLException
     */
    public int agregarProducto(Connection conexion, Producto producto) throws SQLException {

        try {
            PreparedStatement StatementProducto = conexion.prepareStatement("INSERT INTO tb_producto (Nombre, Referencia, Precio, Peso, Categoria, Inventario, Fecha_Creacion, Dias_Inventario) values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            long diasInventario = producto.calcularTiempo();
            StatementProducto.setString(1, producto.getNombre());
            StatementProducto.setString(2, producto.getReferencia());
            StatementProducto.setInt(3, producto.getPrecio());
            StatementProducto.setInt(4, producto.getPeso());
            StatementProducto.setString(5, producto.getCategoria());
            StatementProducto.setInt(6, producto.getInventario());
            StatementProducto.setString(7, producto.getFechaCreacion());
            StatementProducto.setLong(8, diasInventario);
            int resProducto = StatementProducto.executeUpdate();
            return resProducto;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Producto buscarProducto(Connection conexion, String Nombre) throws SQLException {
        Producto producto_aux = null;
        try {
            String cadena = "SELECT * FROM tb_producto WHERE Nombre = ?";
            PreparedStatement consulta = conexion.prepareStatement(cadena);
            consulta.setString(1, Nombre);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int productoId = resultado.getInt(1);
                String productoName = resultado.getString(2);
                String productoReferencia = resultado.getString(3);
                int precioProducto = resultado.getInt(4);
                int pesoProducto = resultado.getInt(5);
                String categoriaProducto = resultado.getString(6);
                int inventarioProducto = resultado.getInt(7);
                String fechaCreacionProducto = resultado.getString(8);
                long diasInventarioProducto = resultado.getLong(9);

                producto_aux = new Producto(productoId, productoName, productoReferencia, precioProducto, pesoProducto, categoriaProducto, inventarioProducto, fechaCreacionProducto, diasInventarioProducto);
            }
            return producto_aux;
        } catch (SQLException e) {
            return producto_aux;
        }
    }
    
    public Producto buscarPorID(Connection conexion, int idProducto) throws SQLException {
        Producto producto_aux = null;
        try {
            String cadena = "SELECT * FROM tb_producto WHERE id_producto = ?";
            PreparedStatement consulta = conexion.prepareStatement(cadena);
            consulta.setInt(1, idProducto);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int productoId = resultado.getInt(1);
                String productoName = resultado.getString(2);
                String productoReferencia = resultado.getString(3);
                int precioProducto = resultado.getInt(4);
                int pesoProducto = resultado.getInt(5);
                String categoriaProducto = resultado.getString(6);
                int inventarioProducto = resultado.getInt(7);
                String fechaCreacionProducto = resultado.getString(8);
                long diasInventarioProducto = resultado.getLong(9);

                producto_aux = new Producto(productoId, productoName, productoReferencia, precioProducto, pesoProducto, categoriaProducto, inventarioProducto, fechaCreacionProducto, diasInventarioProducto);
            }
            return producto_aux;
        } catch (SQLException e) {
            return producto_aux;
        }
    }

    public boolean editarProducto(Connection conexion, Producto producto) {
        try {
            PreparedStatement StatementProducto = conexion.prepareStatement("UPDATE tb_producto SET Nombre = ? , Referencia = ? , Precio = ?, Peso = ?,  Categoria = ?, Inventario = ?, Fecha_Creacion = ?, Dias_Inventario = ? WHERE id_producto = ? ");
            long diasInventario = producto.calcularTiempo();
            StatementProducto.setString(1, producto.getNombre());
            StatementProducto.setString(2, producto.getReferencia());
            StatementProducto.setInt(3, producto.getPrecio());
            StatementProducto.setInt(4, producto.getPeso());
            StatementProducto.setString(5, producto.getCategoria());
            StatementProducto.setInt(6, producto.getInventario());
            StatementProducto.setString(7, producto.getFechaCreacion());
            StatementProducto.setLong(8, diasInventario);
            StatementProducto.setInt(9, producto.getId_producto());
            int resProducto = StatementProducto.executeUpdate();

            return resProducto > 0;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean hacerVenta(Connection conexion, Producto producto){
        try{
            PreparedStatement StatementProducto = conexion.prepareStatement("UPDATE tb_producto SET Nombre = ? , Inventario = ? WHERE id_producto = ? ");
            StatementProducto.setString(1, producto.getNombre());
            StatementProducto.setInt(2, producto.getInventario());
            StatementProducto.setInt(3, producto.getId_producto());
            int resProducto = StatementProducto.executeUpdate();
            
            return resProducto > 0;   
        }catch (SQLException e) {
        }
        return false;
    }
    
    public boolean eliminarProducto(Connection conexion, int idProducto) throws SQLException {

        try {
            PreparedStatement StatementProducto = conexion.prepareStatement("DELETE FROM tb_producto WHERE id_producto = ?");
            StatementProducto.setInt(1, idProducto);
            StatementProducto.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @return the producto
     */
    public ArrayList<Producto> getProducto(Connection conexion) throws SQLException {
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM tb_producto");
            ResultSet resultado = consulta.executeQuery();
            
            while (resultado.next()){
                int productoId = resultado.getInt(1);
                String productoName = resultado.getString(2);
                String productoReferencia = resultado.getString(3);
                int precioProducto = resultado.getInt(4);
                int pesoProducto = resultado.getInt(5);
                String categoriaProducto = resultado.getString(6);
                int inventarioProducto = resultado.getInt(7);
                String fechaCreacionProducto = resultado.getString(8);
                long diasInventarioProducto = resultado.getLong(9);
                
                Producto producto2 = new Producto(productoId, productoName, productoReferencia, precioProducto, pesoProducto, categoriaProducto, inventarioProducto, fechaCreacionProducto, diasInventarioProducto);
                productos.add(producto2);
            }
        }catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
   
        return productos;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }

    
    
    
    
    
    
    
}