/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Conn;
import Clases.Usuario;
import Seguridad.EnvioMail;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rpinto
 */
@Path("/controladorprincipal")
public class Controladorprincipal {

    @GET
    @Path("/nuevoUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerProductos(@QueryParam("nombreCompleto") String nombreCompleto, @QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("password") String password, @QueryParam("email") String email) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        Calendar calendar = new GregorianCalendar();
        Date today = calendar.getTime();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formato.format(today);
        try {
            Usuario usuario = new Usuario(0, nombreCompleto, nombreUsuario, password, email, fechaActual);
            usuario.setCon(con);
            usuario.insertar();
            respuesta = new SimpleResponse(true, "El usuario se inserto correctamente");
        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se inserto correctamente el usuario");
        }
        return respuesta;
    }

    @GET
    @Path("/loggear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse Login(@QueryParam("cuenta") String cuenta, @QueryParam("password") String password) {
        SimpleResponse respuesta;
        Conn con = new Conn();
        Usuario usuario = new Usuario(con);
        try {
            usuario = usuario.loguear(cuenta, password);
            if (usuario == null) {
                respuesta = new SimpleResponse(true, "-1");
            } else {
                respuesta = new SimpleResponse(true, usuario.getusuarioId() + "," + usuario.getnombreCompleto() + "," + usuario.getnombreUsuario());
            }
        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "-1");
        }
        return respuesta;
    }

    //todo esto fue añadido para probar enviar mail, Angel
    @GET
    @Path("/EnvioCorreo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse EnvioCorreo(@QueryParam("email") String email) {
        SimpleResponse respuesta;
        Conn con = new Conn();
        Usuario usuario = new Usuario(con);
        try {
            usuario = usuario.buscarxCorreo(email);
            if (usuario == null) {
                respuesta = new SimpleResponse(true, "-1");
            } else {
                respuesta = new SimpleResponse(true, usuario.getEmail());
                new EnvioMail(email);
            }
        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "-1");
        }
        return respuesta;
    }

}
