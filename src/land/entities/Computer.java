package land.entities;

import java.awt.Graphics2D;
import land.Obstacle;
import player.Player;
import window.Game;
import window.ImageCenter;
import window.MainFrame;

/**
 * This class contains the computer
 * and its functions.
 */
public class Computer extends Obstacle {
    //if the computer is being used or not
    boolean inUse;
    
    public Computer(int x, int y){
        super(x, y, 1, 1, ImageCenter.computer);
    }
    
    @Override
    public void draw(Graphics2D comp){
        comp.drawImage(img, x * MainFrame.blockWidth - Player.dx,
                y * MainFrame.blockHeight - Player.dy, null);
    }
    
    @Override
    public void update(){
        //if the computer is not in use, update the obstacle and request player interaction
        if(!inUse){
            super.update();
            if(Player.canInteract(ox, oy + oHeight, 0) && Player.interact){
                inUse = true;
                Game.focus = false;
                Player.setDirection(0);
            }
        } else {
            
        }
    }
}