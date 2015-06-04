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
                    if (mons[i].getHealth() == 0){
                        font.draw(batch, "Mon has fainted", 60, 120);
                    } else if (i == combatant.getMonsPos()) {
                        font.draw(batch, "Mon is already in battle", 60, 120);
                    } else {
                        font.draw(batch, mons[i].getName(), 12, 236);
                        font.draw(batch, "Level: " + mons[i].getLevel(), 12, 212);
                        font.draw(batch, "Health: " + (int)Math.ceil(mons[i].getHealth()) + "%", 24, 120);
                        font.draw(batch, "Attack: " + Math.round(mons[i].getAttack()), 140, 120);
                        font.draw(batch, "Defense: " + Math.round(mons[i].getDefense()), 24, 80);
                        font.draw(batch, "Speed: " + Math.round(mons[i].getSpeed()), 140, 80);
                        font.draw(batch, "Attitude: " + Math.round(mons[i].getAttitude()), 24, 40);
                        Sprite sprite = monSprites[i];
                        sprite.setCenter(160, 190);
                        sprite.setScale(2);
                        sprite.draw(batch);
                        sprite.setScale(1);
                    }
                } else {
                    font.draw(batch, "No mon selected", 75, 120);
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