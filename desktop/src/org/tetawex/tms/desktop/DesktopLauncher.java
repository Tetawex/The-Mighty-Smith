package org.tetawex.tms.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.tetawex.tms.core.TMSGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.vSyncEnabled = false;
		config.foregroundFPS=0;
		config.backgroundFPS=0;
		new LwjglApplication(new TMSGame(), config);
	}
}
