package gr.mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.black);
        }
        g.drawOval(0, 0, getWidth(), getHeight());
        g.drawString(getText(), getWidth() / 2, getHeight() / 2);
    }

    // Hit detection.
    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        // If the button has changed size, make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

}
