package gr.oodp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class MyWindow3 extends JFrame implements MouseListener, StatusListener {

    private JLabel jLabel1;
    private ArrayList<StatusListener> listeners;

    public MyWindow3() {
        initComponents();
    }

    private void initComponents() {
        listeners = new ArrayList<StatusListener>();
        jLabel1 = new JLabel("StatusBtn");
        add(jLabel1);
        
        jLabel1.addMouseListener(this);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void statusChangedEvent(boolean isSelected) {
        jLabel1.setText("CheckBox state is: "+isSelected);
    }
    
    public void addStatusListener(StatusListener statusListener){
        listeners.add(statusListener);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).statusChangedEvent(true);
        }
    }
    @Override
    public void mouseReleased(MouseEvent me) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).statusChangedEvent(false);
        }
    }
    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
}
