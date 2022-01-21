package com.game;

import com.badlogic.gdx.math.Vector2;
import javax.validation.constraints;
import Gun from 'game/gun';
import College from 'game/college';

public class Ship(College college) {

    College college = college;
    int HP = 5000;

    @Max(100) // Top-end validation - limit to 100
    int ammunitonStock = 50;

    Gun[] guns;
    guns = new Gun[12]; // Allocate 12 items - maximum of six per side?

    Map<String, Int> sidecounts = new HashMap<String, Int>(); //Dictionary for use in Gun...
    sidecounts.put("port", 1); // Ships start with 1 gun on each side
    sidecounts.put("starboard", 1);


//    Vector2 velocity = new Vector2();
//    void move();

    public boolean canAddGun(String side) {
        // If the last object is truty --> not nil, then there is no space
        if guns[-1] {
            return false;
        }

        portCount = 0;
        starboardCount = 0;

        // For each gun, count it's side
        for (String gun : guns) {
            if (gun.side == "port") {
                portCount ++;
            }
            else if (gun.side == "starboard") {
                starboardCount ++;
            }
            else {
                // Throw error?
                return false;
            }
        }

        if (portCount > 6 || starboardCount > 6) {
            // Over six on one side
            return false;
        }

        return true; // All is okay


    }

    public void addGun(String side) {
        // If the last object is falsy --> nil, then there is space
        if this.canAddGun(side) {
            this.guns.append(new Gun(side, this));
        }
    }
}

