package land;

import java.awt.Graphics2D;
import java.awt.Image;
import land.entities.Computer;
import player.Player;
import window.Game;
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
    Obstacle[] obs = new Obstacle[0];
    
    //true if bounds have been added
    boolean boundsAdded;
    
    int roomCode;//code associated with the room
    static int roomsRecorded;//number of rooms recorded
    
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
        
        //give the room its room code
        //NOTE: roomCode has no use so far
        roomCode = roomsRecorded++;
    }
    
    /**
     * Adds a door
     * @param door used
     * @param side wall for the door (0-3)
     * @param coord block on the wall which is used for the door
     */
    void addDoor(Door door, int side, int coord) throws IllegalArgumentException {
        if(coord > width){
            System.err.println("Error: third argument must be <= width");
            throw new IllegalArgumentException();
        }
        
        //increase the doors array's size by 1
        Door[] temp = new Door[doors.length + 1];
        System.arraycopy(doors, 0, temp, 0, doors.length);
        doors = temp;
        
        switch(side){
            case 0:
                door.x = (begX + coord) * MainFrame.blockWidth;
                door.y = (begY - 1) * MainFrame.blockHeight;
                break;
            case 1:
                door.x = (endX + 1) * MainFrame.blockWidth;
                door.y = (begY + coord) * MainFrame.blockHeight;
                break;
            case 2:
                door.x = (begX + coord) * MainFrame.blockWidth;
                door.y = (endY + 1) * MainFrame.blockHeight;
                break;
            case 3:
                door.x = (begX - 1) * MainFrame.blockWidth;
                door.y = (begY + coord) * MainFrame.blockHeight;
                break;
            default:
                System.err.println("Error: side must be 0-3");
                throw new IllegalArgumentException();
        }
        
        doors[doors.length - 1] = door;
        door.roomSide = side;
    }
    
    /** This function adds an obstacle to the room. */
    void addObstacle(Obstacle o) {
        //reinitialize the obstacle to add the rooms wall size to its coordinates
        o.x += begX;
        o.y += begY;
        o.ox += begX * MainFrame.blockWidth;
        o.oy += begY * MainFrame.blockHeight;
        
        //increase the obstacles array's size by 1
        Obstacle[] temp = new Obstacle[obs.length + 1];
        System.arraycopy(obs, 0, temp, 0, obs.length);
        obs = temp;
        
        obs[obs.length - 1] = o;
    }
    
    public void update(){
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
            
            //check if the player is trying to move past an obstacle
            for(Obstacle o : obs)
                o.update();
            
            //if the door is opened and the player is walking through, decrease bounds
            for(Door d : doors){
                if(!Game.focus && !boundsAdded && d.open){
                    boundsAdded = true;
                    addBounds(0, -1);
                }
                
                //if the player has walked through the door, switch to outside
                switch(d.roomSide){
                    case 0://if the room is on the north side of the room
                        if(!Game.focus && d.open && Player.coordY < d.y){
                            Player.setDirection(0);
                            Land.inside = false;
                            d.open = false;
                            Game.focus = true;
                            d.img = d.anim;
                            
                            /*take the roomCode and transform it into the appropriate building array
                            index. This is done by subtracting every building array length from every
                            section prior to the current section.*/
                            int buildingIndex = roomCode;
                            if(Player.curIdX > 0){
                                for(int i = 0; i < Player.curIdX; i++){
                                    for(int j = 0; j < Player.curIdY; j++){
                                        buildingIndex -= Land.curArea.sections[i][j].buildings.length;
                                    }
                                }
                            } else if(Player.curIdY > 0){
                                for(int i = 0; i < Player.curIdY; i++){
                                    for(int j = 0; j < Player.curIdX; j++){
                                        buildingIndex -= Land.curArea.sections[j][i].buildings.length;
                                    }
                                }
                            }
                            
                            Player.dx = 
                                    Land.curArea.sections[Player.curIdX][Player.curIdY].buildings[
                                    buildingIndex].entry.x - Player.x;
                            Player.dy =
                                    Land.curArea.sections[Player.curIdX][Player.curIdY].buildings[
                                    buildingIndex].entry.y - Player.y + MainFrame.blockHeight * 2;
                        }
                        break;
                }
                
                d.animate();
            }
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
        
        //draw every door that has been established in the room
        for(Door d : doors)
            d.draw(comp);
        
        for(Obstacle o : obs)
            o.draw(comp);
    }
    
    /*  Increases or decreases the boundaries.
        bound - which bound to change
        qty - by how much */
    private void addBounds(int bound, int qty){
        switch(bound){
            case 0:
                this.topBound += qty * MainFrame.blockHeight;
                break;
            case 1:
                this.rightBound += qty * MainFrame.blockWidth;
                break;
            case 2:
                this.bottomBound += qty * MainFrame.blockHeight;
                break;
            case 3:
                this.leftBound += qty * MainFrame.blockWidth;
                break;
        }
    }
}