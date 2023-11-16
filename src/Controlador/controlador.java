/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.*;

/**
 *
 * @author alexb
 */
public class controlador implements Runnable, ActionListener, MouseListener {

    
    
    private Socket socket;
    DataOutputStream salida;
    DataInputStream entrada;
    
    //----------------------------------SERVIDOR------------------------------------------
    final String HOST = "localhost";
    final int PUERTO = 5000;
    
    vista ventana;
    Administracion__modelo mod;
    DefaultTableModel modeloTI;

    public controlador(vista ventana, Administracion__modelo mod) {
        this.ventana = ventana;
        this.mod = mod;
        ventana.setVisible(true);
        modeloTI = new DefaultTableModel();
        modeloTI.addColumn("CANDIDATOS");
        
        
        ventana.getBTN_VOTAR().addActionListener(this);
        ventana.getBTN_AGREGA().addActionListener(this);
        ventana.getTABLA_CANDIDATOS().addMouseListener(this);
        
        
        
        Candidatos kl = new Candidatos("juan", "3445");
        
        Object[] columna = new Object[modeloTI.getColumnCount()];
        columna[0] = kl.toString();

        modeloTI.addRow(columna); 
        ventana.getTABLA_CANDIDATOS().setModel(modeloTI);
        
        
        try {

            socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado");
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());

            // Inicia un hilo para leer datos del servidor
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
        

        
        
    }
    

    void guardar_candidatos(String ced,String nom) {
        
      Candidatos kl = new Candidatos(ced, nom);
       Object[] columna = new Object[modeloTI.getColumnCount()];
       columna[0] = kl;

       
        modeloTI.addRow(columna); 
        ventana.getTABLA_CANDIDATOS().setModel(modeloTI);

    }
    
    void guardar_candidatos() {
        
        
        String nombre=ventana.getTXT_NOMBRE().getText();
        String ID=ventana.getTXT_ID().getText();
        String tipo_accion="guardar_candidatos";
        
      
      
      try {

                System.out.println("Conectado");

                salida.writeUTF(tipo_accion);
                salida.writeUTF(nombre);
                salida.writeUTF(ID);
                

            } catch (Exception ex) {
                System.out.println(ex);
            }

       
       

    }

    
    
     public void clickTABLA_CANDIDATOS(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 1) { // Detectar un solo clic
          
            int selectedRow = ventana.getTABLA_CANDIDATOS().getSelectedRow();
            if (selectedRow >= 0) {
                String Codigo = (String) ventana.getTABLA_CANDIDATOS().getValueAt(selectedRow, 0);
                
                try {
                    salida.writeUTF("Votar");
                    salida.writeUTF(Codigo);
                } catch (IOException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
               
           
               
            }
        }
    }
    
    
     
     
     
     
    
    @Override
    public void run() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       if (e.getSource().equals(ventana.getBTN_AGREGA())) {
       
         this.guardar_candidatos();
       }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource().equals(ventana.getTABLA_CANDIDATOS())) {
            clickTABLA_CANDIDATOS(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
