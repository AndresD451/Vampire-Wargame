/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package empresa;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Empresa {
static ArrayList <Empleado> empleados;
static Scanner lea = new Scanner (System.in);


private static Empleado search (String cod){
    for (Empleado e : empleados){
        if (e.getCodigo().equals(cod)){
            return e;
        }
}
return null;
}


private static void hire(){
    String codigo;
    do{
    System.out.print("Codigo del empleado: ");
    codigo = lea.next();
    
    if (search(codigo)!= null)
        System.out.println("Ya existe un empleado con el codigo ingresado, favor de ingresar un codigo unico");
    
}while (search(codigo)!= null);

    System.out.println("Nombre de empleado");
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        empleados = new ArrayList<>();
        int opcion;
        
        do {
              System.out.println("1- Agregar Empleado");

            System.out.println("2- Pagar Empleado");

            System.out.println("3- Lista de Empleados");

            System.out.println("4- Sub Menu especifico");

            System.out.println("5- Salir");

            System.out.print("Escoja Opción: ");

            opcion = lea.nextInt();
            
            
            switch(opcion){
                case 1:
                    hire();
                    break;
                case 2:
                 //   pay();
                    break;
                case 3:
                   // list();
                    break;
                case 4:
                   // submenu();
                    break;
            }
            
            
        } while (opcion!=5);
        
    }
    
}
