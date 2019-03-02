package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import window.ImageCenter;
import window.MainFrame;

public class Player {
    public static int x, y;
    public static int dx = 0, dy = 0;//this moves the background behind the player
    static int speed = 4;//how fast the player moves
    
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
    }
}