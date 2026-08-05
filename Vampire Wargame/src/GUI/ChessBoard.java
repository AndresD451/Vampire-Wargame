/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Pieza.HombreLobo;
import Pieza.Necromante;
import Pieza.Pieza;
import Pieza.Vampiro;
import Pieza.Zombie;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class ChessBoard extends JFrame {
    
    private static final int SIZE = 6;
    private final JButton[][] casillas = new JButton[SIZE][SIZE];
    private final Tablero tablero;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    
    public ChessBoard(){
        setTitle ("Vampire Wargame");
        setSize(480,480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tablero = new Tablero();
        
        JPanel panelTablero = new JPanel (new GridLayout (SIZE,SIZE));
        
        
        for (int fila = 0; fila < SIZE; fila++){
            for (int columna = 0; columna <SIZE; columna++){
                JButton casilla = new JButton();
                casilla.setOpaque(true);
                casilla.setBorderPainted(false);
                casilla.setFont(new Font("Arial", Font.BOLD, 18));
                
                if ((fila + columna) % 2 == 0){
                    casilla.setBackground(new Color(240, 217, 181));
                } 
                else{
                  casilla.setBackground(new Color(181, 136, 99));  
                }
                
                final int f = fila;
                final int c = columna;
                
                casilla.addActionListener(e -> {
                   manejarClic(f, c);
                });

                casillas[fila][columna] = casilla;
                panelTablero.add(casilla);
                
            }
        }
        
        
        
        add(panelTablero);
        actualizarTableroVisual();
        
    }
    
    
    private void actualizarTableroVisual(){
        for(int fila = 0; fila < SIZE; fila++){
            for (int columna  = 0; columna < SIZE; columna++){
                Pieza pieza  = tablero.getPieza(fila, columna);
                
                JButton boton = casillas[fila][columna];
                
                if(pieza == null){
                    boton.setText("");
                } 
                else{
                boton.setText(obtenerEtiqueta(pieza));
                boton.setForeground(pieza.getColor().equals("BLANCO") ? Color.WHITE : Color.BLACK);
                }
                
                
            }
            
           
        }
       
        
        
    }
    
    
    
    
      private void manejarClic(int fila, int columna){
          if (filaSeleccionada == -1){
              Pieza pieza = tablero.getPieza(fila, columna);
          
          if (pieza  == null){
              System.out.println("Selecciona una pieza primero");
          }
          
          
          filaSeleccionada = fila;
          columnaSeleccionada = columna;
          resaltarCasilla (fila,columna,true);
          System.out.println("Pieza seleccionada en (" + fila + "," + columna + ")");
          
          
          }
          else {
              intentarMover(filaSeleccionada, columnaSeleccionada, fila, columna);
        resaltarCasilla(filaSeleccionada, columnaSeleccionada, false);
        filaSeleccionada = -1;
        columnaSeleccionada = -1;
          }
          
          
}
      
      private void resaltarCasilla (int fila, int columna, boolean resaltar){
          JButton boton = casillas[fila][columna];
          
          if (resaltar){
              boton.setBackground(Color.YELLOW);
          }
          else {
              if ((fila + columna) % 2 == 0){
                  boton.setBackground(new Color(240,217,181));
              }
              else {
                  boton.setBackground(new Color(181,136,99));
              }
          }
          
      }
      
      
      private void intentarMover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){
          if (!tablero.estVacia(filaDestino, columnaDestino)){
              System.out.println("Casilla destino ocupaba. El combate lo implementamos después");
              return;
          }
          
          
          int distanciaFila = Math.abs(filaDestino  - filaOrigen);
          int distanciaColumna = Math.abs(columnaDestino - columnaOrigen);
          int distancia = Math.max(distanciaFila, distanciaColumna);
          
          
          Pieza pieza = tablero.getPieza(filaOrigen, columnaOrigen);
          
          if (distancia == 0){
              System.out.println("Debes elegir una casilla distinta a la de origen.");
        return;
          }
          
          
           if (distancia > pieza.getAlcanceMovimiento()) {
        System.out.println("Movimiento inválido: excede el alcance de esta pieza.");
        return;
    }
         
    tablero.setPieza(filaDestino, columnaDestino, pieza);
    tablero.setPieza(filaOrigen, columnaOrigen, null);
    
    actualizarTableroVisual();
    System.out.println("Pieza movida de (" + filaOrigen + "," + columnaOrigen + ") a (" + filaDestino + "," + columnaDestino + ")");
           
      }
      
    
    
    
     private String obtenerEtiqueta(Pieza pieza){
          if (pieza instanceof Vampiro) 
              return "V";
          if (pieza instanceof HombreLobo)
              return "HL";
          if (pieza instanceof Necromante)
              return "N";
          if (pieza instanceof Zombie)
              return "Z";
          return "?";
           }
    
    
}
