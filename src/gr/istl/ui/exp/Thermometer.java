package gr.istl.ui.exp;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Thermometer extends JPanel {

    private int temperature;
    private float degreeStepInPx;
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, getWidth()/2, getHeight()-1);
        
        degreeStepInPx = getHeight()/50;//o enas vathmos antistoixei se tosa pixels
        
        for (int i = 0; i <= 50; i+=5) {
            g.drawString(""+i, getWidth()/2, (int)(degreeStepInPx*i) );
        }
    }
    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

}
