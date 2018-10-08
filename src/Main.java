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
import java.util.Scanner;

public class Main {
    public static City almacen;
    public static Robot robot;
    public static Thing cosa;
    public static void main(String[] args) {
        almacen=new City("Field.txt");
        almacen.showThingCounts(true);
        robot=  new  Robot(almacen,6,2,Direction.NORTH,200);
        Scanner scan= new Scanner(System.in);
        cosa=new Thing (almacen,0,0);
        System.out.println("Escriba el numero");
        int numero=scan.nextInt();
        //pos();
        int digitos=Integer.toString(numero).length();
        String  num=Integer.toString(numero);
        if(digitos==1){
                poner(traducir(0));
                retornar();
                posicion();
                poner(traducir(0));
                retornar();
                posicion();
                poner(traducir(numero));// se tarduce de numero entero a matriz de booleanos y de matriz a numero en el mundo de karel
                retornar();//posicion inicial para el otro digito
            }
            
            if(digitos==2){
               
                poner(traducir(0));
                retornar();
                posicion();
               
               int[]A= separar2(numero);
               poner(traducir(A[0]));
               retornar();
               posicion();
               poner(traducir(A[1]));
            }
            
            if(digitos==3){
                 if(num.charAt(0)=='0'){
                    poner(traducir(0));
                    retornar();
                    posicion();
                }
                int[] B=separar3(numero);
                poner(traducir(B[0]));
                retornar();
                posicion();
                poner(traducir(B[1]));
                retornar();
                posicion();
                poner(traducir(B[2]));
            }
            
       
}

    public static void poner(boolean[][] A){
        for(int i=0;i<5;i++){
            for(int j=0;j<3;j++){
                robot.move();
                if (i==0 && j==1){
                    girar3();
                }
                if(i==0 && j==2){
                    girar3();
                }
                if(i==1 && j==1){
                    robot.turnLeft();
                }
                if(i==1 && j==2){
                    robot.turnLeft();
                }
                if(i==2 && j==1){
                    girar3();
                }
                if(i==2 && j==2){
                    girar3();
                }
                if(i==3 && j==1){
                    robot.turnLeft();
                }
                if(i==3 && j==2){
                    robot.turnLeft();
                }
            
            if(i==4 && j==1){
                robot.turnLeft();
                robot.turnLeft();
                robot.putThing();
                robot.move();
                robot.turnLeft();
                
            }
            
           if(A[i][j]){
               robot.putThing();
           }
           
        }
    }
    
   
}

public static boolean[][] traducir(int x){
    boolean[][] A=new boolean[5][3];
    switch(x){
        case 0:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=false;
        A[2][1]=true;
        A[2][2]=true;
        A[3][1]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 1:
        A[0][1]=true;
        A[0][2]=true;
        A[2][1]=true;
        A[2][2]=true;
        break;
        
        case 2:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[3][1]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 3:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 4:
        robot.putThing();
        A[0][1]=true;
        A[0][2]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        break;
        
        case 5:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 6:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        A[3][1]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 7:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[2][1]=true;
        A[2][2]=true;
        break;
        
        case 8:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        A[3][1]=true;
        A[3][2]=true;
        A[4][0]=true;
        break;
        
        case 9:
        robot.putThing();
        A[0][0]=true;
        A[0][1]=true;
        A[0][2]=true;
        A[1][1]=true;
        A[1][2]=true;
        A[2][0]=true;
        A[2][1]=true;
        A[2][2]=true;
        break;
        
    }
    
    return A;
}

public static int[] separar2(int x){
    int[] digitos= new int[2];
    int i=0;
    digitos[i]=x/10;
    digitos[i+1]=x%10;
    return digitos;
}

public static int[] separar3(int x){
    int[]A= new int[3];
        for(int i=0;i<3;i++){
            A[2-i]=x%10;
            x=x/10;
        }
       
    return A;
}

public static void girar3(){
    robot.turnLeft();
    robot.turnLeft();
    robot.turnLeft();
}

public static void retornar(){
                robot.turnLeft();
                robot.move();            
                robot.move();
                robot.move();
                robot.turnLeft();
}

    public static void posicion(){
        robot.move();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.move();
        robot.move();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
    }       
}

