/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProveedorDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con;

    Conexion conectar = new Conexion();

    public int agregarProveedor(Proveedor proveedor) {

        int r = 0;
        String sql = "INSERT INTO proveedores (nombreProveedor,direccion,nombreEmpresa,telefono,email) VALUES (?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getNombreEmpresa());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getEmail());
            r = ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return r;

    }
    
    public int actualizarProveedor(Proveedor proveedor){
    int r = 0;
    String sql = "UPDATE proveedores SET nombreProveedor = ?, direccion = ?, nombreEmpresa = ? , telefono= ? , email= ? WHERE idProveedor =?";
    
        try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, proveedor.getNombreProveedor());
        ps.setString(2, proveedor.getDireccion());
        ps.setString(3, proveedor.getNombreEmpresa());
        ps.setString(4, proveedor.getTelefono());
        ps.setString(5, proveedor.getEmail());
        ps.setInt(6, proveedor.getIdProveedor());
        r = ps.executeUpdate();
        } catch (Exception ex) {
          System.err.println(ex.toString());   
        }
    return r;
    
    }
    
    public int eliminarProveedor (int id){
    
    int r = 0;
    String sql = "DELETE FROM proveedores WHERE idProveedor ="+id;
    
        try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        r = ps.executeUpdate();
            
        } catch (Exception ex) {
            
        System.err.println(ex.toString());    
        }
        return r;
    }
    
    
    public void filtrarTablaProveedor (JTable table, String filtro){
    
   String header [] = {"ID","Proveedor","Direccion","Empresa","Telefono","Email"};
   String registros [] = new String [6];
   DefaultTableModel model = new DefaultTableModel(null, header);
   String sql = "select * from proveedores where nombreProveedor like '%"+filtro+"%' ";
   
        try {
        con = conectar.getConnection();
        ps = con.prepareCall(sql);
        rs = ps.executeQuery();
        
            while (rs.next()) {
            registros [0] = rs.getString("idProveedor");
            registros [1] = rs.getString("nombreProveedor");
            registros [2] = rs.getString("direccion");
            registros [3] = rs.getString("nombreEmpresa");
            registros [4] = rs.getString("telefono");
            registros [5] = rs.getString("email");
            model.addRow(registros);
            }
          table.setModel(model);
   
        } catch (Exception ex) {
        System.err.println(ex.toString());    
            
        }
   }

    
  public Proveedor cargarProveedorPorId(int idProveedor){
  
  Proveedor proveedor = new Proveedor();
  String sql = "select idProveedor, nombreProveedor from proveedores where idProveedor ="+idProveedor;
      try {
      con = conectar.getConnection();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
          while (rs.next()) {
            proveedor.setIdProveedor(rs.getInt("idProveedor"));
            proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
              
          }
      } catch (Exception ex) {
           System.err.println(ex.toString());  
      }
  return proveedor;    
  }  
    
    
}
