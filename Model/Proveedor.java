/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Proveedor {

    private int idProveedor;
    private String nombreProveedor;
    private String direccion;
    private String nombreEmpresa;
    private String telefono;
    private String email;

    public Proveedor(int idProveedor, String nombreProveedor, String direccion, String nombreEmpresa, String telefono, String email) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccion = direccion;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.email = email;
    }

    public Proveedor() {
       
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

   

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
