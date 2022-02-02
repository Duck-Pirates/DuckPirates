package com.game.utils;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.game.GameInit;

public class CreateObject {

	public static Body createObject(String file, float x, float y, int scale, boolean isStatic) {
    	BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(file));
        
        // 1. Create a BodyDef.
        BodyDef bd = new BodyDef();
        bd.position.set(x / PPM, y / PPM);
        
        if(isStatic) 
    		bd.type = BodyDef.BodyType.StaticBody;
    	else {
    		bd.type = BodyDef.BodyType.DynamicBody;
        	bd.angularDamping = 2f;
        	bd.linearDamping = 2f;
    	}
     
        // 2. Create a FixtureDef.
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;
     
        // 3. Create a Body.
		Body model = GameInit.world.createBody(bd);
     
        // 4. Create the body fixture automatically by using the loader.
        loader.attachFixture(model, "Name", fd, scale / PPM);
        return model;
    }
}
