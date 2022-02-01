package com.game;

import static com.game.utils.Constants.PPM;
import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Ship {
	
	@SuppressWarnings("unused")
	private College college;
    public Body body;
    public Texture texture;
    private Body[] shots = new Body[5];
    
    private float velocity = 0;
    private int hp = 5000;
    private int maxAmo = 100;
    private int ammunitonStock = 50;
    private int numberOfGuns;
    
    Ship(College college) {
    	
    	this.college = college;
    	body = createObject("ship/hitbox/shipHitbox.json", college.getX(), college.getY(), 64, false);
    	texture = new Texture("ship/image/" + college.name + ".png");
    	
    	numberOfGuns = 5
    			;
    }
    
    public boolean update(float delta) {
    	if(hp <= 0)
    		return true;
    	else
    		return false;
    }
    
    public float velocityUpdate(float velocity, int drivingForce, float delta) {
    	return velocity + (drivingForce - (velocity * 0.2f)) * delta;
    }
    
    public float rotationUpdate(int rotation, float velocity, float delta) {
    	return rotation * velocity * 0.2f;
    }
    
    public void shoot(boolean right) {
    	for (int i = 0; i < numberOfGuns; i++) {
    		
            BodyDef bd = new BodyDef();
            bd.position.set(body.getPosition().x, body.getPosition().y);
        	bd.type = BodyDef.BodyType.DynamicBody;
    		
    		shots[i] = GameInit.world.createBody(bd);
    		
    		PolygonShape shape = new PolygonShape();
    		shape.setAsBox(1 / PPM, 1 / PPM);
    		
    		shots[i].createFixture(shape, 1.0f);
    		shape.dispose();

    		if(right) {
	    		shots[i].setTransform(body.getPosition().x + 11f / PPM, body.getPosition().y + (-7f + 4f * i) / PPM, body.getAngle());
	    		float horizontalVelocity = -4 * MathUtils.cos(body.getAngle());
	        	float verticalVelocity = 4 * MathUtils.sin(body.getAngle());
	    		shots[i].setLinearVelocity(horizontalVelocity, verticalVelocity);
    		} else {
    			shots[i].setTransform(body.getPosition().x - 11f / PPM, body.getPosition().y + (-7f + 4f * i) / PPM, body.getAngle());
	    		float horizontalVelocity = -4 * MathUtils.cos(body.getAngle());
	        	float verticalVelocity = 4 * MathUtils.sin(body.getAngle());
	    		shots[i].setLinearVelocity(horizontalVelocity, verticalVelocity);
    		}
    	}
    }
}
