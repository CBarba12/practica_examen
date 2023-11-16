/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVER;

import Modelo.Candidatos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alexb
 */
public class Server {

    private static final List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        List<Candidatos> clientCandidatos = new ArrayList<>();
        try (
                ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor esperando clientes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conectado con cliente de " + clientSocket.getInetAddress());

                ClientHandler client = new ClientHandler(clientSocket, clientCandidatos);

                clients.add(client);

                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        broadcastMessage("hola");

    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}

class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

//    com.mysql.jdbc.PreparedStatement ps;
    ResultSet rs;
    private List<Candidatos> lis_candit;  // Lista de candidatos específica para este cliente

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientHandler(Socket clientSocket, List<Candidatos> lis_candit) {
        try {
            this.socket = clientSocket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.lis_candit = lis_candit;  // Asigna la lista específica para este cliente

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {

            out.writeUTF(message);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAllClients(String message) {
        Server.broadcastMessage(message); // Llama al método broadcastMessage de la clase Server
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensaje_1 = in.readUTF();

                switch (mensaje_1) {
                    case "Votar":
                        votar(in, out);

                        break;
                    case "guardar_candidatos":
                        guardar_candidatos(in, out);
                        break;
                    case "guardarTipoInstrumentos_UPDATE":
//                        actualizarTipoInstrumentos(in, out);
                        break;
                    case "guardarInstrumento":
//                        guardarInstrumento(in, out);
                        break;
                    case "borrarInstrumento":
//                        borrarInstrumento(in, out);
                        break;
                    case "clickInstrumento":
//                        retornar_calibraciones(in, out);
                        break;
                    case "clickTablaCalibracion":
//                        retornar_mediciones(in, out);
                        break;
                    case "INICIAR":
//                        actualizar_jcombobox();
//                        ACTUALIZAR_TODO(in, out);

                        break;

                    default:
                        System.out.println("NO HAY COINCIDENCIA");

                }

                // Procesa y muestra el mensaje al usuario del cliente
//                System.out.println("Mensaje recibido: " + message);
            }
        } catch (IOException e) {
            // Maneja la desconexión del cliente
//            clients.remove(this);

        }
    }

    private void votar(DataInputStream in, DataOutputStream out) throws IOException {

        String cedula = in.readUTF();

        for (Candidatos p : this.lis_candit) {
            if (p.getCedula().equals(cedula)) {
                p.agregar_Voto();
            }
        }
        for (Candidatos p : this.lis_candit) {
            if (!p.getCedula().equals(cedula)) {
                sendMessage("no se encontro el candidato");
            }
        }

    }

    private void guardar_candidatos(DataInputStream in, DataOutputStream out) throws IOException {
        String cedula = in.readUTF();
        String nombre = in.readUTF();

        Candidatos p = new Candidatos(cedula, nombre);
        lis_candit.add(p);

        for (Candidatos jk : lis_candit) {
            JOptionPane.showMessageDialog(null, jk.toString());
        }
    }
}
