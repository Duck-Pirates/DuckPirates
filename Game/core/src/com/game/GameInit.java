package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.utils.BodyEditorLoader;

import java.util.Random;

public class GameInit extends Game {


    /*

    Screen init

     */

	
	
    GameScreen gameScreen;
    
    GameInfo gameInfo = new GameInfo();
    
    private Box2DDebugRenderer b2dr;
    public static World world;
    private Player player;
    private Body playerBody;
    private College derwentCollege;
    private Texture derwent;
    private College playerCollege;
    
    private SpriteBatch batch;
    private Texture ship;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        gameScreen = new GameScreen(w, h);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        
        derwentCollege = new College("Derwent", 128, 128);
        playerBody = player.getPlayerBody();
        
        batch = new SpriteBatch();
        ship = new Texture("ship/image/blueShip.png");
        derwent = new Texture("college/image/Derwent.png");
    }
    
    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        
        Gdx.gl.glClearColor(62 / 255f, 95 / 255f, 201/ 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Sprite shipSprite = new Sprite(ship);
        Sprite derwentSprite = new Sprite(derwent);
        
        batch.begin();
        shipSprite.setOrigin(32f, 23.5f);
        shipSprite.setPosition(player.getPosition().x * PPM - 32f, player.getPosition().y * PPM - 23.5f);
        shipSprite.setRotation(player.getAngle() * MathUtils.radiansToDegrees);
        shipSprite.draw(batch);
        
        derwentSprite.setOrigin(64f, 64f);
        derwentSprite.setPosition(derwentCollege.getX(), derwentCollege.getY());
        derwentSprite.draw(batch);
        batch.end();
        
        b2dr.render(world, gameScreen.combinedCamera().scl(PPM));
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
    
    public void update(float delta) {
    	world.step(1 / 60f, 6, 2);
    	
    	inputUpdate(delta);
    	gameScreen.cameraUpdate(delta, player);
    	batch.setProjectionMatrix(gameScreen.combinedCamera());
    }
    
    public void inputUpdate(float delta) {
    	int rotation = 0;
    	float rotationScale = 0.05f;
    	
    	int drivingForce = 0;
    	float horisontalVelocity = 0;
    	float verticalVelocity = 0;
    	
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
    		rotation += 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
    		rotation -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
    		drivingForce -= 4;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
    		drivingForce += 1;
    	}
    	
    	float newVelocity = shipVelocity((float)Math.sqrt(Math.pow(player.getLinearVelocity().x, 2) +
    			Math.pow(player.getLinearVelocity().y, 2)), drivingForce, delta);
    	horisontalVelocity -= newVelocity * MathUtils.sin(player.getAngle());
    	verticalVelocity = newVelocity * MathUtils.cos(player.getAngle());
    	player.setLinearVelocity(horisontalVelocity, verticalVelocity);
    	
    	float newAngle = shipRotation(rotation, newVelocity) * rotationScale + player.getAngle();
    	player.setTransform(player.getPosition(), newAngle);
    }
    
    public float shipVelocity(float velocity, int drivingForce, float delta) {
    	return velocity + (drivingForce - (velocity * 0.2f)) * delta;
    }
    
    public float shipRotation(int rotation, float velocity) {
    	return rotation * velocity * 0.2f;
    }
    
    public Body createBody(int x, int y, int width, int height, boolean isStatic) {
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


   public GameInit(){
       //TODO Implement choice of college
       int rand = new Random().nextInt(10);
       this.playerCollege = new College(gameInfo.getCollegeNames()[rand], 256, 256);
       this.player = new Player(playerCollege);
       this.playerBody = player.getBody();
   }
 

}
