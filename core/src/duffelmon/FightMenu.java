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
public class FightMenu extends Menu {
    
    private Combatant combatant;
    private int xValue = 100;
    private int yValue = 100;
    private int x1 = 100;
    private int y1 = 100;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;

    private String m = "";
    
    public FightMenu(int x, int y, Combatant c) {
        super(x, y);
        combatant = c;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Mon mon = combatant.getCurrentMon();
        if(m.equals("")){
        
            font.setColor(fontColor);
            font.draw(batch, "Fight", xValue, yValue);
            xValue += 70;
            font.draw(batch, "Change", xValue, yValue);
            xValue -= 70;
            yValue -= 40;
            font.draw(batch, "Run", xValue, yValue);

            xValue += 80;
            font.draw(batch, "Item", xValue, yValue);
            yValue += 40;
            xValue -= 80;

            font.draw(batch, "____", x1, y1);

            font.draw(batch, m, 300, 300);
        } else if(m.equals("Fight")){
            font.setColor(fontColor);
            font.draw(batch, mon.getMove(0).getName(), xValue, yValue);
            xValue += 70;
            font.draw(batch, mon.getMove(1).getName(), xValue, yValue);
            xValue -= 70;
            yValue -= 40;
            font.draw(batch, mon.getMove(2).getName(), xValue, yValue);

            xValue += 80;
            font.draw(batch, mon.getMove(3).getName(), xValue, yValue);
            yValue += 40;
            xValue -= 80;

            font.draw(batch, "____", x1, y1);

            font.draw(batch, m, 300, 300);
        }else if(m.equals("Item")){
            //Item menu
        }else if(m.equals("Change")){
            //Change menu
        }else if(m.equals("Run")){
            //Run
        }
        super.draw(batch, alpha);
    }
    
}
