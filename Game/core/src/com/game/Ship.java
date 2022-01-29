package com.game;

import static com.game.utils.CreateObject.createObject;
import com.badlogic.gdx.physics.box2d.Body;

public class Ship {

    private int HP = 5000;
    private int maxAmo = 100;
    private int ammunitonStock = 50;
    private College college;
    private Body body;
    
    Ship(College college, int x, int y) {
        this.college = college;
        body = createObject(String.format("%s's Ship", college), "ship/hitbox/shipHitbox.json", x, y, Boolean.TRUE);
    }
}

