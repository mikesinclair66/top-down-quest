package land;

import java.awt.Graphics2D;
import window.ImageCenter;

public class Land {
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
     * This function initializes every area variable.
     */
    public static void initAreas(){
        Building b;
        Door d;
        Room r;
        
        areas[0] = new Area(3, 3);
        curArea = areas[0];
        
        b = new Building(0, 0, 3, 3, 3);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 1);
        r = new Room(5, 5, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        r.addDoor(d, 0, 2);
        b.setRoom(r);
        areas[0].addBuilding(b, 0, 0);
        
        b = new Building(11, 5, 5, 3, 2);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 3);
        r = new Room(10, 10, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        r.addDoor(d, 0, 2);
        b.setRoom(r);
        areas[0].addBuilding(b, 0, 0);
        
        b = new Building(0, 0, 3, 3, 3);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 1);
        r = new Room(5, 5, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        r.addDoor(d, 0, 2);
        b.setRoom(r);
        areas[0].addBuilding(b, 1, 0);
    }
    
    public static void update(){
        if(inside)
            curRoom.update();
        else
            curArea.update();
    }
}