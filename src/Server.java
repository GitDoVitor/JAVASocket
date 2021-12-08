import java.net.*;
import java.io.*;

public class Server {


    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("Conectado ao Cliente...");
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String fileName = objectInputStream.readUTF();
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                byte[] buffer = new byte[4096];

                while (true) {
                    int len = objectInputStream.read(buffer);
                    if (len == -1) break;

                    fileOutputStream.write(buffer, 0, len);
                }

                fileOutputStream.flush();
                fileOutputStream.close();
                System.out.println("Arquivo recebido!!! :)");
                socket.close();
            }

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


}
