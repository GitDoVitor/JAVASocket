import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\João Santos\\Downloads\\");

        try {
            Socket socket = new Socket("localhost", 5000);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("----- Transferindo arquivo -----");
            System.out.println("Nome: " + f.getName());
            System.out.println("Caminho: " + f.getPath());
            objectOutputStream.writeUTF(f.getName());
            objectOutputStream.writeLong(f.length());
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] buffer = new byte[4096];

            while (true) {
                int len = fileInputStream.read(buffer);
                if (len == -1) break;
                objectOutputStream.write(buffer, 0, len);
            }

            objectOutputStream.close();
            socket.close();
            System.out.println("Pronto!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
