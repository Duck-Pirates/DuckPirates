package com.game;

import static com.game.utils.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Bullet {

	private Body body;
	private Texture texture;
	
	Bullet(boolean right, float x, float y, float relativeXVelocity, float relativeYVelocity, float angle) {
		
		texture = new Texture("ship/image/Bullet.png");		
		BodyDef bd = new BodyDef();
        bd.position.set(x, y);
    	bd.type = BodyDef.BodyType.DynamicBody;
		body = GameInit.world.createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1 / PPM, 1 / PPM);
		body.createFixture(shape, 1.0f);
		shape.dispose();
		
		body.setTransform(x, y, angle);
		body.setAngularVelocity(0);
		
		float horizontalVelocity;
		float verticalVelocity;
		
		if (right) {
			horizontalVelocity = relativeXVelocity + 4 * MathUtils.cos(angle + 1 / 2 * (float)Math.PI);
			verticalVelocity = relativeYVelocity + 4 * MathUtils.sin(angle);
		} else {
			horizontalVelocity = relativeXVelocity - 4 * MathUtils.cos(angle + 1 / 2 * (float)Math.PI);
			verticalVelocity = relativeYVelocity - 4 * MathUtils.sin(angle);
		}
    	
		body.setLinearVelocity(horizontalVelocity, verticalVelocity);
	}
	
	public boolean update(float delta) {
		
//		if (collides)
//			return true;
		
		return false;
	}
	
	public Body getBody() {
		return body;
	}

	public Texture getTexture() {
		return texture;
	}
}