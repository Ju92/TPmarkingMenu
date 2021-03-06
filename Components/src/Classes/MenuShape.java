package Classes;

import fr.lri.swingstates.canvas.CShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

public class MenuShape extends CShape {

    private static final int DEFAULT_RAYON = 100;
    private static final int DEFAULT_THETA = 0;
    private static final int DEFAULT_ALPHA = 45;

    private int rayon;
    private int alpha;
    private int theta;
    private String text;
    private Point2D center;

    public MenuShape(int rayon,
            int alpha,
            int theta,
            String text) {
        super();
        this.rayon = rayon;
        this.theta = theta;
        this.alpha = alpha;
        this.scaleTo(2 * rayon, 2 * rayon);
        //Place normalement la shape au bon endroit
        this.translateTo(center.getX()-rayon,center.getY()-rayon);
    }

    public MenuShape() {
        this(DEFAULT_RAYON, DEFAULT_ALPHA, DEFAULT_THETA,"");
    }

    @Override
    public void paint(Graphics _g) {
        Graphics2D g = (Graphics2D) _g;
        int x = (int) ((double) (rayon) * Math.cos(degToRad(theta)));
        int y = -(int) ((double) rayon * Math.sin(degToRad(theta)));
        g.setColor(Color.lightGray);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillArc(0,
                0,
                2*rayon, 2*rayon,
                theta, alpha);
        g.setColor(Color.black);
        g.drawArc(0,
                0,
                2*rayon, 2*rayon,
                theta, alpha);
        g.drawLine(rayon, rayon, rayon + x, rayon + y);
        x = (int) ((double) (rayon) * Math.cos(degToRad(theta+alpha)));
        y = -(int) ((double) rayon * Math.sin(degToRad(theta+alpha)));
        g.drawLine(rayon, rayon, rayon + x, rayon + y);
    }

    private double degToRad(int angle) {
        return Math.PI * (double) angle / 180.;
    }

    /**
     * @return the internalRayon
     */
    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
        this.scaleTo(2*rayon,2*rayon);
        repaint();
    }

    /**
     * @return the alpha
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        repaint();
    }

    /**
     * @return the theta
     */
    public int getTheta() {
        return theta;
    }

    /**
     * @param theta the theta to set
     */
    public void setTheta(int theta) {
        this.theta = theta;
        repaint();
    }

    /**
     * @return the DEFAULT_R1
     */
    public static int getDEFAULT__RAYON() {
        return DEFAULT_RAYON;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
