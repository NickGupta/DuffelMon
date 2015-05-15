
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
    }
    
    public void addItem(Item i) {
        if(items.size() < 6){
            items.add(i);
        }else{
            //Print out an error message that they have too many items
        } 
    }
    
    public Item getItem(int i) {
        return items.get(i);
    }
    
    @Override
    public void draw(Batch batch, float alpha){
         int yOffset = 20;
         Menu.drawBox(batch, alpha, getX(), getY() - (yOffset*5 + 8), getX() + 128, getY());
         font.setColor(fontColor);
         for(int i = 0; i < items.size(); i++) {
            font.draw(batch, items.get(i).getName() + " x " + items.get(i).getAmount(), getX() + 4, getY() - ((i * yOffset) + 4));
        }
        font.draw(batch, "______", getX(), getY() - ((selection * yOffset) + 4));
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
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
            setOutput("ITEM" + selection);
        }
    }
    
}
