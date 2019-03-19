package land;

import java.awt.Graphics2D;
import land.entities.Computer;
import player.Player;
import window.Game;
import window.ImageCenter;
import window.MainFrame;

public class Land {
    static Room[][] rooms = new Room[1][1];//[area][room]
    static Area[] areas = new Area[1];//[city/big area of land]
    
    static Area curArea;//the area that the player is currently in
    public static Room curRoom;//the room that the player is currently in
    static boolean inside = false;//true if the player is in a room
    
    /**
     * This function draws
     * the land or necessary
     * room.
     */
    public static void draw(Graphics2D comp){
        if(inside)
            curRoom.draw(comp);
        else
            curArea.draw(comp);
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
        rooms[0][0].addObstacle(new Computer(10, 0));
        
        curRoom = rooms[0][0];
    }
    
    /**
     * This function initializes every area variable.
     */
    public static void initAreas(){
        areas[0] = new Area(3, 3);
        curArea = areas[0];
    }
    
    public static void update(){
        if(inside)
            curRoom.update();
        else
            curArea.update();
    }
}