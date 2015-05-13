package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.HashMap;

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
                TextBox blah = new TextBox(50, 50, "Hello World");
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
            Type normal = Type.makeType("Normal");
            Type heat = Type.makeType("Heat");
            Move tackle = Move.makeMove("Tackle", normal, 40, 1, 35, 0);
            HashMap<Move,Integer> moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            Species.makeSpecies("Charmander", heat, moveset);
        }
}
