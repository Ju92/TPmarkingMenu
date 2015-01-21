package Classes;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juliette
 */
public class MarkingMenu {
   //tous les champs sont privés et on a un constructeur vide => pour faire un component 
    
    private ArrayList<MenuItem> listItem;
    private MarkingMenuSM sm;
    private Menu menu;
    
    public MarkingMenu(){
        super();
        menu = new Menu();
        sm = new MarkingMenuSM(menu);
    }
}
