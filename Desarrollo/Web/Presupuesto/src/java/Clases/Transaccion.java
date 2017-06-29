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
public class Transaccion {
    
    private int transaccion_id;
    private String tipo; //ingreso, egreso Traspaso
    private double monto;
    private String concepto; 
    private int user_id;
    private int cuentaOrigen;
    private int cuentaDestino;
    private int categoria;
    //private SubCategoria subCategoria;
    
    private Conn con;

    public Transaccion() {
    }
    
    public Transaccion(Conn con) {
        this.con = con;
    }

    public Transaccion(int transaccion_id, String tipo, int monto, String Concepto, int user_id, int cuentaOrigen, int cuentaDestino, int categoria) {
        this.transaccion_id = transaccion_id;
        this.tipo = tipo;
        this.monto = monto;
        this.concepto = Concepto;
        this.user_id = user_id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.categoria = categoria;
    }
    
    public List<Transaccion> cargar(ResultSet rs) {
        List<Transaccion> lista = new ArrayList();
        try {
            while (rs.next()) {
                Transaccion aux = new Transaccion(con);
                aux.setTransaccion_id(rs.getInt("transaccion_id"));
                aux.setTipo(rs.getString("tipo"));
                aux.setMonto(rs.getInt("monto"));
                aux.setConcepto(rs.getString("detalle"));
                aux.setUser_id(rs.getInt("user_id"));
                aux.setCuentaOrigen(rs.getInt("cuenta_id"));
                aux.setCuentaDestino(rs.getInt("cuenta_id_destino"));
                aux.setCategoria(rs.getInt("categoria_id"));
               
                lista.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos");
        }
        return lista;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public List<Transaccion> todo() throws SQLException {
        String consulta = "select * from Transaccion";
        return cargar(con.consultar(consulta));
    }

    public Transaccion buscarXid(int id) throws SQLException {
        String consulta = "select * from Transaccion where transaccion_id=" + id;
        ResultSet rs = con.consultar(consulta);
        List<Transaccion> lis = cargar(rs);
        if (lis.size() > 0) {
            return lis.get(0);
        }
        return null;
    }

    public List<Transaccion> buscarxCuenta(int idcuenta) throws SQLException {
        String consulta = "select * from Transaccion where cuenta_id='"+ idcuenta +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<Transaccion> lis = cargar(rs);
//        if (lis.size() > 0) {
//            return lis.get(0);
//        }
        return lis;
    }
    
    public List<Transaccion> buscarxCategoria(int idcategoria) throws SQLException {
        String consulta = "select * from Transaccion where categoria_id='"+ idcategoria +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<Transaccion> lis = cargar(rs);
//        if (lis.size() > 0) {
//            return lis.get(0);
//        }
        return lis;
    }
    
    public List<Transaccion> buscarxUsuario(int idusuario) throws SQLException {
        String consulta = "select * from Transaccion where user_id='"+ idusuario +"'"; 
        ResultSet rs = con.consultar(consulta);
        List<Transaccion> lis = cargar(rs);
//        if (lis.size() > 0) {
//            return lis.get(0);
//        }
        return lis;
    }

    public void modificar(int idtransaccion) throws SQLException {
        String consulta = "update Transaccion set tipo = '" + tipo + "', monto = '" + monto + "', detalle = '" + concepto + "', user_id = '" + user_id + "' , cuenta_id = '" + cuentaOrigen + "', cuenta_id_destino = '" + cuentaDestino + "', categoria_id = '" + categoria + "' where transaccion_id=" + transaccion_id;
        con.manipular(consulta);
    }
    
//    public void modificarName(int idcuenta) throws SQLException {
//        String consulta = "update Cuenta set cuenta_id = " + cuenta_id + ", nombre = '" + nombre + "', user_id = '" + user_id + "' where cuenta_id=" + cuenta_id;
//        con.manipular(consulta);
//    }

    public void insertar() throws SQLException {     
        String consulta = "insert into  presupuesto.dbo.Transaccion(tipo, monto, detalle, user_id, cuenta_id, cuenta_id_destino, categoria_id) values('"+ tipo + "','" + monto + "','" + concepto + "','" + user_id + "','" + cuentaOrigen + "','" + cuentaDestino + "','" + categoria + "')";
        con.manipular(consulta);     
    }
    
    public void eliminar(int id) throws SQLException {
        String consulta = "delete from transaccion where transaccion_id=" + id;
        con.manipular(consulta);
    }
    
    

    public int getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(int transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String Concepto) {
        this.concepto = Concepto;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    public Conn getCon() {
        return con;
    }

    public void setCon(Conn con) {
        this.con = con;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

//    public SubCategoria getSubCategoria() {
//        return subCategoria;
//    }
//
//    public void setSubCategoria(SubCategoria subCategoria) {
//        this.subCategoria = subCategoria;
//////    }
//    
    
}
