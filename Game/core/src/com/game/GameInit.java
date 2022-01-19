package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
// import sun.security.jgss.wrapper.GSSNameElement;
// import java.util.Random;

import static com.game.utils.Constants.PPM;

public class GameInit extends Game {

    /*

    Screen init

     */

    GameScreen gameScreen;
    
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player, ship;
    private OrthographicCamera camera;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / 2, h / 2);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        player = createBox(0, 10, 32, 32, false);
        ship = createBox(10, 10, 64, 32, true);
    }
    
    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        b2dr.render(world, camera.combined.scl(PPM));
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / 2, height / 2);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        b2dr.dispose();
    }
    
    public void update(float delta) {
    	world.step(1 / 60f, 6, 2);
    	
    	inputUpdate(delta);
    	cameraUpdate(delta);
    }
    public void inputUpdate(float delta) {
    	int horizontalForce = 0;
    	int verticalForce = 0;
    	
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		horizontalForce += 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		horizontalForce -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
    		verticalForce -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    		verticalForce += 1;
    	}
    	
    	player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
    }
    
    public void cameraUpdate(float delta) {
    	Vector3 position = camera.position;
    	position.x = player.getPosition().x * PPM;
    	position.y = player.getPosition().y * PPM;
    	camera.position.set(position);
    	
    	camera.update();
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
