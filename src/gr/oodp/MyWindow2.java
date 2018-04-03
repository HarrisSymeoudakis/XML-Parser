package gr.oodp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Administrator
 */
public class MyWindow2 extends JFrame implements MouseListener, StatusListener {

    private JButton jButton1;
    private ArrayList<StatusListener> listeners;

    public MyWindow2() {
        initComponents();
    }

    private void initComponents() {
        listeners = new ArrayList<StatusListener>();
        jButton1 = new JButton("StatusBtn");
        add(jButton1);

        jButton1.addMouseListener(this);
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
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
    
    @Override
    public void statusChangedEvent(boolean isSelected) {
        jButton1.getModel().setPressed(isSelected);
    }

}
