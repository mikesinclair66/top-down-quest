package window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import player.Player;

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
        
        if(Game.inGame && Game.focus)
            Player.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(Game.inGame && Game.focus)
            Player.keyReleased(key);
        
        if(key == KeyEvent.VK_CONTROL)
            System.exit(0);
    }
}