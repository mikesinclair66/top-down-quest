package land;

import java.awt.Graphics2D;
import player.Player;
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
        rooms[0][0] = new Room(5, 7, ImageCenter.ground_stone, ImageCenter.wall_wood);
        
        curRoom = rooms[0][0];
    }
    
    public static void update(){
        curRoom.checkBounds();
    }
}