/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import java.util.Calendar;

/**
 *
 * @author user
 */
public class Usuario {
    
 private String usuario;
 private String contrasena;
 private int puntos;
 private Calendar fechaIngreso;
 private boolean activo;
    
    
 public Usuario (String usuario, String contrasena){
     this.usuario = usuario;
     this.contrasena = contrasena;
     this.puntos = 0;
     this.fechaIngreso = Calendar.getInstance();
     this.activo = true;
     
 }   

    public String getUsuario() {
        return usuario;
    }

    public String getcontrasena() {
        return contrasena;
    }

    public int getPuntos() {
        return puntos;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public void sumarPuntos(int cantidad){
        this.puntos += cantidad;
    }
 
    public void cambiarContra(String nuevaContra){
        this.contrasena = nuevaContra;
    }
    
    public void desactiva(){
        this.activo = false;
    }
 
 public String toString(){
     return usuario; 
 }
 
 
 
 
}
