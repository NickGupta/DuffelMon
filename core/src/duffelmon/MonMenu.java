/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */
public class MonMenu extends Menu {
    
    private Combatant combatant;
    private Mon[] mons;
    private Sprite[] monSprites;
    private int selection = 0;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public MonMenu(Menu m, float x, float y, Combatant c) {
        super(m, x, y);
        combatant = c;
        mons = combatant.getMons();
        monSprites = new Sprite[mons.length];
        for(int i = 0; i < mons.length; i++) {
            if (mons[i] != null) {
                Sprite s = new Sprite(mons[i].getFrontTexture());
                monSprites[i] = s;
            }
        }
    }
    
    public MonMenu(float x, float y, Combatant c) {
        this(null, x, y, c);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, getX(), getY() - 96*3, getX() + 256, getY());
        Menu.drawBox(batch, alpha, 0, 0, 258, 256);
        float yPos = getY();
        for(int i = 0; i < mons.length; i++) {
            if (mons[i] != null) {
                Sprite sprite = monSprites[i];
                sprite.setCenter(getX() + 48, yPos - 48);
                sprite.draw(batch);
                mons[i].drawInfo(batch, alpha, font, fontColor, getX() + 96, yPos - 28);
            } else {
                font.setColor(fontColor);
                font.draw(batch, "---", getX() + 96, yPos - 28);
            }
            if (selection == i) {
                font.setColor(fontColor);
                font.draw(batch, "_____", getX() + 96, yPos - 28);
                if (mons[i] != null) {
                    font.draw(batch, mons[i].getName(), 136, 240);
                    if (mons[i].getNickname() != null) {
                        font.draw(batch, "Species: " + mons[i].getSpecies().getName(), 136, 220); 
                    }
                    font.draw(batch, "Level: " + mons[i].getLevel(), 136, 200);
                    if (mons[i].getHealth() == 0) {
                        font.draw(batch, "Fainted", 136, 180); 
                    } else {
                        font.draw(batch, "Health: " + (int)Math.ceil(mons[i].getHealth()) + "%", 136, 180);
                    }
                    if (i == combatant.getMonsPos()) {
                        font.draw(batch, "Already in battle", 136, 160); 
                    }
                    font.draw(batch, "Attack: " + Math.round(mons[i].getAttack()), 24, 100);
                    font.draw(batch, "Defense: " + Math.round(mons[i].getDefense()), 128, 100);
                    font.draw(batch, "Speed: " + Math.round(mons[i].getSpeed()), 24, 80);
                    font.draw(batch, "Attitude: " + Math.round(mons[i].getAttitude()), 128, 80);
                    Sprite sprite = monSprites[i];
                    sprite.setCenter(68, 188);
                    sprite.setScale(2);
                    sprite.draw(batch);
                    sprite.setScale(1);
                } else {
                    font.draw(batch, "No DuffelMon selected", 64, 128);
                }
            }
            yPos -= 96;
        }
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
                selection = mons.length - 1;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection += 1;
            if (selection >= mons.length) {
                selection = 0;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.SELECT) && mons[selection] != null) {
            setOutput("MONS" + selection);
        }
    }
}
