package characters;

import java.util.Random;
import window.MainFrame;

/**
 * Person is a class that is
 * used for CPU's. When the person
 * is not interacting with the player
 * or another CPU, they generally
 * have an area that they casually
 * walk in, this area is set in
 * the Person's instructor.
 */
public class Person {
    int startX, startY;
    int endX, endY;
    int x, y;
    int dx, dy;
    int speed = 4;
    
    Random ran = new Random();
    
    public Person(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        x = MainFrame.blockWidth * startX;
        y = MainFrame.blockHeight * startY;
    }
    
    public void moveArea(int x, int y){
        startX += x;
        startY += y;
        endX += x;
        endY += y;
        this.x += MainFrame.blockWidth * x;
        this.y += MainFrame.blockHeight * y;
    }
    
    /**
     * Chooses a casual path for the
     * person to randomly walk in.
     * Note: the person only casually
     * walks up, right, down, or left.
     */
    void chooseCasualPath(){
        
    }
}