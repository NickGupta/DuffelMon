
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class ItemMenu extends Menu {
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private ArrayList<Item> items = new ArrayList<Item>();
    private int selection = 0;
    
    public ItemMenu(Menu m, float x, float y){
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
          for(int i = 0; i < items.size(); i++) {
              font.draw(batch, items.get(i).getType(), getX(), getY() - (yOffset * i));
          }
          font.draw(batch, "______", getX(), getY() - (yOffset * selection));
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.BACK)) {
            setOutput("ForgetIt");
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selection -= 1;
            if (selection < 0) {
                selection = items.size() - 1;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection += 1;
            if (selection >= items.size()) {
                selection = 0;
            }
        }
    }
    
}
