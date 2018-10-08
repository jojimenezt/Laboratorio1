/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonaj
 */

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Bodega bodega=new Bodega();
        Scanner scan= new Scanner(System.in);
        int k=0;
        boolean produc=true;
        do{
            
            do{
            System.out.println("Bienvenido, escoja una opcion");
            System.out.println("1.Ingresar nuevo producto ");
            System.out.println("2.Añadir existencias a un producto");
            System.out.println("3.Comprar producto");
            k = scan.nextInt();           
            if(k==2||k==3&&produc){
                System.out.println("Aun no hay productos en la bodega");
            }
            if(k==1){
                produc = false;
            }
            }while(k>3||produc);
            
            switch(k){
                case 1:
                    System.out.println("Ingrese el nombre del producto: ");
                    String nombre=scan.next();
                    System.out.println("Ingrese el tipo del producto: ");
                    String tipo=scan.next();
                    System.out.println("Ingrese el precio del producto: ");
                    double precio=scan.nextDouble();
                    System.out.println("Ingrese la cantidad existente del producto");
                    int cantidad=scan.nextInt();
                    
                    if(bodega.addProducto(nombre, tipo, precio, cantidad)){
                        System.out.println("Se ha adicionado satisfactoriamente el producto");
                    }else{
                        System.out.println("Problemas al adicionar el producto, ya existe");
                    }
                    break;
                case 2:
                    ArrayList<Producto> productos=bodega.getProductos();
                    int m=0,n=0,estante=0,caja=0;
                    cantidad =0;
                    System.out.println("Productos existentes: ");
                    for(Producto p: productos){
                        m++;
                        System.out.println(m+". "+p.getNombre());
                    }
                    do{
                    System.out.println("Ingrese el numero del producto: ");
                    n=scan.nextInt();
                    }while(n<1 || n>productos.size());
                    do{
                    System.out.println("Ingrese la cantidad de productos a incluir: ");
                    cantidad=scan.nextInt();
                    }while(cantidad<0||cantidad>7);
                    do{
                    System.out.println("Ingrese el estante donde se encuentra: ");
                    estante=scan.nextInt()-1;
                    }while(estante<0 || estante >19);
                    do{
                    System.out.println("Ingrese caja donde se encuentra: ");
                    caja=scan.nextInt()-1;
                    }while(caja<0 || caja>2);
                    Integer[] array=new Integer[3];
                    array[0]=cantidad;
                    array[1]=estante;
                    array[2]=caja;
                    HashMap<Integer,Integer[]> product = new HashMap<>();
                    product.put(n-1, array);
                    //bodega.ingresarProductos(product);
                    break;
                case 3:
                    ArrayList<Producto> lista=bodega.getProductos();
                    int q=0,o=0,r=0;
                    System.out.println("Ingrese su nombre: ");
                    String name=scan.next();
                    System.out.println("Productos existentes: ");
                    for(Producto p: lista){
                        q++;
                        System.out.println(q+". "+p.getNombre());
                    }
                    do{
                    System.out.println("Ingrese el numero del producto que se desea comprar: ");
                    o=scan.nextInt();
                    }while(o<1 || o>lista.size());
                    do{
                    System.out.println("Ingrese la cantidad de productos que se desea comprar: ");
                    r=scan.nextInt();
                    if(r>bodega.getProductos().get(r).getCantidad()){
                        System.out.println("No hay suficientes productos, ingrese nuevamente");
                    }
                    }while(r<1 || r>bodega.getProductos().get(r).getCantidad());
                    HashMap<Integer, Integer> pedido = new HashMap<>();
                    pedido.put(o, r);
                    bodega.venta(name, pedido);
                    
                break;
            }
            
        }while(k>=0 && k<=4);
    }
}