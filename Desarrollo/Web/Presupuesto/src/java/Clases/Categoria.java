/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodriguezja
 */
public class Categoria {
    private int categoria_id;
    private String nombre;
    private String descripcion;
    private int usuario_id;
    
    private Conn con;

    public Categoria() {
    }

    public Categoria(int categoria_id, String nombre, String descripcion, int usuario_id) {
        this.categoria_id = categoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario_id = usuario_id;
    }
    
     

    public Categoria(Conn con) {
        this.con = con;
    }
    
    
    
     public void setCon(Conn con) {
        this.con = con;
    }

    public List<Categoria> cargar(ResultSet rs) {
        List<Categoria> lista = new ArrayList();
        try {
            while (rs.next()) {
                Categoria aux = new Categoria(con);
                aux.setCategoria_id(rs.getInt("categoria_id"));
                aux.setNombre(rs.getString("nombre"));
                aux.setDescripcion(rs.getString("descripcion"));
                aux.setUsuario(rs.getInt("usuario_id"));
               
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public List<Categoria> todo() throws SQLException {
        String consulta = "select * from Categoria";
        return cargar(con.consultar(consulta));
    }

    public Categoria buscarXid(int id) throws SQLException {
        String consulta = "select * from Categoria where categoria_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Categoria> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public Categoria buscarxNombre(String nombreCategoria) throws SQLException {
        String consulta = "select * from Categoria where nombre='"+ nombreCategoria +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<Categoria> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public void modificar() throws SQLException {
        String consulta = "update Categoria set categoria_id = " + categoria_id + ", nombre = '" + nombre + "', descripcion = '" + descripcion + "', usuario_id = '" + usuario_id + "' where categoria_id=" + categoria_id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {            
        String consulta = "insert into  presupuesto.dbo.Categoria(nombre, descripcion, usuario_id) values('"+ nombre + "','" + descripcion + "','" + usuario_id + "')";
        con.manipular(consulta);     
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUsuario() {
        return usuario_id;
    }

    public void setUsuario(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    
}
