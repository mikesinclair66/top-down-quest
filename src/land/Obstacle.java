package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.MainFrame;

public class Obstacle {
    int x, y;
    int width, height;
    Image img;
    
    int ox, oy;
    int oWidth, oHeight;
    
    public Obstacle(int x, int y, int width, int height, Image img)
        throws IllegalArgumentException {
        if(width == 0 || height == 0){
            System.err.println("Error: the width or height is too small");
            throw new IllegalArgumentException();
        }
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
    
    void draw(Graphics2D comp){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                comp.drawImage(img, (x + i) * MainFrame.blockWidth - Player.dx,
                        (y + j) * MainFrame.blockHeight - Player.dy, null);
            }
        }
    }
    
    void checkBounds(){
        ox = x * MainFrame.blockWidth;
        oy = y * MainFrame.blockHeight;
        oWidth = width * MainFrame.blockWidth;
        oHeight = height * MainFrame.blockHeight;
        
        int dir = getDirection();
        
        if(Player.coordX < ox + oWidth
                && Player.coordX > (ox + oWidth / 2)
                && Player.coordY > oy - MainFrame.blockHeight && Player.coordY < oy + oHeight)
            Player.move(ox + oWidth - Player.coordX, 0);
        else if(Player.coordX > ox - MainFrame.blockWidth
                && Player.coordX <= (ox + oWidth / 2)
                && Player.coordY > oy - MainFrame.blockHeight && Player.coordY < oy + oHeight)
            Player.move(-(Player.coordX - (ox - MainFrame.blockWidth)), 0);
    }
    
    /**
     * Gets the direction of the player
     * in relation to the obstacle.
     */
    int getDirection(){
        int dir = 0;
        
        return dir;
    }
}