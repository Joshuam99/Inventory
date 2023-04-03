/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Proveedor;
import Model.ProveedorDAO;
import Model.UsuarioDAO;
import View.Proveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControllerProveedor implements ActionListener {

    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    Proveedores vistaProveedor = new Proveedores();

    public ControllerProveedor(Proveedores frm) {
        this.vistaProveedor = frm;
        this.vistaProveedor.btnGuardar.addActionListener(this);
        this.vistaProveedor.btnActualizar.addActionListener(this);
        this.vistaProveedor.btnBuscar.addActionListener(this);
        this.vistaProveedor.btnRefrescar.addActionListener(this);
        this.vistaProveedor.btnEliminar.addActionListener(this);
        this.vistaProveedor.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaProveedor.btnGuardar) {

            if (vistaProveedor.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar un nombre");
            } else if (vistaProveedor.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar la direccion");
            } else if (vistaProveedor.txtEmpresa.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe el nombre de la empresa");
            } else if (vistaProveedor.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar el numero de telefono");
            } else if (vistaProveedor.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar el email");
            } else {
                agregarProveedor();
            }

        }

        if (e.getSource() == vistaProveedor.btnActualizar) {

            if (vistaProveedor.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar un nombre");
            } else if (vistaProveedor.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar la dirrecion");
            } else if (vistaProveedor.txtEmpresa.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar la empresa");
            } else if (vistaProveedor.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar el numero de telefono");
            } else if (vistaProveedor.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProveedor, "Debe ingresar el email");
            } else {
                actualizaProveedor();
            }

        }

        if (e.getSource() == vistaProveedor.btnBuscar) {
            filtrarTablaProveedor(vistaProveedor.jTable1, vistaProveedor.txtBxNombre.getText()
            );
        }

        if (e.getSource() == vistaProveedor.btnRefrescar) {
            iniciar();
        }

        if (e.getSource() == vistaProveedor.btnEliminar) {
            eliminar();
        }

        if (e.getSource() == vistaProveedor.btnCancelar) {

            cancelar();
        }
    }

    public void agregarProveedor() {
        String nombreProveedor = vistaProveedor.txtNombre.getText();
        String direccion = vistaProveedor.txtDireccion.getText();
        String nombreEmpresa = vistaProveedor.txtEmpresa.getText();
        String telefono = vistaProveedor.txtTelefono.getText();
        String email = vistaProveedor.txtEmail.getText();

        proveedor.setNombreProveedor(nombreProveedor);
        proveedor.setDireccion(direccion);
        proveedor.setNombreEmpresa(nombreEmpresa);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        int r = proveedorDAO.agregarProveedor(proveedor);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaProveedor, "Poveedor agregado correctamente");
            filtrarTablaProveedor(vistaProveedor.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaProveedor, "El proveedor no se agrego correctamente");
        }

    }

    public void actualizaProveedor() {

        int idProveedor = Integer.valueOf(vistaProveedor.txtID.getText());
        String nombreProveedor = vistaProveedor.txtNombre.getText();
        String direccion = vistaProveedor.txtDireccion.getText();
        String nombreEmpresa = vistaProveedor.txtEmpresa.getText();
        String telefono = vistaProveedor.txtTelefono.getText();
        String email = vistaProveedor.txtEmail.getText();

        proveedor.setIdProveedor(idProveedor);
        proveedor.setNombreProveedor(nombreProveedor);
        proveedor.setDireccion(direccion);
        proveedor.setNombreEmpresa(nombreEmpresa);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);

        int r = proveedorDAO.actualizarProveedor(proveedor);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaProveedor, "Proveedor actualizado correctamente");
            filtrarTablaProveedor(vistaProveedor.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaProveedor, "Proveedor NO actualizado correctamente");
        }

    }

    public void filtrarTablaProveedor(JTable table, String filtro) {
        proveedorDAO.filtrarTablaProveedor(table, filtro);
    }

    public void limpiarCampos() {
        vistaProveedor.txtID.setText("");
        vistaProveedor.txtNombre.setText("");
        vistaProveedor.txtDireccion.setText("");
        vistaProveedor.txtEmpresa.setText("");
        vistaProveedor.txtEmail.setText("");
        vistaProveedor.txtTelefono.setText("");
        vistaProveedor.txtBxNombre.setText("");
    }

    public void iniciar() {

        limpiarCampos();
        filtrarTablaProveedor(vistaProveedor.jTable1, "");

    }

    public void eliminar() {

        int r = 0;
        int fila = vistaProveedor.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaProveedor, "Debe seleccionar un registro de la tabla");
        } else {
            int idProveedor = Integer.valueOf(vistaProveedor.jTable1.getValueAt(fila, 0).toString());
            r = proveedorDAO.eliminarProveedor(idProveedor);
        }
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaProveedor, "Proveedor elimanado");
            filtrarTablaProveedor(vistaProveedor.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaProveedor, "Proveedor No eliminado");
        }

    }

    public void cancelar() {
        limpiarCampos();
    }

}
