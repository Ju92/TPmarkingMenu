package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.transitions.DragOnShape;
import fr.lri.swingstates.canvas.transitions.ReleaseOnShape;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.jtransitions.DragOnComponent;
import fr.lri.swingstates.sm.jtransitions.ReleaseOnComponent;
import fr.lri.swingstates.sm.transitions.Drag;
import fr.lri.swingstates.sm.transitions.Press;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
 Machine à état contrôlant le comportement du marking menu
 */
/**
 *
 * @author Juliette
 */
public class MarkingMenuSM extends CStateMachine {

    private Menu menu;
    private ArrayList<MenuItem> listItems;
    private MenuItem selectedComponent;
    private Point2D markingMenuCenter;
    private Point2D newPoint;

    public MarkingMenuSM(Menu menu) {
        super();
        this.menu = menu;
        listItems = menu.getListItems();
        markingMenuCenter = new Point();
        newPoint = new Point();
        selectedComponent = null;

    }
    /**
     * INIT.
     */
    public State Init = new State() {
        // Press of the pen on the left part of the strip
        Transition mousePress = new Press(">> Pressing") {
            @Override
            public boolean guard() {
                return false;
            }

            @Override
            public void action() {
                markingMenuCenter = getPoint();
                // if timer ?
                menu.show();
            }
        };
    };
    /**
     * PRESSING.
     */
    public State Pressing = new State() {
        // Press of the pen on the left part of the strip
        Transition mouseDrag = new Drag(">> Dragging") {
            @Override
            public boolean guard() {
                newPoint = getPoint();
                //true quand on détecte un mouvement > 20, un peu d'hystérèse...
                return (newPoint.distance(markingMenuCenter) > 20);
            }

            @Override
            public void action() {
            }
        };
    };
    /**
     * DRAGGING.
     */
    public State Dragging = new State() {
        // Press of the pen on the left part of the strip
        Transition move = new DragOnShape(BUTTON1) {
            @Override
            public boolean guard() {
                return false; //true tant qu'on est en mode press
            }

            @Override
            public void action() {
                selectedComponent = (MenuItem) getShape();
                //on highlight les menuItems survolés du marking menu
                menu.highlightItem(selectedComponent);

            }
        };
        Transition mouserelease = new ReleaseOnShape(BUTTON1, ">> Init") {
            @Override
            public boolean guard() {
                return true; // true quand on release sur un menuItem
            }

            @Override
            public void action() {
                //disparition de marking menu et actionne le bouton sur lequel il release
                selectedComponent = (MenuItem) getSHape();
                menu.selectItem(selectedComponent);
                menu.hide();
            }
        };

    };

}
