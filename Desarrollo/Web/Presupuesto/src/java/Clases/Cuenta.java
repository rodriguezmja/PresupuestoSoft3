/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author rpinto
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Cuenta {
    
    private int cuenta_id;
    private String nombre;
    private double monto;
    private int user_id;

    private Conn con;

    public Cuenta(Conn con) {
        this.con = con;
    }
    
    public Cuenta() {
        this.cuenta_id = cuenta_id;
        this.nombre = nombre;
        this.monto = monto;
        this.user_id = user_id;
    }

    public Cuenta(int cuenta_id, String nombre, double monto, int user_id) {
        this.cuenta_id = cuenta_id;
        this.nombre = nombre;
        this.monto = monto;
        this.user_id = user_id;
    }
    
     public void setCon(Conn con) {
        this.con = con;
    }

    public List<Cuenta> cargar(ResultSet rs) {
        List<Cuenta> lista = new ArrayList();
        try {
            while (rs.next()) {
                Cuenta aux = new Cuenta(con);
                aux.setCuenta_id(rs.getInt("cuenta_id"));
                aux.setNombre(rs.getString("nombre"));
                aux.setMonto(rs.getDouble("monto"));
                aux.setUsuario(rs.getInt("user_id"));
               
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public List<Cuenta> todo() throws SQLException {
        String consulta = "select * from Cuenta";
        return cargar(con.consultar(consulta));
    }

    public List<Cuenta> buscarXid(int id) throws SQLException {
        String consulta = "select * from Cuenta where user_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Cuenta> lis = cargar(rs);
//        if (lis.size() > 0) {
//            return lis.get(0);
//        }
        return lis;
    }

    public Cuenta buscarxNombre(String nombreCuenta) throws SQLException {
        String consulta = "select * from Cuenta where nombre='"+ nombreCuenta +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<Cuenta> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public void modificar(int idcuenta) throws SQLException {
        String consulta = "update Cuenta set nombre = '" + nombre + "', monto = '" + monto + "', user_id = '" + user_id + "' where cuenta_id=" + idcuenta;
        con.manipular(consulta);
    }
    
    public void modificarName(int idcuenta) throws SQLException {
        String consulta = "update Cuenta set cuenta_id = " + cuenta_id + ", nombre = '" + nombre + "', user_id = '" + user_id + "' where cuenta_id=" + cuenta_id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {            
        String consulta = "insert into  presupuesto.dbo.Cuenta(nombre, monto, user_id) values('"+ nombre + "','" + monto + "','" + user_id + "')";
        con.manipular(consulta);     
    }
    
    public void eliminar(int id) throws SQLException {
        String consulta = "delete from cuenta where cuenta_id=" + id;
        con.manipular(consulta);
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getUsuario() {
        return user_id;
    }

    public void setUsuario(int user_id) {
        this.user_id = user_id;
    }
    
    
}
