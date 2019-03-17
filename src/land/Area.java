package land;

import java.awt.Graphics2D;

public class Area {
    Section[][] sections;
    String curSectionID;//the ID of the section that the player is in
    
    public Area(int areaX, int areaY){
        sections = new Section[areaX][areaY];
        
        for(int i = 0; i < areaX; i++){
            for(int j = 0; j < areaY; j++){
                sections[i][j].ID = i + "." + j;
            }
        }
    }
    
    void draw(Graphics2D comp){
        int secX = 0, secY = 0;//the x and y of the current section's ID
        try{
            secX = Integer.parseInt(curSectionID.substring(0, 1));
            secY = Integer.parseInt(curSectionID.substring(2, 3));
        } catch(NumberFormatException e){
            System.err.println("Error: section ID could not be decoded");
        }
        
        for(int i = secX - 1; i <= 1; i++){
            for(int j = secY - 1; j <= 1; j++){
                sections[i][j].draw(comp);
            }
        }
    }
    
    void update(){
        int secX = 0, secY = 0;//the x and y of the current section's ID
        try{
            secX = Integer.parseInt(curSectionID.substring(0, 1));
            secY = Integer.parseInt(curSectionID.substring(2, 3));
        } catch(NumberFormatException e){
            System.err.println("Error: section ID could not be decoded");
        }
        
        for(int i = secX - 1; i <= 1; i++){
            for(int j = secY - 1; j <= 1; j++){
                sections[i][j].update();
            }
        }
    }
}