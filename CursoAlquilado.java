
package alquilercursos;

import java.sql.Date;

/**
 *
 * @author elian
 */
public class CursoAlquilado {
    
    private String Dni;
    private int id;
    private int descuento;
    private int hora;
    private double precio;
    private String fecha;

    public CursoAlquilado(String Dni, int id, int descuento, int hora, double precio, String fecha) {
        this.Dni = Dni;
        this.id = id;
        this.descuento = descuento;
        this.hora = hora;
        this.precio = precio;
        this.fecha = fecha;
    }


    

    public CursoAlquilado() {
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CursoAlquilado{" + "Dni=" + Dni + ", id=" + id + ", descuento=" + descuento + ", hora=" + hora + ", precio=" + precio + ", fecha=" + fecha + '}';
    }  
}
