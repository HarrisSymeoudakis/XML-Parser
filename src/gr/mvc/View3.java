package gr.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class View3 extends JFrame implements MouseListener, StatusListener {
    private JLabel jLabel1;
    private CommonModel commonModel;
    
    public View3(){
        initComponents();
    }
    private void initComponents(){
        jLabel1 = new JLabel("jButton1");
        jLabel1.setForeground(Color.red);
        add(jLabel1);
        
        jLabel1.addMouseListener(this);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void setModel(CommonModel commonModel){
        this.commonModel = commonModel;
    }

     @Override
    public void mousePressed(MouseEvent me) {
        jLabel1.setText("true!");
        commonModel.setPressed(this, true);
    }
    @Override
    public void mouseReleased(MouseEvent me) {
        jLabel1.setText("false!");
        commonModel.setPressed(this, false);
    }
    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void statusChangedEvent(boolean selected) {
        jLabel1.setText(""+selected);
    }
}
