/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.debug.StateMachineVisualization;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Main {

    private static Canvas canvas;
    private JFrame frame;
    private MenuShape shape1;
    private MenuShape shape2;
    private SM sm1, sm2;

    public Main() {
        initComponents();
        shape1 = new MenuShape(100, 100, Color.blue);
        shape2 = new MenuShape(400, 400, Color.yellow);
        canvas.addShape(shape1);
        canvas.addShape(shape2);
        frame.repaint();
        sm1 = new SM(shape1, canvas);
        sm2 = new SM(shape2, canvas);

        //visu sm shape1
        JFrame f = new JFrame("shape1 SM");
        f.setVisible(true);
        f.setSize(300, 300);
        f.setLocation(700, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new GridLayout(1, 2));
        StateMachineVisualization smv;
        smv = new StateMachineVisualization(sm1);
        f.getContentPane().add(smv);
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
        frame.repaint();

    }

    public static void main(String... args) {
        Main main = new Main();
    }
    
    /**
     * Return the canvas instance
     *
     * @return the canvas instance
     */public static Canvas getCanvasInstance() {
        return canvas;
    }
}
