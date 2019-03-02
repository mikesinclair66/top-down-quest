package land;

import java.awt.Graphics2D;
import java.awt.Image;
import player.Player;
import window.MainFrame;

public class Room {
    int width, height;
    
    Image ground, wall;
    
    /*
    When in a room, a block will be drawn
    everywhere except between begX-endX
    and begY-endY. For example, in a 5x5
    room, There will be no blocks between
    X:5-10 and Y:3-8.
    */
    private final int begX, endX, begY, endY;
    
    private int topBound, rightBound, bottomBound, leftBound;
    
    Door[] doors = new Door[0];
    
    /**
     * Constructor for room. Width and height
     * along with its ground and wall image are specified
     * here. Because all images are skewed according
     * to screen dimensions, equal width and height
     * do not make an equally-sized room.
     */
    public Room(int width, int height, Image ground, Image wall){
        this.width = width;
        this.height = height;
        this.ground = ground;
        this.wall = wall;
        
        begX = (MainFrame.DIMENSIONX / 2) - width / 2
                    + ((width % 2 != 0) ? -1 : 0);
        endX = (MainFrame.DIMENSIONX / 2) + width / 2;
        begY = (MainFrame.DIMENSIONY / 2) - height / 2
                    + ((height % 2 != 0) ? -1 : 0);
        endY = (MainFrame.DIMENSIONY / 2) + height / 2;
        
        //set the boundaries for the room
        topBound = -MainFrame.blockHeight / 2 - ((height / 2 - 
                (height % 2 == 0 ? 1 : 0)) * MainFrame.blockHeight) - 1;
        bottomBound = -topBound + (height % 2 == 0 ? MainFrame.blockHeight : 0) - 1;
        leftBound = -MainFrame.blockWidth / 2 - ((width / 2 -
                (width % 2 == 0 ? 1 : 0)) * MainFrame.blockWidth) - 4;
        rightBound = -leftBound + (width % 2 == 0 ? MainFrame.blockWidth : 0) -7;
    }
    
    /**
     * Adds a door
     * @param door used
     * @param side wall for the door (0-3)
     * @param coord block on the wall which is used for the door
     */
    void addDoor(Door door, int side, int coord){
        //increase the doors array's size by 1
        Door[] temp = new Door[doors.length + 1];
        System.arraycopy(doors, 0, temp, 0, doors.length);
        doors = temp;
        
        doors[doors.length - 1] = door;
    }
    
    public void checkBounds(){
        if(Land.inside){
            //make sure the player doesn't go past the boundaries
            if(Player.dy < topBound)
                Player.dy = topBound;
            else if(Player.dy > bottomBound)
                Player.dy = bottomBound;
            
            if(Player.dx < leftBound)
                Player.dx = leftBound;
            else if(Player.dx > rightBound)
                Player.dx = rightBound;
        }
    }
    
    public void draw(Graphics2D comp){
        //draw the walls and ground
        for(int i = -7; i < MainFrame.DIMENSIONX + 7; i++){
            for(int j = -7; j < MainFrame.DIMENSIONY + 7; j++){
                if(i >= begX && i <= endX && j >= begY && j <= endY)
                    comp.drawImage(ground, i * MainFrame.blockWidth - Player.dx,
                            j * MainFrame.blockHeight - Player.dy, null);
                else
                    comp.drawImage(wall, i * MainFrame.blockWidth - Player.dx,
                            j * MainFrame.blockHeight - Player.dy, null);
            }
        }
    }
}