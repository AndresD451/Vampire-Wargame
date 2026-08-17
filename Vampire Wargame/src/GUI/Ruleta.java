/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.Random;

/**
 *
 * @author user
 */
public class Ruleta {
    private static final String[] TIPOS = {"HombreLobo", "Vampiro", "Muerte"};
    private final Random random = new Random();
    
    private String resultadoActual = null;
    
    public String girar(){
     return TIPOS[random.nextInt(TIPOS.length)];
}
    
    public String getResultadoActual(){
        return resultadoActual;
    }
    
    public void reiniciar(){
        resultadoActual = null;
        
    }
    
    
    
    public static int girosPermitidos(int piezasPerdidas){
        if (piezasPerdidas >= 4)
            return 3;
        if (piezasPerdidas >= 2)
            return 2;
        return 1;
    }
    
}
