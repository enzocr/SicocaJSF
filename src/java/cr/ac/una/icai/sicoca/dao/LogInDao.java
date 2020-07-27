/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.dao;

import cr.ac.una.icai.sicoca.clases.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LogInDao {

    private Conexion conn;
    private PreparedStatement sentence;

    public LogInDao() {
        this.conn = new Conexion();
        this.sentence = null;
    }

    public int userExists(Usuario user) {

        try {

            Usuario u = null;

            if (getConn().conectarse()) {

                setSentence(conn.getCon().prepareStatement("SELECT *"
                        + " FROM sicoca.usuario "
                        + "WHERE nombreUsuario = ?"));
                getSentence().setString(1, user.getNombreUsuario());
                ResultSet result = getSentence().executeQuery();
                while (result.next()) {
                    u = new Usuario();
                    u.setNombreUsuario(result.getString("nombreUsuario"));
                    u.setPassword(result.getString("password"));

                }
                getConn().desconectarse();
                if (u != null) {
                    if (user.getPassword().equals(u.getPassword())) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return 3;
                }

            } else {
                return 4;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 5;
        }

    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }

    public PreparedStatement getSentence() {
        return sentence;
    }

    public void setSentence(PreparedStatement sentence) {
        this.sentence = sentence;
    }

}
