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
public class Login extends JFrame {
    private final GestorJugadores gestor;
    private JTextField campoUsuario;
    private JPasswordField campoContra;
    
    
    public Login(GestorJugadores gestor){
        this.gestor = gestor;
        
        setTitle("Vampire Wargame");
        setSize(640,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel ();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        
        JLabel titulo = new JLabel ("Vampire Wargame");
       titulo.setFont(new Font("Fraktur", Font.BOLD,22));
       titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       JLabel labelUsuario = new JLabel("Usuario:");
       labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
       campoUsuario = new JTextField();
       campoUsuario.setMaximumSize(new Dimension(250,30));
       campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       JLabel labelContra = new JLabel ("Contraseña");
       labelContra.setAlignmentX(Component.CENTER_ALIGNMENT);
       campoContra = new JPasswordField();
       campoContra.setMaximumSize(new Dimension(250,30));
       campoContra.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       JButton btnIngresar = new JButton("Iniciar sesión");
       btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
       btnIngresar.addActionListener(e -> intentarLogin());
       
       JButton btnCrear = new JButton ("Crear Usuario");
       btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
       btnCrear.addActionListener(e -> abrirCuentaUsuario());
       
       JButton btnSalir = new JButton("Salir");
       btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
       btnSalir.addActionListener(e -> System.exit(0));
       
       
       panel.add(titulo);
       panel.add(Box.createRigidArea(new Dimension(0,20)));
       panel.add(labelUsuario);
       panel.add(campoUsuario);
       panel.add(Box.createRigidArea(new Dimension(0,10)));
       panel.add(labelContra);
       panel.add(campoContra);
       panel.add(Box.createRigidArea(new Dimension(0,20)));
       panel.add(btnIngresar);
       panel.add(Box.createRigidArea(new Dimension(0,10)));
       panel.add(btnCrear);
       panel.add(Box.createRigidArea(new Dimension(0,10)));
       panel.add(btnSalir);
       
       add(panel);
        
    }
    
    
    private void intentarLogin(){
        String usuarioActual = campoUsuario.getText().trim();
        String contra = new String (campoContra.getPassword());
        
        Usuario usuario = gestor.login(usuarioActual, contra);
        
        
        if (usuario != null){
            dispose();
            new MenuPrincipal(gestor,usuario).setVisible(true);
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos","Error de inicio de sesion", JOptionPane.ERROR_MESSAGE);
            
        }
        
        
    }
    
    private void abrirCuentaUsuario(){
        dispose();
        new CrearUsuario(gestor).setVisible(true);
    }
    
    
    
    
    
}
