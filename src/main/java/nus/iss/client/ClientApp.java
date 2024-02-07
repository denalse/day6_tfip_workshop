package nus.iss.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Client");
        String[] connectionInfo = args[0].split(":");
        System.out.println(connectionInfo[0] + " " + connectionInfo[1]);
        InputStream is;
        // OutputStream os;

        try {
            while (true) {
                Socket socket = new Socket(connectionInfo[0], Integer.parseInt(connectionInfo[1]));
                is = socket.getInputStream();

                DataInputStream dis = new DataInputStream(is);
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Console console = System.console();

                String input = console.readLine("Client command to the server: ");

                dos.writeUTF(input);
                dos.flush();

                String response = dis.readUTF();

                if (response.contains("cookie-text_")) {
                    String[] arrRes = response.split(" ");
                    System.out.println("Cookie from server: " + arrRes[1]);
                } else {
                    System.err.println(response);
                }

                is.close();
                os.close();
                socket.close();

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();

        } finally {
            // is.close();
        }

    }

}
