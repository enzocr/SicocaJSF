/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class CursoDao {

    private Conexion con;
    private PreparedStatement sentencia;

    public CursoDao() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public Integer insert(Curso obj) {

        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("insert into sicoca.curso "
                        + "values (?,?,?,?,?,?,?)");
                this.sentencia.setInt(1, obj.getIdCurso());
                this.sentencia.setString(2, obj.getNombre());
                this.sentencia.setInt(3, obj.getHorasDuracion());
                this.sentencia.setTimestamp(4, new java.sql.Timestamp(obj.getFechaInicio().getTime()));
                this.sentencia.setTimestamp(5, new java.sql.Timestamp(obj.getFechaFinalizacion().getTime()));
                this.sentencia.setDouble(6, obj.getPrecio());
                this.sentencia.setInt(7, obj.getIdInstructor());
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

    public Integer delete(Curso obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("delete from sicoca.curso where idCurso = ?");
                this.sentencia.setInt(1, obj.getIdCurso());
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

    public Integer update(Curso obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("update sicoca.curso"
                        + " set nombre = ?, horasDuracion = ?, fechaInicio=?, fechaFinalizacion=?,"
                        + " precio=?, idInstructor=?"
                        + "  where idCurso = ?");
                this.sentencia.setString(1, obj.getNombre());
                this.sentencia.setInt(2, obj.getHorasDuracion());
                this.sentencia.setTimestamp(3, new java.sql.Timestamp(obj.getFechaInicio().getTime()));
                this.sentencia.setTimestamp(4, new java.sql.Timestamp(obj.getFechaFinalizacion().getTime()));
                this.sentencia.setDouble(5, obj.getPrecio());
                this.sentencia.setInt(6, obj.getIdInstructor());
                this.sentencia.setInt(7, obj.getIdCurso());
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

    /*
         private Integer idCurso;
    private String nombre;
    private Integer horasDuracion;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private Integer precio;
    private Integer idInstructor;
     */
    public Curso getById(Integer id) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.curso where idCurso = ?");
                this.sentencia.setInt(1, id);
                ResultSet result = this.sentencia.executeQuery();

                Curso obj = new Curso();
                while (result.next()) {
                    obj.setIdCurso(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setHorasDuracion(result.getInt(3));
                    obj.setFechaInicio(result.getDate("fechaInicio"));
                    obj.setFechaFinalizacion(result.getDate("fechaFinalizacion"));
                    obj.setPrecio(result.getDouble(6));
                    obj.setIdInstructor(result.getInt(7));

                }
                this.con.desconectarse();
                return obj; // retorna 0 cuando todo esta bien
            } else {

                return new Curso(); // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return new Curso(); // retorna 2 cuando cae al catch
        }
    }

    public List<Curso> getByName(String name) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.curso where nombre like ?");
                this.sentencia.setString(1, "%" + name + "%");
                ResultSet result = this.sentencia.executeQuery();

                List<Curso> list = new ArrayList<>();
                while (result.next()) {
                    Curso obj = new Curso();
                    obj.setIdCurso(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setHorasDuracion(result.getInt(3));
                    obj.setFechaInicio(result.getDate("fechaInicio"));
                    obj.setFechaFinalizacion(result.getDate("fechaFinalizacion"));
                    obj.setPrecio(result.getDouble(6));
                    obj.setIdInstructor(result.getInt(7));
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

    public List<Curso> getAll() {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.curso");
                ResultSet result = this.sentencia.executeQuery();

                List<Curso> list = new ArrayList<>();

                while (result.next()) {
                    Curso obj = new Curso();
                    obj.setIdCurso(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setHorasDuracion(result.getInt(3));
                    obj.setFechaInicio(result.getDate("fechaInicio"));
                    obj.setFechaFinalizacion(result.getDate("fechaFinalizacion"));
                    obj.setPrecio(result.getDouble(6));
                    obj.setIdInstructor(result.getInt(7));
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
