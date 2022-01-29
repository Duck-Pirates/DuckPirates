package com.game;

public class GameInfo {
	
    
	public String difficulty = "Easy";
    /* A timer value will also be added */
    public Double masterVolume, musicVolume, soundFxVolume = 0.5;
    public String playerCollege;
    public int numberOfColleges;
    public final String[] collegeNames = {"Constantine", "Langwith", "Goodricke", "Anne_Lister", "Halifax", "James", "Wentworth", "Alcuin", "Vanbrugh", "Derwent"};
    public String[] colleges = new String[10];

    /* private Boolean time_limit, soundON; */
    private static final int maxNumShips = 5;
    private static final int maxNumDucks = 50;
    private static final float coinsPerDuckPerSecond = 1;
    
    public GameInfo(String playerCollege, int numColleges) {
    	this.playerCollege = playerCollege;
    	numberOfColleges = numColleges;
    	colleges[0] = playerCollege;
    	int j = 0;
    	for(int i = 0; i < numColleges; i++) {
    		if(playerCollege != collegeNames[i] && j == 0)
    			colleges[i + 1] = collegeNames[i];
    		else {
    			colleges[i + 1] = collegeNames[i + 1];
    			j = 1;
    		}
    	}
    }
    
    public static int getMaxNumShips() {
       return maxNumShips;
    }
}
