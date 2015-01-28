/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.lri.swingstates.canvas.CShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Adrien
 */
public class Middle extends CShape {

    private double x, y, rayon;
    private boolean visible;

    public Middle(double x, double y, double rayon) {
        super(new Ellipse2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon));
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        visible = true;

    }

    @Override
    public void paint(Graphics _g) {
        if (visible) {
            Graphics2D g = (Graphics2D) _g;
            g.setColor(Color.white);
            g.setRenderingHint(renderingHints.KEY_ANTIALIASING, renderingHints.VALUE_ANTIALIAS_ON);
            g.fill(getShape());
            g.setColor(Color.black);
            g.draw(getShape());
        }
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    public void setVisible(boolean bool) {
        visible = bool;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
        this.setShape(new Ellipse2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon));
        repaint();
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
        this.setShape(new Ellipse2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon));
        repaint();
    }

}
