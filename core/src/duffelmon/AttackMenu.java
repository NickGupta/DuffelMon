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
public class AttackMenu extends GameObject{
    int xValue = 100;
    int yValue = 100;
    int x1 = 100;
    int y1 = 100;
    Batch b;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    private Mon mon;
    private float x;
    private float y;
    
    
    private String m = "m Default Value";
    public AttackMenu(){
        
    }
    @Override
    public void draw(Batch batch, float alpha) {
        
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
        
        
    }
   
    @Override
    public void frameActions(){
        if(GlobalData.keyPressed(GlobalData.Inputs.UP)||GlobalData.keyPressed(GlobalData.Inputs.DOWN)){
            if(y1 == 100){
               y1 -= 40; 
            }else if(y1 == 60){
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
            if(x1 == 100 && y1 == 60){
                m = "Run"; 
            }
            
            if(x1 == 180 && y1 == 60){
                m = "Item"; 
            }
            
            if(x1 == 180 && y1 == 100){
                m = "Change"; 
            }
        }
    }
}
