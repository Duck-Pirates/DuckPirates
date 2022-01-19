package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
// import sun.security.jgss.wrapper.GSSNameElement;
// import java.util.Random;

public class GameInit extends Game {

    /*

    Screen init

     */

    GameScreen gameScreen;
    GameScreen pauseScreen;
    
    private Box2DDebugRenderer b2dr;
    private World world;
    public Body player;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        gameScreen = new GameScreen(w, h);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        player = createBox(0, 0, 32, 32, false);
        createBox(32, 32, 64, 32, true);
        createBox(32, 0, 32, 32, true);
    }
    
    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        
        Gdx.gl.glClearColor(62 / 255f, 95 / 255f, 201/ 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        b2dr.render(world, gameScreen.combinedCameraScaled());
    }

    @Override
    public void resize(int width, int height) {
        
    }
    
    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        b2dr.dispose();
        pauseScreen.dispose();
    }
    
    public void update(float delta) {
    	world.step(1 / 60f, 6, 2);
    	
    	inputUpdate(delta);
    	gameScreen.cameraUpdate(delta, player);
    }
    
    public void inputUpdate(float delta) {
    	int horizontalForce = 0;
    	int verticalForce = 0;
    	
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		horizontalForce -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		horizontalForce += 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    		verticalForce -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
    		verticalForce += 1;
    	}
    	
    	player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
    }
    
    public Body createBox(int x, int y, int width, int height, boolean isStatic) {
    	Body pBody;
    	BodyDef def = new BodyDef();
    	
    	if(isStatic) 
    		def.type = BodyDef.BodyType.StaticBody;
    	else 
    		def.type = BodyDef.BodyType.DynamicBody;
    	
	    def.position.set(x / PPM, y / PPM);
	    def.fixedRotation = true;
    	
    	pBody = world.createBody(def);
    	
    	PolygonShape shape = new PolygonShape();
    	shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
    	
    	pBody.createFixture(shape, 1.0f);
    	shape.dispose();
    	return pBody;
    }
    
    /*

    Actual game init

     */

//    GameInfo gameInfo = new GameInfo();
//    private final static College[] collegesArray = new College[10];
//
//    public GameInit(){
//
//
//    }
 

}
