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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class CrearUsuario extends JFrame {
    private final GestorJugadores gestor;
    private JTextField campoUsuario;
    private JPasswordField campoContra;
    
    
     public CrearUsuario (GestorJugadores gestor){
         this.gestor = gestor;
         
         setTitle("Crear usuario");
         setSize(640,480);
         setLocationRelativeTo(null);
         setResizable(false);
         
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
         
         JLabel titulo = new JLabel ("Crear Nueva Cuenta");
         titulo.setFont(new Font("Fraktur",Font.BOLD,20));
         titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         
         JLabel labelUsuario = new JLabel ("Usuarios:");
         labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
         campoUsuario = new JTextField();
         campoUsuario.setMaximumSize(new Dimension (250,30));
         campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JLabel labelContra = new JLabel("Contraseña (Solo incluir 5 caracteres)");
         labelContra.setAlignmentX(Component.CENTER_ALIGNMENT);
         campoContra = new JPasswordField();
         campoContra.setMaximumSize(new Dimension(250,30));
         campoContra.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JButton btnCrear = new JButton ("Crear Cuenta");
         btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
         btnCrear.addActionListener(e-> intentarCrear());
         
         JButton btnVolver = new JButton ("Volver a la pantalla de inicio");
         btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
         btnVolver.addActionListener(e -> volverLogin());
         
         
         panel.add(titulo);
         panel.add(Box.createRigidArea(new Dimension(0,20)));
         panel.add(labelUsuario);
         panel.add(campoUsuario);
         panel.add(Box.createRigidArea(new Dimension(0,10)));
         panel.add(labelContra);
         panel.add(campoContra);
         panel.add(Box.createRigidArea(new Dimension(0,20)));
         panel.add(btnCrear);
         panel.add(Box.createRigidArea(new Dimension(0,10)));
         panel.add(btnVolver);
         
         add(panel);
         
         
         
     }
    
    
    
    private void intentarCrear(){
        String usuario = campoUsuario.getText().trim();
        String contrasena = new String(campoContra.getPassword());
        
        
        if (usuario.isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe entrar un nombre de usuario");
            return;
        }
        
        if (contrasena.length() != 5){
            JOptionPane.showMessageDialog(this, "La contraseña debe contener exactamente 5 caracteres");
            return;
            }
        
        if (gestor.existeUsuario(usuario)){
            JOptionPane.showMessageDialog(this, "Nombre de usuario ya existe, porfavor elegir otro");
            return;
        
        }
        
        Usuario nuevoUsuario = new Usuario (usuario,contrasena);
        gestor.agregarJugador(nuevoUsuario);
        
        dispose();
        new MenuPrincipal(gestor, nuevoUsuario).setVisible(true);
        
        
        
    }
    
    
    private void volverLogin(){
        dispose();
        new Login(gestor).setVisible(true);
    }
    
}
