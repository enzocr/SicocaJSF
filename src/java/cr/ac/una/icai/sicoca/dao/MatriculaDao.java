/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Area;
import cr.ac.una.icai.sicoca.clases.Curso;
import cr.ac.una.icai.sicoca.clases.Matricula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class MatriculaDao {

    private Conexion con;
    private PreparedStatement sentencia;

    public MatriculaDao() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public Integer insert(Matricula obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("insert into sicoca.matricula values (?,?,?)");
                this.sentencia.setInt(1, obj.getIdCurso());
                this.sentencia.setInt(2, obj.getCedula());
                this.sentencia.setDouble(3, obj.getNota());
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

    public Integer update(Matricula obj) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("update sicoca.matricula"
                        + " set nota = ?"
                        + " where idCurso=? and cedula = ? ");
                this.sentencia.setDouble(1, obj.getNota());
                this.sentencia.setInt(2, obj.getIdCurso());
                this.sentencia.setInt(3, obj.getCedula());
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

    public List<Matricula> getByCourse(Matricula matricula) {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.matricula where idCurso=?");
                this.sentencia.setInt(1, matricula.getIdCurso());
                ResultSet result = this.sentencia.executeQuery();

                List<Matricula> list = new ArrayList<>();
                while (result.next()) {
                    Matricula obj = new Matricula();
                    obj.setIdCurso(result.getInt(1));
                    obj.setCedula(result.getInt(2));
                    obj.setNota(result.getDouble(3));
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

    public List<Matricula> getAll() {
        try {
            if (this.con.conectarse()) {
                this.sentencia = this.con.getCon().prepareStatement("select * from sicoca.matricula");
                ResultSet result = this.sentencia.executeQuery();

                List<Matricula> list = new ArrayList<>();

                while (result.next()) {
                    Matricula obj = new Matricula();
                    obj.setIdCurso(result.getInt(1));
                    obj.setCedula(result.getInt(2));
                    obj.setNota(result.getDouble(3));
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
}
