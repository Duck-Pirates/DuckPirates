package com.duckpirates;

import com.badlogic.gdx.Game;

public class Boot extends Game{

	public static Boot INSTANCE;
	private int width_screen, height_screen;
	private OrthographicCamera camera;

	public Boot{
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.width_screen = Gdx.graphics.getWidth();
		this.height_screen = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(yDown:false, width_screen, height_screen);
		setScreen(new GameScreen(camera));
	}
}