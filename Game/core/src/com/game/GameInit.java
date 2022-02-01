package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameInit extends Game {

    //TODO Pause Screen and End Game Screen
    //TODO Combact College


    GameInfo gameInfo = new GameInfo("Derwent", 4);
    GameScreen gameScreen;
    
    
    // Not implemented yet. Ideal is to delete Bodys only after all physics have been calculated to avoid errors
    // public Body[] toDelete;
    
    private Box2DDebugRenderer b2dr;
    public static World world;
    
    private College[] colleges;
    
    public static SpriteBatch batch;
    
    private int stage;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        stage = 1;
        
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        gameScreen = new GameScreen(w, h);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        colleges = new College[gameInfo.getNumOfColleges()];
        
        for(int i = 0; i < gameInfo.getNumOfColleges(); i++) {
        	if(i != 0) {
	        	colleges[i] = new College(gameInfo.getColleges()[i], 128f * i, 128f);
	        	colleges[i].addShip();
        	} else {
	        	colleges[i] = new College(gameInfo.getColleges()[i], 128f * i, 128f);
        		colleges[i].addPlayer();
	        }
        }
        
        batch = new SpriteBatch();
        
        // Max number of bodies to be deleted is the theoretical max number of ships and bullets
        // int maxTotalShips = GameInfo.getMaxNumShips() * GameInfo.getNumOfColleges();
        // toDelete = new Body[maxTotalShips + maxTotalShips * 5];
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
	        	for(Ship j : i.getShips()) {
	        		if (j != null) {
	        			for(Bullet k : j.getBullets()) {
	        				if (k != null) {
		        				Sprite sprite = new Sprite(k.getTexture());
				        		sprite.setOrigin(1.5f, 1.5f);
				                sprite.setPosition(k.getBody().getPosition().x * PPM - 1.5f, k.getBody().getPosition().y * PPM - 1.5f);
				                sprite.setRotation(k.getBody().getAngle() * MathUtils.radiansToDegrees);
				                sprite.draw(batch);
	        				}
	        			}
		        		Sprite sprite = new Sprite(j.getTexture());
		        		sprite.setOrigin(32f, 23.5f);
		                sprite.setPosition(j.getBody().getPosition().x * PPM - 32f, j.getBody().getPosition().y * PPM - 23.5f);
		                sprite.setRotation(j.getBody().getAngle() * MathUtils.radiansToDegrees);
		                sprite.draw(batch);
	        		}
	        	}
	        	Sprite sprite = new Sprite(i.getTexture());
	        	sprite.setOrigin(64f, 64f);
	            sprite.setPosition(i.getX(), i.getY());
	            sprite.draw(batch);
        	}
        }
        
        batch.end();
        
        b2dr.render(world, gameScreen.combinedCamera().scl(PPM));
    }
    
    public void update(float delta) {
    	
    	if (stage == 1) {
	    	for(College i : colleges) {
	    		if(i != null) {
	    			i.update(delta);
	    		}
	    	}
	    	gameScreen.cameraUpdate(colleges[0].getShips()[0].getBody(), delta);
	    	batch.setProjectionMatrix(gameScreen.combinedCamera());
	    	world.step(1 / 60f, 6, 2);
	        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
	            gameScreen.pause();
	            stage = 2;
	        }
    	} else if (stage == 2) {
    		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
	            gameScreen.resume();
	            stage = 1;
	        }
    	}
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
