package com.game;

import java.util.List;

public class GameInfo {

   public String difficulty = "Easy";
   /* A timer value will also be added */
   public Double volumeMUSIC, volumeSOUNDEFFECTS = 0.50;
   public static String[] colleges_names_array = {"Constantine", "Langwith", "Goodricke", "Anne Lister", "Halifax", "James", "Wentworth", "Alcuin", "Vanbrugh", "Derwent"};

   /* private Boolean time_limit, soundON; */
   private static final int maxNumBoats = 5;
   private static final int maxNumDucks = 50;
   private static final float coinsPerDuckPerSecond = 1;

   public int getMaxNumBoats(){
      return maxNumBoats;
   }

}
