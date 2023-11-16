/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author alexb
 */
public class Administracion__modelo {
     ArrayList<Candidatos> p ;

    public Administracion__modelo() {  
        p=new  ArrayList<Candidatos>(); 
    }
    
    void agregar(Candidatos e){
        p.add(e);     
    }
    
      Candidatos buscar(String m) {
        for (Candidatos candidato : p) {
         
            if (candidato.getCedula().equals(m)) {
                return candidato;
            }else{
                return null;
            }
        }
        // Devuelve null si no se encuentra ningún candidato con el nombre proporcionado.
        return null;
    }

    
    
     
     
     
    
}
