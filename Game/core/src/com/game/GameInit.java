package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.utils.BodyEditorLoader;

public class GameInit extends Game {


    /*

    Screen init

     */

    GameScreen gameScreen;
    
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;
    
    private SpriteBatch batch;
    private Texture ship;
    
    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        gameScreen = new GameScreen(w, h);
        
        world = new World(new Vector2(0, 0f), false);
        b2dr = new Box2DDebugRenderer();
        
        player = createShip(player, "shipBody", 0, 0, 64 / PPM);
        createBody(32, 0, 32, 32, true);
        
        batch = new SpriteBatch();
        ship = new Texture("ship/image/blueShip.png");
    }
    
    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        
        Gdx.gl.glClearColor(62 / 255f, 95 / 255f, 201/ 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(ship, player.getPosition().x * PPM - 32f, player.getPosition().y * PPM - 23.5f);
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
    	float rotation = 0;
    	float rotationScale = 0.05f;
    	
    	int totalForce = 0;
    	float horisontalForce = 0;
    	float verticalForce = 0;
    	
    	if(player != null) {
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		rotation += 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		rotation -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    		totalForce -= 1;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
    		totalForce += 1;
    	}
    	
    	float newAngle = rotation * rotationScale + player.getAngle();
    	player.setTransform(player.getPosition(), newAngle);
    	
    	horisontalForce -= totalForce * MathUtils.sin(player.getAngle());
    	verticalForce = totalForce * MathUtils.cos(player.getAngle());
    	player.setLinearVelocity(horisontalForce * 5, verticalForce * 5);
    	}
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
    
    public Body createShip(Body model, String name, int x, int y, float scale) {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("ship/hitbox/shipHitbox.json"));
     
        // 1. Create a BodyDef.
        BodyDef bd = new BodyDef();
        bd.position.set(x / PPM, y / PPM);
        bd.type = BodyType.DynamicBody;
     
        // 2. Create a FixtureDef.
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;
     
        // 3. Create a Body.
        model = world.createBody(bd);
     
        // 4. Create the body fixture automatically by using the loader.
        loader.attachFixture(model, name, fd, scale);
        return model;
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
