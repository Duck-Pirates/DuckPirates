package com.game;

import com.badlogic.gdx.physics.box2d.Body;

public class Player extends Ship{


    Player (College college){
        super(college, 0,0, Boolean.TRUE);

    }

    public Body getPlayerBody() {
        return super.getBody();
    }
}
