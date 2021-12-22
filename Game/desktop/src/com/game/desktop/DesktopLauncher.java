package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.DuckPirates;

public class DesktopLauncher {
	public static void main (String[] arg) {

		// If you run this method, the game will start.

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DuckPirates(), config);
	}
}
