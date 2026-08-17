/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class FondoPanel extends JPanel {
    private Image fondo;
    
    public FondoPanel(String rutaImagen){
        java.net.URL url = getClass().getResource(rutaImagen);
        if (url != null) {
            fondo = new ImageIcon(url).getImage();
        } else {
            System.out.println("No se encontró la imagen de fondo: " + rutaImagen);
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    
}
