/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Categoria;
import Clases.Conn;
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
 * @author rpinto
 */
@Path("/controladorcategoria")
public class ControladorCategoria {

    @GET
    @Path("/crearcategoria")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse crearCuenta(@QueryParam("nombrecategoria") String nombrecategoria, @QueryParam("descripcion") String descripcion, @QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
//        Calendar calendar = new GregorianCalendar();
//        Date today = calendar.getTime();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        String fechaActual = formato.format(today);
        try {
            Categoria categoria = new Categoria(0, nombrecategoria, descripcion, usuario_id);
            categoria.setCon(con);
            categoria.insertar();
            respuesta = new SimpleResponse(true, "La Categoria se creo correctamente");

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se creo correctamente la categoria");
        }
        return respuesta;
    }
    
     @GET
    @Path("/obtenercategoria")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerTipoCliente() {
        Conn con = new Conn();
        String respuesta = "";
        try {
            Categoria infoCategoria = new Categoria();
            infoCategoria.setCon(con);
            List<Categoria> listainfoCategorias = infoCategoria.todo();
            respuesta = "[";
            for (int i = 0; i < listainfoCategorias.size(); i++) {
                respuesta += "{\"id\":\"" + listainfoCategorias.get(i).getCategoria_id()
                        + "\",\"nombre\":\"" + listainfoCategorias.get(i).getNombre()
                        + "\",\"monto\":\"" + listainfoCategorias.get(i).getDescripcion()
                        + "\",\"usuario_id\":\"" + listainfoCategorias.get(i).getUsuario()
                        +  "\"},";
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