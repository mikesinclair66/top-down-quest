package land.entities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import land.Obstacle;
import characters.Player;
import window.Game;
import window.ImageCenter;
import window.MainFrame;

/**
 * This class contains the computer
 * and its functions.
 */
public class Computer extends Obstacle {
    //if the computer is being used or not
    public static boolean inUse;
    
    public Computer(int x, int y){
        super(x, y, 1, 1, ImageCenter.computer);
    }
    
    @Override
    public void draw(Graphics2D comp){
        if(!inUse)
            comp.drawImage(img, x * MainFrame.blockWidth - Player.dx,
                y * MainFrame.blockHeight - Player.dy, null);
        else {
            comp.setStroke(new BasicStroke(10));
            
            //screen
            comp.setColor(Color.black);
            comp.fillRect(0, 0, MainFrame.width, MainFrame.height);
            
            //screen frame
            comp.setColor(Color.gray);
            comp.drawRect(5, 5, MainFrame.width - 10, MainFrame.height - 10);
            
            comp.setStroke(new BasicStroke(1));
        }
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
    
    public static void keyPressed(int key){
        
    }
    
    public static void keyReleased(int key){
        /*
        Player.interact is set to false when
        the player first interacts with the
        computer and lets go of e. This way,
        the player doesn't exit out of the
        computer the first time they let go
        of e after interacting with the computer.
        */
        if(!Player.interact)
            switch(key){
                case KeyEvent.VK_E:
                case KeyEvent.VK_ESCAPE:
                    inUse = false;
                    Game.focus = true;
                    break;
            }
        
        if(Player.interact)
            Player.interact = false;
    }
}