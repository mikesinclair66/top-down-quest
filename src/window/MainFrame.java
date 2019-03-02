package window;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * This class creates the window
 * for the program.
 * @author Michael Sinclair
 * @created February 22, 2019
 */
public class MainFrame extends JFrame {
    public static int width, height;
    
    /*
    Upon startup, the screen is
    divided into blocks; 16x12.
    blockWidth and height contain
    the size of these blocks.
    */
    public static int blockWidth, blockHeight;
    public static final int DIMENSIONX = 16, DIMENSIONY = 12;
    
    public MainFrame(){
        super("Top Down Quest v1.0");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        blockWidth = width / DIMENSIONX;//85
        blockHeight = height / DIMENSIONY;//64
        
        addMouseListener(new Mouse());
        addKeyListener(new Keys());
        
        setContentPane(new MainPanel());
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
}