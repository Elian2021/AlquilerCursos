/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilercursos;

/**
 *
 * @author elian
 */
public class Cliente {
    
    private String Dni;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private int Telefono;
    private String Familiar;
    private String Federacion;

    public Cliente(String Dni, String Nombre, String Apellido, String Correo, int Telefono, String Familiar, String Federacion) {
        this.Dni = Dni;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.Familiar = Familiar;
        this.Federacion = Federacion;
    }

    public Cliente() {
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getFamiliar() {
        return Familiar;
    }

    public void setFamiliar(String Familiar) {
        this.Familiar = Familiar;
    }

    public String getFederacion() {
        return Federacion;
    }

    public void setFederacion(String Federacion) {
        this.Federacion = Federacion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "Dni=" + Dni + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Correo=" + Correo + ", Telefono=" + Telefono + ", Familiar=" + Familiar + ", Federacion=" + Federacion + '}';
    }
    
    
    
    
    
    
}
