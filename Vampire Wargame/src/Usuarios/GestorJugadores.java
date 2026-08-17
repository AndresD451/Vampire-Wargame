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
public class GestorJugadores implements RepositorioDatos {
    
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<PartidaLog> historial = new ArrayList<>();
    
    public GestorJugadores(){
        usuarios = new ArrayList<>();
    }
    
    
    
    @Override
    public Usuario buscarPorUsuario(String usuario){
        for (Usuario u : usuarios){
            if (u.getUsuario().equals(usuario)){
                return u;
            }
        }
        return null;
    }
    
    
    @Override
   public boolean existeUsuario(String usuario){
       return buscarPorUsuario(usuario) != null;
   }
    
    @Override
    public boolean agregarJugador (Usuario usuario){
        if (existeUsuario(usuario.getUsuario())){
            return false;
        }
        usuarios.add(usuario);
        return true;
    }
    
    @Override
    public ArrayList<Usuario> listarActivos(){
        ArrayList<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios){
            if (u.isActivo()){
                activos.add(u);
            }
        }
        return activos;
    }    
    
    
    @Override
    public int getCantidad(){
        return usuarios.size();
    }
    
    public Usuario login (String usuario, String contrasena){
        Usuario u = buscarPorUsuario(usuario);
        if (u != null && u.getcontrasena().equals(contrasena) && u.isActivo()){
            return u;
        }
        return null;
    }
    
    
    public void registrarPartida (String mensaje, String jugador1, String jugador2){
        historial.add(new PartidaLog(mensaje,jugador1));
        historial.add(new PartidaLog(mensaje,jugador2));
    }
    
    public ArrayList<PartidaLog> obtenerHistorial(String usuario){
        ArrayList<PartidaLog> resultado = new ArrayList<>();
        for (int  i = historial.size() - 1; i>=0; i--){
            PartidaLog log = historial.get(i);
            if (log.getJugadorRelacionado().equals(usuario)){
                resultado.add(log);
            }
        }
        return resultado;
    }
    
    public ArrayList<Usuario> obtenerRanking(){
        ArrayList<Usuario> ranking = new ArrayList<>(listarActivos());
        ranking.sort((a,b) -> b.getPuntos() - a.getPuntos());
        return ranking;
    }
    
    
}