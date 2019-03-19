package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.MainFrame;

public class Section {
    int x, y;//coordinates of the section
    
    Image groundImg;
    
    Building[] buildings = new Building[0];
    
    void addBuilding(Building b){
        //increase the buildings array's size by 1
        Building[] temp = new Building[buildings.length + 1];
        System.arraycopy(buildings, 0, temp, 0, buildings.length);
        buildings = temp;
        
        buildings[buildings.length - 1] = b;
    }
    
    public void draw(Graphics2D comp){
        for(int i = 0; i < MainFrame.DIMENSIONX; i++){
            for(int j = 0; j < MainFrame.DIMENSIONY; j++){
                comp.drawImage(groundImg, i * MainFrame.blockWidth - Player.dx,
                        j * MainFrame.blockHeight - Player.dy, null);
            }
        }
        
        for(Building b : buildings)
            b.draw(comp);
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
        for(Building b : buildings)
            b.update();
    }
    
    void setGroundImage(Image img){
        groundImg = img;
    }
}