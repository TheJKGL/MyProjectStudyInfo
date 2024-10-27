package malakhov.study.network.socket.http_get_request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpServer {

    private static final String GET_REQUEST = "GET /hello HTTP/1.1\r\nHost:localhost\r\n\r\n";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        //DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        try {
            os.write(GET_REQUEST.getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int b;
        while (true) {
            try {
                if ((b = is.read()) == -1) {
                    break;
                }
                System.out.print((char) b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //System.out.println(new String(dataInputStream.readAllBytes()));
        socket.close();
    }
}