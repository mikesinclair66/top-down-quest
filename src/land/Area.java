package land;

import java.awt.Graphics2D;
import player.Player;
import window.ImageCenter;
import window.MainFrame;

public class Area {
    Section[][] sections;
    int areaX, areaY;//dimension sizes of the sections array
    int curIdX, curIdY;//indicates which section the player is in
    
    public Area(int areaX, int areaY){
        this.areaX = areaX;
        this.areaY = areaY;
        sections = new Section[areaX][areaY];
        initSections();
    }
    
    void draw(Graphics2D comp){
        sections[curIdX][curIdY].draw(comp);
        
        //draw the sections around the current section if possible
        //true if there is available area around the current section
        boolean up = curIdY > 0;
        boolean right = curIdX < areaX - 1;
        boolean down = curIdY < areaY - 1;
        boolean left = curIdX > 0;
        
        if(up)
            sections[curIdX][curIdY - 1].draw(comp, 0, -1);
        if(right)
            sections[curIdX + 1][curIdY].draw(comp, 1, 0);
        if(down)
            sections[curIdX][curIdY + 1].draw(comp, 0, 1);
        if(left)
            sections[curIdX - 1][curIdY].draw(comp, -1, 0);
    }
    
    void update(){
        sections[curIdX][curIdY].update();
        //NOTE: only the current section is being updated right now
        
        if(Player.coordX < 0 && curIdX > 0){
            curIdX--;
            Player.dx = MainFrame.blockWidth * MainFrame.DIMENSIONX
                    - Player.x;
        } else if(Player.coordX > MainFrame.blockWidth * MainFrame.DIMENSIONX
                && curIdX < areaX - 1){
            curIdX++;
            Player.dx = -Player.x;
        }
        
        if(Player.coordY < 0 && curIdY > 0){
            curIdY--;
            Player.dy = MainFrame.blockHeight * MainFrame.DIMENSIONY
                    - Player.y;
        } else if(Player.coordY > MainFrame.blockHeight * MainFrame.DIMENSIONY
                && curIdY < areaY - 1){
            curIdY++;
            Player.dy = -Player.y;
        }
        System.out.println(Player.dx);
    }
    
    /** Initializes the sections array. */
    private void initSections(){
        for(int i = 0; i < areaX; i++){
            for(int j = 0; j < areaY; j++){
                sections[i][j] = new Section();
                sections[i][j].setGroundImage(ImageCenter.ground_grass);
                sections[i][j].x = i * (MainFrame.blockWidth * MainFrame.DIMENSIONX);
                sections[i][j].y = j * (MainFrame.blockHeight * MainFrame.DIMENSIONY);
            }
        }
    }
}