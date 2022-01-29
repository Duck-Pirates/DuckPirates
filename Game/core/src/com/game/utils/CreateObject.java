package com.game.utils;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.GameInit;

public class CreateObject {

	public static Body createObject(String name, String file, float x, float y) {
    	BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(file));
        
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
