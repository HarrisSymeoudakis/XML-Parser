package gr.dmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class MyClient extends JFrame implements MouseListener {

    private JButton jButton1;
    private Socket socket;
    PrintWriter out;
    
    private String CLIENT_ID = "3";
            
    public MyClient() {
        try {
            socket = new Socket("localhost", MyServer.SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            ClientReaderThread crthread = new ClientReaderThread(socket);
            crthread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        setTitle(CLIENT_ID);
        jButton1 = new JButton("PressMe-"+CLIENT_ID+"!");

        add(jButton1);

        jButton1.addMouseListener(this);

        pack();
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mousePressed(MouseEvent me) {
        out.println("pressed");
    }
    @Override
    public void mouseReleased(MouseEvent me) {
        out.println("released");
    }
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    class ClientReaderThread extends Thread {

        Socket socket;

        public ClientReaderThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String incomingMessage = in.readLine();
                    System.out.println(incomingMessage);
                    if(incomingMessage.equals("pressed")){
                        jButton1.getModel().setPressed(true);
                    } else if(incomingMessage.equals("released")){
                        jButton1.getModel().setPressed(false);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MyClient();
    }

}
