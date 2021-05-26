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
public class Curso {
    private int id;
    private String Nombre;
    private double una_hora;
    private String carnet_federacion;

    public Curso(int id, String Nombre, double una_hora, String carnet_federacion) {
        this.id = id;
        this.Nombre = Nombre;
        this.una_hora = una_hora;
        this.carnet_federacion = carnet_federacion;
    }

    public Curso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getUna_hora() {
        return una_hora;
    }

    public void setUna_hora(double una_hora) {
        this.una_hora = una_hora;
    }

    public String getCarnet_federacion() {
        return carnet_federacion;
    }

    public void setCarnet_federacion(String carnet_federacion) {
        this.carnet_federacion = carnet_federacion;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", Nombre=" + Nombre + ", una_hora=" + una_hora + ", carnet_federacion=" + carnet_federacion + '}';
    }

   
}
