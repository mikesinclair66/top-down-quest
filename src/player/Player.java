package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import window.ImageCenter;
import window.MainFrame;

public class Player {
    public static int x, y;
    public static int dx = 0, dy = 0;//this moves the background behind the player
    public static int speed = 4;//how fast the player moves
    public static int coordX, coordY;//x + dx, y + dy
    
    //booleans for if the player is moving
    static boolean up, right, down, left;
    
    static Image img;
    
    public static void draw(Graphics2D comp){
        comp.drawImage(img, x, y, null);
    }
    
    public static void init(){
        img = ImageCenter.down0;
        setCoords(MainFrame.width / 2 - MainFrame.blockWidth / 2,
                MainFrame.height / 2 - MainFrame.blockHeight / 2);
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
                        && coordY <= spotY + MainFrame.blockHeight * (5 / 2))
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
}