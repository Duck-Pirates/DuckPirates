package com.game;
import College from 'game/college';
import ReferenceData from 'game/referenceData';
import Ship from 'game/ship';

public class Player {

    College college = college;

    Ship[] ships;
    ships = new Ship[ReferenceData.maxShips]  //Lenght of max number of ships - from refence data

    int money = 5000; //Start with 5000 coins (??)

    public boolean buyShip() {
        if (this.money >= ReferenceData.shipCost) {
            // We have enough money
            if (!this.ships[-1]) {
                // There is room for another ship...
                ships = ships.push(new Ship(this.college));  // Add a new ship to the array
                this.money = this.money - ReferenceData.shipCost;
                return true;
            } else {
                throw new Exception("Already have the maximum number of ships");
                return false;
            }
        } else {
            throw new Exception("Not enough money");
            return false;
        }
    }

    //Add delete ship stuff etc....
}

