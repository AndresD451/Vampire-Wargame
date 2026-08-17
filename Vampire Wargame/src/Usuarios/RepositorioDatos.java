/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface RepositorioDatos {
    boolean agregarJugador (Usuario jugador);
    Usuario buscarPorUsuario (String usuario);
    boolean existeUsuario (String usuario);
    ArrayList<Usuario> listarActivos();
    int getCantidad();
}
