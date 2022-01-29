 package com.game;

import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class College {

    public String name;
    private Body body;
    public Ship[] ships;
    
    public Texture texture;
    
    private int hp = 10000;
    
    private float x;
    private float y;

    College(String name, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
        body = createObject("college/hitbox/collegeHitbox.json", x, y, 128, true);
        texture = new Texture("college/image/" + name + ".png");
        ships = new Ship[GameInfo.getMaxNumShips()];
    }
    
    public boolean update(float delta) {
    	
    	if (hp <= 0) {
    		return true;
    	}
    	
    	for(Ship i : ships) {
    		if(i != null) {
    			i.update(delta);
    		}
    	}
    	return false;
    }
    
    public boolean addPlayer() {
   		// Finds the first entry in the array that is empty. If non are then returns true
    	// telling the parent that it couldn't make a new ship
    	for(int i = 0; i < GameInfo.getMaxNumShips(); i++) {
    		if(ships[i] == null) {
    			ships[i] = new PlayerShip(this);
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public void addShip() {
    	for(int i = 0; i < GameInfo.getMaxNumShips(); i++) {
    		if(ships[i] == null) {
    			ships[i] = new CPUShip(this);
    			break;
    		}
    	}
    }
    
    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
}
