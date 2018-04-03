package gr.rb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Main {

    public Main() {
        JFrame jFrame1 = new JFrame();
        RoundButton roundButton1 = new RoundButton("text");
        roundButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ap) {
                System.out.println("Round button just pressed!");
            }
        });
        jFrame1.add(roundButton1);
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame1.pack();
        jFrame1.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();

    }
}
