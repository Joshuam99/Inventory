 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Helpers.Helpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProductoDAO {
    
    
PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con;
    Helpers helper = new Helpers();

    Conexion conectar = new Conexion();   
    
    
    public int agregarProducto (Producto product){
    int r = 0;
    String sql = "insert into productos (descripcion, unidades, categoria, precio, activo, fechaCaducidad, idProveedor, idUsuario) values (?,?,?,?,?,?,?,?)";
    
        try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, product.getDescripcion());
        ps.setInt(2, product.getUnidades());
        ps.setString(3, product.getCategoria());
        ps.setDouble(4, product.getPrecio());
        ps.setInt(5, product.getActivo());
        ps.setDate(6, product.getFechaCaducidad());
        ps.setInt(7, product.getProveedor().getIdProveedor());
        ps.setInt(8, product.getUsuario().getIdUsuario());
        r= ps.executeUpdate();
        } catch (Exception ex) {
             System.err.println(ex.toString());
        }
       return r;
    }
    
   public void filtrarTablaProductos(JTable table, String criterio, String filtro) {
        String[] titulos = {"ID PRODUCTO", "DESCRIPCION", "UNIDADES", "CATEGORIA", "PRECIO", "ACTIVO", "FECHA DE CADUCIDAD", "ID PROVEEDOR", "NOMBRE", "USUARIO"};
        String[] registros = new String[10];
        String sql = "SELECT productos.idProducto, productos.descripcion, productos.unidades, productos.categoria, productos.precio, productos.activo, productos.fechaCaducidad, "                + "proveedores.idProveedor, proveedores.nombreProveedor, usuarios.nombreUsuario "                + "FROM ((productos INNER JOIN proveedores ON productos.idProveedor = proveedores.idProveedor) "                + "INNER JOIN usuarios ON productos.idUsuario = usuarios.idUsuario) WHERE " + criterio + " LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idProducto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("unidades");
                registros[3] = rs.getString("categoria");
                registros[4] = rs.getString("precio");
                registros[5] = rs.getString("activo");
                registros[6] = rs.getString("fechaCaducidad");
                registros[7] = rs.getString("idProveedor");
                registros[8] = rs.getString("nombreProveedor");
                registros[9] = rs.getString("nombreUsuario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
    
}
