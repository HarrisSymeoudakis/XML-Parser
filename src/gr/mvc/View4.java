package gr.mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class View4 extends JFrame implements ActionListener, StatusListener {
    private JCheckBox jCheckBox1;
    private CommonModel commonModel;
    
    public View4(){
        initComponents();
    }
    private void initComponents(){
        jCheckBox1 = new JCheckBox("JCheckBox1");
        jCheckBox1.setForeground(Color.green);
        add(jCheckBox1);
        
        jCheckBox1.addActionListener(this);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void setModel(CommonModel commonModel){
        this.commonModel = commonModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        commonModel.setPressed(this, jCheckBox1.isSelected());
    }
    
    @Override
    public void statusChangedEvent(boolean selected) {
        jCheckBox1.setSelected(selected);
    }
}
