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
    private int xItem = 250;
    private int yItem = 100;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private String m = "";
    
    public BattleMenu(float x, float y, Combatant c) {
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
        } else if(m.equals("Fight")){
            font.setColor(fontColor);
            font.draw(batch, mon.getMoveName(0), xValue, yValue);
            xValue += 70;
            font.draw(batch, mon.getMoveName(1), xValue, yValue);
            xValue -= 70;
            yValue -= 40;
            font.draw(batch, mon.getMoveName(2), xValue, yValue);

            xValue += 80;
            font.draw(batch, mon.getMoveName(3), xValue, yValue);
            yValue += 40;
            xValue -= 80;

            font.draw(batch, "____", x1, y1);
        }else if(m.equals("Change")){
            //Change menu
        }else if(m.equals("Run")){
            //Run
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
                    x1 += 80; 
                }else if(x1 == 180){
                    x1 -= 80;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
                if(x1 == 100 && y1 == 100){
                    m = "Fight"; 
                }
                else if(x1 == 100 && y1 == 60){
                    m = "Run"; 
                }
                else if(x1 == 180 && y1 == 60){
                    setServant(new ItemMenu(this, xItem, yItem));
                }
                else if(x1 == 180 && y1 == 100){
                    m = "Change"; 
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
                    x1 += 80; 
                }else if(x1 == 180){
                    x1 -= 80;
                }
            }
            if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
                if(x1 == 100 && y1 == 100){
                    setOutput("MOVE0"); 
                }
                if(x1 == 100 && y1 == 60){
                    setOutput("MOVE2");
                }
                if(x1 == 180 && y1 == 60){
                    setOutput("MOVE3"); 
                }
                if(x1 == 180 && y1 == 100){
                    setOutput("MOVE1");
                }
            }   
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.BACK)){
            m = "";
        }
    }
}
