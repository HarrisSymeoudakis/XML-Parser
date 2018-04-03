package gr.istl.ui.exp;

import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Main {

    public Main() {
        JFrame jFrame1 = new JFrame();
        Thermometer thermometer1 = new Thermometer();
        
        jFrame1.add(thermometer1);
        
        jFrame1.pack();
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame1.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
}
