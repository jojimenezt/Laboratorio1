/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonaj
 */
import becker.robots.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bodega {
    public static City almacen;
    public static Robot[] empleados;
    private Estante[] estantes;
    public static Thing[] stand;
    private ArrayList<Producto> productos=new ArrayList<>();
    private int robot=0;
    private ArrayList<Factura> facturas;
    
    public Bodega(){
        this.almacen=new City(0, 0, 11, 14);
        for(int i=1;i<8;i++){
            Wall pared=new Wall(almacen,i,4,Direction.WEST);
            pared=new Wall(almacen,i,3,Direction.EAST);
        } 
        for(int i=1;i<13;i++){
            Wall pared=new Wall(almacen,1,i,Direction.NORTH);
            if(i<10){
                pared=new Wall(almacen,i,1,Direction.WEST);
                pared=new Wall(almacen,i,12,Direction.EAST);
            }
        }
         for(int i=1;i<9;i++){
            Wall pared=new Wall(almacen,9,i,Direction.SOUTH); 
        }
        for (int i = 6; i < 11; i++) {
            Wall pared=new Wall(almacen,3,i,Direction.NORTH);
            pared=new Wall(almacen,4,i,Direction.NORTH);
            pared=new Wall(almacen,i-3,6,Direction.WEST);
            pared=new Wall(almacen,i-3,10,Direction.EAST);
            if(i>6){
                pared=new Wall(almacen,i-3,9,Direction.WEST);
                pared=new Wall(almacen,i-3,7,Direction.EAST);
            }
        }
        Wall pared =new Wall(almacen,7,8,Direction.SOUTH);
        this.estantes=new Estante[20];
        this.empleados=new Robot[8];
        for (int i = 0; i < 8; i++) {
            this.empleados[i]=new Robot(almacen,8-i,2,Direction.EAST);
        }
        this.stand =new Thing[20];
        for (int i = 6; i>=0; i--) {
            if(i>0){
                stand[6-i]=new Thing(almacen,i+1,4);
                stand[6-i].getIcon().setLabel("Estante"+String.valueOf(7-i));
            }
            stand[13-i]=new Thing(almacen,1,11-i);
            stand[13-i].getIcon().setLabel("Estante"+String.valueOf(13-i));
            stand[19-i]=new Thing(almacen,8-i,12);
            stand[19-i].getIcon().setLabel("Estante"+String.valueOf(20-i));
        }
    }
    
    public Estante[] getEstantes() {
        return estantes;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public boolean addProducto(String nombre,String tipo,double precio,int cantidad) {
        for(Producto p: productos){
            if(p.getNombre().equals(nombre)){
                return false;
            }             
        }
        productos.add(new Producto(nombre, tipo, precio, cantidad));
        return true;
    }
    
    public void deleteProducto(Producto producto){
        this.productos.remove(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public Factura venta(String cliente, HashMap<Integer, Integer> pedido){
        Factura factura=new Factura(cliente);
        for(Map.Entry<Integer,Integer> it:pedido.entrySet()){
            factura.addProducto(productos.get(it.getKey()), it.getValue());
            int cantidad=it.getValue();
            ArrayList<Integer[]> bk=(ArrayList)productos.get(it.getKey()).getEstantes().clone();
            while(cantidad!=0){
                Integer[]e=productos.get(it.getKey()).getEstantes().get(0);
                if(!estantes[e[1]].isTomado()){
                    ingresar(empleados[robot++],e[1]);
                }
                if(e[0]>=cantidad){
                    productos.get(it.getKey()).venta(cantidad, e[1], e[2]);
                    cantidad=0;
                }else{
                    cantidad-=e[0];
                    productos.get(it.getKey()).venta(e[0], e[1], e[2]);
                    
                }                
            }            
            cantidad=it.getValue();
            robot=0;
            while(cantidad!=0){
                Integer[]e=bk.get(0);
                if(estantes[e[1]].isTomado()){
                    devolver(empleados[robot],e[1]);
                }
                if(e[0]>cantidad){                    
                    cantidad=0;
                }else{
                    cantidad-=e[0];
                    bk.remove(0);
                }                
            }
            robot=0;
        }
        return factura;
    }
    
    private void move(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.move();
        }  
    }
    
    private void turn(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.turnLeft();
        }  
    }
    
    private void comprobar(Robot R,int i, int c, boolean t){
        if(c==i){
                turn(R,1);
                move(R,1);
                if(t)R.pickThing();
                else R.putThing();
                turn(R,2);
                move(R,1);
                turn(R,1);
            }
    }
    
    private void recorrer(Robot R, int c,boolean t){
        for (int i = 0; i < 6; i++) {
            move(R,1);
            comprobar(R,i,c,t);            
        }
        turn(R,3);
        for (int i = 6; i < 12; i++) {
            comprobar(R,i,c,t);
            move(R,1);
        }
        comprobar(R,12,c,t);
        turn(R,3);
        for (int i = 13; i < 19; i++) {
            comprobar(R,i,c,t);
            move(R,1);
        }
        comprobar(R,19,c,t);
        
        
    }
    
    private void ingresar(Robot R,int c){
        estantes[c].setTomado(true);
        move(R,1);
        turn(R,3);
        move(R,8-R.getStreet());
        turn(R,1);
        move(R,2);
        turn(R,1);
        recorrer(R,c,true);
        turn(R,3);
        move(R,1);
        turn(R,3);
        if(robot<5){
            move(R,4);
            turn(R,1);
            move(R,1);
            turn(R,1);
            move(R,4-robot);
        }else{
            move(R,7-robot);
        }
        
    }

    public void devolver(Robot R, int c){
            if(this.robot<4)move(R,robot);
            else{
                move(R,robot-4);
                turn(R,1);
                move(R,1);                
            }
            R.putThing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
            }
            R.pickThing();
            move(R, 1);
            turn(R,3);
            move(R,4);
            turn(R,3);
            recorrer(R, c, false);
            move(R,1);
            turn(R,3);
            move(R,10);
            turn(R,3);    
            move(R,++robot);
            turn(R,3);
            move(R,1);
            estantes[c].setTomado(false);        
    }
}
