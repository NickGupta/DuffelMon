/*
*Methods that return booleans to determine if a key is being pushed 
 */
package duffelmon;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class BattleMenu extends Menu {
    
    private Combatant combatant;
    private int xValue = 100;
    private int yValue = 100;
    private int x1 = 100;
    private int y1 = 100;
    private int xItem = 256;
    private int yItem = 118;
    private int xMon = 256;
    private int yMon = 288;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private String m = "";
    
    public BattleMenu(Combatant c) {
        combatant = c;
    }
    
    public void drawMove(int x, int y, Batch batch, Mon mon, int i){
        if(mon.getMove(i) != null)
            font.draw(batch, mon.getMoveName(i) + "    " + mon.getPowerPoints(i) + "/" + mon.getMove(i).getPowerPoints(), x, y);
        else
            font.draw(batch, "----", x, y);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 128);
        Mon mon = combatant.getCurrentMon();
        if(m.equals("")){
        
            font.setColor(fontColor);
            font.draw(batch, "Fight", xValue, yValue);
            xValue += 120;
            font.draw(batch, "Change", xValue, yValue);
            xValue -= 120;
            yValue -= 40;
            font.draw(batch, "Run", xValue, yValue);

            xValue += 120;
            font.draw(batch, "Item", xValue, yValue);
            yValue += 40;
            xValue -= 120;

            font.draw(batch, "____", x1, y1);
        } else if(m.equals("Fight")){
            font.setColor(fontColor);
            drawMove(xValue, yValue, batch, mon, 0);
            
            xValue += 120;
            drawMove(xValue, yValue, batch, mon, 1);
            xValue -= 120;
            yValue -= 40;
            
            drawMove(xValue, yValue, batch, mon, 2);

            xValue += 120;
            drawMove(xValue, yValue, batch, mon, 3);
            yValue += 40;
            xValue -= 120;

            font.draw(batch, "____", x1, y1);
        }
        super.draw(batch, alpha);
    }
  
    @Override
    public void frameActions() {
        Mon mon = combatant.getCurrentMon();
        if(m.equals("")){ 
            if(GlobalData.keyPressed(GlobalData.Inputs.UP)||GlobalData.keyPressed(GlobalData.Inputs.DOWN)){
                if(y1 == 100){
                    y1 -= 40; 
                } else if(y1 == 60){
                    y1 += 40;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.RIGHT)||GlobalData.keyPressed(GlobalData.Inputs.LEFT)){
                if(x1 == 100){
                    x1 += 120; 
                }else if(x1 == 220){
                    x1 -= 120;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
                if(x1 == 100 && y1 == 100){
                    m = "Fight"; 
                }
                else if(x1 == 100 && y1 == 60){
                    m = "Run"; 
                }
                else if(x1 == 220 && y1 == 60){
                    //setServant(new ItemMenu(this, xItem, yItem));
                    setServant(new ItemMenu(this, xItem, yItem, combatant.getItems()));
                }
                else if(x1 == 220 && y1 == 100){
                    setServant(new MonMenu(this, xMon, yMon, combatant.getMons()));
                }
            }
        } else if (m.equals("Fight")) {
            if(GlobalData.keyPressed(GlobalData.Inputs.UP)||GlobalData.keyPressed(GlobalData.Inputs.DOWN)){
                if(y1 == 100) {
                    y1 -= 40; 
                } else if(y1 == 60) {
                    y1 += 40;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.RIGHT)||GlobalData.keyPressed(GlobalData.Inputs.LEFT)){
                if(x1 == 100){
                    x1 += 120; 
                }else if(x1 == 220){
                    x1 -= 120;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
                if(x1 == 100 && y1 == 100){
                    setOutput("MOVE0");
                }
                if(x1 == 100 && y1 == 60){
                    setOutput("MOVE2");
                }
                if(x1 == 220 && y1 == 60){
                    setOutput("MOVE3"); 
                }
                if(x1 == 220 && y1 == 100){
                    setOutput("MOVE1");
                }
            }   
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.BACK)){
            m = "";
        }
    }
    
    @Override
    public boolean readServantOutput(String s) {
        if (!s.equals("ForgetIt")) {
            setOutput(s);
        }
        return true;
    }
}
