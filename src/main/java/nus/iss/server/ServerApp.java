package nus.iss.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {

        int portNumber = 3000;

        if (args.length > 0) {
            portNumber = Integer.parseInt(args[0]);
        }

        String cookieFile = args[1];

        ExecutorService es = Executors.newFixedThreadPool(2);
        try (ServerSocket server = new ServerSocket(portNumber)) {

            while (true) {
                System.out.println("listening to: " + portNumber);
                Socket socket = server.accept();
                // initiate a new thread to handle the client
                CookieClientHandler handler = new CookieClientHandler(socket, cookieFile);
                es.submit(handler);
                System.out.println("Submitted to threadpool");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}