package duffelmon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import duffelmon.DuffelMon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new DuffelMon(), config);
                new LwjglApplication(new DuffelMon(), "DuffelMon", 512, 448);
	}
}
