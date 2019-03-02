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
    Timer t = new Timer(20, this);
    
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