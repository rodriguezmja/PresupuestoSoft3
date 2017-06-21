/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Conn;
import Clases.Cuenta;
import Clases.Usuario;
import Seguridad.EnvioMail;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
@Path("/controladorcuenta")
public class ControladorCuenta {

    @GET
    @Path("/crearcuenta")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse crearCuenta(@QueryParam("idcuenta") int idcuenta, @QueryParam("nombrecuenta") String nombrecuenta, @QueryParam("monto") Double monto, @QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Cuenta cuenta = new Cuenta(con);
            cuenta = cuenta.buscarxNombre(nombrecuenta);
            if (cuenta != null) {
                respuesta = new SimpleResponse(true, "El nombre de la cuenta ya existe");
            } else {
                cuenta = new Cuenta(0, nombrecuenta, monto, usuario_id);
                cuenta.setCon(con);
                if (idcuenta == 0) {
                    cuenta.insertar();
                    respuesta = new SimpleResponse(true, "Lacuenta se creo correctamente");
                } else {
                    cuenta.modificar(idcuenta);
                    respuesta = new SimpleResponse(true, "La Categoria se modifico correctamente");
                }
            }
        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se creo correctamente la cuenta");
        }
        return respuesta;
    }

    @GET
    @Path("/obtenercuenta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerTipoCliente(@QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        String respuesta = "";
        try {
            Cuenta infoCuenta = new Cuenta();
            infoCuenta.setCon(con);
            List<Cuenta> listainfoCuentas = infoCuenta.buscarXid(usuario_id);
            respuesta = "[";
            for (int i = 0; i < listainfoCuentas.size(); i++) {
                respuesta += "{\"id\":\"" + listainfoCuentas.get(i).getCuenta_id()
                        + "\",\"nombre\":\"" + listainfoCuentas.get(i).getNombre()
                        + "\",\"monto\":\"" + listainfoCuentas.get(i).getMonto()
                        + "\",\"usuario_id\":\"" + listainfoCuentas.get(i).getUsuario()
                        + "\"},";
            }
            if (respuesta.length() > 2) {
                respuesta = respuesta.substring(0, respuesta.length() - 1);
            }
            respuesta += "]";

        } catch (Exception ex) {
            respuesta = "[]";
        }
        return new SimpleResponse(true, respuesta);
    }

}
