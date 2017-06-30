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
    private String tipocategoria;
    private String nombre;
    private String descripcion;
    private int user_id;
    
    private Conn con;

    public Categoria() {
    }

    public Categoria(int categoria_id, String tipocategoria, String nombre, String descripcion, int usuario_id) {
        this.categoria_id = categoria_id;
        this.tipocategoria = tipocategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.user_id = usuario_id;
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
                aux.setTipocategoria(rs.getString("tipocategoria"));
                aux.setNombre(rs.getString("nombre"));
                aux.setDescripcion(rs.getString("descripcion"));
                aux.setUsuario(rs.getInt("user_id"));
               
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

    public List<Categoria> buscarXid(int id) throws SQLException {
        String consulta = "select * from Categoria where user_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Categoria> lis = cargar(rs);
//        if (lis.size() > 0) {
//            return lis.get(0);
//        }
        return lis;
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

    public void modificar(int id) throws SQLException {
        String consulta = "update Categoria set tipocategoria = '" + tipocategoria + "', nombre = '" + nombre + "', descripcion = '" + descripcion + "', user_id = '" + user_id + "' where categoria_id=" + id;
        con.manipular(consulta);
    }
    public void eliminar(int id) throws SQLException {
        String consulta = "delete from categoria where categoria_id=" + id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {            
        String consulta = "insert into  presupuesto.dbo.Categoria(tipocategoria, nombre, descripcion, user_id) values('"+ tipocategoria + "','"+ nombre + "','" + descripcion + "','" + user_id + "')";
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
        return user_id;
    }

    public void setUsuario(int usuario_id) {
        this.user_id = usuario_id;
    }
    
    public String getTipocategoria() {
        return tipocategoria;
    }

    public void setTipocategoria(String tipocategoria) {
        this.tipocategoria = tipocategoria;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
