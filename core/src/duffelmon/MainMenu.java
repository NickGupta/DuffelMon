package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class MainMenu extends Menu{
    String title;
    private BitmapFont font = GlobalData.getFont();
    private int xValue = 0;
    private int yValue = 260;
    private int x1 = 120;
    private int y1 = 100;
    private Color fontColor = Color.BLACK;
    Texture tex = new Texture("Duffelmon.png");
    private Sprite sprite = new Sprite(tex);
    
    public MainMenu(){
        
    }
    
    @Override
    public void draw(Batch batch, float alpha){
        sprite.setX(xValue);
        sprite.setY(yValue);
        sprite.draw(batch);
        xValue = 120;
        yValue = 100;
        
        font.setColor(fontColor);
        font.draw(batch, "New Game", xValue, yValue);
        xValue += 200;
        font.draw(batch, "Load", xValue, yValue);
        font.draw(batch, "_________", x1, y1);
        
        
        xValue = 0;
        yValue = 260;
    }
    
    @Override
    public void frameActions() {
        if(GlobalData.keyPressed(GlobalData.Inputs.RIGHT)||GlobalData.keyPressed(GlobalData.Inputs.LEFT)){
                if(x1 == 120){
                    x1 += 180; 
                }else if(x1 == 300){
                    x1 -= 180;
                }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            if (x1 == 120) {
                GameObject.makeDependent(this);
                Player player = new Player("");
                GlobalData.setPlayer(player);
                GameMenu g = new GameMenu();
                GameObject.makeIndependent(g);
            } else if (x1 == 300) {
                GameObject.makeDependent(this);
                Player player = new Player("Joe");
                GlobalData.setPlayer(player);
                player.addMon(new Mon(null, Species.getSpecies("Flurricane"), 10));
                player.addMon(new Mon(null, Species.getSpecies("Pulsect"), 10));
                player.addMon(new Mon(null, Species.getSpecies("Scythera"), 10));
                player.addItem(new Item("Potion"));
                player.addItem(new Item("Panacea"));
                player.addItem(new Item("Super Potion"));
                player.addItem(new Item("Duffel Bag"));
                player.addItem(new Item("Duffel Bag"));
                GameMenu g = new GameMenu();
                GameObject.makeIndependent(g);
            }
        }
    }
}
