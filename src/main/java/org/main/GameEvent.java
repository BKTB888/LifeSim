package org.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GameEvent {
    public final String name;
    List<GameAction> choices;
    Function<GameCharacter, Double> chanceForCharachter;

    public GameEvent(String name, GameAction[] choices, Function<GameCharacter, Double> chanceFunction){
        this.name = name;
        this.choices = new ArrayList<>(List.of(choices));
        this.chanceForCharachter = chanceFunction;
    }

    public GameEvent(String name, GameAction choice, Function<GameCharacter, Double> chanceFunction){
        this.name = name;
        this.choices = new ArrayList<>(List.of(choice));
        this.chanceForCharachter = chanceFunction;
    }

    public List<GameAction> getChoices() {
        return choices;
    }

    public double chanceFor(GameCharacter gameCharacter){
        return chanceForCharachter.apply(gameCharacter);
    }
}
