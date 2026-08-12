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
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
                    casilla.setBackground(new Color(220, 224, 230));
                } 
                else{
                  casilla.setBackground(new Color(75, 85, 99));  
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
       for (int fila = 0; fila <SIZE; fila++){
           for (int columna = 0; columna < SIZE; columna++){
               Pieza pieza = tablero.getPieza(fila, columna);
               JButton boton = casillas[fila][columna];
               
               if (pieza == null){
                   boton.setIcon(null);
                   boton.setText("");
               } 
               else{
                   ImageIcon icono = obtenerIcono(pieza);
                   if (icono != null){
                       boton.setIcon(icono);
                       boton.setText("");
                   }
                   else{
                       boton.setIcon(null);
                       boton.setText(obtenerEtiqueta(pieza));
                       boton.setForeground(pieza.getColor().equals("BLANCO")? Color.WHITE : Color.BLACK);
                   }
               }
           }
       }
       
        
        
    }
    
    
    
    
      private void manejarClic(int fila, int columna){
          if (filaSeleccionada == -1){
              Pieza pieza = tablero.getPieza(fila, columna);
          
          if (pieza  == null){
              System.out.println("Selecciona una pieza primero");
              return;
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
                  boton.setBackground(new Color(220, 224, 230));
              }
              else {
                  boton.setBackground(new Color(75, 85, 99));
              }
          }
          
      }
      
      private void ejecutarAtaqueNormal(Pieza atacante, Pieza objetivo, int filaObjetivo, int columnaObjetivo){
          objetivo.recibirDaño(atacante.getAtaque());
          
          if (objetivo.estaViva()){
              System.out.println("Se atacó la pieza. Le quedan " +objetivo.getEscudo()+  " de escudo y " +objetivo.getVida() + " de vida");
              
          }else{
              System.out.println("Pieza destuida");
              tablero.setPieza(filaObjetivo, columnaObjetivo, null);
          }
          
          actualizarTableroVisual();
      }
      
      
      private boolean caminoLibre(int filaActual, int columnaActual, int filaDestino, int columnaDestino){
          if (filaActual == filaDestino && columnaActual == columnaDestino){
              return true;
          }
          
          int pasoFila = Integer.compare(filaDestino, filaActual);
          int pasoColumna = Integer.compare(columnaDestino, columnaActual);
          
          int siguienteFila = filaActual + pasoFila;
          int siguienteColumna = columnaActual + pasoColumna;
          
          if (!(siguienteFila == filaDestino && siguienteColumna == columnaDestino)){
              if (!tablero.estVacia(siguienteFila, siguienteColumna)){
                  return false;
              }
          }
          return caminoLibre (siguienteFila, siguienteColumna, filaDestino, columnaDestino);
      }
      
      private Zombie buscarZombiePropioAdyacente(String colorNecromante, int filaObjetivo, int columnaObjetivo){
          for (int df = -1; df <= 1; df++){
              for(int dc = -1 ; dc <=1; dc++){
                  if (df==0 && dc == 0 )
                      continue;
                  
                  int f = filaObjetivo + df;
                          int c = columnaObjetivo + dc;
                          
                          if (f<0 || f>= SIZE || c < 0 || c>= SIZE)
                              continue;
                          Pieza  p = tablero.getPieza(f, c);
                          if (p instanceof Zombie && p.getColor().equals(colorNecromante)){
                              return (Zombie) p;
                          }
              }
          }
          return null;
      }
      
      
      private void intentarMover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){
          
          Pieza piezaOrigen = tablero.getPieza(filaOrigen, columnaOrigen);
          Pieza piezaDestino = tablero.getPieza(filaDestino, columnaDestino);
          
          
          int distanciaFila = Math.abs(filaDestino  - filaOrigen);
          int distanciaColumna = Math.abs(columnaDestino - columnaOrigen);
          int distancia = Math.max(distanciaFila, distanciaColumna);
          
          
         
          
          if (distancia == 0){
              System.out.println("Debes elegir una casilla distinta a la de origen.");
        return;
          }
          
       if (piezaDestino == null){
           if (piezaOrigen instanceof Necromante){
               String[] opciones = {"Mover", "Invocar Zombie"};
               int seleccion = JOptionPane.showOptionDialog(this, "Qué deseas hacer?", "Necromante", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
           if (seleccion == 1){
               try{
                   ((Necromante) piezaOrigen).invocarZombie(tablero, filaDestino, columnaDestino);
                   actualizarTableroVisual();
                   System.out.println("Zombie invocado en(" + filaDestino + "," + columnaDestino + ")");
               }catch(IllegalStateException e){
                   JOptionPane.showMessageDialog(this, e.getMessage());
               }
               return;
               
           }
           
           
           
           }
           
           if (distancia > piezaOrigen.getAlcanceMovimiento()){
               System.out.println("Excede los limites del movimiento");
               return;
           }
           
           if (!caminoLibre(filaOrigen, columnaOrigen, filaDestino, columnaDestino)){
               System.out.println("Movimiento inválido: camino bloqueado");
           }
           
           tablero.setPieza(filaDestino, columnaDestino, piezaOrigen);
           tablero.setPieza(filaOrigen, columnaOrigen, null);
           actualizarTableroVisual();
           System.out.println("Pieza ahora en posicion("+ filaOrigen + "," + columnaOrigen + ")");
           
       } else if (piezaDestino.getColor().equals(piezaOrigen.getColor())){
           System.out.println("Casilla ocupada por una pieza propia, elige otra");
       }
       
       else{
           if (distancia == 1){
               String [] opciones = {"Ataque normal" , "Habilidad especial"};
                int seleccion = JOptionPane.showOptionDialog(this, "Qué tipo de ataque quiere usar?", "Elegir ataque",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                
                if (seleccion == 0){
                    ejecutarAtaqueNormal(piezaOrigen,piezaDestino,filaDestino,columnaDestino);
                }
                else if (seleccion == 1){
                    try{
                        piezaOrigen.habilidadEspecial(piezaDestino);
                        actualizarTableroVisual();
                        System.out.println("Habilidad especial lanzada al oponente");
                        
                    } catch(UnsupportedOperationException e){
                        JOptionPane.showMessageDialog(this, e.getMessage(), "No disponible", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                }
                else if (distancia == 2 && (distanciaFila == 0 || distanciaColumna == 0) && piezaOrigen instanceof Necromante){
                    
                    if (!caminoLibre(filaOrigen,columnaOrigen,filaDestino, columnaDestino)){
                        System.out.println("La lanza no puede atravezar piezas entre medio");
                        return;
                    }
                    
                    
                    ((Necromante) piezaOrigen).atacarConLanza(piezaDestino);
                    if (!piezaDestino.estaViva()){
                        tablero.setPieza(filaDestino, columnaDestino, null);
                        System.out.println("Pieza destruida por la lanza");
                    }
                    else{
                        System.out.println("Objetivo dañado con lanza. Vida restante" + piezaDestino.getVida());
                    }
                    actualizarTableroVisual();
                    
                }
                
                else if (piezaOrigen instanceof Necromante){
                    Zombie zombieAdyacente = buscarZombiePropioAdyacente(piezaOrigen.getColor(), filaDestino, columnaDestino);
                    
                    if (zombieAdyacente != null){
                        ((Necromante) piezaOrigen).atacarATravesDeZombie(piezaDestino, zombieAdyacente);
                        
                        if(!piezaDestino.estaViva()){
                            tablero.setPieza(filaDestino, columnaDestino, null);
                            System.out.println("Pieza destruida por el Zombie");
                        } else{
                            System.out.println("Ataque a través de Zombie. Vida restante: " +piezaDestino);
                        }
                        actualizarTableroVisual();
                    }else{
                        System.out.println("Enemigo fuera de alcance");
                    }
                } else{
                    System.out.println("Enemigo fuera de alcance");
                }
                
                
           
       }
       
    
    
      }
      
    
      
      private String obtenerEtiqueta(Pieza pieza) {
        if (pieza instanceof Vampiro) return "V";
        if (pieza instanceof HombreLobo) return "HL";
        if (pieza instanceof Necromante) return "N";
        if (pieza instanceof Zombie) return "Z";
        return "?";
    }
      
    
    
    private ImageIcon obtenerIcono (Pieza pieza){
        String tipo;
        
        if (pieza instanceof Vampiro) tipo = "vampiro";
        else if (pieza instanceof HombreLobo) tipo = "hombrelobo";
        else if (pieza instanceof Necromante) tipo = "necromante";
        else if (pieza instanceof Zombie) tipo = "zombie";
        else tipo = "desconocido";
        
        String color = pieza.getColor().equals("BLANCO") ? "blanco" : "negro";
        
        String ruta = "/FICHAS/Icono" + tipo + color + ".png";

        
        java.net.URL url = getClass().getResource(ruta);
        
        if (url == null){
            System.out.println("No se encotró la imagen: " +ruta);
            return null;
        }
        ImageIcon icono = new ImageIcon(url);
        Image imagenEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
    
    
}
