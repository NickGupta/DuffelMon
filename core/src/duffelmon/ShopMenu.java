
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public class ShopMenu extends Menu {
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private int selection = 0;
    
    public ShopMenu(Menu m) {
        super(m);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 126, 512, 448);
        Menu.drawBox(batch, alpha, 0, 0, 512, 128);
        font.setColor(fontColor);
        font.draw(batch, "DuffelMart", 223, 416);
        font.draw(batch, "Your items:", 32, 320);
        Item.drawItems(batch, alpha, font, fontColor, 32, 280, GlobalData.getPlayer().getItems(), -1);
        font.draw(batch, "Items for sale:", 344, 320);
        font.draw(batch, "Potion - $500", 344, 280);
        font.draw(batch, "Panacea - $500", 344, 260);
        font.draw(batch, "Super Potion - $1000", 344, 240);
        font.draw(batch, "Duffel Bag - $200", 344, 220);
        font.draw(batch, "_____", 344, 280 - 20*selection);
        font.draw(batch, "Your money: $" + GlobalData.getPlayer().getMoney(), 344, 180);
        String description = "";
        switch(selection) {
            case 0:
                description = "Restores a DuffelMon's health by 50%.";
                break;
            case 1:
                description = "Removes all of a DuffelMon's status effects.";
                break;
            case 2:
                description = "Restores a DuffelMon's health to maximum.";
                break;
            case 3:
                description = "Allows one attempt to catch a wild DuffelMon.";
                break;
        }
        font.draw(batch, description, 16, 112);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.BACK)) {
            setOutput("ForgetIt");
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selection -= 1;
            if (selection < 0) {
                selection = 3;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection += 1;
            if (selection > 3) {
                selection = 0;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            String itemType = "";
            int price = 0;
            switch(selection) {
                case 0:
                    itemType = "Potion";
                    price = 500;
                    break;
                case 1:
                    itemType = "Panacea";
                    price = 500;
                    break;
                case 2:
                    itemType = "Super Potion";
                    price = 1000;
                    break;
                case 3:
                    itemType = "Duffel Bag";
                    price = 200;
                    break;
            }
            Player player = GlobalData.getPlayer();
            if (player.getMoney() >= price) {
                if (player.addItem(new Item(itemType))) {
                    player.takeMoney(price);
                    setServant(new TextBox(this, "You bought a " + itemType + "!", true));
                } else {
                    setServant(new TextBox(this, "Your inventory is full!", true));
                }
            } else {
                setServant(new TextBox(this, "You don't have enough money for a " + itemType + "!", true));
            }
        }
    }
    
}
