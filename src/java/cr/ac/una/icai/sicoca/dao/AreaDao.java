/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class AreaDao {

    private Conexion con;
    private PreparedStatement sentencia;

    public AreaDao() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public Integer insert(Area obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("insert into sicoca.area values (?,?,?)");
                this.sentencia.setInt(1, obj.getIdArea());
                this.sentencia.setString(2, obj.getNombre());
                this.sentencia.setString(3, obj.getUbicacion());
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

    public Integer delete(Area obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("delete from sicoca.area where idArea = ?");
                this.sentencia.setInt(1, obj.getIdArea());
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

    public Integer update(Area obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("update sicoca.area set nombre = ?, ubicacion = ?"
                        + "  where idArea = ?");
                this.sentencia.setString(1, obj.getNombre());
                this.sentencia.setString(2, obj.getUbicacion());
                this.sentencia.setInt(3, obj.getIdArea());
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

    public Area getById(Integer id) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.area where idArea = ?");
                this.sentencia.setInt(1, id);
                ResultSet result = this.sentencia.executeQuery();

                Area obj = new Area();
                
                while (result.next()) {
                    obj.setIdArea(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setUbicacion(result.getString("ubicacion"));
                }
                this.con.desconectarse();
                return obj; // retorna 0 cuando todo esta bien
            } else {
                
                return new Area(); // retorna 1 cuando no se conecta a la BD
            }

        } catch (SQLException ex) {
            return new Area(); // retorna 2 cuando cae al catch
        }
    }

    public List<Area> getByName(String name) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.area where nombre like ?");
                this.sentencia.setString(1, "%" + name + "%");
                ResultSet result = this.sentencia.executeQuery();

                List<Area> list = new ArrayList<>();
                while (result.next()) {
                    Area obj = new Area();
                    obj.setIdArea(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setUbicacion(result.getString("ubicacion"));
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

    public List<Area> getAll() {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.area");
                ResultSet result = this.sentencia.executeQuery();

                List<Area> list = new ArrayList<>();

                while (result.next()) {
                    Area obj = new Area();
                    obj.setIdArea(result.getInt(1));
                    obj.setNombre(result.getString("nombre"));
                    obj.setUbicacion(result.getString("ubicacion"));
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
