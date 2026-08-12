/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

import GUI.Tablero;

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
        objetivo.vida -= danoLanza;
        
        if (objetivo.vida<0){
            objetivo.vida = 0;
        }
    }
    
    
    public void atacarATravesDeZombie (Pieza objetivo, Zombie zombiePropio){
        int danoZombie = 1;
        objetivo.recibirDaño(danoZombie);
    }
    
    public void invocarZombie(Tablero tablero, int fila, int columna){
        if (!tablero.estVacia(fila, columna)){
            throw new IllegalStateException ("Solo es posible invocarlo en una casilla vacia");
        }
        Zombie nuevoZombie = new Zombie (this.color);
        tablero.setPieza(fila, columna, nuevoZombie);
    }
    
    
}
