package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

public class DuffelMon extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        BitmapFont font = new BitmapFont();
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                Species crabmon = new Species("CrabMon");
                Stats stat = new Stats(1, 0, 10, 3 ,10, 25);
                Mon player = new Mon("Bob", crabmon, stat);
	}
        
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
                font.setColor(Color.BLUE);
                font.draw(batch, "Player health" + player.getHealth() + "/" + player.getMaxHealth(), 100, 100);
                
	}
}
