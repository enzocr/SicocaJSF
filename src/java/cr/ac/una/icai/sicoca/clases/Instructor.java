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
public class Instructor {

    private Integer idInstructor;
    private String nombre;
    private String gradoAcademico;
    private Integer annosExperiencia;
    private String telefono;
    private String correo;

    public Instructor() {

    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public Integer getAnnosExperiencia() {
        return annosExperiencia;
    }

    public void setAnnosExperiencia(Integer annosExperiencia) {
        this.annosExperiencia = annosExperiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "***Instructor***\n"
                + "ID: " + idInstructor + "\n"
                + "Nombre: " + nombre + "\n"
                + "Grado Academico: " + gradoAcademico + "\n"
                + "Años de experiencia: " + annosExperiencia + "\n"
                + "Telefono: " + telefono + "\n"
                + "Correo: " + correo;
    }

}
