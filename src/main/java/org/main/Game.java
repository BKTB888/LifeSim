package org.main;

import org.helper.StaticRandom;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Charachter> charachters;
    List<Event> events;

    public Game(int numOfCharachters){
        this.charachters = new ArrayList<>(numOfCharachters);
        for (int i=0; i<numOfCharachters; ++i)
            charachters.add(new Charachter());
    }
    public Game(){
        Game game = new Game(500);
        this.charachters = game.charachters;
    }

    public Charachter getRandomCharachter(){
        return charachters.get(StaticRandom.nextInt(charachters.size()));
    }

    public void nextTurn(){
        for (Charachter charachter: charachters) {
            for (Event event : events)
                if (StaticRandom.nextDouble() < event.chanceFor(charachter))
                    charachter.giveEvent(event);
            charachter.start();
        }
    }

    @Override
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        for (Charachter charachter : charachters)
            resultBuilder.append(charachter).append('\n');
        resultBuilder.deleteCharAt(resultBuilder.length()-1);
        return resultBuilder.toString();
    }
}
