package malakhov.study.network.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        // Here, we create a Socket instance named socket
        Socket clientSocket;
        ServerSocket serverSocket = new ServerSocket(5001);
        System.out.println("Listening for clients...");
        clientSocket = serverSocket.accept();
        String clientSocketIP = clientSocket.getInetAddress().toString();
        int clientSocketPort = clientSocket.getPort();
        System.out.println("[IP: " + clientSocketIP + " ,Port: " + clientSocketPort + "]  " + "Client Connection Successful!");

        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());

        String clientMessage = dataIn.readUTF();
        System.out.println(clientMessage);
        String serverMessage = "Hi this is coming from Server!";
        dataOut.writeUTF(serverMessage);

        dataIn.close();
        dataOut.close();
        serverSocket.close();
        clientSocket.close();
    }
}
