package window;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageCenter{
    //player (without clothes/armor)
    public static Image up0 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\up0.png").getImage();
    public static Image up1 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\up1.png").getImage();
    public static Image up2 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\up2.png").getImage();
    public static Image up3 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\up3.png").getImage();
    public static Image up4 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\up4.png").getImage();
    public static Image right0 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\right0.png").getImage();
    public static Image right1 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\right1.png").getImage();
    public static Image right2 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\right2.png").getImage();
    public static Image right3 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\right3.png").getImage();
    public static Image right4 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\right4.png").getImage();
    public static Image down0 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\down0.png").getImage();
    public static Image down1 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\down1.png").getImage();
    public static Image down2 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\down2.png").getImage();
    public static Image down3 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\down3.png").getImage();
    public static Image down4 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\down4.png").getImage();
    public static Image left0 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\left0.png").getImage();
    public static Image left1 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\left1.png").getImage();
    public static Image left2 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\left2.png").getImage();
    public static Image left3 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\left3.png").getImage();
    public static Image left4 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\left4.png").getImage();
    
    public static Image computer = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\computer.png").getImage();
    
    //door and door animations
    public static Image door_brown1 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\door_brown1.png").getImage();
    public static Image door_brown2 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\door_brown2.png").getImage();
    public static Image door_brown3 = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBeansProjects\\Top Down Quest\\images\\door_brown3.png").getImage();
    
    //ground images
    public static Image ground_grass = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBean"
            + "sProjects\\Top Down Quest\\images\\ground_grass.png").getImage();
    public static Image ground_stone = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBean"
            + "sProjects\\Top Down Quest\\images\\ground_stone.png").getImage();
    
    //wall images
    public static Image wall_wood = new ImageIcon("C:\\Users\\michael66\\Documents\\NetBean"
            + "sProjects\\Top Down Quest\\images\\wall_wood.png").getImage();
    
    /**
     * Skews all images according to the
     * size
     */
    public static void skewAll(){
        int x = MainFrame.blockWidth;
        int y = MainFrame.blockHeight;
        
        //door images
        door_brown1 = door_brown1.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        door_brown2 = door_brown2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        door_brown3 = door_brown3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        //player images
        up0 = up0.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        up1 = up1.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        up2 = up2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        up3 = up3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        up4 = up4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        right0 = right0.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        right1 = right1.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        right2 = right2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        right3 = right3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        right4 = right4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        down0 = down0.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        down1 = down1.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        down2 = down2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        down3 = down3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        down4 = down4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        left0 = left0.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        left1 = left1.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        left2 = left2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        left3 = left3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        left4 = left4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        computer = computer.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        //ground images
        ground_grass = ground_grass.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ground_stone = ground_stone.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        
        //wall images
        wall_wood = wall_wood.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }
}