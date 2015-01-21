package Classes;


import java.awt.geom.Point2D;
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
public class Menu {

    private ArrayList<MenuShape> listItems;

    public Menu() {
        super();
    }

    //apparition du menu
    public void show(Point2D center) {
        for(MenuShape item : listItems){
            //affichage de chaque item selon le découpage du camembert
        }
    }
    //disparition du menu
    public void hide(){
        for(MenuShape item : listItems){
            //cacher chaque item
        }
    }

    public ArrayList<MenuShape> getListItems() {
        return listItems;
    }

    //selection d'un des items du menu -> application de son effet
    public void selectItem(MenuShape item) {
        
    }
    
     //highlight d'un des items du menu
    public void highlightItem(MenuShape item) {
        
    }
}
