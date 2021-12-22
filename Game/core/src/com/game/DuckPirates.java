package com.game;

import com.badlogic.gdx.Game;

public class DuckPirates extends Game {

    GameScreen gameScreen;

    @Override
    public void create(){
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        gameScreen.resize(width, height);
    }
}
