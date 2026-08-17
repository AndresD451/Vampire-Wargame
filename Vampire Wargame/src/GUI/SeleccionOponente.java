/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Usuarios.GestorJugadores;
import Usuarios.Usuario;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class SeleccionOponente extends JFrame {
    private final GestorJugadores gestor;
    private final Usuario jugador1;
    private JComboBox<Usuario> comboOponentes;
    private boolean huboOponentes = true;
    
    public SeleccionOponente (GestorJugadores gestor, Usuario jugador1){
        this.gestor = gestor;
        this.jugador1 = jugador1;
        
        
        setTitle("Seleccionar Oponente");
        setSize(640,480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        ArrayList<Usuario> disponibles = obtenerOponentes();
        
        if(disponibles.isEmpty()){
            huboOponentes = false;
            JOptionPane.showMessageDialog(this, "No hay otros oponentes registrados al juego", "Vuelve mas tarde", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        
        JLabel titulo = new JLabel ("Elige tu contricante");
        titulo.setFont(new Font("Fraktur",Font.BOLD,18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        comboOponentes = new JComboBox<>(disponibles.toArray(new Usuario[0]));
        comboOponentes.setMaximumSize(new Dimension(250,30));
        comboOponentes.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JButton btnIniciar = new JButton("Iniciar Partida");
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(e ->  iniciarPartida());
        
        
        JButton btnCancelar = new JButton ("Cancelar");
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.addActionListener(e -> dispose());
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(comboOponentes);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(btnIniciar);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnCancelar);
        
        add(panel);
        
    }
    
    
    
    private ArrayList<Usuario> obtenerOponentes(){
        ArrayList<Usuario> todos = gestor.listarActivos();
        ArrayList<Usuario> disponibles = new ArrayList<>();
        for (Usuario u : todos){
            if (!u.getUsuario().equals(jugador1.getUsuario())){
                disponibles.add(u);
            }
        }
        return disponibles;
    }
    
    
    private void iniciarPartida(){
        Usuario jugador2 = (Usuario) comboOponentes.getSelectedItem();
        dispose();
        
        ChessBoard tablero = new ChessBoard(gestor, jugador1, jugador2);
        tablero.setVisible(true);
    }
    
    public boolean tieneOponentesDisponibles(){
        return huboOponentes;
    }
}
