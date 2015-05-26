package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class MainMenu extends Menu{
    String title;
    private BitmapFont font = GlobalData.getFont();
    private int xValue = 240;
    private int yValue = 400;
    private Color fontColor = Color.RED;
    Texture tex = new Texture("Duffelmon.png");
    private Sprite sprite = new Sprite(tex);
    
    public MainMenu(){
        
    }
    
    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
        
        
    }
}
