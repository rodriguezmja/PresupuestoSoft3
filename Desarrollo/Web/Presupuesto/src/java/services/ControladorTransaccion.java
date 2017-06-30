/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Categoria;
import Clases.Conn;
import Clases.Cuenta;
import Clases.Transaccion;
import java.sql.SQLException;
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
 * @author rodriguezja
 */
@Path("/controladortransaccion")
public class ControladorTransaccion {

    @GET
    @Path("/creartransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse crearTransaccion(@QueryParam("idtransaccion") int idtransaccion, @QueryParam("fecha") String fecha,@QueryParam("tipotransaccion") String tipotransaccion, @QueryParam("montotransaccion") int monto, @QueryParam("conceptotransaccion") String detalle, @QueryParam("user_id") int user_id, @QueryParam("cuenta_id") int idcuenta, @QueryParam("cuenta_id_destino") int idcuentadestino, @QueryParam("categoria_id") int idcategoria) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Transaccion transaccion = new Transaccion(con);
            transaccion = transaccion.buscarXid(idtransaccion);
            Cuenta cuenta=new Cuenta(con);
            Cuenta cuentadestino=new Cuenta(con);
            cuenta = cuenta.obtenerCuenta(idcuenta);
            cuentadestino = cuentadestino.obtenerCuenta(idcuentadestino);
            if (transaccion == null) {
                transaccion = new Transaccion(0, fecha, tipotransaccion, monto, detalle, user_id, idcuenta, idcuentadestino, idcategoria);
                transaccion.setCon(con);
                transaccion.insertar();
                    if (tipotransaccion.equals("Ingreso")){
                        double montonuevo=cuenta.getMonto()+monto;
                        cuenta.setMonto(montonuevo);
                        cuenta.modificar(idcuenta);
                    }
                    if (tipotransaccion.equals("Egreso")){
                        double montonuevo=cuenta.getMonto()-monto;
                        cuenta.setMonto(montonuevo);
                        cuenta.modificar(idcuenta);
                    }
                    if (tipotransaccion.equals("Traspaso")){
                        double montoegreso=cuenta.getMonto()-monto;
                        cuenta.setMonto(montoegreso);
                        double montoingreso = cuentadestino.getMonto()+monto;
                        cuentadestino.setMonto(montoingreso);
                        cuenta.modificar(idcuenta);
                        cuentadestino.modificar(idcuentadestino);
                    }
                respuesta = new SimpleResponse(true, "La transaccion se creo correctamente");
            } else {
                transaccion.modificar(idtransaccion);
                respuesta = new SimpleResponse(true, "La transaccion se modifico correctamente");
            }

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se pudieron guardar los datos de la transaccion"+ex);
        }
        return respuesta;
    }

    @GET
    @Path("/obtenertransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerTransaccion(@QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        String respuesta = "";
        try {
            Transaccion infoTransaccion = new Transaccion();
            infoTransaccion.setCon(con);
            List<Transaccion> listainfoTransacciones = infoTransaccion.buscarxUsuario(usuario_id);
            respuesta = "[";
            for (int i = 0; i < listainfoTransacciones.size(); i++) {
                respuesta += "{\"id\":\"" + listainfoTransacciones.get(i).getTransaccion_id()
                        + "\",\"fecha\":\"" + listainfoTransacciones.get(i).getFecha()
                        + "\",\"tipo\":\"" + listainfoTransacciones.get(i).getTipo()
                        + "\",\"monto\":\"" + listainfoTransacciones.get(i).getMonto()
                        + "\",\"detalle\":\"" + listainfoTransacciones.get(i).getConcepto()
                        + "\",\"user_id\":\"" + listainfoTransacciones.get(i).getUser_id()
                        + "\",\"cuenta_id\":\"" + listainfoTransacciones.get(i).getCuentaOrigen()
                        + "\",\"cuenta_id_destino\":\"" + listainfoTransacciones.get(i).getCuentaDestino()
                        + "\",\"categoria_id\":\"" + listainfoTransacciones.get(i).getCategoria()
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

    @GET
    @Path("/eliminartransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse eliminarTransaccion(@QueryParam("transaccion_id") int transaccion_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Transaccion transaccion = new Transaccion();
            transaccion.setCon(con);
            transaccion.eliminar(transaccion_id);
            respuesta = new SimpleResponse(true, "La transaccion se elimino correctamente");

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "Error al eliminar la transaccion");
        }
        return respuesta;
    }

}
