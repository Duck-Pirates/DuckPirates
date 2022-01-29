package com.game;

public class Ship {

    private float velocity = 0;
    private int HP = 5000;
    private int maxAmo = 100;
    private int ammunitonStock = 50;
    
    private Gun[] guns;
    private College college;
    
    Ship(College college) {
    	
    	this.college = college;
    	guns = new Gun[12];
    }

    public void addGun (String side) {
    	
    }

    public void fire(String side) {
    	
    }
}

