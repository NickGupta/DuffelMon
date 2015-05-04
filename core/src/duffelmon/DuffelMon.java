package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.TreeMap;

public class DuffelMon extends ApplicationAdapter {
        
        float framesPerSecond = 60;
        float elapsedTime = 0;
        
        Type normal = Type.makeType("Normal");
        // creates the first dufflemon for the battle
        Species crabmon = Species.makeSpecies("CrabMon", normal, new TreeMap<Move,Integer>());
        Mon player = new Mon("Bob", crabmon, 1);
        Combatant goodGuy = new Combatant(player);
        
	// creates the second dufflemon for the battle
        Species geobro = Species.makeSpecies("Geobro", normal, new TreeMap<Move,Integer>());
        Mon enemy = new Mon("Joe", geobro, 1);
        Combatant badGuy = new Combatant(enemy);
        
        @Override
	public void create () {
                GlobalData.initialize(new Stage(), new BitmapFont());
                Battle.startBattle(goodGuy, badGuy);
	}
        
	@Override
	public void render () {
            elapsedTime += Gdx.graphics.getDeltaTime();
            while (elapsedTime > 0) {
                frameActions();
                elapsedTime -= 1.0/framesPerSecond;
            }
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            GlobalData.getStage().draw(); 
	}
        
        private void frameActions() {
            
        }
}
