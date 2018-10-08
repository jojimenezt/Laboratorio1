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

public class Bodega {
    public static City almacen;
    public static Robot[] empleados;
    public static Thing[] stand;
    private Estante[] estantes;
    
    public Bodega(Estante[] estantes){
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
                //pared=new Wall(almacen,i,10,Direction.EAST);
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
        
        for (int i = 0; i < 8; i++) {
            this.empleados[i]=new Robot(almacen,8-i,2,Direction.EAST);
        }
    }

    
    
}
