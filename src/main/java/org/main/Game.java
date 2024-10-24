package org.main;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Charachter> charachters;
    List<Event> events;

    public Game(){
        this.charachters = new ArrayList<Charachter>(500);
    }
}
