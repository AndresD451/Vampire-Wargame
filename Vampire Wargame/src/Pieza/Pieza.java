/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieza;

/**
 *
 * @author user
 */
public abstract class Pieza {
    protected int ataque;
    protected int vida;
    protected int escudo;
    protected String color;
    
    
    public Pieza (int ataque, int vida, int escudo, String color){
        this.ataque = ataque;
        this.vida = vida;
        this.escudo = escudo;
        this.color = color;
    }
    
    public abstract void habilidadEspecial (Pieza objetivo);
    
    
    public final void recibirDaño(int dano){
        if (escudo > 0){
            if (dano<= escudo){
                escudo -= dano;
            }
            else{
                int danoRestante = dano - escudo;
                escudo = 0;
                vida -= danoRestante;
            }
        }
        else{
            vida -= dano;
        }
        
        if (vida < 0 ){
            vida = 0;
        }
        
        
    }
    
    public final boolean estaViva(){
        return vida > 0;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }

    public String getColor() {
        return color;
        
        
        }
    
    
    public int getAlcanceMovimiento(){
            return 1;
    }
        
    }
    
    
    
    
    
    

