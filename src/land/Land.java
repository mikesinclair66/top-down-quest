package land;

import java.awt.Graphics2D;
import land.entities.Computer;
import player.Player;
import window.Game;
import window.ImageCenter;
import window.MainFrame;

public class Land {
    static Room[][] rooms = new Room[1][1];//[area][room]
    
    Area curArea;//the area that the player is currently in
    public static Room curRoom;//the room that the player is currently in
    static boolean inside = true;//true if the player is in a room
    
    /**
     * This function draws
     * the land or necessary
     * room.
     */
    public static void draw(Graphics2D comp){
        if(inside)
            curRoom.draw(comp);
    }
    
    /**
     * This function initializes
     * every room variable.
     */
    public static void initRooms(){
        Door door;
        Obstacle o;
        
        rooms[0][0] = new Room(10, 10, ImageCenter.ground_stone, ImageCenter.wall_wood);
        door = new Door(true);
        door.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        rooms[0][0].addDoor(door, 0, 3);
        o = new Obstacle(7, 7, 2, 2, ImageCenter.wall_wood);
        rooms[0][0].addObstacle(o);
        rooms[0][0].addComputer(new Computer(10, 0));
        
        curRoom = rooms[0][0];
    }
    
    public static void update(){
        if(inside){
            curRoom.checkBounds();
            for(Door d : curRoom.doors)
                d.animate();
        }
    }
}