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
        
}

}