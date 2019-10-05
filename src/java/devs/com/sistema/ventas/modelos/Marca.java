package devs.com.sistema.ventas.modelos;

import java.util.List;

public class Marca {
    private long idMarca;
    private String marca;
    private List<Producto> productos;

    public Marca() {
    }

    public Marca(long idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(long idMarca) {
        this.idMarca = idMarca;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
}
