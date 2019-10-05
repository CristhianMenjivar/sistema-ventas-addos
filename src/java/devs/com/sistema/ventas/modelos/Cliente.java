/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.modelos;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Cliente {
    private long idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String numfactura;
    private String tipofactura;
    private String estadofactura;
    private Double total;
    private String formapago;
    private long numdocumento;
    private String banco;
    private Date fecha;
    private Double monto;
    private String rut;
    
    public Cliente(){}

    public Cliente(long idCliente, String nombre, String apellido, String direccion, String telefono, String numfactura, String tipofactura, String estadofactura, Double total, String formapago, long numdocumento, String banco, Date fecha, Double monto, String rut) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numfactura = numfactura;
        this.tipofactura = tipofactura;
        this.estadofactura = estadofactura;
        this.total = total;
        this.formapago = formapago;
        this.numdocumento = numdocumento;
        this.banco = banco;
        this.fecha = fecha;
        this.monto = monto;
        this.rut = rut;
    }
    
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(String numfactura) {
        this.numfactura = numfactura;
    }

    public String getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(String tipofactura) {
        this.tipofactura = tipofactura;
    }

    public String getEstadofactura() {
        return estadofactura;
    }

    public void setEstadofactura(String estadofactura) {
        this.estadofactura = estadofactura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public long getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(long numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    
    
    
}
