package characters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import process.Animation;
import window.ImageCenter;
import window.MainFrame;
import window.MainPanel;

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
    int curX, curY;//the spot the person is currently in
    int x, y;
    int dx, dy;
    int speed = 4;
    
    Random ran = new Random();
    boolean casual = true;//if the person isn't interacting with anyone
    int dir;//the direction the player is moving (0-7)
    int path;
    int distanceToPath;//how far along the path the person has moved
    boolean moving;//if the player is moving
    int waitTime, timeWaited;//the time the person has to wait before casually moving again
    
    Animation anim;
    Image img;
    
    public Person(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        curX = startX;
        curY = startY;
        x = MainFrame.blockWidth * startX;
        y = MainFrame.blockHeight * startY;
        
        //set up animation object
        anim = new Animation(8);
        anim.setImages(0, ImageCenter.up0, ImageCenter.up1, ImageCenter.up2, ImageCenter.up1, ImageCenter.up0,
                ImageCenter.up3, ImageCenter.up4, ImageCenter.up3);
        anim.setImages(1, ImageCenter.right0, ImageCenter.right1, ImageCenter.right2, ImageCenter.right1,
                ImageCenter.right0, ImageCenter.right3, ImageCenter.right4, ImageCenter.right3);
        anim.setImages(2, ImageCenter.down0, ImageCenter.down1, ImageCenter.down2, ImageCenter.down1, ImageCenter.down0,
                ImageCenter.down3, ImageCenter.down4, ImageCenter.down3);
        anim.setImages(3, ImageCenter.left0, ImageCenter.left1, ImageCenter.left2, ImageCenter.left1,
                ImageCenter.left0, ImageCenter.left3, ImageCenter.left4, ImageCenter.left3);
        img = anim.getAnim();
    }
    
    public void draw(Graphics2D comp){
        comp.drawImage(img, x - Player.dx, y - Player.dy, null);
    }
    
    public void moveArea(int x, int y){
        startX += x;
        startY += y;
        endX += x;
        endY += y;
        curX += x;
        curY += y;
        this.x += MainFrame.blockWidth * x;
        this.y += MainFrame.blockHeight * y;
    }
    
    /**
     * Applies the direction the player is moving
     * to dx and dy.
     */
    private void applyDirection(){
        if(moving){
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
            }
        } else{
            dx = 0;
            dy = 0;
        }
    }
    
    /**
     * Chooses a casual path for the
     * person to randomly walk in.
     */
    private void chooseCasualPath(){
        if(casual){
            do{
                dir = ran.nextInt(8);
                path = 0;
                
                switch(dir){
                    case 0:
                        if(curY > startY)
                            path = ran.nextInt(curY - startY) + 1;
                        break;
                    case 1:
                        if(curY > startY && curX < endX)
                            path = ran.nextInt(
                            (curY - startY <= endX - curX) ? curY - startY : endX - curX) + 1;
                        break;
                    case 2:
                        if(endX > curX)
                            path = ran.nextInt(endX - curX) + 1;
                        break;
                    case 3:
                        if(endY > curY && endX > curX)
                            path = ran.nextInt(
                            (endY - curY <= endX - curX) ? endY - curY : endX - curX) + 1;
                        break;
                    case 4:
                        if(endY > curY)
                            path = ran.nextInt(endY - curY);
                        break;
                    case 5:
                        if(endY > curY && curX > startX)
                            path = ran.nextInt(
                            (endY - curY <= curX - startX) ? endY - curY : curX - startX);
                        break;
                    case 6:
                        if(curX > startX)
                            path = ran.nextInt(curX - startX) + 1;
                        break;
                    case 7:
                        if(curY > startY && curX > startX)
                            path = ran.nextInt(
                            (curY - startY <= curX - startX) ? curY - startY : curX - startX);
                        break;
                }
            } while(path == 0);
        }
    }
    
    /** Updates the person. */
    public void update(){
        if(casual){
            //make the player wait
            if(!moving && waitTime == 0){
                //wait time is a random value up to two seconds
                waitTime = ran.nextInt(MainPanel.FPS * 2 - 1) + 1;
                chooseCasualPath();//set the path that the person will walk after waiting
                distanceToPath = 0;//reset distanceToPath
            }
            else if(!moving && waitTime > 0){
                timeWaited++;
                if(timeWaited == waitTime){//if the waitTime has been reached
                    timeWaited = 0;
                    waitTime = 0;
                    moving = true;//since wait time has been reached, start moving
                    applyDirection();//set dx and dy
                    
                    //start the animation process
                    anim.setDirection(dir / 2);
                    anim.keepAnimating(true);
                }
            }
            
            else if(moving){
                distanceToPath += speed;
                curX = x / MainFrame.blockWidth;
                curY = y / MainFrame.blockHeight;
                
                /* If the person is moving up or down, take into account that they will
                move faster vertically than horizontally. Knowing this, in the case that
                the person is moving vertically, check if they've moved the path distance
                vertically. If they are only moving horizontally, check if they've moved
                the path distance horizontally.
                */
                if((dir != 2 && dir != 6 && distanceToPath >= (path * MainFrame.blockHeight) - speed)
                        || (distanceToPath >= (path * MainFrame.blockWidth))){
                    moving = false;
                    applyDirection();
                    anim.keepAnimating(false);
                }
            }
            
            x += dx;
            y += dy;
            anim.update();
            img = anim.getAnim();
        }
    }
}