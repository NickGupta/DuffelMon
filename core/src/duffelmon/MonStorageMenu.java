/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class MonStorageMenu extends Menu {
    private int selectionX = 0;
    private int selectionY = 0;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private Mon[] party;
    private Mon[] storage;
    
    public MonStorageMenu(Menu m, Mon[] p, Mon[] s) {
        super(m);
        party = p;
        storage = s;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 448, true);
        font.setColor(fontColor);
        font.draw(batch, "Party", 32, 414);
        font.draw(batch, "Storage", 128, 414);
        for (int i = 0; i < 3; i++) {
            String name;
            if (party[i] == null) {
                name = "---";
            } else {
                name = party[i].getName();
            }
            font.draw(batch, name, 32, 384 - i * 20);
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                String name;
                if (storage[j + i * 4] == null) {
                    name = "---";
                } else {
                    name = storage[j + i * 4].getName();
                }
                font.draw(batch, name, 128 + j * 96, 384 - i * 20);
            }
        }
        font.draw(batch, "_____", 32 + selectionX * 96, 384 - selectionY * 20);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.RIGHT)) {
            selectionX++;
            if (selectionX > 4) {
                selectionX = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.LEFT)) {
            selectionX--;
            if (selectionX < 0) {
                selectionX = 4;
            }
        }
        if (selectionX == 0) {
            if (selectionY > 2) {
                selectionY = 2;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selectionY++;
            if (selectionX == 0) {
                if (selectionY > 2) {
                    selectionY = 0;
                }
            } else {
                if (selectionY > 15) {
                    selectionY = 0;
                }
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selectionY--;
            if (selectionY < 0) {
                if (selectionX == 0) {
                    selectionY = 2;
                } else {
                    selectionY = 15;
                }
            }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.BACK)) {
            setOutput("ForgetIt");
        }
    }
}
