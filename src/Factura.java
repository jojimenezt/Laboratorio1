/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonaj
 */
import java.util.HashMap;

public class Factura {
    private String cliente;
    private double precio;
    private HashMap<Producto, Integer> productos;
    
    public Factura(String cliente){
        this.cliente= cliente;
        this.productos=new HashMap<>();
    }
    
    public HashMap<Producto, Integer> getProductos() {
        return productos;
    }

    public boolean addProducto(Producto producto, int a) {
        this.precio+=producto.getPrecio()*a;
        return productos.put(producto,a)==null;
    }
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
