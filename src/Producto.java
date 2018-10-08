
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonaj
 */
public class Producto {
    private String nombre;
    private String tipo;
    private double precio;
    private int cantidad;
    private ArrayList<Integer[]> estantes=new ArrayList<>();

    public Producto(String nombre, String tipo, double precio, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public ArrayList<Integer[]> getEstantes() {
        return estantes;
    } 

    public void ingreso(int cant,int estante){
        Integer[] nuevo={cant,estante};
        this.estantes.add(nuevo);
        this.cantidad+=cant;
    }
    public void venta(int cant,int estante, int caja){
        this.cantidad-=cant;
        if(this.estantes.get(0)[0]-cant==0){
            this.estantes.remove(0);
        }else{
            Integer[] key={this.estantes.get(0)[0]-cant,estante,caja};
            this.estantes.add(0, key);
        }
        
    }
        
}
