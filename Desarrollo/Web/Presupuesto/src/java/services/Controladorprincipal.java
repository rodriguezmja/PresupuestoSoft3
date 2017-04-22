/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Conn;
import Clases.Usuario;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        String respuesta = "El usuario se inserto correctamente";
        Calendar calendar= new GregorianCalendar();
            Date today=calendar.getTime();
            SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual=formato.format(today);
        try {
            Usuario usuario = new Usuario(0, nombreCompleto, nombreUsuario, password, email,fechaActual);
            usuario.setCon(con);
            usuario.insertar();

        } catch (SQLException ex) {
            respuesta = "[no se inserto correctamente el usuario]";
        }
        return new SimpleResponse(true, respuesta);
    }

}

