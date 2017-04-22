/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rpinto
 */
public class Conn {

    public String BASEDATOS = "presupuesto";
    public String SERVIDOR;
    public String USUARIO = "";
    public String IP = "localhost";
    public String CONTRASENA = "Passw0rd";
    public String PUERTO = "";
    public Connection con;

    public Conn() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SERVIDOR = "jdbc:sqlserver://" + IP + ":" + PUERTO + ";" + "databaseName=" + BASEDATOS + ";user=" + USUARIO + ";password=" + CONTRASENA + ";";
            con = (Connection) DriverManager.getConnection(SERVIDOR, USUARIO, CONTRASENA);
        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base de datos");
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Conn(String usuario, String contrasena, String direccion, String puerto) {
        try {
            PUERTO = puerto;
            IP = direccion;
            USUARIO = usuario;
            CONTRASENA = contrasena;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SERVIDOR = "jdbc:sqlserver://" + IP + ":" + PUERTO + ";" + "databaseName=" + BASEDATOS + ";user=" + USUARIO + ";password=" + CONTRASENA + ";";
            con = (Connection) DriverManager.getConnection(SERVIDOR, USUARIO, CONTRASENA);
            con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet consultar(String sql) throws SQLException {
        PreparedStatement rs = con.prepareStatement(sql);
        return rs.executeQuery();
    }

    public void manipular(String sql) throws SQLException {
        PreparedStatement rs = con.prepareStatement(sql);
        rs.executeUpdate();
    }

    public void commit() throws SQLException {
        con.commit();
    }

    public void transaccion() throws SQLException {
        con.setAutoCommit(false);
    }

    public void rollback() {
        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBASEDATOS() {
        return BASEDATOS;
    }

    public void setBASEDATOS(String BASEDATOS) {
        this.BASEDATOS = BASEDATOS;
    }

    public String getSERVIDOR() {
        return SERVIDOR;
    }

    public void setSERVIDOR(String SERVIDOR) {
        this.SERVIDOR = SERVIDOR;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public String getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(String PUERTO) {
        this.PUERTO = PUERTO;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
