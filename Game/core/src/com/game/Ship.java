package com.game;

import static com.game.utils.CreateObject.createObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

public class Ship {
	
	protected College college;
    protected Body body;
    protected Texture texture;
    protected Bullet[] bullets = new Bullet[5];
    
    protected float velocity = 0;
    protected int hp = 5000;
    protected int maxAmmo = 100;
    protected int ammo = 50;
    protected int numberOfGuns;
    protected float maxVelocity = 4;
    
    Ship(College college) {
    	
    	this.college = college;
    	body = (createObject("ship/hitbox/shipHitbox.json", college.getX(), college.getY(), 64, false));
    	texture = (new Texture("ship/image/" + college.getName() + ".png"));
    	
    	numberOfGuns = 5;
    }
    
    public boolean update(float delta) {
    	if(hp <= 0)
    		return true;
    	else
    		return false;
    }
    
    public void velocityUpdate(int linearAcceleration, float delta) {
    	
    	velocity = velocity + (linearAcceleration * delta) * (1 - velocity / maxVelocity);
    	if (velocity < -0.5f)
    		velocity = -0.5f;
    	float horizontalVelocity = -velocity * MathUtils.sin(body.getAngle());
    	float verticalVelocity = velocity * MathUtils.cos(body.getAngle());
    	body.setLinearVelocity(horizontalVelocity, verticalVelocity);
    }
    
    public void rotationUpdate(int angularAcceleration, float delta) {
    	float angularVelocity = body.getAngularVelocity() + (angularAcceleration * delta) * (velocity / maxVelocity);
    	body.setAngularVelocity(angularVelocity);
    }
    
    public void shoot(boolean right) {
    	for (int i = 0; i < numberOfGuns; i++) {
    		if(ammo > 0) {
	    		if(bullets[i] != null)
	    			GameInit.world.destroyBody(getBullets()[i].getBody());;
	    		
	            bullets[i] = new Bullet(right, body.getPosition().x, body.getPosition().y, body.getAngle(), i);
	            ammo--;
    		} else {
    			return;
    		}
    	}
    }
    
    public int addAmmo(int value) {
    	
    	if (ammo + value > maxAmmo) {
    		int diff = maxAmmo - ammo;
    		ammo = maxAmmo;
    		return value - diff;
    	}
    	
    	ammo += value;
    	return 0;
    }

	public Bullet[] getBullets() {
		return bullets;
	}

	public Texture getTexture() {
		return texture;
	}

	public Body getBody() {
		return body;
	}
}
