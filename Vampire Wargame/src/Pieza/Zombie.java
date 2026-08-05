/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

/**
 *
 * @author user
 */
public final class Zombie extends Pieza {
    
    public Zombie(String color){
        super(1,1,0,color);
    }

    @Override
    public void habilidadEspecial(Pieza objetivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    @Override
    public final int getAlcanceMovimiento(){
        return 0;
    }
    
}
