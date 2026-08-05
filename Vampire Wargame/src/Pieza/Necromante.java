/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

/**
 *
 * @author user
 */
public class Necromante extends Pieza {
    
    public Necromante (int ataque, int vida, int escudo, String color){
        super(ataque, vida, escudo, color);
    }

    @Override
    public void habilidadEspecial(Pieza objetivo) {
        atacarConLanza(objetivo);
    }
    
    public void atacarConLanza(Pieza objetivo){
        int danoLanza = ataque / 2;
        objetivo.vida = danoLanza;
        
        if (objetivo.vida<0){
            objetivo.vida = 0;
        }
    }
    
    
    public void atacarATravesDeZombie (Pieza objetivo, Zombie zombiePropio){
        int danoZombie = 1;
        objetivo.recibirDaño(danoZombie);
    }
    
    
}
