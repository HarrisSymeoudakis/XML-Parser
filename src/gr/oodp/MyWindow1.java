package gr.oodp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class MyWindow1 extends JFrame implements ActionListener, StatusListener {
    private JCheckBox jCheckBox1;
    private ArrayList<StatusListener> listeners;
    
    public MyWindow1() {
        initComponents();
    }
    
    private void initComponents(){
        listeners = new ArrayList<StatusListener>();
        jCheckBox1 = new JCheckBox("Status");
        add(jCheckBox1);
        
        jCheckBox1.addActionListener(this);
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void addStatusListener(StatusListener statusListener){
        listeners.add(statusListener);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).statusChangedEvent(jCheckBox1.isSelected());
        }
    }

    @Override
    public void statusChangedEvent(boolean selected) {
        jCheckBox1.setSelected(selected);
    }
}
