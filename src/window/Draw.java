package window;

import java.awt.Graphics2D;
import land.Land;
import player.Player;

/**
 * This class contains all
 * the operations for drawing
 * components onto the screen.
 */
public class Draw {
    static void draw(Graphics2D comp){
        if(Game.inGame){
            Land.draw(comp);
            Player.draw(comp);
        }
    }
}