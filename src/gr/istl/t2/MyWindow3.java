package gr.istl.t2;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class MyWindow3 extends JFrame implements CommonBtnAPI {

    private int cols;
    private int rows;

    public MyWindow3(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows * cols; i++) {
            add(new JButton("" + i));
        }

        pack();
        setVisible(true);
    }

    @Override
    public void setPressedButton(int id) {
        for (int i = 0; i < rows * cols; i++) {
            JButton jButtoni = (JButton) getContentPane().getComponent(i);
            if (jButtoni.getText().equals("" + id)) {
                jButtoni.getModel().setPressed(true);
            }
        }
    }

    @Override
    public void setReleasedButton(int id) {
        for (int i = 0; i < rows * cols; i++) {
            JButton jButtoni = (JButton) getContentPane().getComponent(i);
            if (jButtoni.getText().equals("" + id)) {
                jButtoni.getModel().setPressed(false);
            }
        }
    }

}
