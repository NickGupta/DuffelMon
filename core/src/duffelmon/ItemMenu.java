
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.*;
/**
 *
 * @author Jonathan
 */
public class ItemMenu extends Menu{
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private List<Item> items;
    
    public ItemMenu(BattleMenu m, float x, float y){
        super(m, x, y);
         
    }
    
    public void addItem(Item i){
        if(items.size() < 6){
            items.add(i);
        }else{
            //Print out an error message that they have too many items
            
        }
        
    }
    
}

