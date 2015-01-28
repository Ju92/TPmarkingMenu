/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.canvas.transitions.ReleaseOnShape;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Press;
import java.awt.Color;

/**
 *
 * @author Adrien
 */
public class SM extends CStateMachine {

    private MenuShape shape;

    public SM(CShape shape) {
        super(Main.getCanvasInstance());
        this.shape = (MenuShape) shape;

    }

    public State Init = new State() {
        Transition mousePress = new PressOnShape(BUTTON1, ">> OnShape") {
            @Override
            public boolean guard() {
                System.out.println("coucou guard");
                //return getShape() == shape;
                return true;
            }

            @Override
            public void action() {
                shape.setColor(Color.green);
                System.out.println("coucou ENTER");
            }
        };

    };

    public State OnShape = new State() {
        Transition mouseRelease = new ReleaseOnShape(BUTTON1,">> Init") {
            @Override
            public boolean guard() {
                return getShape() == shape;
            }

            @Override
            public void action() {
                shape.setColor(Color.pink);
                System.out.println("coucou LEAVE");
            }
        };

    };

}
