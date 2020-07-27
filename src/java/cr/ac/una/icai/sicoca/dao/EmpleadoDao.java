/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class EmpleadoDao {

    private Conexion con;
    private PreparedStatement sentencia;

    public EmpleadoDao() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public Integer insert(Empleado obj) {

        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("insert into sicoca.empleado"
                        + " values(?,?,?,?,?,?,?)");
                this.sentencia.setInt(1, obj.getCedula());
                this.sentencia.setString(2, obj.getNombre());
                this.sentencia.setString(3, obj.getApellido1());
                this.sentencia.setString(4, obj.getApellido2());
                this.sentencia.setString(5, obj.getTelefono());
                this.sentencia.setString(6, obj.getCorreo());
                this.sentencia.setInt(7, obj.getIdArea());
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

    public Integer delete(Empleado obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("delete from sicoca.empleado where cedula = ?");
                this.sentencia.setInt(1, obj.getCedula());
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

    public Integer update(Empleado obj) {
        try {

            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("update sicoca.empleado"
                        + " set nombre = ?, apellido1=?, apellido2=?,"
                        + " telefono=?, correo=?, idArea=?"
                        + " where cedula = ?");
                this.sentencia.setString(1, obj.getNombre());
                this.sentencia.setString(2, obj.getApellido1());
                this.sentencia.setString(3, obj.getApellido2());
                this.sentencia.setString(4, obj.getTelefono());
                this.sentencia.setString(5, obj.getCorreo());
                this.sentencia.setInt(6, obj.getIdArea());
                this.sentencia.setInt(7, obj.getCedula());
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

    public Empleado getById(Integer id) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.empleado where cedula = ?");
                this.sentencia.setInt(1, id);
                ResultSet result = this.sentencia.executeQuery();

                Empleado obj = new Empleado();
                while (result.next()) {
                    obj.setCedula(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setApellido1(result.getString("apellido1"));
                    obj.setApellido2(result.getString("apellido2"));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));
                    obj.setIdArea(result.getInt(7));

                }
                this.con.desconectarse();
                return obj; // retorna 0 cuando todo esta bien
            } else {

                return new Empleado(); // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return new Empleado(); // retorna 2 cuando cae al catch
        }
    }

    public List<Empleado> getByName(String name) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.empleado where nombre like ?");
                this.sentencia.setString(1, "%" + name + "%");
                ResultSet result = this.sentencia.executeQuery();

                List<Empleado> list = new ArrayList<>();
                while (result.next()) {
                    Empleado obj = new Empleado();
                    obj.setCedula(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setApellido1(result.getString("apellido1"));
                    obj.setApellido2(result.getString("apellido2"));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));
                    obj.setIdArea(result.getInt(7));
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

    public List<Empleado> getAll() {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.empleado");
                ResultSet result = this.sentencia.executeQuery();

                List<Empleado> list = new ArrayList<>();

                while (result.next()) {
                    Empleado obj = new Empleado();
                    obj.setCedula(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setApellido1(result.getString("apellido1"));
                    obj.setApellido2(result.getString("apellido2"));
                    obj.setTelefono(result.getString("telefono"));
                    obj.setCorreo(result.getString("correo"));
                    obj.setIdArea(result.getInt(7));
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
