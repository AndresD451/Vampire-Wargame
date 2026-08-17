/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vampire.wargame;

import GUI.Login;
import Usuarios.GestorJugadores;
import GUI.ChessBoard;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class VampireWargame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        GestorJugadores gestor = new GestorJugadores();
        Login login = new Login(gestor);
        login.setVisible(true);
        });
    }
    
}
