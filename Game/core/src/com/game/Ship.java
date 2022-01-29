package com.game;

import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class Ship {
	
	@SuppressWarnings("unused")
	private College college;
    public Body body;
    public Texture texture;
    
    private float velocity = 0;
    private int hp = 5000;
    private int maxAmo = 100;
    private int ammunitonStock = 50;
    
    Ship(College college) {
    	
    	this.college = college;
    	body = createObject("ship/hitbox/shipHitbox.json", college.getX(), college.getY(), 64, false);
    	texture = new Texture("ship/image/" + college.name + ".png");
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
}
