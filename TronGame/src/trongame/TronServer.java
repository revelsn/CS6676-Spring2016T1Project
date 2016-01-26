/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trongame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
* A simple socket server
*
*/
public class TronServer {
    
    private ServerSocket serverSocket;
    private int port;
    private ArrayList<String> clientIPs;
    
    public TronServer(int port) {
        this.port = port;
    }
    
    public void start() throws IOException {
        System.out.println("Starting the TRON server at port:" + port);
        serverSocket = new ServerSocket(port);

        Socket client = null;
        
        while(true){
            System.out.println("Waiting for clients...");
            client = serverSocket.accept();
            System.out.println("The following client has connected:"+client.getInetAddress().getCanonicalHostName());
            //A client has connected to this server. Send welcome message
            Thread thread = new Thread(new TronClientHandler(client, this));
            thread.start();
        }     
    }
    
    public ArrayList<String> getConnectedClients(){
        return this.clientIPs;
    }
    
    
    /**
    * Creates a TronServer object and starts the server.
    *
    * @param args
    */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9991;
        
        try {
            // initializing the Socket Server
            TronServer tronServer = new TronServer(portNumber);
            tronServer.start();
            
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}