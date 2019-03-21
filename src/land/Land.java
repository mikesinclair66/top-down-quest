package land;

import java.awt.Graphics2D;
import land.entities.Computer;
import window.ImageCenter;

public class Land {
    static Room[][] rooms = new Room[1][5];//[area][room]
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
        Door d;
        
        rooms[0][0] = new Room(10, 10, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        rooms[0][0].addDoor(d, 0, 3);
        
        rooms[0][1] = new Room(4, 4, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        rooms[0][1].addDoor(d, 0, 0);
        
        rooms[0][2] = new Room(25, 10, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        rooms[0][2].addDoor(d, 0, 3);
        
        rooms[0][3] = new Room(10, 10, ImageCenter.ground_stone, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        rooms[0][3].addDoor(d, 0, 3);
        rooms[0][3].addObstacle(new Obstacle(9, 0, 1, 10, ImageCenter.wall_wood));
        
        rooms[0][4] = new Room(10, 10, ImageCenter.ground_grass, ImageCenter.wall_wood);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        d.setOutsideDoor(true);
        rooms[0][4].addDoor(d, 0, 3);
        
        
        curRoom = rooms[0][0];
    }
    
    /**
     * This function initializes every area variable.
     */
    public static void initAreas(){
        Building b;
        Door d;
        
        areas[0] = new Area(3, 3);
        b = new Building(0, 0, 5, 3, 3);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 2);
        areas[0].addBuilding(b, 0, 0);
        
        b = new Building(10, 2, 5, 4, 3);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 2);
        areas[0].addBuilding(b, 1, 0);
        
        b = new Building(2, 2, 3, 3, 2);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 1);
        areas[0].addBuilding(b, 0, 1);
        
        b = new Building(0, 4, 2, 2, 1);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 1);
        areas[0].addBuilding(b, 1, 1);
        
        b = new Building(10, 5, 4, 4, 3);
        d = new Door(true);
        d.setImages(ImageCenter.door_brown1, ImageCenter.door_brown2, ImageCenter.door_brown3);
        b.addDoor(d, 2);
        areas[0].addBuilding(b, 2, 0);
        
        curArea = areas[0];
    }
    
    public static void update(){
        if(inside)
            curRoom.update();
        else
            curArea.update();
    }
    
    /**
     * Sets the room to the specified room number.
     */
    static void setRoom(int roomNo) throws IllegalArgumentException {
        try{
            curRoom = rooms[0][roomNo];
        } catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Error: the room was set with an illegal room no.");
            throw new IllegalArgumentException();
        }
    }
}