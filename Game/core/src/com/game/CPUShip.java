package com.game;

import com.badlogic.gdx.math.Vector2;
import javax.validation.constraints;
import { Map } from 'game/map';  //This will need to import the map (matrix) for pathfinding around obstacles

public class CPUShip extends Ship {

    CPUShip(College college, int x, int y){
        super(college, x, y);
    }
    // Seek takes a destination as a 2D array of doubles (co-ordinates)
    public void seek(double[] destination) {

    }
}
