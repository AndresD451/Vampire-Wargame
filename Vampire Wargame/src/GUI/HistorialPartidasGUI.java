/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Usuarios.GestorJugadores;
import Usuarios.PartidaLog;
import Usuarios.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author user
 */
public class HistorialPartidasGUI extends JFrame {
     public HistorialPartidasGUI(GestorJugadores gestor, Usuario usuarioActivo) {
        setTitle("Historial de Mis Últimos Juegos");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Mis Últimos Juegos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        ArrayList<PartidaLog> historial = gestor.obtenerHistorial(usuarioActivo.getUsuario());

        if (historial.isEmpty()) {
            modeloLista.addElement("Todavía no has jugado ninguna partida.");
        } else {
            for (PartidaLog log : historial) {
                modeloLista.addElement(log.getMensaje());
            }
        }

        JList<String> lista = new JList<>(modeloLista);
        lista.setEnabled(false);
        JScrollPane scroll = new JScrollPane(lista);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal(gestor, usuarioActivo).setVisible(true);
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBoton, BorderLayout.SOUTH);

        add(panel);
    }
}
