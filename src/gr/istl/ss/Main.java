package gr.istl.ss;

import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.JStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.jtransitions.ClickOnComponent;
import fr.lri.swingstates.sm.jtransitions.EnterOnComponent;
import fr.lri.swingstates.sm.jtransitions.LeaveOnComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Main extends JFrame {
    private JStateMachine smWidgets;
    private JLabel label1, label2, label3;
    private JButton jButton1;
    
    public Main() {
        initComponents();
        smWidgets = new JStateMachine() {
            Color initColor;
            public State out = new State() {
                Transition enter = new EnterOnComponent(">> in") {
                    public void action() {
                        initColor = getComponent().getBackground();
                        getComponent().setBackground(Color.YELLOW);
                    }
                };
            };

            public State in = new State() {
                Transition getClass = new ClickOnComponent(BUTTON1) {
                    public void action() {
                        label1.setText("This widget is a " + getComponent().getClass().getName());
                        label2.setText("Coordinates in top level container: (" + getPoint().getX() + ", " + getPoint().getY() + ")");
                        label3.setText("Coordinates in local component    : (" + getPointInComponent(getComponent()).getX() + ", " + getPointInComponent(getComponent()).getY() + ")");
                    }
                };
                Transition enter = new LeaveOnComponent(">> out") {
                    public void action() {
                        getComponent().setBackground(initColor);
                    }
                };
            };
        };
//        ...
//        smWidgets.attachTo(getContentPane());
        smWidgets.attachTo(jButton1);
        
        StateMachineVisualization smv = new StateMachineVisualization(smWidgets);
        JDialog jDialog1 = new JDialog();
        jDialog1.add(smv);
        jDialog1.pack();
        jDialog1.setVisible(true);
    }
    
    private void initComponents(){
        label1 = new JLabel(" ");
        label2 = new JLabel(" ");
        label3 = new JLabel(" ");
        jButton1 = new JButton("PressMe!");
        
        JPanel jPanel1 = new JPanel();
        add(jPanel1, BorderLayout.SOUTH);
        
        add(jButton1, BorderLayout.NORTH);
        
        jPanel1.add(label1);
        jPanel1.add(label2);
        jPanel1.add(label3);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
