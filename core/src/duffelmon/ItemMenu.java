
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.*;
/**
 *
 * @author Jonathan
 */
public class ItemMenu extends Menu{
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private List<Item> items = new ArrayList<Item>();
    private int underlineOffset = 0;
    
    public ItemMenu(BattleMenu m, float x, float y){
        super(m, x, y);
         items.add(new Item("Potion"));
         items.add(new Item("Potion"));
    }
    
    public void addItem(Item i){
        if(items.size() < 6){
            items.add(i);
        }else{
            //Print out an error message that they have too many items
            
        }
        
    }
    
    @Override
    public void draw(Batch batch, float alpha){
          int yOffset = 20;
       
          font.setColor(fontColor);
          font.draw(batch, items.get(0).getType(), getX(), getY());
          
          if(items.size() > 1){
             font.setColor(fontColor);
             font.draw(batch, items.get(1).getType(), getX(), getY() - yOffset); 
              
          }
          
          if(items.size() > 2){
             font.setColor(fontColor);
             font.draw(batch, items.get(2).getType(), getX(), getY() - yOffset*2); 
              
          }
          
          if(items.size() > 3){
             font.setColor(fontColor);
             font.draw(batch, items.get(3).getType(), getX(), getY() - yOffset*3); 
              
          }
          
          if(items.size() > 4){
             font.setColor(fontColor);
             font.draw(batch, items.get(4).getType(), getX(), getY() - yOffset*4); 
              
          }
          
          if(items.size() > 5){
             font.setColor(fontColor);
             font.draw(batch, items.get(5).getType(), getX(), getY() - yOffset*5); 
              
          }
          
       
          font.draw(batch, "______", getX(), getY() - (20 * underlineOffset));
    }
    @Override
    public void frameActions() {
        
        
        if(GlobalData.keyPressed(GlobalData.Inputs.BACK)){
            setOutput("ForgetIt");
        }
    }
    
}

