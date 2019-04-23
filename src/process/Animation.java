package process;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This class is used for any
 * animatable object such as a player.
 */
public class Animation {
    //series of animations for each direction
    Image[] up, right, down, left;
    Image anim;//the current animation being used
    int dir;//the direction being used
    
    private int animNo;//how many animations each direction has
    boolean animating;//if true, time is incremented every update()
    //time is incremented every update() if animating == true, and the animation changes when time == maxTime
    int time, maxTime = 50;//maxTime's default is 50, but it can be changed
    int animSeq;//the animation that the image is currently on. Goes from 0 - (animNo - 1)
    
    public Animation(int animNo){
        this.animNo = animNo;
    }
    
    /**
     * Stops or starts animation process
     * based on boolean value.
     */
    void keepAnimating(boolean val){
        animating = val;
    }
    
    /** Sets anim based on the object's direction. */
    private void setAnim(int animNum){
        switch(dir){
            case 0:
                anim = up[animNum];
                break;
            case 1:
                anim = right[animNum];
                break;
            case 2:
                anim = down[animNum];
                break;
            case 3:
                anim = left[animNum];
                break;
        }
    }
    
    /**
     * Sets the direction of the
     * object to be animated.
     */
    void setDirection(int dir){
        this.dir = dir;
        
        setAnim(0);
        
        time = 0;
        animSeq = 0;
    }
    
    /**
     * Sets the animations for the specified
     * direction of the object.
     */
    void setImages(int dir, String ... path) throws IllegalArgumentException {
        if(path.length != animNo){
            System.err.println("Error: setImages() requires the same amount of paths"
                    + " as the number specified in the Animation object's constructor");
            throw new IllegalArgumentException();
        } else if(dir < 0 || dir > 3){
            System.err.println("Error: dir must be 0-3");
            throw new IllegalArgumentException();
        }
        
        for(int i = 0; i < animNo; i++)
            switch(dir){
                case 0:
                    up[i] = (new ImageIcon(path[i])).getImage();
                    break;
                case 1:
                    right[i] = (new ImageIcon(path[i])).getImage();
                    break;
                case 2:
                    down[i] = (new ImageIcon(path[i])).getImage();
                    break;
                case 3:
                    left[i] = (new ImageIcon(path[i])).getImage();
            }
    }
    
    /**
     * changes the maxTime that time has to
     * increment to each update()
     */
    void setTime(int maxTime){
        this.maxTime = maxTime;
    }
    
    void update(){
        if(animating){
            time++;
            
            if(time == maxTime){
                time = 0;
                animSeq++;
                if(animSeq >= animNo)
                    animSeq = 0;
                
                setAnim(animSeq);
            }
        }
    }
}