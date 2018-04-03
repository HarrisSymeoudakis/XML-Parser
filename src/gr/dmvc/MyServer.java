package gr.dmvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class MyServer extends Thread {

    private ArrayList<SocketHandler> clients;
    public static final int SERVER_PORT = 9090;
    private boolean serverAlive = true;

    public MyServer() {
        start();
    }

    @Override
    public void run() {
        clients = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            while (serverAlive) {
                SocketHandler socketHandler = new SocketHandler(serverSocket.accept());
                clients.add(socketHandler);
                socketHandler.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class SocketHandler extends Thread {

        Socket socket;
        PrintWriter out = null;
        BufferedReader in = null;
        boolean alive = true;

        SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (alive) {
                    String incomingMessage = in.readLine();
                    System.out.println(incomingMessage);
                    for (int i = 0; i < clients.size(); i++) {
                        if (clients.get(i).getSocket() != socket) {
                            clients.get(i).getPrintWriter().println(incomingMessage);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            out.close();
        }

        Socket getSocket() {
            return socket;
        }
        PrintWriter getPrintWriter() {
            return out;
        }

        void terminate() {
            alive = false;
        }
    }

    public static void main(String[] args) {
        new MyServer();
    }
}
