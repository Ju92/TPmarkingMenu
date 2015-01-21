/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import fr.lri.swingstates.canvas.CShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Adrien
 */
public class MenuShape extends CShape {

    private int x;
    private int y;
    private Color color;

    public MenuShape(int x, int y, Color overColor) {
        super(new Rectangle2D.Double(1, 1, 1, 1));
        this.x = x;
        this.y = y;
        this.color = overColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 200, 200);
    }

    public void setColor(Color c) {
        color = c;
    }
}
