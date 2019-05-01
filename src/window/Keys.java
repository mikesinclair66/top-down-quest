package window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import land.entities.Computer;
import characters.Player;

/**
 * This class controls keyboard
 * operations.
 */
public class Keys implements KeyListener {
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(Game.inGame){
            if(Game.focus)
                Player.keyPressed(key);
            else if(Computer.inUse)
                Computer.keyPressed(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(Game.inGame){
            if(Game.focus)
                Player.keyReleased(key);
            else if(Computer.inUse)
                Computer.keyReleased(key);
        }
        
        if(key == KeyEvent.VK_CONTROL)
            System.exit(0);
    }
}