package characters;

import java.awt.Image;
import java.util.Random;
import process.Animation;
import window.MainFrame;

public class Person {
    Random ran = new Random();
    
    public int startX, startY;
    public int endX, endY;
    int curBlockX, curBlockY;//the current block the person is on
    
    int speed = 4;
    int x, y;
    int dx, dy;
    
    int path;//number of blocks to move
    int dir;//the direction the player moves (0-3)
    
    boolean casual = true;//if the player is walking freely
    Animation anim;
    Image img;
    
    boolean moving;//if the player should be moving
    int waitCounter, waitTime;//the random time the npc waits in between moves
    int distanceMoved;//distance from when the person first set out on their path
    
    public Person(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        curBlockX = startX;
        curBlockY = startY;
        this.endX = endX;
        this.endY = endY;
        x = curBlockX * MainFrame.blockWidth;
        y = curBlockY * MainFrame.blockHeight;
        anim = new Animation(8);
    }
    
    /**
     * This moves the person's casual walk
     * area the specified amount of blocks
     */
    public void moveArea(int x, int y){
        startX += x;
        startY += y;
        endX += x;
        endY += y;
        curBlockX += x;
        curBlockY += y;
        this.x += x * MainFrame.blockWidth;
        this.y += y * MainFrame.blockHeight;
    }
    
    void choosePath(){
        do{
            dir = ran.nextInt(8);
            switch(dir){
                case 0:
                case 1:
                case 7:
                    if(curBlockY - startY > 0)
                        path = ran.nextInt(curBlockY - startY) + 1;
                    else
                        path = 0;//restart loop
                    anim.setDirection(0);
                    break;
                case 2:
                    if(endX - curBlockX > 0)
                        path = ran.nextInt(endX - curBlockX) + 1;
                    else
                        path = 0;
                    anim.setDirection(1);
                    break;
                case 3:
                case 4:
                case 5:
                    if(endY - curBlockY > 0)
                        path = ran.nextInt(endY - curBlockY) + 1;
                    else
                        path = 0;
                    anim.setDirection(2);
                    break;
                case 6:
                    if(curBlockX - startX > 0)
                        path = ran.nextInt(curBlockX - startX) + 1;
                    else
                        path = 0;
                    anim.setDirection(3);
                    break;
            }
        } while(path == 0);
        anim.keepAnimating(true);
    }
    
    void update(){
        if(!moving){
            if(waitTime == 0)
                waitTime = ran.nextInt(25) + 5;
            if(++waitCounter == waitTime){
                moving = true;
                waitCounter = 0;
                waitTime = 0;
                distanceMoved = 0;
            }
        }
        
        if(casual && moving){
            switch(dir){
                case 0:
                    dx = 0;
                    dy = -speed;
                    break;
                case 1:
                    dx = speed;
                    dy = -speed;
                    break;
                case 2:
                    dx = speed;
                    dy = 0;
                    break;
                case 3:
                    dx = speed;
                    dy = speed;
                    break;
                case 4:
                    dx = 0;
                    dy = speed;
                    break;
                case 5:
                    dx = -speed;
                    dy = speed;
                    break;
                case 6:
                    dx = -speed;
                    dy = 0;
                    break;
                case 7:
                    dx = -speed;
                    dy = -speed;
                    break;
                default:
                    dx = 0;
                    dy = 0;
                    break;
            }
            
            if((dx < 0 && x >= startX * MainFrame.blockWidth + speed)
                    || (dx > 0 && x <= endX * MainFrame.blockWidth - speed))
                x += dx;
            if((dy < 0 && y >= startY * MainFrame.blockHeight + speed)
                    || (dy > 0 && y <= endY * MainFrame.blockHeight - speed))
                y += dy;
        }
    }
}