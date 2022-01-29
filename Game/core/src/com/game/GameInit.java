package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.physics.box2d.World;
import com.game.utils.BodyEditorLoader;

public class GameInit extends Game {


    /*

    Screen init

     */

	
	
    GameScreen gameScreen;
    
    GameInfo gameInfo = new GameInfo();
    
    private Box2DDebugRenderer b2dr;
    public static World world;
    private Body player;
    private College[] colleges;
    private College derwentCollege;
    private Texture derwent;
    
    public static SpriteBatch batch;
    private Texture ship;
    
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
        	colleges[i] = new College(gameInfo.collegeNames[i], -128f * i, 128f);
        	colleges[i].addShip();
        }
        
        
        derwentCollege = new College("Derwent", 128f, 128f);
        player = createShip(player, "Name", 0, 0, 64 / PPM);
        
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
    	
    	for(College i : colleges) {
    		if(i != null) {
    			i.update(delta);
    		}
    	}
    	
    	gameScreen.cameraUpdate(delta, player);
    	batch.setProjectionMatrix(gameScreen.combinedCamera());
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

//    private final static College[] collegesArray = new College[10];
//
//    public GameInit(){
//
//
//    }
 

}
