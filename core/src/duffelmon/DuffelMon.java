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
        BitmapFont font;
        
        // creates the first dufflemon for the battle
        Species crabmon = Species.makeSpecies("CrabMon");
        Stats crabmonStat = new Stats(10, 3, 10, 25);
        Mon player = new Mon("Bob", crabmon, 1, crabmonStat);
        Combatant goodGuy = new Combatant(player);
        
	// creates the second dufflemon for the battle
        Species geobro = Species.makeSpecies("Geobro");
        Stats geobroStat = new Stats(10, 3, 10, 25);
        Mon enemy = new Mon("Joe", geobro, 1, geobroStat);
        Combatant badGuy = new Combatant(enemy);
        
        Battle battle = new Battle(goodGuy, badGuy);
        
        @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");  
                font = new BitmapFont();
	}
        
	@Override
	public void render () {
                long playerHealth = Math.round(player.getHealth());
                long playerMaxHealth = Math.round(player.getMaxHealth());
                long enemyHealth = Math.round(enemy.getHealth());
                long enemyMaxHealth = Math.round(enemy.getMaxHealth());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
                font.setColor(Color.WHITE);
                font.draw(batch,  "Player health: " + playerHealth + " / " + playerMaxHealth, 300, 300);
                font.draw(batch, "Enemy health: " + enemyHealth + " / " + enemyMaxHealth, 300, 250);
		batch.end();
                
              
               
                
	}
}
