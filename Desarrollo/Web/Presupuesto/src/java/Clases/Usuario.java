/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Seguridad.Seguridad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rpinto
 */
@Path("usuario")
public class Usuario {

    private int user_id;
    private String nombreCompleto;
    private String nombreUsuario;
    private String password;
    private String email;
    private String fecha;

    private Conn con;

    public Usuario(Conn con) {
        this.con = con;
    }

    public Usuario(int user_id, String nombreCompleto, String nombreUsuario, String password, String email, String fecha) {
        this.user_id = user_id;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.email = email;
        this.fecha = fecha;
    }

    public void setCon(Conn con) {
        this.con = con;
    }

    public List<Usuario> cargar(ResultSet rs) {
        List<Usuario> lista = new ArrayList();
        try {
            while (rs.next()) {
                Usuario aux = new Usuario(con);
                aux.user_id(rs.getInt("user_id"));
                aux.setnombreCompleto(rs.getString("nombreCompleto"));
                aux.setnombreUsuario(rs.getString("nombreUsuario"));
                aux.setpassword(rs.getString("password"));
                aux.setEmail(rs.getString("email"));
                aux.setFecha(rs.getString("fecha"));
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public List<Usuario> todo() throws SQLException {
        String consulta = "select * from Usuario";
        return cargar(con.consultar(consulta));
    }

    public Usuario buscarXid(int id) throws SQLException {
        String consulta = "select * from Usuario where usuarioId=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Usuario> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public Usuario loguear(String cuenta, String password) throws SQLException {
        Seguridad sha1= new Seguridad();
        String pass= sha1.SHA1(password);
        String consulta = "select * from Usuario where nombreUsuario='" + cuenta + "' and password = '" + pass + "'";
        ResultSet rs = con.consultar(consulta);
        List<Usuario> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public void modificar() throws SQLException {
        String consulta = "update Usuario set usuarioId = " + user_id + ", nombreCompleto = '" + nombreCompleto + "', nombreUsuario = '" + nombreUsuario + "', password = '" + password + "', email = '" + email + "' where usuarioId=" + user_id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {
        Seguridad sha1= new Seguridad();
        String pass= sha1.SHA1(password);
        
        
        String consulta = "insert into  presupuesto.dbo.Usuario(nombreCompleto, nombreUsuario, password, email, fecha) values('"+ nombreCompleto + "','" + nombreUsuario + "','" + pass + "','" + email + "','" + fecha + "')";

        con.manipular(consulta);
        
    }

    public int getusuarioId() {
        return user_id;
    }

    public void user_id(int user_id) {
        this.user_id = user_id;
    }

    public String getnombreCompleto() {
        return nombreCompleto;
    }

    public void setnombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}