package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Usuarios;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControllerUsuario implements ActionListener {

    //paso 1 hacer que la clase implete de ActionListener    
    //paso 2 hacer el metodo contructor
    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuarios vistaUsuarios = new Usuarios();

    public ControllerUsuario(Usuarios frm) {
        this.vistaUsuarios = frm;
        this.vistaUsuarios.btnGuardar.addActionListener(this);
        this.vistaUsuarios.btnEditar.addActionListener(this);
        this.vistaUsuarios.btnBuscar.addActionListener(this);
        this.vistaUsuarios.btnRefrescar.addActionListener(this);
        this.vistaUsuarios.btnCancelar.addActionListener(this);
        this.vistaUsuarios.btnEliminar.addActionListener(this);
    }

    //crear metodo action performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaUsuarios.btnGuardar) {

            if (vistaUsuarios.txtNombreUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un nombre");
            } else if (vistaUsuarios.txtNombreUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un nombre de usuario");
            } else if (vistaUsuarios.txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un password");
            } else if (vistaUsuarios.txtConfirmar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe confirmar el password");
            } else if (!vistaUsuarios.txtConfirmar.getText().equals(vistaUsuarios.txtPassword.getText())) {
                JOptionPane.showMessageDialog(vistaUsuarios, "El password y la confirmacion no son iguales");
            } else {
                agregarUsuario();
            }
        }

        if (e.getSource() == vistaUsuarios.btnEditar) {

            if (vistaUsuarios.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe selecionar un registro en la tabla");
            } else if (vistaUsuarios.txtNombreUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un nombre");
            } else if (vistaUsuarios.txtNombreUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un nombre de usuario");
            } else if (vistaUsuarios.txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un password");
            } else if (vistaUsuarios.txtConfirmar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe confirmar el password");
            } else if (!vistaUsuarios.txtConfirmar.getText().equals(vistaUsuarios.txtPassword.getText())) {
                JOptionPane.showMessageDialog(vistaUsuarios, "El password y la confirmacion no son iguales");
            } else {
                actualizarUsuario();
            }
        }

        if (e.getSource() == vistaUsuarios.btnBuscar) {

            filtrarTablaPorNombre(vistaUsuarios.jTable1, vistaUsuarios.txtBuscarxNom.getText());
        }

        if (e.getSource() == vistaUsuarios.btnRefrescar) {

            filtrarTablaPorNombre(vistaUsuarios.jTable1, "");
        }

        if (e.getSource() == vistaUsuarios.btnCancelar) {
            limpiarCampos();
        }
        
         if (e.getSource() == vistaUsuarios.btnEliminar) {
          eliminar();
        }

    }

    //esta funcion permite agregar un usuario
    public void agregarUsuario() {

        String nombre = vistaUsuarios.txtNombre.getText();
        String nombreUsuario = vistaUsuarios.txtNombreUsuario.getText();
        String password = vistaUsuarios.txtPassword.getText();
        String tipoUsuario = vistaUsuarios.jComboBox1.getSelectedItem().toString();
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setTipoUsuario(tipoUsuario);
        int r = usuarioDAO.agregarUsuario(usuario);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario agregado correctamente");
            filtrarTablaPorNombre(vistaUsuarios.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "El usuario no se agrego correctamente");
        }

    }

    public void actualizarUsuario() {

        int idUsuario = Integer.valueOf(vistaUsuarios.txtID.getText());
        String nombre = vistaUsuarios.txtNombre.getText();
        String nombreUsuario = vistaUsuarios.txtNombreUsuario.getText();
        String password = vistaUsuarios.txtPassword.getText();
        String tipoUsuario = vistaUsuarios.jComboBox1.getSelectedItem().toString();

        usuario.setIdUsuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setTipoUsuario(tipoUsuario);

        int r = usuarioDAO.actualizarUsuario(usuario);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario actualizado correctamente");
            filtrarTablaPorNombre(vistaUsuarios.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario NO actualizado correctamente");
        }
    }

    public void filtrarTablaPorNombre(JTable tabla, String filtro) {
        usuarioDAO.filtrarTablaPorNombre(tabla, filtro);
    }

    public void limpiarCampos() {

        vistaUsuarios.txtID.setText("");
        vistaUsuarios.txtNombre.setText("");
        vistaUsuarios.txtNombreUsuario.setText("");
        vistaUsuarios.txtPassword.setText("");
        vistaUsuarios.txtConfirmar.setText("");
        vistaUsuarios.jComboBox1.setSelectedIndex(0);
    }

    public void iniciar() {

        limpiarCampos();
        filtrarTablaPorNombre(vistaUsuarios.jTable1, "");
    }

    public void eliminar() {
        int r = 0;
        int fila = vistaUsuarios.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un registro de la tabla");
        } else {
            int idUsuario = Integer.valueOf(vistaUsuarios.jTable1.getValueAt(fila, 0).toString());
            r = usuarioDAO.eliminarUsuario(idUsuario);
        }
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario elimanado");
            filtrarTablaPorNombre(vistaUsuarios.jTable1, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario No eliminado");
        }

    }
}
