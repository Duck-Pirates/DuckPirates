package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerShip extends Ship {

	private int hp = 5000;

	PlayerShip(College college) {
		super(college);
	}
	
	@Override
	public boolean update(float delta) {
    	
    	// Checks if the ship should die. If yes then returns true telling the program to delete the ship.
    	if(this.hp  <= 0)
    		return true;
    	
    	int angularAcceleration = 0;
    	int linearAcceleration = 0;
    	
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
    		angularAcceleration += 3;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
    		angularAcceleration -= 3;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
    		linearAcceleration -= 5;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
    		linearAcceleration += 1;
    	}
    	
    	super.rotationUpdate(angularAcceleration, delta);
    	super.velocityUpdate(linearAcceleration, delta);
    	
    	if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
    		shoot(true);
    	} else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
    		shoot(false);
    	}
    	
    	return false;
    }
}
