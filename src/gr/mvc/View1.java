package gr.mvc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class View1 extends JFrame implements MouseListener, StatusListener {

    private JButton jButton1;
    private CommonModel commonModel;

    public View1() {
        initComponents();
    }

    private void initComponents() {
        jButton1 = new JButton("jButton1");
        add(jButton1);

        jButton1.addMouseListener(this);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setModel(CommonModel commonModel) {
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
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void statusChangedEvent(boolean selected) {
        jButton1.getModel().setPressed(selected);
    }
}
