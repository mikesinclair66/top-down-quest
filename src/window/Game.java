package window;

import land.Land;
import characters.Player;

public class Game {
    static boolean inGame = true;
    public static boolean focus = true;//if the player can move or not
    
    static void init(){
        ImageCenter.skewAll();
        Land.initAreas();
        Player.init();
    }
    
    static void update(){
        Player.update();
        Land.update();
    }
}