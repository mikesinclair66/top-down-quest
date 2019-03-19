package land;

import java.awt.Graphics2D;
import player.Player;
import window.ImageCenter;
import window.MainFrame;

public class Area {
    Section[][] sections;
    int areaX, areaY;//dimension sizes of the sections array
    int curIdX, curIdY;//indicates which section the player is in
    //if there is a section around the current section in the area
    boolean sectionUp, sectionRight, sectionDown, sectionLeft;
    
    public Area(int areaX, int areaY){
        this.areaX = areaX;
        this.areaY = areaY;
        sections = new Section[areaX][areaY];
        initSections();
    }
    
    /**
     * Adds a building to the specified section.
     */
    void addBuilding(Building b, int secX, int secY){
        sections[secX][secY].addBuilding(b);
    }
    
    void draw(Graphics2D comp){
        //draw the current section and its surrounding sections
        sections[curIdX][curIdY].draw(comp);
        if(sectionUp)
            sections[curIdX][curIdY - 1].draw(comp, 0, -1);
        if(sectionRight)
            sections[curIdX + 1][curIdY].draw(comp, 1, 0);
        if(sectionDown)
            sections[curIdX][curIdY + 1].draw(comp, 0, 1);
        if(sectionLeft)
            sections[curIdX - 1][curIdY].draw(comp, -1, 0);
        if(sectionUp && sectionLeft)
            sections[curIdX - 1][curIdY - 1].draw(comp, -1, -1);
        if(sectionUp && sectionRight)
            sections[curIdX + 1][curIdY - 1].draw(comp, 1, -1);
        if(sectionDown && sectionLeft)
            sections[curIdX - 1][curIdY + 1].draw(comp, -1, 1);
        if(sectionDown && sectionRight)
            sections[curIdX + 1][curIdY + 1].draw(comp, 1, 1);
    }
    
    void update(){
        //update the current section and its surrounding sections
        sections[curIdX][curIdY].update();
        
        /*Note: without this code, only the current section is being updated
        if(sectionUp)
            sections[curIdX][curIdY - 1].update();
        if(sectionRight)
            sections[curIdX + 1][curIdY].update();
        if(sectionDown)
            sections[curIdX][curIdY + 1].update();
        if(sectionLeft)
            sections[curIdX - 1][curIdY].update();
        if(sectionUp && sectionLeft)
            sections[curIdX - 1][curIdY - 1].update();
        if(sectionUp && sectionRight)
            sections[curIdX + 1][curIdY - 1].update();
        if(sectionDown && sectionLeft)
            sections[curIdX - 1][curIdY + 1].update();
        if(sectionDown && sectionRight)
            sections[curIdX + 1][curIdY + 1].update();*/
        
        //if the player leaves their section and goes into a new section, switch current section
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
        
        //true if there is available area around the current section
        sectionUp = curIdY > 0;
        sectionRight = curIdX < areaX - 1;
        sectionDown = curIdY < areaY - 1;
        sectionLeft = curIdX > 0;
        
        Player.curIdX = curIdX;
        Player.curIdY = curIdY;
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