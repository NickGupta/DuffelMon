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
public class NameMenu extends Menu {
    private int selectionX = 0;
    private int selectionY = 0;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private String name = "";
    boolean capital = false;
    private String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                                "u", "v", "w", "x", "y", "z", "_", "Shift", "Del.", "Done"};
    
    public NameMenu(Menu m) {
        super(m);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 448, true);
        font.setColor(fontColor);
        font.draw(batch, "Name: " + name, 16, 288);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                font.draw(batch, letters[j + i * 10], 16 + j * 48, 256 - i * 48);
            }
        }
        font.draw(batch, "_", 16 + selectionX * 48, 252 - selectionY * 48);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.RIGHT)) {
            selectionX++;
            if (selectionX > 9) {
                selectionX = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.LEFT)) {
            selectionX--;
            if (selectionX < 0) {
                selectionX = 9;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selectionY++;
            if (selectionY > 2) {
                selectionY = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selectionY--;
            if (selectionY < 0) {
                selectionY = 2;
            }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            int lettersPos = selectionX + selectionY * 10;
            switch(lettersPos) {
                case 26:
                    if (name.length() < 10) {
                        name += " ";
                    } else {
                        setServant(new TextBox("The name can't be any longer!", true));
                    }
                    break;
                case 27:
                    capital = !capital;
                    break;
                case 28:
                    if (name.length() > 0) {
                        name = name.substring(0, name.length() - 1);
                    }
                    break;
                case 29:
                    if (name.length() > 0) {
                        setOutput(name);
                    } else {
                        setServant(new TextBox("You didn't enter a name!", true));
                    }
                    break;
                default:
                    if (name.length() < 10) {
                        String l = letters[lettersPos];
                        if (capital) {
                            capital = false;
                            l = l.toUpperCase();
                        }
                        name += l;
                    } else {
                        setServant(new TextBox("The name can't be any longer!", true));
                    }
                    break;
            }
        }
    }
}
