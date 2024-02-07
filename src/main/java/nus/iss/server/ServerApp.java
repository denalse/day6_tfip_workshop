package nus.iss.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    // private static final String COOKIE_FILE = "cookie_file.txt";
    // private static final int port = 12345;
    // // public void makeConnection(int port) {

    // ServerSocket server = new ServerSocket(port);
    // Socket socket = server.accept();

    // }
    // Socket socket = new Socket("localhost", 12345);

    public static void main(String[] args) {
        System.out.println("Server");

        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            String serverPort = args[0];
            String cookieFile = args[1];
            System.out.println("" + serverPort + " " + cookieFile);

            // Create a new Server
            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));

            System.out.println("Cookie Server started on " + serverPort);

            while (true) {
                //accept incoming connections
                socket = server.accept();
                // get data client program as input in bytes
                is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                while (true) {
                    try {
                        String dataFromClient = dis.readUTF();
                        if (dataFromClient.equals("get-cookie")) { 
                            String cookieName = Cookie.getRandomCookie(cookieFile);
                            dos.writeUTF("cookie-text_cookie");
                        } else {
                            dos.writeUTF("Invalid command: " + dataFromClient);
                        }
                    } catch (EOFException e) {
                        System.out.println("Client disconnected");
                        socket.close();
                        break;
                    }

                }


            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
