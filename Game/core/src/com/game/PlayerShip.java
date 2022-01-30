package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

public class PlayerShip extends Ship {

	private float velocity = 0;
    private int hp = 5000;

	PlayerShip(College college) {
		super(college);
	}
	
	@Override
	public boolean update(float delta) {
    	
    	// Checks if the ship should die. If yes then returns true telling the program to delete the ship.
    	if(this.hp  <= 0)
    		return true;
    	
    	
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
		if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			if (drivingForce != 0){
				drivingForce -= 1;
			}
		}
    	
    	velocity = super.velocityUpdate(velocity, drivingForce, delta);
    	float horizontalVelocity = -velocity * MathUtils.sin(body.getAngle());
    	float verticalVelocity = velocity * MathUtils.cos(body.getAngle());
    	body.setLinearVelocity(horizontalVelocity, verticalVelocity);
    	
    	float newAngle = super.rotationUpdate(rotation, velocity, delta) * rotationScale + body.getAngle();
    	body.setTransform(body.getPosition(), newAngle);
    	
    	
    	return false;
    }
}
