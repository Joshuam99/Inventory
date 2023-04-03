/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Helpers.Helpers;
import Model.Producto;
import Model.ProductoDAO;
import Model.Proveedor;
import Model.ProveedorDAO;
import Model.Usuario;
import View.InventarioDeProductos;
import View.frmpaginaprincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JTable;

public class ControllerProducto implements ActionListener {

    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    InventarioDeProductos vistaProductos = new InventarioDeProductos();
    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    Helpers helper = new Helpers();
    Usuario usuario = new Usuario();

    public ControllerProducto(InventarioDeProductos frm) {
        this.vistaProductos = frm;
        this.vistaProductos.btnBuscar.addActionListener(this);
        this.vistaProductos.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProductos.btnBuscar) {

            cargarProveedorPorId();
        }

        if (e.getSource() == vistaProductos.btnGuardar) {

            if (vistaProductos.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar una descripcion");
            } else if (vistaProductos.txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar un precio");
            } else if ((Integer) vistaProductos.txtUnidades.getValue() <= 0) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar una cantidad");
            } else if (vistaProductos.jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar la fecha de caducidad");
            } else if (vistaProductos.txtIdProveedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe selecionar un proveedor");
            } else {
                agregarProducto();
            }

        }
        if (e.getSource() == vistaProductos.btnBuscar) {

            filtrarTablaProductos(vistaProductos.jTable, vistaProductos.jComboBox.getSelectedItem().toString(), vistaProductos.txtBuscar2.getText());
        }

    }

    public void cargarProveedorPorId() {

        String idProveedor = JOptionPane.showInputDialog("Ingrese el id del proveedor");
        proveedor = proveedorDAO.cargarProveedorPorId(Integer.parseInt(idProveedor));

        if (proveedor.getNombreProveedor() == null) {
            JOptionPane.showMessageDialog(vistaProductos, "No se encontro ningun registro");
            vistaProductos.txtIdProveedor.setText("");
            vistaProductos.txtnombreProveedor.setText("");
        } else {
            vistaProductos.txtIdProveedor.setText(String.valueOf(proveedor.getIdProveedor()));
            vistaProductos.txtnombreProveedor.setText(proveedor.getNombreProveedor());
        }

    }

    public void agregarProducto() {
        String descripcion = vistaProductos.txtDescripcion.getText();
        int unidades = (int) vistaProductos.txtUnidades.getValue();
        String categoria = vistaProductos.jComboBox.getSelectedItem().toString();
        double precio = Double.valueOf(vistaProductos.txtPrecio.getText());
        boolean activo = vistaProductos.jcbActivo.isSelected();
        Date fechaCaducidad = vistaProductos.jDateChooser1.getDate();
        int idProveedor = Integer.valueOf(vistaProductos.txtIdProveedor.getText());
        int idUsuario = Integer.valueOf(frmpaginaprincipal.IdUsuariolbl.getText());

        producto.setDescripcion(descripcion);
        producto.setUnidades(unidades);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        //si el producto esta activo o no
        if (activo == true) {
            producto.setActivo(1);
        } else {
            producto.setActivo(0);
        }
        producto.setFechaCaducidad(helper.convertDate(fechaCaducidad));
        proveedor.setIdProveedor(idProveedor);
        producto.setProveedor(proveedor);

        usuario.setIdUsuario(idUsuario);
        producto.setUsuario(usuario);

        int r = productoDAO.agregarProducto(producto);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaProductos, "Producto Registrado");
        } else {
            JOptionPane.showMessageDialog(vistaProductos, "Producto NO Registrado");
        }
    }

    public void filtrarTablaProductos(JTable table, String criterio, String filtro) {

        productoDAO.filtrarTablaProductos(table, criterio, filtro);
    }

}
