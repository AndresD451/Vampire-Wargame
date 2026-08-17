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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class MenuPrincipal extends JFrame {
    
    private final GestorJugadores gestor;
    private final Usuario usuarioActivo;
    
    public MenuPrincipal (GestorJugadores gestor, Usuario usuarioActivo){
        this.gestor = gestor;
        this.usuarioActivo = usuarioActivo;
        
        setTitle("Menu Principal");
        setSize(640,480);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        
        JLabel titulo = new JLabel("Bienvenido, " +usuarioActivo.getUsuario());
        titulo.setFont(new Font("Fraktur",Font.BOLD,18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnJugar = new JButton("Jugar Vampire Wargame");
        btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnJugar.addActionListener(e -> jugarPartida());
        
        
        JButton btnCuenta = new JButton("Mi Cuenta");
        btnCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCuenta.addActionListener(e -> abrirMiCuenta());
        
        JButton btnHistorial = new JButton ("Historial");
        btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHistorial.addActionListener(e -> abrirHistorial());
        
        JButton btnCerrarSesion = new JButton ("Cerrar Sesión");
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0,30)));
        panel.add(btnJugar);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnCuenta);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnHistorial);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnCerrarSesion);
        
        add(panel);
        
        
    }
    
    private void jugarPartida(){
        dispose();
        SeleccionOponente pantalla = new SeleccionOponente(gestor,usuarioActivo);
        
        if (pantalla.tieneOponentesDisponibles()){
            pantalla.setVisible(true);
        }
        else{
            new MenuPrincipal(gestor, usuarioActivo).setVisible(true);
        }
    }
    
    private void abrirMiCuenta(){
        dispose();
        new MiCuenta(gestor,usuarioActivo).setVisible(true);
    }
    
    private void abrirHistorial(){
    JOptionPane.showMessageDialog(this, "Luego");
}
    
    private void cerrarSesion(){
        dispose();
        new Login(gestor).setVisible(true);
    }
    
}
