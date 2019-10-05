/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.modelos;


/**
 *
 * @author Usuario
 */
public class Producto {
    private long idProducto;
    private String medida;
    private String nombre;
    private String color;
    private String serie;
    private String modelo;
    private int enLinea;
    private double precioCompra;
    private double precioVenta;
    private double stocks;
    private double existencias;
    private double averiado;
    private Marca marca;
    private Proveedor proveedor;
    private Categoria categoria;
    private String tipoRegistro; //producto o servicio

    public Producto() {
    }

    public Producto(long idProducto, String medida, String nombre, String color, String serie, String modelo, int enLinea, double precioCompra, double precioVenta, double stocks, double existencias, double averiado, Marca marca, Proveedor proveedor, Categoria categoria, String tipoRegistro) {
        this.idProducto = idProducto;
        this.medida = medida;
        this.nombre = nombre;
        this.color = color;
        this.serie = serie;
        this.modelo = modelo;
        this.enLinea = enLinea;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stocks = stocks;
        this.existencias = existencias;
        this.averiado = averiado;
        this.marca = marca;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.tipoRegistro = tipoRegistro;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getEnLinea() {
        return enLinea;
    }

    public void setEnLinea(int enLinea) {
        this.enLinea = enLinea;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getStocks() {
        return stocks;
    }

    public void setStocks(double stocks) {
        this.stocks = stocks;
    }

    public double getExistencias() {
        return existencias;
    }

    public void setExistencias(double existencias) {
        this.existencias = existencias;
    }

    public double getAveriado() {
        return averiado;
    }

    public void setAveriado(double averiado) {
        this.averiado = averiado;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
