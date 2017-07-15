/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Clases.Categoria;
import Clases.Conn;
import Clases.Presupuesto;
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
    public SimpleResponse crearCategoria(@QueryParam("idcategoria") int idcategoria, @QueryParam("tipocategoria") String tipocategoria, @QueryParam("nombrecategoria") String nombrecategoria, @QueryParam("descripcion") String descripcion, @QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Categoria categoria = new Categoria(con);
            categoria = categoria.buscarxNombre(usuario_id, nombrecategoria);
            if (categoria != null) {
                respuesta = new SimpleResponse(true, "equal");
            } else {
                categoria = new Categoria(0, tipocategoria, nombrecategoria, descripcion, usuario_id);
                categoria.setCon(con);
                if (idcategoria == 0) {
                    categoria.insertar();
                    respuesta = new SimpleResponse(true, "insert");
                } else {
                    categoria.modificar(idcategoria);
                    respuesta = new SimpleResponse(true, "update");
                }
            }

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "error");
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
                        + "\",\"tipocategoria\":\"" + listainfoCategorias.get(i).getTipocategoria()
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
            respuesta = new SimpleResponse(true, "delete");

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "error");
        }
        return respuesta;
    }

    @GET
    @Path("/crearpresupuesto")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse crearPresupuesto(@QueryParam("presupuestoid") int presupuestoid,@QueryParam("categoria") int categoria, @QueryParam("nombrepresupuesto") String nombrepresupuesto, @QueryParam("monto") Double monto, @QueryParam("usuario_id") int usuario_id, @QueryParam("fecha") String fecha) {
        Conn con = new Conn();
        SimpleResponse respuesta;
        try {
            Presupuesto presupuesto = new Presupuesto(con);
            presupuesto = presupuesto.buscarxNombre(usuario_id, nombrepresupuesto);
            if (presupuesto != null) {
                respuesta = new SimpleResponse(true, "equal");
            } else {
                presupuesto = new Presupuesto(nombrepresupuesto, monto, categoria,fecha, usuario_id);
                presupuesto.setCon(con);
                if (presupuestoid == 0) {
                    presupuesto.insertar();
                    respuesta = new SimpleResponse(true, "insert");
                } else {
                    presupuesto.modificar(presupuestoid);
                    respuesta = new SimpleResponse(true, "update");
                }
            }

        } catch (SQLException ex) {
            respuesta = new SimpleResponse(true, "error");
        }
        return respuesta;
    }
    
     @GET
    @Path("/obtenerpresupuesto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse obtenerPresupuesto(@QueryParam("usuario_id") int usuario_id) {
        Conn con = new Conn();
        String respuesta = "";
        try {
            Presupuesto infoPresupuesto = new Presupuesto();
            infoPresupuesto.setCon(con);
            List<Presupuesto> listaPresupuesto = infoPresupuesto.buscarXid(usuario_id);
            respuesta = "[";
            for (int i = 0; i < listaPresupuesto.size(); i++) {
                respuesta += "{\"id\":\"" + listaPresupuesto.get(i).getPresupuesto_id()
                        + "\",\"nombre\":\"" + listaPresupuesto.get(i).getNombre()
                        + "\",\"monto\":\"" + listaPresupuesto.get(i).getMonto_limite()
                        + "\",\"categoria\":\"" + listaPresupuesto.get(i).getCategoria_id()
                        + "\",\"fecha\":\"" + listaPresupuesto.get(i).getFecha()
                        + "\",\"user_id\":\"" + listaPresupuesto.get(i).getUser_id()
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
