package com.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.GameInit;

public class DesktopLauncher {
	public static void main (String[] arg) {

		// If you run this method, the game will start.

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Duck Pirates";
		config.width = 1000;
		config.height = 700;
		config.addIcon("icon/duckpirateslogo_16x16.png", Files.FileType.Internal);
		config.addIcon("icon/duckpirateslogo_32x32.png", Files.FileType.Internal);
		new LwjglApplication(new GameInit(), config);
	}
}
