/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Pieza.HombreLobo;
import Pieza.Muerte;
import Pieza.Pieza;
import Pieza.Vampiro;

/**
 *
 * @author user
 */
public class Tablero {
    
    private static final int SIZE = 6;
    private Pieza[][] casillas;
    private Pieza[] piezasBlancas = new Pieza[6];
    private Pieza[] piezasNegras = new Pieza[6];
    
    public Tablero(){
        casillas = new Pieza[SIZE][SIZE];
        colocarPiezasIniciales();
        
    }
    
    private void colocarPiezasIniciales(){
       //Piezas negras (jugador 2)
       piezasNegras[0] = new HombreLobo(3,6,3,"NEGRO");
       piezasNegras[1] = new Vampiro (3,5,2,"NEGRO");
       piezasNegras[2] = new Muerte(4,5,2,"NEGRO");
       piezasNegras[3] = new Muerte (4,5,2,"NEGRO");
       piezasNegras[4] = new Vampiro(3,5,2,"NEGRO");
       piezasNegras[5] = new HombreLobo(3,6,3,"NEGRO");
        for (int i = 0; i < 6; i++)
            casillas[0][i] = piezasNegras[i];
        
        
        //Piezas blancas (jugador 1)
        piezasBlancas[0] = new HombreLobo(3,6,3,"BLANCO");
        piezasBlancas[1] = new Vampiro(3,5,2,"BLANCO");
        piezasBlancas[2] = new Muerte(4,5,2,"BLANCO");
        piezasBlancas[3] = new Muerte(4,5,2,"BLANCO");
        piezasBlancas[4] = new Vampiro(3,5,2,"BLANCO");
        piezasBlancas[5] = new HombreLobo(3,5,3,"BLANCO");
        for (int i = 0; i < 6; i++)
            casillas[5][i] = piezasBlancas[i];
        
        
    }
    
    
    public int[] buscarPosicion(Pieza pieza){
        for (int fila = 0; fila < SIZE; fila++){
            for (int columna = 0; columna < SIZE; columna++){
                if (casillas[fila][columna] == pieza){
                    return new int[]{fila,columna};
                }
            }
        }
        return null;
    }
    
    
    public Pieza[] getPiezasDe(String color){
        return color.equals("BLANCO") ? piezasBlancas : piezasNegras;
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
    
    public int contarPiezasVivas(String color){
     return contarOpt(color,0,0);
    }
    
    
    
    private int contarOpt (String color, int fila, int columna){
        if (fila >= SIZE){
            return 0;
        }
        
        int siguienteFila = (columna == SIZE - 1) ? fila + 1 : fila;
        int siguienteColumna = (columna == SIZE - 1) ? 0 : columna + 1;
        
        Pieza  p = casillas[fila][columna];
        int cuentaActual = (p != null && p.getColor().equals(color) && p.estaViva()) ? 1 : 0;
        return cuentaActual + contarOpt(color, siguienteFila, siguienteColumna);
        
    }
    
    
    private boolean coincideTipo(Pieza p, String tipo){
        switch (tipo){
            case "HombreLobo":
                return p instanceof HombreLobo;
            case "Vampiro":
                return p instanceof Vampiro;
            case "Muerte": 
                return p instanceof Muerte;    
            default:
                return false;
        }
    }
    
    
    public int contarPiezasPorTipo(String color, String tipo){
        int contador = 0;
        for(int fila = 0; fila <SIZE; fila++){
            for (int columna = 0; columna < SIZE; columna++){
                Pieza p = casillas[fila][columna];
                if (p!= null && p.getColor().equals(color) && coincideTipo(p,tipo)){
                    contador++;
                }
            }
        }
        return contador;
    }
    
    
    
    
    
    
}
