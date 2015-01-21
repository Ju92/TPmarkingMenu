package Classes;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juliette
 */
public class MenuItem extends JLayeredPane {
    
    private final JLabel label;
    private final MenuShape item;
    
    
    public MenuItem(String text,int rayon, int alpha, int theta){
        label = new JLabel(text);
        item = new MenuShape(rayon,alpha,theta);
    }
    
    public MenuItem(){
        label = new JLabel("");
        item = new MenuShape();
    }
    
    
}
