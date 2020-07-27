/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.clases;

/**
 *
 * @author enzoq
 */
public class Area {

    private Integer idArea;
    private String nombre;
    private String ubicacion;

    public Area() {

    }

    public Area(Integer idArea, String nombre, String ubicacion) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
