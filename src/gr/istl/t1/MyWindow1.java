package gr.istl.t1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class MyWindow1 extends JFrame implements ActionListener, MouseListener {

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton jButton1;

    public MyWindow1() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel(new GridLayout());
        jPanel2 = new JPanel();
        jLabel1 = new JLabel("rows: ");
        jLabel2 = new JLabel("cols: ");
        jTextField1 = new JTextField(5);
        jTextField2 = new JTextField(5);
        jButton1 = new JButton("Generate");

        jButton1.addActionListener(this);

        jPanel2.add(jLabel1);
        jPanel2.add(jTextField1);
        jPanel2.add(jLabel2);
        jPanel2.add(jTextField2);
        jPanel2.add(jButton1);

        add(jPanel1, BorderLayout.CENTER);
        add(jPanel2, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int rows = Integer.valueOf(jTextField1.getText());
        int cols = Integer.valueOf(jTextField2.getText());
        
        ((GridLayout)jPanel1.getLayout()).setRows(rows);
        ((GridLayout)jPanel1.getLayout()).setColumns(cols);
        
        for (int i = 0; i < rows*cols; i++) {
            JButton jButtoni = new JButton(""+i);
            
            jButtoni.addMouseListener(this);
            
            jPanel1.add(jButtoni);
        }
        jPanel1.validate();
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        String srcText = ((JButton)me.getSource()).getText();
        System.out.println(srcText);
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        String srcText = ((JButton)me.getSource()).getText();
        
    }
    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    
    
    public static void main(String[] args) {
        new MyWindow1();
    }
    
}
