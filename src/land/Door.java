package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.Game;
import window.ImageCenter;

/**
 * This class is used for any
 * door or entry point
 */
public class Door {
    /*
    If closed is true, the door is a standard door
    which needs to be opened before being walked
    through. If it is false, the door is an entry
    way that can be walked through without being
    opened. In this case, the door has no animation.
    */
    boolean openable;
    
    boolean openAnimation;//true if the open animation is taking place
    boolean closeAnimation;
    int animationCount;//used in animation() to time the animation
    
    public int x, y;//these are set by either a room or area object
    
    Image img;
    
    /**
     * Constructor for door.
     * @param openable is true
     * if the door has to be
     * opened in order to be walked
     * through.
     */
    public Door(boolean openable){
        this.openable = openable;
    }
    
    public void draw(Graphics2D comp){
        comp.drawImage(img, x - Player.dx, y - Player.dy, null);
    }
    
    public void animateOpen(){
        if(openAnimation){
            animationCount++;
            
            if(animationCount == 50)
                img = ImageCenter.door_brown1;
            else if(animationCount == 100){
                img = ImageCenter.door_brown2;
                animationCount = 0;
                openAnimation = false;
                Game.focus = true;//MAKE THIS SET TO FALSE WHEN THE DOOR FIRST STARTS OPENING
            }
        }
        
        else if(closeAnimation){
            animationCount++;
            
            if(animationCount == 50)
                img = ImageCenter.door_brown1;
            else if(animationCount == 100){
                img = ImageCenter.door_brown;
                animationCount = 0;
                closeAnimation = false;
            }
        }
    }
}