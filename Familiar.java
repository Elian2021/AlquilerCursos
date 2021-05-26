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
public class Familiar extends Curso{
    
    protected int tipo_carnet;

    public Familiar(int tipo_carnet, int id, String Nombre, double una_hora, String carnet_federacion) {
        super(id, Nombre, una_hora, carnet_federacion);
        this.tipo_carnet = tipo_carnet;
    }

    public Familiar(int tipo_carnet) {
        this.tipo_carnet = tipo_carnet;
    }

    public int getTipo_carnet() {
        return tipo_carnet;
    }

    public void setTipo_carnet(int tipo_carnet) {
        this.tipo_carnet = tipo_carnet;
    }

    @Override
    public String toString() {
        return "Familiar{" + "tipo_carnet=" + tipo_carnet + '}';
    }

    
      
    
    
}
