/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */
public class GameMenu extends Menu{
    private int xValue = 0;
    private int yValue = 256;
    private int x1 = 300;
    private int y1 = 256;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    Texture tex = new Texture("Duffelmon.png");
    private Sprite sprite = new Sprite(tex);
    
    public GameMenu(){
        
    }
    
    @Override
    public void draw(Batch batch, float alpha){
        sprite.setX(xValue);
        sprite.setY(yValue);
        font.setColor(fontColor);
        font.draw(batch, "Hunt for Wild DuffelMon", xValue, yValue);
        xValue -= 200;
        font.draw(batch, "Battle Trainer", xValue, yValue);
        xValue += 200;
        yValue-=128;
        font.draw(batch, "Shop at DuffelMart", xValue, yValue);
        xValue -= 200;
        font.draw(batch, "Save", xValue, yValue);
        yValue += 128;
        font.draw(batch, "_________", x1, y1);
        xValue = 300;
        yValue = 256;
    }
    
    @Override
    public void frameActions() {
        
        
        if(GlobalData.keyPressed(GlobalData.Inputs.RIGHT)||GlobalData.keyPressed(GlobalData.Inputs.LEFT)){
                if(x1 == 300){
                    x1 -= 200; 
                }else if(x1 == 100){
                    x1 += 200;
                }
        }
        
        if(GlobalData.keyPressed(GlobalData.Inputs.UP)||GlobalData.keyPressed(GlobalData.Inputs.DOWN)){
                if(y1 == 256){
                    y1 -= 128; 
                }else if(y1 == 128){
                    y1 += 128;
                }
        }
        
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            if(x1 == 300 && y1 == 256){
                GameObject.makeDependent(this);
                
                Player player = new Player("Joe");
                player.addMon(new Mon(null, Species.getSpecies("Charmander"), 10));
                player.addItem(new Item("Potion"));
                GlobalData.setPlayer(player);
                
                Mon enemy = new Mon(null, Species.getSpecies("Kingdra"), 8);
                Trainer enemyTrainer = new Trainer("Bob", "Hiker", 500, new RandomMoveAI());
                enemyTrainer.addMon(enemy);
                Battle.startBattle(player, enemyTrainer);
            }
        }
    }
}
