/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Pieza.HombreLobo;
import Pieza.Necromante;
import Pieza.Pieza;
import Pieza.Vampiro;

/**
 *
 * @author user
 */
public class Tablero {
    
    private static final int SIZE = 6;
    private Pieza[][] casillas;
    
    
    public Tablero(){
        casillas = new Pieza[SIZE][SIZE];
        colocarPiezasIniciales();
        
    }
    
    private void colocarPiezasIniciales(){
       //Piezas negras (jugador 2)
        casillas[0][0] = new HombreLobo (3,6,3,"NEGRO");
        casillas[0][1] = new Vampiro(3,5,2,"NEGRO");
        casillas[0][2] = new Necromante(4,5,2,"NEGRO");
        casillas[0][3] = new Necromante(4,5,2,"NEGRO");
        casillas[0][4] = new Vampiro(3,5,2,"NEGRO");
        casillas[0][5] = new HombreLobo (3,6,3,"NEGRO");
        
        
        //Piezas blancas (jugador 1)
        casillas[5][0] = new HombreLobo(3,6,3,"BLANCO");
        casillas[5][1] = new Vampiro (3,5,2,"BLANCO");
        casillas[5][2] = new Necromante(4,5,2,"BLANCO");
        casillas[5][3] = new Necromante(4,5,2,"BLANCO");
        casillas[5][4] = new Vampiro (3,5,2,"BLANCO");
        casillas[5][5] = new HombreLobo(3,6,3,"BLANCO");
        
        
        
    }
    
    public Pieza getPieza (int fila, int columna){
        return casillas[fila][columna];
    }
    
    
    public void setPieza (int fila, int columna , Pieza pieza){
        casillas[fila][columna] = pieza;
        
    }
    
    
    public boolean estVacia(int fila, int columna){
        return casillas[fila][columna] == null;
    }
    
    
    
}
