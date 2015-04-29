package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DuffelMon extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        BitmapFont font;
        
        Stage stage;
        
        Battle battle;
        HealthDisplay h1;
        HealthDisplay h2;
        
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
        @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");  
                font = new BitmapFont();
                stage = new Stage();
                battle = new Battle(goodGuy, badGuy);
                h1 = new HealthDisplay(goodGuy, 400, 100);
                stage.addActor(h1);
                h2 = new HealthDisplay(badGuy, 100, 400);
                stage.addActor(h2);
	}
        
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();    
	}
}
