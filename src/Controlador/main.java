/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import Modelo.*;
import java.awt.Color;
import vista.*;

/**
 *
 * @author alexb
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Administracion__modelo p=new Administracion__modelo();
        vista j=new vista();
        
        controlador contro=new controlador(j, p);
//        contro.agregarFila("hjf", "fd", Color.blue);
        
        
    }
    
}
