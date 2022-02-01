package com.game;

public class GameInfo {
	
    
	@SuppressWarnings("unused")
	private String difficulty = "Easy";
    /* A timer value will also be added */
	@SuppressWarnings("unused")
	private Double masterVolume, musicVolume, soundFxVolume = 0.5;
	@SuppressWarnings("unused")
	private String playerCollege;
	private int numOfColleges;
	private final String[] collegeNames = {"Constantine", "Langwith", "Goodricke", "Anne_Lister", "Halifax", "James", "Wentworth", "Alcuin", "Vanbrugh", "Derwent"};
	private String[] colleges = new String[10];

    /* private Boolean time_limit, soundON; */
    private static final int maxNumShips = 5;
    @SuppressWarnings("unused")
	private static final int maxNumDucks = 50;
    @SuppressWarnings("unused")
	private static final float coinsPerDuckPerSecond = 1;
    
    public GameInfo(String playerCollege, int numColleges) {
    	this.playerCollege = playerCollege;
    	numOfColleges = numColleges;
    	
    	// Not the most elligant solution but will reacte colleges to reach the number required. The college that the player
    	// is part of is set as college[0] and then the rest are added based in when they appear in collegeNames
    	colleges[0] = playerCollege;
    	// j is used so that the players college (college[0]) isnt added to the list twice
    	int j = 0;
    	for(int i = 0; i < numColleges; i++) {
    		if(playerCollege != collegeNames[i] && j == 0)
    			getColleges()[i + 1] = collegeNames[i];
    		else {
    			getColleges()[i + 1] = collegeNames[i + 1];
    			j = 1;
    		}
    	}
    }
    
    public static int getMaxNumShips() {
       return maxNumShips;
    }

	public int getNumOfColleges() {
		return numOfColleges;
	}

	public String[] getColleges() {
		return colleges;
	}
}
