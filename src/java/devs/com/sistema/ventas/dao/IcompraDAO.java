/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Compra;
import devs.com.sistema.ventas.modelos.DetalleCompra;
import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IcompraDAO {
    public Compra insert(Compra compra);
    
    public List<Compra> listAll();
    
    public List<Compra> listComprasDia(Date fecha);
    
    public Compra findById(long idCompra);
    
    public List<DetalleCompra> detalles(Compra compra);
    
    public int comprasDelDia(Date fecha);
    
}
