/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Usuarios.GestorJugadores;
import Usuarios.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author user
 */
public class MiCuenta extends JFrame {
    
    private final GestorJugadores gestor;
    private final Usuario usuarioActivo;
    
    public MiCuenta(GestorJugadores gestor, Usuario usuarioActivo){
        this.gestor = gestor;
        this.usuarioActivo = usuarioActivo;
        
        setTitle("Mi Cuenta");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        
        FondoPanel panel = new FondoPanel("/FICHAS/fondo_menu.png"); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); 
        
        JLabel titulo = new JLabel ("Mi cuenta");
        titulo.setFont(new Font("Fraktur",Font.BOLD,18));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha = sdf.format(usuarioActivo.getFechaIngreso().getTime());
        
        JLabel infoUsuario = new JLabel ("Puntos: " + usuarioActivo.getPuntos());
        infoUsuario.setForeground(Color.WHITE);
        infoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel infoPuntos = new JLabel ("Puntos: " +usuarioActivo.getPuntos());
        infoPuntos.setForeground(Color.WHITE);
        infoPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel infoFecha = new JLabel("Fecha de ingreso: " +fecha);
        infoFecha.setForeground(Color.WHITE);
        infoFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel infoActivo = new JLabel("Activo: " +(usuarioActivo.isActivo() ? "Si": "No"));
        infoActivo.setForeground(Color.WHITE);
        infoActivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnCambiarContra = new JButton("Cambiar contraseña");
        btnCambiarContra.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarContra.addActionListener(e -> cambiarContrasena());
        
        JButton btnCerrarCuenta = new JButton("Cerrar mi cuenta");
        btnCerrarCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarCuenta.addActionListener(e-> cerrarCuenta());
        
        JButton btnVolver = new JButton("Volver al Menu");
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> volverMenu());
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(infoUsuario);
        panel.add(infoPuntos);
        panel.add(infoFecha);
        panel.add(infoActivo);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(btnCambiarContra);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnCerrarCuenta);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(btnVolver);
        
        add(panel);
        
    }
    
    
    private void cambiarContrasena(){
        JPasswordField campoNueva = new JPasswordField();
        int resultado = JOptionPane.showConfirmDialog(this, campoNueva, "Nueva contraseña (5 caracteres obligatorio)" , JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION){
            String nueva = new String(campoNueva.getPassword());
            if (nueva.length() != 5){
                JOptionPane.showMessageDialog(this, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            usuarioActivo.cambiarContra(nueva);
            JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente");
        }
    }
    
    private void cerrarCuenta(){
        int confirmacion = JOptionPane.showConfirmDialog(this, "Anda seguro que desea cerrar su cuenta? Esta Accion es irreversible!!", "Confirmar cierre de cuenta",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if(confirmacion == JOptionPane.YES_OPTION){
            usuarioActivo.desactiva();
            JOptionPane.showMessageDialog(this, "Su cuenta  ha sido cerrada.");
            dispose();
            new Login(gestor).setVisible(true);
        }
        
    }
    
    private void volverMenu(){
        dispose();
        new MenuPrincipal(gestor, usuarioActivo).setVisible(true);
    }
    
}
