package com.game;

public class Gun(String side, Ship ship) {
    sides = new String["port", "starboard"];
    String side;

    ship = ship;

    public void setSide(String side) {
        this.side = side;
    }

    public boolean sufficentAmmunition(Ship ship) {
        return  (ship.ammunitonStock > 0) ;
    }

    public void fire(Ship ship, String side) {
        int fireNumber;

        if sufficentAmmunition(ship) {
            // If we have enough ammunition...
            // Use all of that side
            if (ship.ammunitonStock >= ship.sidecounts.get(side)) {
                fireNumber = ship.sidecounts.get(side)
            }
            // Otherwise use what we have
            else {
                fireNumber = ship.ammunitonStock;
            }
        }
    }
}
