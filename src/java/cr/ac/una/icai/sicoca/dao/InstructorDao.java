/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Empleado;
import cr.ac.una.icai.sicoca.clases.Instructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class InstructorDao {

    private Conexion con;
    private PreparedStatement sentencia;

    public InstructorDao() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public Integer insert(Instructor obj) {

        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("insert into sicoca.instructor"
                        + " values(?,?,?,?,?,?)");
                this.sentencia.setInt(1, obj.getIdInstructor());
                this.sentencia.setString(2, obj.getNombre());
                this.sentencia.setString(3, obj.getGradoAcademico());
                this.sentencia.setInt(4, obj.getAnnosExperiencia());
                this.sentencia.setString(5, obj.getTelefono());
                this.sentencia.setString(6, obj.getCorreo());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; // retorna 0 cuando todo esta bien
            } else {
                return 1; // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                return 3;
            } else {
                return 2; // retorna 2 cuando cae al catch
            }
        }
    }

    public Integer delete(Instructor obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("delete from sicoca.instructor where idInstructor = ?");
                this.sentencia.setInt(1, obj.getIdInstructor());
                int i = this.sentencia.executeUpdate();
                this.con.desconectarse();
                if (i == 0) {
                    return 3;
                } else {
                    return 0; // retorna 0 cuando todo esta bien
                }
            } else {
                return 1; // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return 2; // retorna 2 cuando cae al catch
        }
    }

    public Integer update(Instructor obj) {
        try {

            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("update sicoca.instructor"
                        + " set nombre = ?, gradoAcademico=?, annosExperiencia=?,"
                        + " telefono=?, correo=?"
                        + " where idInstructor = ?");
                this.sentencia.setString(1, obj.getNombre());
                this.sentencia.setString(2, obj.getGradoAcademico());
                this.sentencia.setInt(3, obj.getAnnosExperiencia());
                this.sentencia.setString(4, obj.getTelefono());
                this.sentencia.setString(5, obj.getCorreo());
                this.sentencia.setInt(6, obj.getIdInstructor());
                int i = this.sentencia.executeUpdate();
                this.con.desconectarse();
                if (i == 0) {
                    return 3;
                }
                return 0; // retorna 0 cuando todo esta bien
            } else {
                return 1; // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return 2; // retorna 2 cuando cae al catch
        }
    }

    public Instructor getById(Integer id) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.instructor where idInstructor = ?");
                this.sentencia.setInt(1, id);
                ResultSet result = this.sentencia.executeQuery();

                Instructor obj = new Instructor();
                while (result.next()) {
                    obj.setIdInstructor(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setGradoAcademico(result.getString("gradoAcademico"));
                    obj.setAnnosExperiencia(result.getInt(4));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));

                }
                this.con.desconectarse();
                return obj; // retorna 0 cuando todo esta bien
            } else {

                return new Instructor(); // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return new Instructor(); // retorna 2 cuando cae al catch
        }
    }

    public List<Instructor> getByName(String name) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.instructor where nombre like ?");
                this.sentencia.setString(1, "%" + name + "%");
                ResultSet result = this.sentencia.executeQuery();

                List<Instructor> list = new ArrayList<>();
                while (result.next()) {
                    Instructor obj = new Instructor();
                    obj.setIdInstructor(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setGradoAcademico(result.getString("gradoAcademico"));
                    obj.setAnnosExperiencia(result.getInt(4));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));
                    list.add(obj);
                }
                this.con.desconectarse();
                return list; // retorna 0 cuando todo esta bien
            } else {
                return new ArrayList<>(); // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return new ArrayList<>();  // retorna 2 cuando cae al catch
        }
    }

    public List<Instructor> getAll() {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.instructor");
                ResultSet result = this.sentencia.executeQuery();

                List<Instructor> list = new ArrayList<>();

                while (result.next()) {
                    Instructor obj = new Instructor();
                    obj.setIdInstructor(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setGradoAcademico(result.getString("gradoAcademico"));
                    obj.setAnnosExperiencia(result.getInt(4));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));
                    list.add(obj);
                }
                this.con.desconectarse();
                return list; // retorna 0 cuando todo esta bien
            } else {
                return null; // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return null; // retorna 2 cuando cae al catch
        }
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }
}
