package com.game;

import java.util.Random;

public class CPUShip extends Ship {
	
	private int mode = 0;
	private int countDown = 0;
	private int direction = 0;
	
    CPUShip(College college) {
		super(college);
	}
    
    public boolean update(float delta) {
    	if(hp <= 0)
    		return true;
    	
    	if(mode == 0)
    		explore(delta);
    	
    	return false;
    }
    
    private void explore(float delta) {
    	
    	countDown -= delta;
    	if (countDown <= 0) {
    		Random rand = new Random();
    		direction = rand.nextInt(3);
    		countDown += 500;
    	}
    	
    	switch(direction) {
    		case 0:
    			rotationUpdate(3, delta);
    			break;
    		case 1:
    			rotationUpdate(-3, delta);
    			break;
    		case 2:
    			rotationUpdate(0, delta);
    			break;
    	}
    	velocityUpdate(1, delta);
    }
}