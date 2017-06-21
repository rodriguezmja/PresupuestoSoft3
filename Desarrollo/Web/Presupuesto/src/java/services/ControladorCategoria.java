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
    public SimpleResponse crearCategoria(@QueryParam("idcategoria") int idcategoria, @QueryParam("nombrecategoria") String nombrecategoria, @QueryParam("descripcion") String descripcion, @QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Categoria categoria = new Categoria(con);
            categoria = categoria.buscarxNombre(nombrecategoria);
            if (categoria != null) {
                respuesta = new SimpleResponse(true, "El nombre de la categoria ya existe");
            } else {
                categoria = new Categoria(0, nombrecategoria, descripcion, usuario_id);
                categoria.setCon(con);
                if (idcategoria == 0) {
                    categoria.insertar();
                    respuesta = new SimpleResponse(true, "La Categoria se creo correctamente");
                } else {
                    categoria.modificar(idcategoria);
                    respuesta = new SimpleResponse(true, "La Categoria se modifico correctamente");
                }
            }

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se pudieron guardar los cambios correctamente de la categoria");
        }
        return respuesta;
    }

    @GET
    @Path("/obtenercategoria")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerTipoCliente(@QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        String respuesta = "";
        try {
            Categoria infoCategoria = new Categoria();
            infoCategoria.setCon(con);
            List<Categoria> listainfoCategorias = infoCategoria.buscarXid(usuario_id);
            respuesta = "[";
            for (int i = 0; i < listainfoCategorias.size(); i++) {
                respuesta += "{\"id\":\"" + listainfoCategorias.get(i).getCategoria_id()
                        + "\",\"nombre\":\"" + listainfoCategorias.get(i).getNombre()
                        + "\",\"descripcion\":\"" + listainfoCategorias.get(i).getDescripcion()
                        + "\",\"user_id\":\"" + listainfoCategorias.get(i).getUsuario()
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
    @Path("/eliminarcategoria")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse eliminarCategoria(@QueryParam("categoria_id") int categoria_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Categoria categoria = new Categoria();
            categoria.setCon(con);
            categoria.eliminar(categoria_id);
            respuesta = new SimpleResponse(true, "La Categoria se elimino correctamente");

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "no se elimino correctamente la categoria");
        }
        return respuesta;
    }

}
