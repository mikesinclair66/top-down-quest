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
    
    public int animNo;//how many animations each direction has
    public boolean animating;//if true, time is incremented every update()
    //time is incremented every update() if animating == true, and the animation changes when time == maxTime
    int time, maxTime = 4;//maxTime's default is 50, but it can be changed
    public int animSeq;//the animation that the image is currently on. Goes from 0 - (animNo - 1)
    
    public int cycles;//the amount of cycles the animation has gone through
    
    public Animation(int animNo){
        up = new Image[animNo];
        right = new Image[animNo];
        down = new Image[animNo];
        left = new Image[animNo];
        this.animNo = animNo;
    }
    
    /**
     * Stops or starts animation process
     * based on boolean value.
     */
    public void keepAnimating(boolean val){
        animating = val;
    }
    
    /**
     * Gets the image for the object's
     * current animation.
     */
    public Image getAnim(){
        return anim;
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
    public void setDirection(int dir){
        if(dir != this.dir){
            this.dir = dir;
        
            setAnim(0);

            time = 0;
            animSeq = 0;
        }
    }
    
    /**
     * Resets the animation variables.
     */
    public void resetAnim(){
        setAnim(0);
        time = 0;
        animSeq = 0;
    }
    
    /**
     * Sets the object's image to the
     * specified image.
     */
    public void setImage(Image img){
        anim = img;
    }
    
    /**
     * Sets the animations for the specified
     * direction of the object.
     */
    public void setImages(int dir, Image ... imgs) throws IllegalArgumentException {
        if(imgs.length != animNo){
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
                    up[i] = imgs[i];
                    break;
                case 1:
                    right[i] = imgs[i];
                    break;
                case 2:
                    down[i] = imgs[i];
                    if(i == 0)
                        anim = down[0];//once the down image is set, make it the object's default if the default isn't set
                    break;
                case 3:
                    left[i] = imgs[i];
                    break;
            }
    }
    
    /**
     * changes the maxTime that time has to
     * increment to each update()
     */
    public void setTime(int maxTime){
        this.maxTime = maxTime;
    }
    
    public void update(){
        if(animating){
            time++;
            
            if(time == maxTime){
                time = 0;
                animSeq++;
                if(animSeq >= animNo){
                    animSeq = 0;
                    cycles++;
                }
                
                setAnim(animSeq);
            }
        }
    }
}