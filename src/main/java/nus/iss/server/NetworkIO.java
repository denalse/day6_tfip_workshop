package nus.iss.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {

    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public NetworkIO(Socket _s) throws IOException {
        this.is = _s.getInputStream();
        this.dis = new DataInputStream(this.is);
        this.os = _s.getOutputStream();
        this.dos = new DataOutputStream(this.os);

    }

    public String read() throws IOException {
        return dis.readUTF();
    }

    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
    }

    public void close() {

        try {
            dis.close();
            is.close();
            dos.close();
            os.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
