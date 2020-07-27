package cr.ac.una.icai.sicoca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private Connection con;

    public Conexion() {
        this.con = null;
    }

    public boolean conectarse() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "localhost";
            int port = 3306;
            String user = "root";
            String pass = "root";
            String db = "sicoca";
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass + "&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

            this.con = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean desconectarse() {
        try {
            this.con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
