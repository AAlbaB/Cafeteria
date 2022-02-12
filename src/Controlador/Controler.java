/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import cafeteria_konecta.*;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Andrés Alba
 * @Prueba técnica KONECTA
 */
public class Controler implements ActionListener {

    private final Cafeteria cafeteria = new Cafeteria();
    private frmCafeteria vista = new frmCafeteria();

    public Controler(frmCafeteria vista) throws ClassNotFoundException, SQLException {
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscarVenta.addActionListener(this);
        this.vista.btnVender.addActionListener(this);
        agregarProductosaTabla();
        cantidadProductos();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == vista.btnAgregar) {
            agregarProducto();
        }
        if (evt.getSource() == vista.btnBuscar) {
            buscarProducto();
        }
        if (evt.getSource() == vista.btnEditar) {
            editarProducto();
        }
        if (evt.getSource() == vista.btnEliminar) {
            eliminarProducto();
        }
        if (evt.getSource() == vista.btnBuscarVenta) {
            buscarporID();
        }
        if (evt.getSource() == vista.btnVender) {
            venderProducto();
        }
    }

    private void agregarProducto() {
        String nombreProducto = vista.txtNombre.getText();
        String referenciaProducto = vista.txtRefencia.getText();
        String precioProducto = vista.txtPrecio.getText();
        String pesoProducto = vista.txtPeso.getText();
        String categoriaProducto = vista.txtCategoria.getText();
        String inventarioProducto = vista.txtInventario.getText();
        String fechaProducto = vista.txtFechaIngreso.getText();

        if (nombreProducto.equals("") || referenciaProducto.equals("") || precioProducto.equals("") || pesoProducto.equals("") || categoriaProducto.equals("") || inventarioProducto.equals("") || fechaProducto.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingresar todos los campos requeridos");
        } else {
            try {
                boolean dato = existeProducto(nombreProducto);
                if (dato) {
                    JOptionPane.showMessageDialog(null, "Ya existe producto con ese nombre, verificar información");
                } else {
                    boolean fecha = verificarFecha(fechaProducto);
                    if (fecha) {
                        Producto producto = new Producto(nombreProducto, referenciaProducto, Integer.parseInt(precioProducto), Integer.parseInt(pesoProducto), categoriaProducto, Integer.parseInt(inventarioProducto), fechaProducto);
                        try {
                            int resultado = cafeteria.agregarProducto(conexion.abrir(), producto);
                            if (resultado == 1) {
                                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                                agregarProductosaTabla();
                                limpiarCampos();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al agregar producto");
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese un formato de fecha válido");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void buscarProducto() {
        try {
            String nameProducto = vista.txtBuscar.getText();
            Producto producto = cafeteria.buscarProducto(conexion.abrir(), nameProducto);
            if (producto == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
                limpiarCampos();
            } else {
                vista.txtNombre.setText(producto.getNombre());
                vista.txtRefencia.setText(producto.getReferencia());
                vista.txtCategoria.setText(producto.getCategoria());
                vista.txtDiasInventario.setText(producto.getDiasInventario() + "");
                vista.txtPrecio.setText(producto.getPrecio() + "");
                vista.txtPeso.setText(producto.getPeso() + "");
                vista.txtInventario.setText(producto.getInventario() + "");
                vista.txtFechaIngreso.setText(producto.getFechaCreacion());
                vista.txtIdProducto.setText(producto.getId_producto() + "");
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Ingresar un nombre válido");
        }
    }

    private void buscarporID() {
        try {
            int idProducto = Integer.parseInt(vista.txtbuscarID.getText());
            Producto producto = cafeteria.buscarPorID(conexion.abrir(), idProducto);
            if (producto == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
                limpiarCamposVenta();
            } else {
                vista.txtNombreBuscar.setText(producto.getNombre());
                vista.txtCantidadBuscar.setText(producto.getInventario() + "");
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            limpiarCamposVenta();
            JOptionPane.showMessageDialog(null, "Ingresar un ID válido");
        }
    }

    private void editarProducto() {
        try {
            String nombreProducto = vista.txtNombre.getText();
            String referenciaProducto = vista.txtRefencia.getText();
            int precioProducto = Integer.parseInt(vista.txtPrecio.getText());
            int pesoProducto = Integer.parseInt(vista.txtPeso.getText());
            String categoriaProducto = vista.txtCategoria.getText();
            int inventarioProducto = Integer.parseInt(vista.txtInventario.getText());
            String fechaProducto = vista.txtFechaIngreso.getText();
            int idProducto = Integer.parseInt(vista.txtIdProducto.getText());

            Producto producto = new Producto(idProducto, nombreProducto, referenciaProducto, precioProducto, pesoProducto, categoriaProducto, inventarioProducto, fechaProducto);
            boolean response = cafeteria.editarProducto(conexion.abrir(), producto);
            if (response) {
                agregarProductosaTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(null, "Producto actuliazado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error actualizando producto");
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Debes buscar el producto para editar");
        }
    }

    private void venderProducto() {
        try {
            int idProducto = Integer.parseInt(vista.txtbuscarID.getText());
            String nombreProducto = vista.txtNombreBuscar.getText();
            int cantidadProducto = Integer.parseInt(vista.txtCantidadBuscar.getText());
            int ventaProducto = Integer.parseInt(vista.txtCantidadVenta.getText());
            int inventarioProducto = cantidadProducto - ventaProducto;

            if (inventarioProducto < 0) {
                JOptionPane.showMessageDialog(null, "El inventario no puede ser menor a cero");
                limpiarCamposVenta();
            } else {
                Producto producto = new Producto(idProducto, nombreProducto, inventarioProducto);
                boolean response = cafeteria.hacerVenta(conexion.abrir(), producto);
                if (response) {
                    agregarProductosaTabla();
                    limpiarCamposVenta();
                    JOptionPane.showMessageDialog(null, "Venta éxitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "Error actualizando producto");
                }
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Debes buscar el producto para vender");
        }
    }

    private void eliminarProducto() {
        try {
            int row = vista.ListaProductos.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Error, Debes seleccionar un producto de la tabla");
            } else {
                int resultado = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto?");
                if (resultado == 0) {
                    String nombreProducto = vista.ListaProductos.getValueAt(row, 1).toString();
                    Producto producto = cafeteria.buscarProducto(conexion.abrir(), nombreProducto);
                    int idProducto = producto.getId_producto();

                    boolean response = cafeteria.eliminarProducto(conexion.abrir(), idProducto);
                    if (response) {
                        agregarProductosaTabla();
                        JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error eliminando producto");
                    }
                } else if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "No se elimino el producto");
                }
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Producto> selectProductos() throws ClassNotFoundException, SQLException {
        ArrayList<Producto> productos;
        productos = cafeteria.getProducto(conexion.abrir());
        return productos;
    }

    private boolean existeProducto(String nombreProducto) throws ClassNotFoundException, SQLException {
        short cont = 0;
        while (cont < selectProductos().size()) {
            Producto aux = selectProductos().get(cont);
            if (aux.getNombre().equals(nombreProducto)) {
                return true;
            }
            cont++;
        }
        return false;
    }

    private void agregarProductosaTabla() throws ClassNotFoundException, SQLException {
        String matriz[][] = new String[selectProductos().size()][9];

        for (int i = 0; i < selectProductos().size(); i++) {
            matriz[i][0] = String.valueOf(selectProductos().get(i).getId_producto());
            matriz[i][1] = selectProductos().get(i).getNombre();
            matriz[i][2] = selectProductos().get(i).getReferencia();
            matriz[i][3] = String.valueOf(selectProductos().get(i).getPrecio());
            matriz[i][4] = String.valueOf(selectProductos().get(i).getPeso());
            matriz[i][5] = selectProductos().get(i).getCategoria();
            matriz[i][6] = String.valueOf(selectProductos().get(i).getInventario());
            matriz[i][7] = selectProductos().get(i).getFechaCreacion();
            matriz[i][8] = String.valueOf(selectProductos().get(i).getDiasInventario());
        }
        vista.ListaProductos.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "ID Productos", "Nombre", "Referencia", "Precio", "Peso (kg)", "Categoria", "Inventario", "Fecha Creación", "Días en Inventario"
                }
        ));
    }

    private boolean verificarFecha(String fechaProducto) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fechaProducto, fmt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtRefencia.setText("");
        vista.txtCategoria.setText("");
        vista.txtPrecio.setText("");
        vista.txtPeso.setText("");
        vista.txtInventario.setText("");
        vista.txtFechaIngreso.setText("");
        vista.txtDiasInventario.setText("");
        vista.txtIdProducto.setText("");
        vista.txtBuscar.setText("");
    }

    private void limpiarCamposVenta() {
        vista.txtNombreBuscar.setText("");
        vista.txtbuscarID.setText("");
        vista.txtCantidadVenta.setText("");
        vista.txtCantidadBuscar.setText("");
    }
    
    private void cantidadProductos() throws ClassNotFoundException, SQLException {
        LinkedList<reporteporInventario> reporte = cafeteria.listaInventarioProductos(conexion.abrir());
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (reporteporInventario dato: reporte){
            dataset.setValue(dato.getNombreProducto(), dato.getCantidadInventario());           
        }
        JFreeChart chart = ChartFactory.createPieChart("Inventario de Productos",dataset, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        vista.jpInventarioProductos.setLayout(new java.awt.BorderLayout());  
        vista.jpInventarioProductos.add(panel, BorderLayout.CENTER);         
        vista.jpInventarioProductos.validate();
    }

}
