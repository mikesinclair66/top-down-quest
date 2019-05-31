package process;

import java.awt.Color;
import java.awt.Graphics2D;
import window.MainFrame;

public class Inventory {
    static ItemHotbar hb = new ItemHotbar();
    
    /**
     * The ItemHotbar goes in the Hotbar
     * at the top of the user's screen.
     */
    static class ItemHotbar{
        final int SLOT_SIZE = 50;
        int x = SLOT_SIZE / 4, y = x;//starting coordinates of the hotbar
        
        //hotbar instance variables
        final int SLOTS = 6;
        int hotbarSelected = 0;//0-(HOTBAR_SLOTS - 1)
        final Color slotColor = new Color(0, 0, 0, 150);
        //an array for each slot of the hotbar. Each element has an id representing the item it holds
        int itemID[] = new int[SLOTS];
        
        public void draw(Graphics2D comp){
            int half = (SLOTS % 2 == 0 ? SLOTS / 2
                    : SLOTS / 2 + 1);
            for(int i = 0; i < half; i++){
                comp.setColor(slotColor);
                comp.fillRoundRect(x + (i * SLOT_SIZE * 5/4), y, SLOT_SIZE, SLOT_SIZE, 10, 10);
                comp.setColor(Color.white);
                comp.drawString((i + 1) + "", x + (i * SLOT_SIZE * 5/4) + 5, y + 15);
                if(SLOTS % 2 == 0){
                    comp.setColor(slotColor);
                    comp.fillRoundRect(x + (i * SLOT_SIZE * 5/4), y + SLOT_SIZE * 5/4,
                            SLOT_SIZE, SLOT_SIZE, 10, 10);
                    comp.setColor(Color.white);
                    comp.drawString((i + 1 + half) + "", x + (i * SLOT_SIZE * 5/4) + 5,
                            y + SLOT_SIZE * 5/4 + 15);
                }
                else if(i < half - 1){
                    comp.setColor(slotColor);
                    comp.fillRoundRect(x + SLOT_SIZE * 5/4 / 2 + (i * SLOT_SIZE * 5/4),
                            y + SLOT_SIZE * 5/4, SLOT_SIZE, SLOT_SIZE, 10, 10);
                    comp.setColor(Color.white);
                    comp.drawString((i + 1 + half) + "", x + SLOT_SIZE * 5/4 / 2 + (i * SLOT_SIZE * 5/4) + 5,
                            y + SLOT_SIZE * 5/4 + 15);
                }
            }
        }
    }
}