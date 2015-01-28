/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.Canvas;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main {

    private static Canvas canvas;
    private JFrame frame;
    private CShape shapeBase;
    private SMbase sm;

    public Main() {
        initComponents();
        // on peut ici mettre n'importe quelle shape
        shapeBase = canvas.newRectangle(500, 500, 200, 200);
        sm = new SMbase(canvas, shapeBase, 6);

    }

    private void initComponents() {
        canvas = new Canvas(1000, 1000);
        frame = new JFrame("Super appli");
        canvas.setBounds(0, 0, 1000, 1000);
        frame.setSize(canvas.getWidth(), canvas.getHeight());
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocusInWindow();
    }

    public static void main(String... args) {
        Main main = new Main();
    }

    /**
     * Return the canvas instance
     *
     * @return the canvas instance
     */
    public static Canvas getCanvasInstance() {
        return canvas;
    }
}
