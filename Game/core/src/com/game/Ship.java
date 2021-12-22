package com.game;

import com.badlogic.gdx.math.Vector2;

public interface Ship {

    int HP = 5000;
    Vector2 velocity = new Vector2();

    void move();


}
