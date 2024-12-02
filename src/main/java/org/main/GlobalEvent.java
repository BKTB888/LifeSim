package org.main;

import java.util.List;
import java.util.function.Function;

public class GlobalEvent extends GameEvent{
    Function<GameCharacter, Double> chanceForCharacter;

    public GlobalEvent(String name, List<GameAction> choices, Function<GameCharacter, Double> chanceFunction){
        super(name, choices);
        this.chanceForCharacter = chanceFunction;
    }

    public GlobalEvent(String name, GameAction choice, Function<GameCharacter, Double> chanceFunction){
        super(name, choice);
        this.chanceForCharacter = chanceFunction;
    }

    public double chanceFor(GameCharacter gameCharacter){
        return chanceForCharacter.apply(gameCharacter);
    }
}
