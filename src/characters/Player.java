package characters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import process.Animation;
import window.ImageCenter;
import window.MainFrame;

public class Player {
    public static int x, y;
    public static int dx = 0, dy = 0;//this moves the background behind the player
    public static int speed = 4;//how fast the player moves
    public static int coordX, coordY;//x + dx, y + dy
    
    //booleans for if the player is moving
    public static boolean up, right, down, left;
    
    //if the player is attempting to interact
    public static boolean interact;
    
    static Image img;
    public static Animation anim;
    
    public static int curIdX, curIdY;//indicates which section the player is in within their area
    
    public static void draw(Graphics2D comp){
        comp.drawImage(img, x, y, null);
    }
    
    public static void init(){
        img = ImageCenter.down0;
        setCoords(MainFrame.width / 2 - MainFrame.blockWidth / 2,
                MainFrame.height / 2 - MainFrame.blockHeight / 2);
        anim = new Animation(8);
        anim.setImages(0, ImageCenter.up0, ImageCenter.up1, ImageCenter.up2, ImageCenter.up1, ImageCenter.up0,
                ImageCenter.up3, ImageCenter.up4, ImageCenter.up3);
        anim.setImages(1, ImageCenter.right0, ImageCenter.right1, ImageCenter.right2, ImageCenter.right1,
                ImageCenter.right0, ImageCenter.right3, ImageCenter.right4, ImageCenter.right3);
        anim.setImages(2, ImageCenter.down0, ImageCenter.down1, ImageCenter.down2, ImageCenter.down1, ImageCenter.down0,
                ImageCenter.down3, ImageCenter.down4, ImageCenter.down3);
        anim.setImages(3, ImageCenter.left0, ImageCenter.left1, ImageCenter.left2, ImageCenter.left1,
                ImageCenter.left0, ImageCenter.left3, ImageCenter.left4, ImageCenter.left3);
    }
    
    static void setCoords(int x, int y){
        Player.x = x;
        Player.y = y;
    }
    
    /**
     * Returns true if the player is one
     * block size away from a specified
     * spot. Example of use: returns true
     * if the user is one spot away from
     * a door.
     */
    public static boolean canInteract(int spotX, int spotY, int dir){
        /*
        The switch statement checks with side of the player
        that the object is supposed to be, and then returns
        true if the player is a block away from the object
        on that side.
        */
        switch(dir){
            case 0:
                if(coordX >= spotX - MainFrame.blockWidth / 2
                        && coordX <= spotX + MainFrame.blockWidth * (3 / 2)
                        && coordY >= spotY
                        && coordY <= spotY + MainFrame.blockHeight + MainFrame.blockHeight / 4)
                    return true;
                break;
            case 1:
                if(coordX >= spotX - MainFrame.blockWidth && coordX <= spotX
                        && coordY >= spotY - MainFrame.blockWidth / 2
                        && coordY <= spotY + MainFrame.blockWidth * (3 / 2))
                    return true;
                break;
            case 2:
                if(coordX >= spotX - MainFrame.blockWidth / 2
                        && coordX <= spotX + MainFrame.blockWidth * (3 / 2)
                        && coordY >= spotY - MainFrame.blockHeight * 2
                        && coordY <= spotY + MainFrame.blockHeight)
                    return true;
                break;
            case 3:
                if(coordX >= spotX && coordX <= spotX + MainFrame.blockWidth * 2
                        && coordY >= spotY - MainFrame.blockWidth / 2
                        && coordY <= spotY + MainFrame.blockWidth * (3 / 2))
                    return true;
                break;
        }
        
        return false;
    }
    
    public static void keyPressed(int key){
        switch (key) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_E:
                interact = true;
                break;
        }
    }
    
    public static void keyReleased(int key){
        switch (key) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_E:
                interact = false;
                break;
        }
    }
    
    public static void update(){
        if(up && !right && !left)
            dy -= speed;
        else if(up && right && !left){
            dy -= speed;
            dx += speed;
        } else if(up && !right && left){
            dy -= speed;
            dx -= speed;
        }
        
        else if(down && !right && !left)
            dy += speed;
        else if(down && right && !left){
            dy += speed;
            dx += speed;
        } else if(down && !right && left){
            dy += speed;
            dx -= speed;
        }
        
        else if(left && !right)
            dx -= speed;
        else if(!left && right)
            dx += speed;
        
        coordX = x + dx;
        coordY = y + dy;
        
        //animate the player
        if(!anim.animating && (up || right || down || left))
            anim.keepAnimating(true);
        else if(anim.animating && !up && !right && !down && !left)
            anim.keepAnimating(false);
        
        if(up && !down)
            anim.setDirection(0);
        else if(right && !up && !down && !left)
            anim.setDirection(1);
        else if(down && !up)
            anim.setDirection(2);
        else if(left && !up && !right && !down)
            anim.setDirection(3);
        
        anim.update();
        img = anim.getAnim();
    }
    
    /**
     * Moves the player from their current
     * position to the specified amount of
     * pixels.
     */
    public static void move(int spotX, int spotY){
        dx += spotX;
        dy += spotY;
    }
    
    /**
     * Moves the player in the specified
     * direction. (used when focus is false).
     * The specified direction is a clockwise int:
     * 0 - stay still
     * 1-8 clockwise directions
     */
    public static void setDirection(int dir)
        throws IllegalArgumentException {
        switch(dir){
            case 0:
                up = false;
                right = false;
                down = false;
                left = false;
                break;
            case 1:
                up = true;
                right = false;
                down = false;
                left = false;
                break;
            case 2:
                up = true;
                right = true;
                down = false;
                left = false;
                break;
            case 3:
                up = false;
                right = true;
                down = false;
                left = false;
                break;
            case 4:
                up = false;
                right = true;
                down = true;
                left = false;
                break;
            case 5:
                up = false;
                right = false;
                down = true;
                left = false;
                break;
            case 6:
                up = false;
                right = false;
                down = true;
                left = true;
                break;
            case 7:
                up = false;
                right = false;
                down = false;
                left = true;
                break;
            case 8:
                up = true;
                right = false;
                down = false;
                left = true;
                break;
            default:
                System.err.println("Error: directions must be from 0-8.");
                throw new IllegalArgumentException();
        }
    }
}