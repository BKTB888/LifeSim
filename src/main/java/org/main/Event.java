package org.main;

import java.util.List;
import java.util.function.Function;

public class Event {
    String name;
    List<Action> choices;
    Function<Charachter, Double> chanceForCharachter;

    public Event(String name, List<Action> choices, Function<Charachter, Double> chanceFunction){
        this.name = name;
        this.choices = choices;
        this.chanceForCharachter = chanceFunction;
    }

    public List<Action> getChoices() {
        return choices;
    }

    public double chanceFor(Charachter charachter){
        return chanceForCharachter.apply(charachter);
    }
}
