package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.MainFrame;

public class Section {
    int x, y;//coordinates of the section
    
    Image groundImg;
    
    public void draw(Graphics2D comp){
        for(int i = 0; i < MainFrame.DIMENSIONX; i++){
            for(int j = 0; j < MainFrame.DIMENSIONY; j++){
                comp.drawImage(groundImg, i * MainFrame.blockWidth - Player.dx,
                        j * MainFrame.blockHeight - Player.dy, null);
            }
        }
    }
    
    /**
     * Used to draw another section
     * beside the section the player is
     * currently in.
     */
    public void draw(Graphics2D comp, int x, int y){
        for(int i = 0; i < MainFrame.DIMENSIONX; i++){
            for(int j = 0; j < MainFrame.DIMENSIONY; j++){
                comp.drawImage(groundImg, i * MainFrame.blockWidth - Player.dx
                        + (x * MainFrame.blockWidth * MainFrame.DIMENSIONX),
                        j * MainFrame.blockHeight - Player.dy
                        + (y * MainFrame.blockHeight * MainFrame.DIMENSIONY), null);
            }
        }
    }
    
    public void update(){
        
    }
    
    void setGroundImage(Image img){
        groundImg = img;
    }
}