/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author user
 */
public class PartidaLog {
    private String mensaje;
    private String jugadorRelacionado;
    
    public PartidaLog(String mensaje, String jugadorRelacionado){
        this.mensaje = mensaje;
        this.jugadorRelacionado = jugadorRelacionado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getJugadorRelacionado() {
        return jugadorRelacionado;
    }
    
    @Override
    public String toString(){
        return mensaje;
    }
}
