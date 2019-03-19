package land;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import player.Player;
import window.Game;
import window.MainFrame;

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
    boolean open;//true if the door is open
    int animationCount;//used in animation() to time the animation
    
    int x, y;//these are set by either a room or area object
    
    Image anim, anim2, anim3;//door animations
    Image img;
    
    //determines which side of the room the door is on (only applicable to rooms)
    int roomSide;
    
    //the room number that the door is associated with; used for outside doors
    int roomCode;
    
    boolean leadsOutside;//true if the door leads outside
    
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
        AffineTransform standard = comp.getTransform();
        
        //based on the comp rotation, these variables push the door to its original spot
        int offX = 0, offY = 0;
        
        switch(roomSide){
            case 1:
                comp.rotate(Math.toRadians(90), x + MainFrame.blockWidth / 2
                        - Player.dx, y + MainFrame.blockHeight / 2 - Player.dy);
                offY = MainFrame.blockHeight / 6;
                break;
            case 2:
                comp.rotate(Math.toRadians(180), x + MainFrame.blockWidth / 2
                        - Player.dx, y + MainFrame.blockHeight / 2 - Player.dy);
                offX = MainFrame.blockHeight / 8;
                break;
            case 3:
                comp.rotate(Math.toRadians(270), x + MainFrame.blockWidth / 2
                        - Player.dx, y + MainFrame.blockHeight / 2 - Player.dy);
                offY = MainFrame.blockHeight / 5;
                break;
        }
        
        comp.drawImage(img, x + offX - Player.dx, y + offY - Player.dy, null);
        
        comp.setTransform(standard);
    }
    
    public void animate(){
        if(!open){
            if(Player.canInteract(x, y, roomSide)){
                openAnimation = true;
                Game.focus = false;
                Player.setDirection(0);//stop the player
            }
        } else {
            if(!Game.focus)
                movePlayerHere();
        }
        
        if(openAnimation){
            animationCount++;
            
            if(animationCount == 8)
                img = anim2;
            else if(animationCount == 16){
                img = anim3;
                animationCount = 0;
                openAnimation = false;
                open = true;
            }
        }
        
        else if(closeAnimation){
            animationCount++;
            
            if(animationCount == 8)
                img = anim2;
            else if(animationCount == 16){
                img = anim;
                animationCount = 0;
                closeAnimation = false;
                open = false;
            }
        }
    }
    
    /**
     * Moves the player into the door.
     * Used when focus is turned off.
     */
    private void movePlayerHere(){
        if(Player.coordX < x)
            Player.setDirection(3);
        else if(Player.coordX > x + Player.speed)
            Player.setDirection(7);
        else
            Player.setDirection(1);
    }
    
    /**
     * Sets the image for an non-openable door.
     */
    public void setImages(Image img) throws IllegalArgumentException {
        if(openable){
            System.err.println("Error: an openable door must have 3 animations.");
            throw new IllegalArgumentException();
        } else 
            anim = img;
        
        this.img = anim;
    }
    
    /**
     * Sets the image for an openable door.
     */
    public void setImages(Image img, Image img2, Image img3)
        throws IllegalArgumentException {
        if(!openable){
            System.err.println("Error: A non-openable door must have only 1 image.");
            throw new IllegalArgumentException();
        } else{
            anim = img;
            anim2 = img2;
            anim3 = img3;
        }
        
        this.img = anim;
    }
    
    /**
     * Sets the room code that the door is associated with.
     * This is used with doors on the outside
     */
    void setRoomCode(int roomCode){
        this.roomCode = roomCode;
    }
    
    /**
     * Makes the door the door that leads the player
     * outside. When the player comes inside, they
     * come through this door. This is used with
     * doors on the inside.
     */
    void setOutsideDoor(boolean val){
        leadsOutside = val;
    }
}