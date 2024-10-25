package org.main;

import org.helper.StaticRandom;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Charachter> charachters;
    List<Event> events;

    public Game(){
        this.charachters = new ArrayList<Charachter>(500);
        for (int i=0; i<500; ++i)
            charachters.add(new Charachter());
    }
    public Game(int numOfCharachters){
        this.charachters = new ArrayList<Charachter>(numOfCharachters);
        for (int i=0; i<numOfCharachters; ++i)
            charachters.add(new Charachter());
    }

    public Charachter getRandomCharachter(){
        return charachters.get(StaticRandom.nextInt(charachters.size()));
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
