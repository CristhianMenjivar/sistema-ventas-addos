/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.modelos;

import java.text.DecimalFormat;

/**
 *
 * @author Usuario
 */
public class DetalleOrdenes {
    private Orden orden;
    private long iddetalle;
    private Producto producto;
    private double cantidad;
    private double importe;
    private String importeRedondeado;
    
    public DetalleOrdenes(){}

    public DetalleOrdenes(Orden orden, long iddetalle, Producto producto, double cantidad, double importe) {
        this.orden = orden;
        this.iddetalle = iddetalle;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondeado() {
       return new DecimalFormat("#.##").format(importe);
    }

    public void setImporteRedondeado(String importeRedondeado) {
        this.importeRedondeado = importeRedondeado;
    }
   
    
}
