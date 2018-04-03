package gr.epp.delme;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class Test {

    public Test() {
        ArrayList al = new ArrayList();
        
        al.add(new JButton());
        al.add(new JButton());
        al.add(new JButton());
        al.add(new JButton());
        al.add(new JButton());
        al.add(new JButton());
        al.add(new JButton());
        
        for (int i = 0; i < al.size(); i++) {
            al.get(i);
        }
        al.get(4);
        
    }
    
    public static void main(String[] args) {
        new Test();
    }
    
}
