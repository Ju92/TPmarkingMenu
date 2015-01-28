/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.transitions.DragOnShape;
import fr.lri.swingstates.canvas.transitions.MoveOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.canvas.transitions.ReleaseOnShape;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Drag;
import fr.lri.swingstates.sm.transitions.Leave;
import fr.lri.swingstates.sm.transitions.Move;
import fr.lri.swingstates.sm.transitions.Release;
import fr.lri.swingstates.sm.transitions.TimeOut;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Adrien
 */
public class SMbase extends CStateMachine {

    private CShape shapeBase;
    private ShapeQuartier shapeQuartier;
    private Canvas canvas;
    private double rayon;
    private double x, y;
    private ArrayList<ShapeQuartier> listQuartiers;
    private ShapeQuartier selectedShape;
    private Middle middle;

    public SMbase(Canvas canvas, CShape shapeBase, int nbrQuartiers) {
        super(Main.getCanvasInstance());
        this.canvas = canvas;
        this.shapeBase = shapeBase; // shapeBase = canvas.newRectangle(500, 500, 200, 200);
        canvas.addShape(shapeBase);
        canvas.repaint();
        rayon = 200;
        x = 100;
        y = 100;
        selectedShape = null;
        middle = new Middle(x, y, rayon * 0.2);
        //Creation du PieMenu
        listQuartiers = new ArrayList<>();
        for (int i = 0; i < nbrQuartiers; i++) {
            listQuartiers.add(i, new ShapeQuartier(x, y, rayon, 360 * i / nbrQuartiers, 360 / nbrQuartiers, Color.gray));
        }
    }
    //INIT//
    public State Init = new State() {

        // Transition click gauche : apparition de tout le menu
        Transition MDD = new PressOnShape(BUTTON3, ">> Marking") {
            @Override
            public boolean guard() {
                System.out.println(getButton());
                return getShape() == shapeBase;
            }

            @Override
            public void action() {
                System.out.println("Init >> marking");
                //démarrage du timer, ajout des shape au canvas en invisible
                armTimer(1000, false);
                x = getPoint().getX();
                y = getPoint().getY();
                System.out.println("x : " + x + " y : " + y);
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    shapeQuartier.setVisible(false);
                    shapeQuartier.setX(x);
                    shapeQuartier.setY(y);
                    canvas.addShape(shapeQuartier);

                }
                middle.setX(x);
                middle.setY(y);
                middle.setVisible(false);
                canvas.addShape(middle);
                canvas.repaint();

            }

        };

    };

    //MARKING//
    public State Marking = new State() {

        Transition Timer = new TimeOut(">> Visible") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                System.out.println("marking >> Visible");
                //apparition de menuShape
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    shapeQuartier.setVisible(true);
                }
                middle.setVisible(true);
                canvas.repaint();
                disarmTimer();
            }

        };
        Transition MUD = new Release(BUTTON3, ">> Menu") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                System.out.println("marking >> Menu");
                //apparition de menuShape
                System.out.println("x : " + x + " y : " + y);
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    shapeQuartier.setVisible(true);
                }
                middle.setVisible(true);
                canvas.repaint();

            }
        };

        Transition MM = new Move(">> Invisible") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                System.out.println("marking >> Invisible");
            }

        };

    };

//INVISIBLE //
    public State Invisible = new State() {
        Transition MM = new MoveOnShape(">> Invisible") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                //highlight de l'item survolé
                selectedShape = (ShapeQuartier) getShape();
                selectedShape.setColor(Color.blue);
            }
        };
        Transition MUout = new Release(">> Init") {
            @Override
            public boolean guard() {
                return (selectedShape == null);
            }

            @Override
            public void action() {
                //nothing, init
            }
        };

        Transition MUin = new Release(">> Init") {
            @Override
            public boolean guard() {
                return (selectedShape != null);
            }

            @Override
            public void action() {
                //exec comande, init
                System.out.println("COMMANDE A EXECUTER");
            }
        };

    };

    //VISIBLE //
    public State Visible = new State() {
        Transition MM = new DragOnShape(">> Visible") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                System.out.println("Visible mousemouve");
                //highlight de l'item survolé
                if (getShape() == middle) {
                    for (ShapeQuartier shapeQuartier : listQuartiers) {
                        shapeQuartier.setColor(Color.gray);
                    }
                } else {
                    selectedShape = (ShapeQuartier) getShape();
                    selectedShape.setColor(Color.blue);
                    for (ShapeQuartier shapeQuartier : listQuartiers) {
                        if (selectedShape != shapeQuartier) {
                            shapeQuartier.setColor(Color.gray);
                        }
                    }
                }
                canvas.repaint();
            }

        };
        Transition mouveOut = new Drag(">> Visible") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                System.out.println("Visible mousemouve");
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    shapeQuartier.setColor(Color.gray);
                }
                canvas.repaint();
            }
        };

        Transition MUin = new ReleaseOnShape(">> Init") {
            @Override
            public boolean guard() {

                return ((selectedShape != null) && (getShape() != middle));
            }

            @Override
            public void action() {
                //exec comande, init
                selectedShape = (ShapeQuartier) getShape();
                System.out.println("Visible >> Init");
                System.out.println("COMMANDE A EXECUTER");

            }
        };

        Transition MUout = new Release(">> Init") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                //nothing, init
                System.out.println("Visible >> Init");
            }
        };

    };

//MENU //
    public State Menu = new State() {
        Transition MM = new MoveOnShape(">> Menu") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override

            public void action() {
                System.out.println("Menu >> Menu");
                //highlight de l'item survolé
                if (getShape() == middle) {
                    for (ShapeQuartier shapeQuartier : listQuartiers) {
                        shapeQuartier.setColor(Color.gray);
                    }
                } else if (getShape() != null) {
                    selectedShape = (ShapeQuartier) getShape();
                    selectedShape.setColor(Color.blue);
                    for (ShapeQuartier shapeQuartier : listQuartiers) {
                        if (selectedShape != shapeQuartier) {
                            shapeQuartier.setColor(Color.gray);
                        }
                    }
                }
                canvas.repaint();
            }
        };
        Transition mouveOut = new Move(">> Menu") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override

            public void action() {
                System.out.println("Menu >> Menu");
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    shapeQuartier.setColor(Color.gray);
                }
                canvas.repaint();
            }
        };
        Transition CGout = new ReleaseOnShape(BUTTON1, ">> Init") {
            @Override
            public boolean guard() {
                boolean ok = false;
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    if (getShape() == shapeQuartier) {
                        ok = true;
                    }
                }
                return !ok;
            }

            @Override
            public void action() {
                //nothing, init, eff menu
                System.out.println("Menu >> Init");
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    canvas.removeShape(shapeQuartier);
                    shapeQuartier.setColor(Color.gray);
                }
                canvas.removeShape(middle);
            }
        };

        Transition CGin = new ReleaseOnShape(BUTTON1, ">> Init") {
            @Override
            public boolean guard() {
                return (getShape() != middle);
            }

            @Override
            public void action() {
                //exec comande, init, eff menu
                System.out.println("Menu >> Init");
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    canvas.removeShape(shapeQuartier);
                    shapeQuartier.setColor(Color.gray);
                }
                canvas.removeShape(middle);

                System.out.println("COMMANDE A EXECUTER");

            }
        };
        Transition release = new Release(BUTTON1, ">> Init") {
            @Override
            public boolean guard() {
                return true;
            }

            @Override
            public void action() {
                //nothing, init, eff menu
                System.out.println("Menu >> Init");
                for (ShapeQuartier shapeQuartier : listQuartiers) {
                    canvas.removeShape(shapeQuartier);
                    shapeQuartier.setColor(Color.gray);
                }
                canvas.removeShape(middle);
            }
        };

    };

}
