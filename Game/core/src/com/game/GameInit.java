package com.game;

import com.badlogic.gdx.Game;
import sun.security.jgss.wrapper.GSSNameElement;
import java.util.Random;

public class GameInit extends Game {

    /*

    Screen init

     */

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

    /*

    Actual game init

     */

    GameInfo gameInfo = new GameInfo();
    private final static College[] collegesArray = new College[10];

    public GameInit(){

        /* Colleges and ships Init */
        for (int i = 0; i < 10; i++){
            College college = new College(GameInfo.colleges_names_array[i]);
            collegesArray[i] = college;
            Random random = new Random();
            int numShips;
            numShips = random.nextInt(gameInfo.getMaxNumBoats()-1)+1;
            /* for (int i = 0; i < numShips; i++){
                Add these many ships to the college's ships array
            }
             */
        }





    }


}
