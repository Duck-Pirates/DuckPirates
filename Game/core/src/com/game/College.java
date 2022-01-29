package com.game;

import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.physics.box2d.Body;

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
        body = createObject("College", "college/hitbox/collegeHitbox.json", 128f, 128f);
    }
    
    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
    
    
}
