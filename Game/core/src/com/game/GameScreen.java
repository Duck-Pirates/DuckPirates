package com.game;

import static com.game.utils.Constants.PPM;
import static com.game.utils.Constants.SCALE;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class GameScreen implements Screen {

    //screen
    private OrthographicCamera camera;
    //graphics
    @SuppressWarnings("unused")
	private Texture background;

    GameScreen(float width, float height){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width / SCALE, height / SCALE);
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        //TODO Resize bodies so objects (like ships) don't have the same dimensions when the screen resized
        //TODO MAX and MIN value for resizing the screen
    	camera.setToOrtho(false, width / SCALE, height / SCALE);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }
    
    @Override
    public void dispose() {
    }
    
    public void cameraUpdate(Body player, float delta) {
    	Vector3 position = camera.position;
    	position.x = player.getPosition().x * PPM;
    	position.y = player.getPosition().y * PPM;
    	camera.position.set(position);
    	
    	camera.update();
    }
    
    public Matrix4 combinedCamera() {
    	return camera.combined;
    }
}
