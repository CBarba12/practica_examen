/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import vista.vista;

/**
 *
 * @author alexb
 */
public class Candidatos {
    
    private String cedula;
    private String nombre;
    private int voto;

    public Candidatos(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.voto = 0;
    }

    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
  
    public void agregar_Voto(){
      voto++;  
    }

    @Override
    public String toString() {
        return   cedula + "  -   " + nombre + " -  " + voto ;
    }

    
    
    
    
    
    public static void main(String[] args) {
        ArrayList<Candidatos> listaCandidatos = new ArrayList<>();

        Candidatos can1=new Candidatos("123", "Juan");
         Candidatos can2=new Candidatos("123", "Juan");
          Candidatos can3=new Candidatos("789", "Pedro");
        
          
          can1.agregar_Voto();
          can1.agregar_Voto();
          can1.agregar_Voto();
          can1.agregar_Voto();
          
          can2.agregar_Voto();
          
          can3.agregar_Voto();
          can3.agregar_Voto();
          can3.agregar_Voto();
          
        listaCandidatos.add(can1);
        listaCandidatos.add(can2);
        listaCandidatos.add(can3);
        
        
        

        System.out.println("Lista antes de ordenar: " + listaCandidatos);
        
     
        // Ordenar de mayor a menor por votos usando un comparador externo
        Collections.sort(listaCandidatos, new VotoComparator());

        System.out.println("Lista después de ordenar: " + listaCandidatos);
        
           for(Candidatos p: listaCandidatos){
            JOptionPane.showMessageDialog(null, p);
        }

       
    }
}


class VotoComparator implements Comparator<Candidatos> {
    @Override
    public int compare(Candidatos candidato1, Candidatos candidato2) {
        // Compara los votos de mayor a menor
        return Integer.compare(candidato2.getVoto(), candidato1.getVoto());
    }
}






