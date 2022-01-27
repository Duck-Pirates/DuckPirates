package com.game;

import com.badlogic.gdx.math.Vector2;
import javax.validation.constraints;
import Gun from 'game/gun';
import College from 'game/college';

public class Ship(College college) {

    boolean active = true; //Ships begin active (/alive)

    velocity = new double[2] //An array of length 2, with doubles, will be used to represent vlocity later on

    College college = college;
    int HP = 5000;

    @Max(100) // Top-end validation - limit to 100
    int ammunitonStock = 50;

    Gun[] guns;
    guns = new Gun[12]; // Allocate 12 items - maximum of six per side?

    Map<String, Int> sidecounts = new HashMap<String, Int>(); //Dictionary for use in Gun...
    sidecounts.put("port",1); // Ships start with 1 gun on each side
    sidecounts.put("starboard",1);


//    Vector2 velocity = new Vector2();
//    void move();

    public boolean canAddGun(String side) {
        // If the last object is truty --> not nil, then there is no space
        if guns[-1] {
            return false;
        }

        if (this.sidecounts(side) >= 6 ) {
            // Six on the side we want to add to
            return false;
        }
        return true; // All is okay
    }

    public void addGun (String side) {
        // If the last object is falsy --> nil, then there is space
        if this.canAddGun(side) {
            this.guns.append(new Gun(side, this));
            this.sidecounts(side) = this.sidecounts(side) + 1; //Adds one to that side
        }
    }

    public boolean fire(String side) {
        if (this.ammunitonStock >= 1) {
            if (side == "port") {
                // FIRE THIS SIDE - CANONBALL MECHANICS TRIGGER
                return true;
            }
            else if (side == "starbaord") {
                // FIRE THIS SIDE - CANONBALL MECHANICS TRIGGER
                return true;
            }
        }
        else {
            //No ammunition - haven't fired
            return false;
        }
    }

    public boolean softDestroy() {
        this.active = false;
        return true; // Return to be able to verify elsewhere if a ship is made inactive... i.e. if (otherShip.softDestroy() --> true, we know it's been destroyed properly
    }
}

