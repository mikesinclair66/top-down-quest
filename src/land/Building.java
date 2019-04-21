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
    
    int secX, secY;
    int buildingNo;//number of buildings
    
    Room room;//the room that the building is connected to
    
    public Building(int x, int y, int width, int height, int length){
        super(x, y, width, height, ImageCenter.wall_wood);
        roofImg = ImageCenter.ground_stone;
        this.length = length;
        oHeight += length * MainFrame.blockHeight;//add the roof to the boundaries
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
            Land.curRoom = room;
            Land.inside = true;
            
            //reset settings as player goes inside
            Player.setDirection(0);
            Game.focus = true;
            entry.img = entry.anim;
            //find the door that leads outside, and put the player in front of it
            for(Door d : Land.curRoom.doors)
                if(d.leadsOutside){
                    //if the door is on the north side of the room
                    if(d.roomSide == 0){
                        Player.dx = -Player.x + d.x;
                        Player.dy = -Player.y + d.y + MainFrame.blockHeight * 2;
                    }
                    break;//since there can only be one door leading outside, end the loop
                }
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
    
    /**
     * Sets the room that the building
     * is connected to.
     */
    void setRoom(Room r){
        room = r;
    }
    
    Building getBuilding(int secX, int secY, int buildingNo){
        return Land.curArea.sections[secX][secY].buildings[buildingNo];
    }
}