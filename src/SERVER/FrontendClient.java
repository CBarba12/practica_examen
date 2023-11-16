/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVER;

/**
 *
 * @author alexb
 */
import java.io.*;
import java.net.Socket;

public class FrontendClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            // Configurar lectura y escritura
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Crear hilo para manejar la recepción de actualizaciones
            Thread thread = new Thread(() -> recibirActualizaciones(reader));
            thread.start();

            // Simular votación
            writer.println("VOTAR idCandidato");

            // Simular agregar nuevo candidato
            writer.println("AGREGAR idNuevoCandidato NuevoCandidato");

            // Cerrar la conexión
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirActualizaciones(BufferedReader reader) {
        try {
            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                // Procesar actualizaciones recibidas del servidor
                System.out.println("Actualización recibida: " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

