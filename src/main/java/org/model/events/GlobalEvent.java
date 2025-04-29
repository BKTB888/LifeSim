package org.model.events;

import org.model.GameCharacter;
import org.model.actions.SingleplayerAction;

import java.util.Collection;
import java.util.function.Function;

public class GlobalEvent extends GameEvent{
    Function<GameCharacter, Double> chanceForCharacter;

    public GlobalEvent(String name, Collection<SingleplayerAction> choices, Function<GameCharacter, Double> chanceFunction){
        super(name, choices);
        this.chanceForCharacter = chanceFunction;
    }

    public GlobalEvent(String name, SingleplayerAction choice, Function<GameCharacter, Double> chanceFunction){
        super(name, choice);
        this.chanceForCharacter = chanceFunction;
    }

    public double chanceFor(GameCharacter gameCharacter){
        return chanceForCharacter.apply(gameCharacter);
    }
}
