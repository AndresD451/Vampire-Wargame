/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author user
 */
public class RuletaPanel extends JPanel {
    
    private static final String[] PIEZAS = {
        "Muerte", "Vampiro", "HombreLobo", "HombreLobo", "Vampiro", "Muerte"
    };
    
    private static final int NUM_PIEZAS = PIEZAS.length;
    private static final double ANGULO_POR_PIEZAS = 360.0/NUM_PIEZAS;
    
    private double anguloActual = 0;
    private Image[] imagenesPiezas;
    private Timer timerGiro;
    private boolean[] piezaViva = new boolean[NUM_PIEZAS];
    
    
    public RuletaPanel(){
        setPreferredSize(new Dimension(180, 180));
        setOpaque(false);
        Arrays.fill(piezaViva, true);
        CargarImagenes();
        
    }
    
    public void actualizarVivas(boolean[] vivas){
        System.arraycopy(vivas, 0, piezaViva, 0, NUM_PIEZAS);
        repaint();
    }
    
    private void CargarImagenes(){
        imagenesPiezas = new Image [NUM_PIEZAS];
        for(int i = 0; i < NUM_PIEZAS; i++){
            String ruta = "/FICHAS/ruleta_" + PIEZAS[i].toLowerCase() + ".png";
            URL url = getClass().getResource(ruta);
            if (url != null){
                imagenesPiezas[i] = new ImageIcon(url).getImage();
            }
            else{
                System.out.println("No se encontro: " +ruta);
            }
        }
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int diametro = Math.min(getWidth(), getHeight()) - 10;
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int radio = diametro / 2;
        
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(anguloActual),cx,cy);
        
        g2.setColor(new Color(40,40,40));
        g2.fillOval(cx - radio, cy - radio, diametro, diametro);
        
        
        for(int i = 0; i < NUM_PIEZAS; i++){
            double anguloInicio = i* ANGULO_POR_PIEZAS;
            
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(cx,cy, (int) (cx + radio * Math.cos(Math.toRadians(anguloInicio - 90))),(int) (cy + radio * Math.sin(Math.toRadians(anguloInicio - 90))));
            
            if (imagenesPiezas[i] !=null){
                double anguloCentro = anguloInicio + ANGULO_POR_PIEZAS / 2;
                int imgSize = 40;
                double distanciaCentro = radio * 0.6;
                int ix = (int) (cx + distanciaCentro * Math.cos(Math.toRadians(anguloCentro - 90))- imgSize / 2 );
                int iy = (int) (cy + distanciaCentro * Math.sin(Math.toRadians(anguloCentro - 90)) - imgSize / 2);
                
                
                AffineTransform imgTransform = new AffineTransform();
                imgTransform.translate(ix+imgSize / 2, iy+imgSize / 2);
                imgTransform.rotate(Math.toRadians(anguloCentro));
                imgTransform.translate(-imgSize / 2.0, -imgSize / 2.0);
                
                
                AffineTransform beforeImg = g2.getTransform();
                g2.transform(imgTransform);
                
                if (!piezaViva[i]){
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
                }
                
                
                g2.drawImage(imagenesPiezas[i],0 ,0,imgSize,imgSize,this);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                
                g2.setTransform(beforeImg);
            }
            
        }
        
        g2.setTransform(old);
        
        g2.setColor(Color.RED);
        int[] xs = {cx - 8, cx + 8, cx};
        int[] ys = {cy - radio - 15, cy - radio - 15, cy - radio + 5};
        g2.fillPolygon(xs,ys,3);
        g2.dispose();
        
    }
    
    
    public void girarHacia(String resultado, Runnable onFinish){
        int indiceDestino = 0;
        
        for (int i = 0; i < NUM_PIEZAS;i++){
            if (PIEZAS[i].equals(resultado)){
                indiceDestino = i;
                break;
            }
        }
        
        double anguloPiezasDestino = indiceDestino * ANGULO_POR_PIEZAS + ANGULO_POR_PIEZAS / 2;
        double vueltasExtra = 5 * 360;
        double anguloFinal = anguloActual - (anguloActual % 360) + vueltasExtra - anguloPiezasDestino;
        
        double anguloInicial = anguloActual;
        double distanciaTotal = anguloFinal - anguloInicial;
        
        int duracionMs = 3000;
        int intervaloMs = 20;
        int pasosTotales = duracionMs / intervaloMs;
        int[] paso = {0};
        
        if (timerGiro!= null && timerGiro.isRunning()){
            timerGiro.stop();
        }
        
        timerGiro = new Timer (intervaloMs, null);
        timerGiro.addActionListener(e -> {
            paso[0]++;
            double progreso = (double) paso[0] / pasosTotales;
            double progresoSuavizado = 1 - Math.pow(1-progreso, 3);
            
            anguloActual = anguloInicial + distanciaTotal * progresoSuavizado;
            repaint();
            
            if (paso[0] >= pasosTotales){
                timerGiro.stop();
                anguloActual = anguloFinal;
                repaint();
                if (onFinish != null) onFinish.run();
            }
            
        });
        timerGiro.start();
    }
    
    
    
}
