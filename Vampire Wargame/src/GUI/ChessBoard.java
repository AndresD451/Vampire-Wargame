/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Pieza.HombreLobo;
import Pieza.Muerte;
import Pieza.Pieza;
import Pieza.Vampiro;
import Pieza.Zombie;
import Usuarios.GestorJugadores;
import Usuarios.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



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
    private int filaMarcada = -1;
    private int columnaMarcada = -1;
    private String jugadorActual = "BLANCO";
    private String tipoPermitido = null;
    private int girosUsados = 0;
    private JLabel labelTurno;
    private JButton botonGirar;
    private RuletaPanel ruletaPanel;
    private final Ruleta ruleta = new Ruleta();
    private final GestorJugadores gestor;
    private final Usuario jugadorBlanco;
    private final Usuario jugadorNegro;
    private JTextArea areaAdvertencias;
    private JTextArea areaReportes;
    private JTextArea areaEstadisticas;

    
    
    public ChessBoard(GestorJugadores gestor, Usuario jugador1, Usuario jugador2){
        this.gestor = gestor;
        this.jugadorBlanco = jugador1;
        this.jugadorNegro = jugador2;
        
        setTitle ("Vampire Wargame");
        setSize(700,480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
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
        
        
        JPanel panelRuleta = crearPanelRuleta();
        
       
        
        add(envolverConCoordenadas(panelTablero),BorderLayout.CENTER);
        add(crearPanelInfo(), BorderLayout.WEST);
        add(panelRuleta, BorderLayout.EAST);
        
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
    
   private JPanel envolverConCoordenadas(JPanel panelTablero) {
        JPanel contenedor = new JPanel(new BorderLayout());

        JPanel panelFilas = new JPanel(new GridLayout(SIZE, 1));
        for (int i = 1; i <= SIZE; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            panelFilas.add(label);
        }

        JPanel panelColumnas = new JPanel(new GridLayout(1, SIZE));
        for (int i = 1; i <= SIZE; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            panelColumnas.add(label);
        }

        contenedor.add(panelFilas, BorderLayout.WEST);
        contenedor.add(panelTablero, BorderLayout.CENTER);
        contenedor.add(panelColumnas, BorderLayout.SOUTH);

        return contenedor;
    } 
   
   private JPanel crearPanelInfo() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(220, 0));

    areaAdvertencias = new JTextArea(4, 20);
    areaAdvertencias.setEditable(false);
    areaAdvertencias.setLineWrap(true);
    areaAdvertencias.setWrapStyleWord(true);
    JScrollPane scrollAdvertencias = new JScrollPane(areaAdvertencias);
    scrollAdvertencias.setBorder(BorderFactory.createTitledBorder("Advertencias"));

    areaReportes = new JTextArea(10, 20);
    areaReportes.setEditable(false);
    areaReportes.setLineWrap(true);
    areaReportes.setWrapStyleWord(true);
    JScrollPane scrollReportes = new JScrollPane(areaReportes);
    scrollReportes.setBorder(BorderFactory.createTitledBorder("Reportes"));

    areaEstadisticas = new JTextArea(6, 20);
    areaEstadisticas.setEditable(false);
    areaEstadisticas.setLineWrap(true);
    areaEstadisticas.setWrapStyleWord(true);
    JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
    scrollEstadisticas.setBorder(BorderFactory.createTitledBorder("Vida / Escudo"));

    panel.add(scrollAdvertencias);
    panel.add(scrollReportes);
    panel.add(scrollEstadisticas);

    return panel;
}
    
    
    
    private String nombreJugadorActual(){
        return jugadorActual.equals("BLANCO") ? jugadorBlanco.getUsuario() : jugadorNegro.getUsuario();
    }
 
    
    private JPanel crearPanelRuleta(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        JLabel titulo  = new JLabel("Ruleta");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelTurno = new JLabel("Turno: " +nombreJugadorActual());
        labelTurno.setFont(new Font("Arial",Font.BOLD,14));
        labelTurno.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ruletaPanel = new RuletaPanel();
        ruletaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        botonGirar = new JButton("Girar");
        botonGirar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonGirar.addActionListener(e -> girarRuleta());
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(labelTurno);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(ruletaPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(botonGirar);
        
        JButton botonMiedoso = new JButton ("Retirarse");
        botonMiedoso.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMiedoso.addActionListener(e -> retirarse());
        
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(botonMiedoso);
        
        return panel;
    }
    
    
    private void girarRuleta(){
     int piezasVivas = tablero.contarPiezasVivas(jugadorActual);
     int piezasPerdidas = 6 - piezasVivas;
     int girosPermitidos = Ruleta.girosPermitidos(piezasPerdidas);
     
     if (girosUsados >= girosPermitidos){
         JOptionPane.showMessageDialog(this, "Sin Giros, Pierdes turno");
         terminarTurno();
         return;
     }
     
     botonGirar.setEnabled(false);
     String resultado = ruleta.girar();
     
     ruletaPanel.girarHacia(resultado, () -> {
         girosUsados++;
         
         int disponibles = tablero.contarPiezasPorTipo(jugadorActual, resultado);
         
         if(disponibles == 0){
             JOptionPane.showMessageDialog(this, "No tienes piezas de tipo " +resultado + ". Volviendo a girar");
             if (girosUsados < girosPermitidos){
                 girarRuleta();
             }
             else{
                 JOptionPane.showMessageDialog(this, "Sin mas giros, pierde turno");
                 terminarTurno();
             }
         }
         else{
             tipoPermitido = resultado;
             JOptionPane.showMessageDialog(this, "Debes mover una pieza de tipo: " +resultado);
         }
         
     });
        
    }
    
    
      private void manejarClic(int fila, int columna){
          if (filaSeleccionada == -1){
              Pieza pieza = tablero.getPieza(fila, columna);
          
          if (pieza  == null){
              mostrarAdvertencia("Primer gira la ruleta");
              return;
          }
          
          if (!pieza.getColor().equals(jugadorActual)){
              mostrarAdvertencia("No es tu turno, espera que el jugador contrario mueva su pieza");
              return;
          }
          
          if (tipoPermitido == null){
              mostrarAdvertencia("Primero gira la ruleta");
              return;
          }
          
          if(!coincideConTipo(pieza)){
              mostrarAdvertencia("Debes mover una pieza de tipo " +tipoPermitido);
              return;
          }
          
          
          
          filaSeleccionada = fila;
          columnaSeleccionada = columna;
          resaltarCasilla (fila,columna,true);
          resaltarCasilla(fila,columna,true);
          mostrarAdvertencia("");
          mostrarEstadisticas(pieza);
          
          
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
      
      
      private void resaltarPiezaExacta(int fila, int columna, boolean resaltar){
          JButton boton = casillas[fila][columna];
          if(resaltar){
              boton.setBackground(Color.GREEN);
          }else{
              if ((fila + columna) % 2 == 0){
                  boton.setBackground(new Color (220,224,230));
              }
              else{
                  boton.setBackground(new Color(75,85,99));
              }
          }
      }
      
      
      
      private void ejecutarAtaqueNormal(Pieza atacante, Pieza objetivo, int filaObjetivo, int columnaObjetivo){
          objetivo.recibirDaño(atacante.getAtaque());
          String nombreAtacante = jugadorActual.equals("BLANCO") ? jugadorBlanco.getUsuario() : jugadorNegro.getUsuario();
          String nombreObjetivo = jugadorActual.equals("BLANCO") ? jugadorNegro.getUsuario() : jugadorBlanco.getUsuario();
          
          if (objetivo.estaViva()){
              agregarReporte(nombreAtacante + " atacó a " + nombreObjetivo + 
            " (" + obtenerEtiqueta(objetivo) + "). Le quedan " + objetivo.getEscudo() + 
            " de escudo y " + objetivo.getVida() + " de vida.");
              
          }else{
            agregarReporte(nombreAtacante + " destruyó la pieza " + obtenerEtiqueta(objetivo) + 
            " de " + nombreObjetivo + ".");
        tablero.setPieza(filaObjetivo, columnaObjetivo, null);
        actualizarTableroVisual();
        validarVictoria();
        return;
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
              mostrarAdvertencia("Debes elegir una casilla distinta a la de origen.");
        return;
          }
          
       if (piezaDestino == null){
           if (piezaOrigen instanceof Muerte){
               String[] opciones = {"Mover", "Invocar Zombie"};
               int seleccion = JOptionPane.showOptionDialog(this, "Qué deseas hacer?", "Necromante", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
               
               if (seleccion == JOptionPane.CLOSED_OPTION){
                   return;
               }
        
           if (seleccion == 1){
               try{
                   ((Muerte) piezaOrigen).invocarZombie(tablero, filaDestino, columnaDestino);
                   actualizarTableroVisual();
                   agregarReporte(nombreJugadorActual() + " invocó un Zombie en (" + (filaDestino+1) + "," + (columnaDestino+1) + ").");
                   terminarTurno();
               }catch(IllegalStateException e){
                   mostrarAdvertencia(e.getMessage());
               }
               return;
               
           }
           
           
           
           }
           
           if (distancia > piezaOrigen.getAlcanceMovimiento()){
               mostrarAdvertencia("Excede los limites del movimiento");
               return;
           }
           
           if (!caminoLibre(filaOrigen, columnaOrigen, filaDestino, columnaDestino)){
               mostrarAdvertencia("Movimiento inválido: camino bloqueado");
           }
           
           String nombreMueve = nombreJugadorActual();
           String etiquetaPieza = obtenerEtiqueta(piezaOrigen);
           
           tablero.setPieza(filaDestino, columnaDestino, piezaOrigen);
           tablero.setPieza(filaOrigen, columnaOrigen, null);
           actualizarTableroVisual();
           agregarReporte(nombreMueve + " movió su " + etiquetaPieza + 
            " de (" + (filaOrigen+1) + "," + (columnaOrigen+1) + ") a (" + (filaDestino+1) + "," + (columnaDestino+1) + ").");
           terminarTurno();
           
       } else if (piezaDestino.getColor().equals(piezaOrigen.getColor())){
           mostrarAdvertencia("Casilla ocupada por una pieza propia, elige otra");
       }
       
       else{
           String nombreAtacante = nombreJugadorActual();
           String nombreDefensor = jugadorActual.equals("BLANCO") ? jugadorNegro.getUsuario() : jugadorBlanco.getUsuario();
           
           
           if (distancia == 1){
               String [] opciones = {"Ataque normal" , "Habilidad especial"};
                int seleccion = JOptionPane.showOptionDialog(this, "Qué tipo de ataque quiere usar?", "Elegir ataque",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                
                if (seleccion == 0){
                    ejecutarAtaqueNormal(piezaOrigen,piezaDestino,filaDestino,columnaDestino);
                    terminarTurno();
                }
                else if (seleccion == 1){
                    try{
                        piezaOrigen.habilidadEspecial(piezaDestino);
                        actualizarTableroVisual();
                        
                       
                        if(!piezaDestino.estaViva()){
                            tablero.setPieza(filaDestino, columnaDestino, null);
                            actualizarTableroVisual();
                            agregarReporte(nombreAtacante+ " usó habilidad especial y destruye " +obtenerEtiqueta(piezaDestino) + " de " +nombreDefensor + ".");
                            validarVictoria();
                        } else{
                            agregarReporte(nombreAtacante + " usó habilidad especial sobre " +nombreDefensor + " (" + obtenerEtiqueta(piezaDestino) + "). Le quedan " + piezaDestino.getEscudo() + " de escudo y " + piezaDestino.getVida() + " de vida.");
                            
                        }
                        
                        terminarTurno();
                        
                    } catch(UnsupportedOperationException e){
                        JOptionPane.showMessageDialog(this, e.getMessage(), "No disponible", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                }
                else if (distancia == 2 && piezaOrigen instanceof Muerte){
                    
                    if (!caminoLibre(filaOrigen,columnaOrigen,filaDestino, columnaDestino)){
                        mostrarAdvertencia("La lanza no puede atravezar piezas entre medio");
                        return;
                    }
                    
                    
                    ((Muerte) piezaOrigen).atacarConLanza(piezaDestino);
                    if (!piezaDestino.estaViva()){
                        tablero.setPieza(filaDestino, columnaDestino, null);
                        agregarReporte(nombreAtacante + " lanzó su lanza y destruyó la pieza " + obtenerEtiqueta(piezaDestino) + " de " + nombreDefensor + ".");
                        validarVictoria();
                    }
                    else{
                        agregarReporte(nombreAtacante + " atacó con lanza a " + nombreDefensor + " (" + obtenerEtiqueta(piezaDestino) + "). Vida restante: " + piezaDestino.getVida() + ".");
                        
                    }
                    actualizarTableroVisual();
                    terminarTurno(); 
                    
                }
                
                else if (piezaOrigen instanceof Muerte){
                    Zombie zombieAdyacente = buscarZombiePropioAdyacente(piezaOrigen.getColor(), filaDestino, columnaDestino);
                    
                    if (zombieAdyacente != null){
                        ((Muerte) piezaOrigen).atacarATravesDeZombie(piezaDestino, zombieAdyacente);
                        
                        if(!piezaDestino.estaViva()){
                            tablero.setPieza(filaDestino, columnaDestino, null);
                            agregarReporte(nombreAtacante + " atacó a través de su Zombie y destruyó la pieza " + obtenerEtiqueta(piezaDestino) + " de " + nombreDefensor + ".");
                            validarVictoria();
                        } else{
                            agregarReporte(nombreAtacante + " atacó a través de Zombie a " + nombreDefensor + ". Vida restante: " + piezaDestino.getVida() + ".");
                        }
                        actualizarTableroVisual();
                        terminarTurno();
                    }else{
                        mostrarAdvertencia("Ese enemigo está fuera de alcance.");
                    }
                   
                } else{
                    mostrarAdvertencia("Ese enemigo está fuera de alcance.");
                }
                
                
           
       }
       
    
    
      }
      
      
      private void terminarTurno(){
          jugadorActual = jugadorActual.equals("BLANCO") ? "NEGRO" : "BLANCO";
          
          if (filaMarcada != -1){
              resaltarPiezaExacta(filaMarcada, columnaMarcada, false);
              filaMarcada = -1;
              columnaMarcada = -1;
          }
          
          labelTurno.setText("Turno " +nombreJugadorActual());
          tipoPermitido = null;
          girosUsados = 0;
          botonGirar.setEnabled(true);
          
          System.out.println("Turno actual es de: " +jugadorActual);
          
      }
      
      
      private void validarVictoria(){
          String colorRival = jugadorActual.equals("BLANCO") ? "NEGRO" : "BLANCO";
          
          if(tablero.contarPiezasVivas(colorRival) == 0 ){
              Usuario ganador = jugadorActual.equals("BLANCO") ? jugadorBlanco : jugadorNegro;
              Usuario perdedor = jugadorActual.equals("BLANCO") ? jugadorNegro : jugadorBlanco;
              
              ganador.sumarPuntos(3);
              
              String mensaje = ganador.getUsuario() + " vencio a " +perdedor.getUsuario() + ". Felicidades, has ganado 3 puntos!";
              gestor.registrarPartida(mensaje, ganador.getUsuario(), perdedor.getUsuario());
              
              JOptionPane.showMessageDialog(this, mensaje, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
              dispose();
              new MenuPrincipal(gestor, jugadorBlanco).setVisible(true);
          }
      }
    
      private void retirarse(){
          int confirmacion = JOptionPane.showConfirmDialog(this, "Deseas retirarte? (ADVERTENCIA: ACCION IRREVERSIBLE)", "Confirmar retiro", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
          
          if (confirmacion == JOptionPane.YES_OPTION){
              Usuario retirado = jugadorActual.equals("BLANCO") ? jugadorBlanco : jugadorNegro;
              Usuario ganador = jugadorActual.equals("BLANCO") ? jugadorNegro : jugadorBlanco;
              
              ganador.sumarPuntos(3);
              
              String mensaje = retirado.getUsuario() + " se ha retirado. Felicidades, " +ganador.getUsuario() + ", has ganado 3 puntos!";
              gestor.registrarPartida(mensaje, retirado.getUsuario(), ganador.getUsuario());
              
              JOptionPane.showMessageDialog(this, mensaje, "Fin del juego",JOptionPane.INFORMATION_MESSAGE);
              dispose();
              new MenuPrincipal(gestor,jugadorBlanco).setVisible(true);
          }
      }

      
      private String obtenerEtiqueta(Pieza pieza) {
        if (pieza instanceof Vampiro) return "V";
        if (pieza instanceof HombreLobo) return "HL";
        if (pieza instanceof Muerte) return "N";
        if (pieza instanceof Zombie) return "Z";
        return "?";
    }
      
      
      private boolean coincideConTipo(Pieza pieza){
          switch(tipoPermitido){
              case "HombreLobo": return pieza instanceof HombreLobo;
              case "Vampiro": return pieza instanceof Vampiro;
              case "Muerte": return pieza instanceof Muerte;
              default: return false;
          }
      }
    
    
    private ImageIcon obtenerIcono (Pieza pieza){
        String tipo;
        
        if (pieza instanceof Vampiro) tipo = "vampiro";
        else if (pieza instanceof HombreLobo) tipo = "hombrelobo";
        else if (pieza instanceof Muerte) tipo = "necromante";
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
    
    
    private void mostrarAdvertencia(String mensaje) {
    areaAdvertencias.setText(mensaje);
}
    
    private void agregarReporte(String mensaje){
        areaReportes.append(mensaje + "\n");
        areaReportes.setCaretPosition(areaReportes.getDocument().getLength());
    }
    
    private void mostrarEstadisticas(Pieza pieza){
        if (pieza == null){
            areaEstadisticas.setText("");
            return;
        }
        
        areaEstadisticas.setText(
        "Tipo: " + obtenerEtiqueta(pieza) + "\n" +
        "Color: " + pieza.getColor() + "\n" +
        "Vida: " + pieza.getVida() + "\n" +
        "Escudo: " + pieza.getEscudo() + "\n" +
        "Ataque: " + pieza.getAtaque()
    );
        
        
    }
    
}
