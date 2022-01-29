package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameInit extends Game {
	
	GameInfo gameInfo = new GameInfo("Derwent", 4);
    GameScreen gameScreen;
    
    private Box2DDebugRenderer b2dr;
    public static World world;
    
    private College[] colleges;
    
    public static SpriteBatch batch;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        gameScreen = new GameScreen(w, h);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        colleges = new College[gameInfo.numberOfColleges];
        
        for(int i = 0; i < gameInfo.numberOfColleges; i++) {
        	if(i != 0) {
	        	colleges[i] = new College(gameInfo.colleges[i], 128f * i, 128f);
	        	colleges[i].addShip();
        	} else {
	        	colleges[i] = new College(gameInfo.colleges[i], 128f * i, 128f);
        		colleges[i].addPlayer();
	        }
        }
        
        batch = new SpriteBatch();
    }
    
    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        
        Gdx.gl.glClearColor(62 / 255f, 95 / 255f, 201/ 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        for(College i : colleges) {
        	if (i != null) {
	        	for(Ship j : i.ships) {
	        		if (j != null) {
		        		Sprite sprite = new Sprite(j.texture);
		        		sprite.setOrigin(32f, 23.5f);
		                sprite.setPosition(j.body.getPosition().x * PPM - 32f, j.body.getPosition().y * PPM - 23.5f);
		                sprite.setRotation(j.body.getAngle() * MathUtils.radiansToDegrees);
		                sprite.draw(batch);
	        		}
	        	}
	        	Sprite sprite = new Sprite(i.texture);
	        	sprite.setOrigin(64f, 64f);
	            sprite.setPosition(i.getX(), i.getY());
	            sprite.draw(batch);
        	}
        }
        
        batch.end();
        
        b2dr.render(world, gameScreen.combinedCamera().scl(PPM));
    }
    
    public void update(float delta) {
    	world.step(1 / 60f, 6, 2);
    	
    	for(College i : colleges) {
    		if(i != null) {
    			i.update(delta);
    		}
    	}
    	gameScreen.cameraUpdate(delta, colleges[0].ships[0].body);
    	batch.setProjectionMatrix(gameScreen.combinedCamera());
    }

    @Override
    public void resize(int width, int height) {
        gameScreen.resize(width, height);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        gameScreen.dispose();
    }
}
