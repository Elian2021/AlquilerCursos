
package alquilercursos;


public class AlquilarCurso {
    
    private Cliente Dni;
    private Curso id;
    private int descuento;
    private int horas;
    private double precio;
    private String fecha;

    public AlquilarCurso() {
    }

    
    
    
    public AlquilarCurso(Cliente Dni, Curso id, int descuento, int horas, double precio, String fecha) {
        this.Dni = Dni;
        this.id = id;
        this.descuento = descuento;
        this.horas = horas;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Cliente getDni() {
        return Dni;
    }

    public void setDni(Cliente Dni) {
        this.Dni = Dni;
    }

    public Curso getId() {
        return id;
    }

    public void setId(Curso id) {
        this.id = id;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
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
        return "AlquilarCurso{" + "Dni=" + Dni + ", id=" + id + ", descuento=" + descuento + ", horas=" + horas + ", precio=" + precio + ", fecha=" + fecha + '}';
    }
    
    
    
    

}
