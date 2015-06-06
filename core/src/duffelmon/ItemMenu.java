
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Jonathan
 */
public class ItemMenu extends Menu {
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private Item[] items;
    private int selection = 0;
    
    public ItemMenu(Menu m, float x, float y, Item[] i) {
        super(m, x, y);
        items = i;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, getX(), getY() - 108, getX() + 128, getY());
        Item.drawItems(batch, alpha, font, fontColor, getX() + 4, getY() - 4, items, selection);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.BACK)) {
            setOutput("ForgetIt");
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selection -= 1;
            if (selection < 0) {
                selection = items.length - 1;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection += 1;
            if (selection >= items.length) {
                selection = 0;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.SELECT) && items[selection] != null) {
            setOutput("ITEM" + selection);
        }
    }
    
}
