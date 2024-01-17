package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/17/2024 - 4:49 PM 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ChatServer chatServer;
    private PrintWriter out;
    String username;

    public ClientHandler(Socket socket, ChatServer server) {
        this.clientSocket = socket;
        this.chatServer = server;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            username = in.readLine();
            chatServer.broadcastMessage(username + " has joined the chat.", this);

            String message;
            while ((message = in.readLine()) != null) {
                chatServer.broadcastMessage(username + ": " + message, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatServer.removeClient(this);
            chatServer.broadcastMessage(username + " has left the chat.", this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}

