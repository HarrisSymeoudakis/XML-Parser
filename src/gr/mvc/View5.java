package gr.mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class View5 extends JFrame implements MouseListener, StatusListener {
    private RoundButton jRoundButton1;
    private CommonModel commonModel;
    
    public View5(){
        initComponents();
    }
    private void initComponents(){
        jRoundButton1 = new RoundButton("text");
        jRoundButton1.setForeground(Color.green);
        add(jRoundButton1);
        
        jRoundButton1.addMouseListener(this);
        
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
        commonModel.setPressed(this, true);
    }
    @Override
    public void mouseReleased(MouseEvent me) {
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
        jRoundButton1.getModel().setPressed(selected);
    }
}
