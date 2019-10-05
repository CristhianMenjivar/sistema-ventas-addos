/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.modelos;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Categoria {
    private long categoriaId;
    private String nombreCat;
    private String descripcion;
    private List<Producto> listaProducto;

    public Categoria() {
    }
    
    public Categoria(long categoriaId, String nombreCat, String descripcion) {
        this.categoriaId = categoriaId;
        this.nombreCat = nombreCat;
        this.descripcion= descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
    
}
