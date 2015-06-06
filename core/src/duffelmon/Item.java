/*Represents a single item
*/
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


/**
 *
 * @author Jonathan
 */
public class Item {
    
    private String name;
    
    public static void drawItems(Batch batch, float alpha, BitmapFont font, Color color, float x, float y, Item[] items, int s) {
        font.setColor(color);
        for(int i = 0; i < items.length; i++) {
            String itemInfo;
            if (items[i] != null) {
                itemInfo = items[i].getName();
            } else {
                itemInfo = "---";
            }
            font.draw(batch, itemInfo, x, y - (i * 20));
        }
        if (s >= 0) {
            font.draw(batch, "______", x, y - (s * 20));
        }
    }
    
    public Item(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public Move getMove() {
        return Move.getMove("Item_" + name);
    }
    
}
