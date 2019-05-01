package land;

import java.awt.Graphics2D;
import java.awt.Image;
import characters.Player;
import window.MainFrame;

public class Obstacle {
    public int x, y;
    int width, height;
    public Image img;
    
    public int ox, oy;
    public int oWidth, oHeight;
    
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
        
        ox = x * MainFrame.blockWidth;
        oy = y * MainFrame.blockHeight;
        oWidth = width * MainFrame.blockWidth;
        oHeight = height * MainFrame.blockHeight;
    }
    
    public void draw(Graphics2D comp){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                comp.drawImage(img, (x + i) * MainFrame.blockWidth - Player.dx,
                        (y + j) * MainFrame.blockHeight - Player.dy, null);
            }
        }
    }
    
    /**
     * This method checks the boundaries
     * of the object.
     */
    public void update(){
        //if the player collides with the obstacle from above
        if(Player.coordY > oy - MainFrame.blockHeight && Player.coordY <= oy + oHeight / 4
                && Player.coordY - Player.speed <= oy - MainFrame.blockHeight
                && Player.coordX > ox - MainFrame.blockWidth
                && Player.coordX <= ox + oWidth)
            Player.move(0, -Player.speed);
        //from the right
        else if(Player.coordX >= ox + (oWidth / 4) * 3 && Player.coordX < ox + oWidth
                && Player.coordX + Player.speed >= ox + oWidth
                && Player.coordY > oy - MainFrame.blockHeight
                && Player.coordY <= oy + oHeight)
            Player.move(Player.speed, 0);
        //from the bottom
        else if(Player.coordY >= oy + (oHeight / 4) * 3 && Player.coordY < oy + oHeight
                && Player.coordY + Player.speed >= oy + oHeight
                && Player.coordX > ox - MainFrame.blockWidth
                && Player.coordX <= ox + oWidth)
            Player.move(0, Player.speed);
        //from the left
        else if(Player.coordX > ox - MainFrame.blockWidth && Player.coordX <= ox + oWidth / 4
                && Player.coordX - Player.speed <= ox - MainFrame.blockWidth
                && Player.coordY > oy - MainFrame.blockHeight
                && Player.coordY <= oy + oHeight)
            Player.move(-Player.speed, 0);
    }
}