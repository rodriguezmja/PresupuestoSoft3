/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author rodriguezja
 */
public class SubCategoria {
    private int subCategoria_id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;

    public SubCategoria() {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
