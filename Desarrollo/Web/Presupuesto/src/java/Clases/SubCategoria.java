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
public class SubCategoria {
    private int subCategoria_id;
    private String nombre;
    private String descripcion;
    private int categoria_id;
    
    private Conn con;

    public SubCategoria() {
    }

    public SubCategoria(Conn con) {
        this.con = con;
    }
    
    

    public SubCategoria(int subCategoria_id, String nombre, String descripcion, int categoria_id) {
        this.subCategoria_id = subCategoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria_id = categoria_id;
    }
    
    
    public List<SubCategoria> cargar(ResultSet rs) {
        List<SubCategoria> lista = new ArrayList();
        try {
            while (rs.next()) {
                SubCategoria aux = new SubCategoria(con);
                aux.setSubCategoria_id(rs.getInt("subCategoria_id"));
                aux.setNombre(rs.getString("nombre"));
                aux.setDescripcion(rs.getString("descripcion"));
                aux.setCategoria_id(rs.getInt("categoria_id"));
               
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public List<SubCategoria> todo() throws SQLException {
        String consulta = "select * from SubCategoria";
        return cargar(con.consultar(consulta));
    }

    public SubCategoria buscarXid(int id) throws SQLException {
        String consulta = "select * from SubCategoria where subcategoria_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<SubCategoria> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public SubCategoria buscarxNombre(String nombreSubCategoria) throws SQLException {
        String consulta = "select * from SubCategoria where nombre='"+ nombreSubCategoria +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<SubCategoria> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public void modificar() throws SQLException {
        String consulta = "update SubCategoria set subcategoria_id = " + subCategoria_id + ", nombre = '" + nombre + "', descripcion = '" + descripcion + "', categoria_id = '" + categoria_id + "' where subcategoria_id=" + subCategoria_id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {            
        String consulta = "insert into  presupuesto.dbo.SubCategoria(nombre, descripcion, categoria_id) values('"+ nombre + "','" + descripcion + "','" + categoria_id + "')";
        con.manipular(consulta);     
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    

    public void setCon(Conn con) {
        this.con = con;
    }
    
    

    public int getSubCategoria_id() {
        return subCategoria_id;
    }

    public void setSubCategoria_id(int subCategoria_id) {
        this.subCategoria_id = subCategoria_id;
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
    
}
