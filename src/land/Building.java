package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.Game;
import window.ImageCenter;
import window.MainFrame;

/**
 * This class is used for
 * buildings made outside.
 */
public class Building extends Obstacle {
    int length;//length for the roof
    Image roofImg;
    
    Door entry;
    
    public Building(int x, int y, int width, int height, int length){
        super(x, y, width, height, ImageCenter.wall_wood);
        roofImg = ImageCenter.ground_stone;
    }
    
    public void addDoor(Door d, int x){
        d.x = ox + x * MainFrame.blockWidth;
        d.y = oy + oHeight - MainFrame.blockHeight;
        entry = d;
    }
    
    @Override
    public void draw(Graphics2D comp){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                comp.drawImage(roofImg, (x + i) * MainFrame.blockWidth - Player.dx,
                        (y + j) * MainFrame.blockHeight - Player.dy, null);
            }
            
            for(int j = 0; j < height; j++){
                comp.drawImage(img, (x + i) * MainFrame.blockWidth - Player.dx,
                        (y + length + j) * MainFrame.blockHeight - Player.dy, null);
            }
        }
        
        entry.draw(comp);
    }
    
    @Override
    public void update(){
        entry.animate();
        if(Game.focus || !entry.open){
            super.update();
        }
        //if the door is open and there's no focus, send the player through the door
        else if(Player.coordY <= oy + oHeight - MainFrame.blockHeight){
            //send the player to the door's associated room
            Land.inside = true;
            //TODO send player to room using door code?
            
            //reset settings as player goes inside
            Player.setDirection(0);
            Game.focus = true;
            Player.dx = 0;
            Player.dy = 0;
            entry.open = false;
        }
    }
    
    /**
     * Sets the main images for the
     * building.
     */
    void setImages(Image wall, Image roof){
        img = wall;
        roofImg = roof;
    }
}