/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

/**
 *
 * @author user
 */
public class HombreLobo extends Pieza {


    
    public HombreLobo (int ataque, int vida, int escudo, String color){
        super(ataque,vida,escudo,color);
    }
    
    
    
    @Override
    public void habilidadEspecial(Pieza objetivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public int getAlcanceMovimiento(){
        return 2;
    }
    
    
    
}
