package window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class creates the JPanel
 * operations for the program.
 */
public class MainPanel extends JPanel implements ActionListener {
    static final int UPDATE_TIME = 20;//miliseconds per update
    public static final int FPS = 1000 / UPDATE_TIME;
    Timer t = new Timer(UPDATE_TIME, this);
    
    public MainPanel(){
        Game.init();
        t.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D comp = (Graphics2D) g;
        
        Draw.draw(comp);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(Game.inGame)
            Game.update();
        
        repaint();
    }
}