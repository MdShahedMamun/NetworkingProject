package networkingproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server {

    private static ServerSocket server;
    private static Socket clientSocket[];
    private static ClientThread clientThread[] = new ClientThread[2];

    public Server() throws IOException {

        try {
            int portNo=Integer.parseInt(GameInitializeFrame.portNo);
            server = new ServerSocket(portNo);
            System.out.println("server started ,waiting for clients at port " + portNo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
        clientSocket = new Socket[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("waiting for client...");
            clientSocket[i] = server.accept(); // the method blocks untill a connection is made
            System.out.println("accepted");

        }
        System.out.println("connected...");
        for (int j = 0; j < 2; j++) {
            if (clientThread[j] == null) 
            {
                clientThread[j] = new ClientThread(clientSocket[j], clientThread, j);
            }
            clientThread[j].start();
        }
        server.close();
    }

}
