/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

/**
 *
 * @author user
 */
public class Vampiro extends Pieza {
    
    public Vampiro (int ataque, int vida, int escudo, String color){
        super(ataque,vida,escudo,color);
    }

    @Override
    public void habilidadEspecial(Pieza objetivo) {
        int robo = 1;
        
        objetivo.recibirDaño(robo);
        this.vida += robo;
    }
    
    
    
    
}
