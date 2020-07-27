/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.clases;

import java.util.Date;

/**
 *
 * @author enzoq
 */
public class Curso {

    private Integer idCurso;
    private String nombre;
    private Integer horasDuracion;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private Double precio;
    private Integer idInstructor;

    public Curso() {

    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHorasDuracion() {
        return horasDuracion;
    }

    public void setHorasDuracion(Integer horasDuracion) {
        this.horasDuracion = horasDuracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    @Override
    public String toString() {
        return "***Curso***\n"
                + "ID: " + idCurso + "\n"
                + "Nombre: " + nombre + "\n"
                + "Horas duración: " + horasDuracion + "\n"
                + "Fecha de inicio:" + fechaInicio + "\n"
                + "Fecha de finalización: " + fechaFinalizacion + "\n"
                + "Precio: " + precio;
    }

}
