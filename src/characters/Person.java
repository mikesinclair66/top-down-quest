package characters;

import java.util.Random;
import window.MainPanel;

public class Person {
    //the start and end points determine the area the person generally walks in
    int startX, startY;
    int maxX, maxY;
    int[] pathX, pathY;//all the possible paths the player may take
    int curPathX, curPathY;//the current path the player is taking
    boolean startRight, startDown;//if both are true, the player's first movements are down and right
    boolean lastVer;//true if the player's last movement was a vertical movement
    
    //the random time which the player will have to wait in between general movements
    int waitCount, waitTime;
    
    Random ran = new Random();
    
    int x, y;//person's current coordinates
    int dx, dy;
    int speed = 4;
    
    public Person(int startX, int startY, int maxX, int maxY){
        this.startX = startX;
        this.startY = startY;
        //randomly chooses whether the person's first path is vertical or horizontal
        lastVer = (ran.nextInt(2) == 0) ? true : false;
    }
    
    public void setPathX(int ... x){
        pathX = x;
    }
    
    public void setPathY(int ... y){
        pathY = y;
    }
    
    /**
     * Randomly picks a path for the person
     * to walk.
     */
    public void choosePath(){
        //randomizes whether the player moves left/right and up/down
        int hor = (ran.nextInt(2) == 0) ? 1 : -1;
        int ver = (ran.nextInt(2) == 0) ? 1 : -1;
        dx = hor * speed;
        dy = ver * speed;
        
        //out of the path arrays, choose a random value
        curPathX = hor * pathX[ran.nextInt(pathX.length)];
        curPathY = ver * pathY[ran.nextInt(pathY.length)];
    }
    
    public void update(){
        
    }
}