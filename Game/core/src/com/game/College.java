package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.utils.BodyEditorLoader;

public class College {

    public String collegeName;
    //private Ship[] ships;
    private Body body;
    private float x;
    private float y;

    College(String name, int x, int y){
        collegeName = name;
        //ships = new Ship[GameInfo.getMaxNumShips()];
        this.x = x;
        this.y = y;
        body = createBody("College");
    }
    
    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
    
    private Body createBody(String name) {
    	BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("college/hitbox/collegeHitbox.json"));
        
        // 1. Create a BodyDef.
        BodyDef bd = new BodyDef();
        bd.position.set(x / PPM, y / PPM);
        bd.type = BodyType.StaticBody;
     
        // 2. Create a FixtureDef.
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;
     
        // 3. Create a Body.
		Body model = GameInit.world.createBody(bd);
     
        // 4. Create the body fixture automatically by using the loader.
        loader.attachFixture(model, name, fd, 128 / PPM);
        return model;
    }
}
