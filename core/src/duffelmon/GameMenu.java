/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class GameMenu extends Menu {
    private int selectionX = 0;
    private int selectionY = 0;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public GameMenu(){
        
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        font.setColor(fontColor);
        font.draw(batch, "Hunt for Wild DuffelMon", 100, 256);
        font.draw(batch, "Battle Trainer", 300, 256);
        font.draw(batch, "View DuffelMon", 100, 192);
        font.draw(batch, "Shop at DuffelMart", 300, 192);
        font.draw(batch, "Store DuffelMon", 100, 128);
        font.draw(batch, "Store Items", 300, 128);
        font.draw(batch, "Save Game", 100, 64);
        font.draw(batch, "Quit Game", 300, 64);
        font.draw(batch, "_________", 100 + selectionX * 200, 256 - selectionY * 64);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.RIGHT)) {
            selectionX++;
            if (selectionX > 1) {
                selectionX = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.LEFT)) {
            selectionX--;
            if (selectionX < 0) {
                selectionX = 1;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selectionY++;
            if (selectionY > 3) {
                selectionY = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selectionY--;
            if (selectionY < 0) {
                selectionY = 3;
            }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            if(selectionY == 0) {
                if (selectionX == 0) {
                    GameObject.makeDependent(this);
                    Battle.startBattle(GlobalData.getPlayer(), new Mon(null, Species.getSpecies("Spongerob"), 8), new RandomMoveAI());
                } else if (selectionX == 1) {
                    GameObject.makeDependent(this);
                    Trainer enemyTrainer = new Trainer("Bob", "Hiker", 500, new RandomMoveAI());
                    enemyTrainer.addMon(new Mon(null, Species.getSpecies("Pawprince"), 8));
                    enemyTrainer.addMon(new Mon(null, Species.getSpecies("Massant"), 8));
                    enemyTrainer.addMon(new Mon(null, Species.getSpecies("Auroralisk"), 8));
                    Battle.startBattle(GlobalData.getPlayer(), enemyTrainer);
                }
            } else if (selectionY == 1) {
                if (selectionX == 0) {
                    setServant(new MonMenu(this, 256, 288, GlobalData.getPlayer().getMons(), false));
                } else if (selectionX == 1) {
                    setServant(new ShopMenu(this));
                }
            } else if (selectionY == 2) {
                if (selectionX == 0) {
                    setServant(new MonStorageMenu(this, GlobalData.getPlayer().getMons(), GlobalData.getPlayer().getStorageMons()));
                } else if (selectionX == 1) {
                    
                }
            } else if (selectionY == 3) {
                if (selectionX == 0) {
                    
                } else if (selectionX == 1) {
                    Gdx.app.exit();
                }
            }
        }
    }
}
