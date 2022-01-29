package com.game;

public class GameInfo {
	
    
	public String difficulty = "Easy";
    /* A timer value will also be added */
    public Double masterVolume, musicVolume, soundFxVolume = 0.5;
    public static String[] collegeNames = {"Constantine", "Langwith", "Goodricke", "Anne Lister", "Halifax", "James", "Wentworth", "Alcuin", "Vanbrugh", "Derwent"};

    /* private Boolean time_limit, soundON; */
    private static final int maxNumShips = 5;
    private static final int maxNumDucks = 50;
    private static final float coinsPerDuckPerSecond = 1;
    
    public GameInfo() {
    	
    }
    
    public static int getMaxNumShips() {
       return maxNumShips;
    }

    public static String[] getCollegeNames(){return collegeNames;}
}
