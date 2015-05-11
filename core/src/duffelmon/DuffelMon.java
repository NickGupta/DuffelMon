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
        
        @Override
	public void create () {
                initializeData();
                
                // creates the first dufflemon for the battle
                Mon player = new Mon("Bob", Species.getSpecies("Charmander"), 1);

                // creates the second dufflemon for the battle
                Mon enemy = new Mon("Joe", Species.getSpecies("Charmander"), 1);

                GlobalData.initialize(new Stage(), new BitmapFont());
                Battle.startBattle(player, null, enemy, new RandomMoveAI());
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
            GlobalData.updateInputs();
            GameObject.runFrameActions();
        }
        
        private void initializeData() {
            Type heat = Type.makeType("Heat");
            Species.makeSpecies("Charmander", heat, new TreeMap<Move,Integer>());
        }
}
