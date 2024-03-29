package nus.iss.server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable {

    private Socket sock;
    private String cookieFile;

    public CookieClientHandler(Socket s, String _cookieFile) {
        this.sock = s;
        this.cookieFile = _cookieFile;
    }

    public void run() {
        System.out.println("Starting a client thread");
        NetworkIO netIO = null;
        try {
            netIO = new NetworkIO(sock);
            String req = "";
            String randomCookieResp = "";
            while (true) {
                req = netIO.read();
                System.out.printf("[client] %s\n", req);
                if (req.trim().equals("exit"))
                    break;
                if (req.trim().equals("get-cookie")) {
                    System.out.printf("file -> %s\n", this.cookieFile);

                    randomCookieResp = Cookie.getRandomCookie(this.cookieFile);
                    netIO.write("cookie-text, " + randomCookieResp);
                    break;
                } else {
                    netIO.write("error, invalid command");
                    break;
                }
            }
            netIO.close();
            sock.close();

            // System.out.println("Exiting the thread !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
