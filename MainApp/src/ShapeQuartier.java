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

/**
 *
 * @author Adrien
 */
public class ShapeQuartier extends CShape {

    private double x, y;
    private Color color;
    private double rayon;
    private double startAngle, exitAngle;
    private boolean visible;

    public ShapeQuartier(double x, double y, double rayon, double startAngle, double exitAngle, Color overColor) {
        super(new Arc2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon, startAngle, exitAngle, Arc2D.PIE));
        this.x = x;
        this.y = y;
        this.visible = true;
        this.rayon = rayon;
        overColor = Color.gray;
        this.color = overColor;
        this.startAngle = startAngle;
        this.exitAngle = exitAngle;

    }

    @Override
    public void paint(Graphics _g) {
        if (visible) {
            Graphics2D g = (Graphics2D) _g;
            g.setColor(getColor());
            g.setRenderingHint(renderingHints.KEY_ANTIALIASING, renderingHints.VALUE_ANTIALIAS_ON);
            g.fill(getShape());
            g.setColor(Color.black);
            g.draw(getShape());
        }
    }

    public void setColor(Color c) {
        color = c;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean bool) {
        visible = bool;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
        this.setShape(new Arc2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon, startAngle, exitAngle, Arc2D.PIE));
        repaint();
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
        this.setShape(new Arc2D.Double(x - rayon, y - rayon, 2 * rayon, 2 * rayon, startAngle, exitAngle, Arc2D.PIE));
        repaint();
    }

}
