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
public class Competicion extends Curso{
    
    protected String carnet;

    public Competicion(String carnet, int id, String Nombre, double una_hora, String carnet_federacion) {
        super(id, Nombre, una_hora, carnet_federacion);
        this.carnet = carnet;
    }

    public Competicion(String carnet) {
        this.carnet = carnet;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "Competicion{" + "carnet=" + carnet + '}';
    }
    
   
    
    
    
}
