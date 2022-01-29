package com.game;

import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

public class Ship {

	private College college;
    public Body body;
    public Texture texture;
    private Gun[] guns;
    
    private float velocity = 0;
    private int hp = 5000;
    private int maxAmo = 100;
    private int ammunitonStock = 50;
    
    Ship(College college) {
    	
    	this.college = college;
    	body = createObject("ship/hitbox/shipHitbox.json", college.getX(), college.getY(), 64, false);
    	texture = new Texture("ship/image/blueShip.png");
    	guns = new Gun[12];
    }
    
    // This needs to be changed. Update will probably be overrided by and extension for player and for cupShip
    public boolean update(float delta) {
    	
    	// Checks if the ship should die. If yes then returns true telling the program to delete the ship.
    	if (hp <= 0) {
    		return true;
    	}
    	
    	int rotation = 0;
    	float rotationScale = 0.05f;
    	
    	int drivingForce = 0;
    	
    	
    	
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
    	
    	velocity = velocityUpdate(velocity, drivingForce, delta);
    	float horisontalVelocity = -velocity * MathUtils.sin(body.getAngle());
    	float verticalVelocity = velocity * MathUtils.cos(body.getAngle());
    	body.setLinearVelocity(horisontalVelocity, verticalVelocity);
    	
    	float newAngle = rotationUpdate(rotation, velocity, delta) * rotationScale + body.getAngle();
    	body.setTransform(body.getPosition(), newAngle);
    	
    	
    	return false;
    }
    
    public float velocityUpdate(float velocity, int drivingForce, float delta) {
    	return velocity + (drivingForce - (velocity * 0.2f)) * delta;
    }
    
    public float rotationUpdate(int rotation, float velocity, float delta) {
    	return rotation * velocity * 0.2f * delta;
    }

    public void addGun (String side) {
    	
    }

    public void fire(String side) {
    	
    }
}

