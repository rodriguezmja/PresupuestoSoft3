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
public class Presupuesto {

    private int presupuesto_id;
    private String nombre;
    private double monto_limite;
    private int user_id;
    private int categoria_id;
    private String fecha;
    
    private double totalGasto;

    private Conn con;

    public Presupuesto() {
    }

    public Presupuesto(String nombre, double monto_limite, int categoria_id, String fecha, int user_id) {
        this.nombre = nombre;
        this.monto_limite = monto_limite;
        this.categoria_id = categoria_id;
        this.fecha = fecha;
        this.user_id = user_id;
    }

    public Presupuesto(Conn con) {
        this.con = con;
    }

    public void setCon(Conn con) {
        this.con = con;
    }

    public List<Presupuesto> cargar(ResultSet rs) {
        List<Presupuesto> lista = new ArrayList();
        try {
            while (rs.next()) {
                Presupuesto aux = new Presupuesto(con);
                aux.setPresupuesto_id(rs.getInt("presupuesto_id"));
                aux.setNombre(rs.getString("nombre"));
                aux.setMonto_limite(rs.getDouble("monto_limite"));
                aux.setCategoria_id(rs.getInt("categoria_id"));
                aux.setFecha(rs.getString("fecha"));
                aux.setUser_id(rs.getInt("user_id"));

                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }
    public List<Presupuesto> cargarLimite(ResultSet rs) {
        List<Presupuesto> lista = new ArrayList();
        try {
            while (rs.next()) {
                Presupuesto aux = new Presupuesto(con);                
                aux.setNombre(rs.getString("nombre"));
                aux.setMonto_limite(rs.getDouble("monto_limite"));
                aux.setTotalGasto(rs.getDouble("totalGasto"));
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public List<Presupuesto> todo() throws SQLException {
        String consulta = "select * from presupuesto";
        return cargar(con.consultar(consulta));
    }

    public List<Presupuesto> buscarXid(int id) throws SQLException {
        String consulta = "select * from presupuesto where user_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Presupuesto> lis = cargar(rs);
        return lis;
    }

    public List<Presupuesto> verificarLimite(int categoria_id) throws SQLException {
        String consulta = "  select p.nombre, p.monto_limite,\n"
                + "  (select sum(monto)as totalGasto from transaccion t  where t.categoria_id="+categoria_id+") as totalGasto\n"
                + "  from presupuesto p where p.categoria_id="+categoria_id;
        ResultSet rs = con.consultar(consulta);
        List<Presupuesto> lis = cargarLimite(rs);
        return lis;
    }

    public Presupuesto buscarxNombre(int usuario_id, String nombrePresupuesto) throws SQLException {
        String consulta = "select * from presupuesto where user_id=" + usuario_id + " and nombre='" + nombrePresupuesto + "'";
        ResultSet rs = con.consultar(consulta);
        List<Presupuesto> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public void modificar(int id) throws SQLException {
        String consulta = "update presupuesto set nombre = '" + nombre + "', monto_limite = '" + monto_limite + "', categoria_id = '" + categoria_id + "', fecha = '" + fecha + "', user_id = '" + user_id + "' where categoria_id=" + id;
        con.manipular(consulta);
    }

    public void eliminar(int id) throws SQLException {
        String consulta = "delete from presupuesto where presupuesto_id=" + id;
        con.manipular(consulta);
    }

    public void insertar() throws SQLException {
        String consulta = "insert into  presupuesto(nombre, monto_limite, categoria_id, fecha, user_id) values('" + nombre + "','" + monto_limite + "','" + categoria_id + "','" + fecha + "','" + user_id + "')";
        con.manipular(consulta);
    }

    public int getPresupuesto_id() {
        return presupuesto_id;
    }

    public void setPresupuesto_id(int presupuesto_id) {
        this.presupuesto_id = presupuesto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto_limite() {
        return monto_limite;
    }

    public void setMonto_limite(double monto_limite) {
        this.monto_limite = monto_limite;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
    
}
