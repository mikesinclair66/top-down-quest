package process;

import java.awt.Graphics2D;

public class Hotbar{
    public static void draw(Graphics2D comp){
        //draw the hotbar
        Inventory.ItemHotbar.draw(comp);
    }
}