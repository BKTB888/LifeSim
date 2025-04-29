package org.model.actions;

import org.model.GameCharacter;

import java.util.List;
import java.util.function.BiConsumer;

public class MultiplayerAction {
    String name;
    BiConsumer<GameCharacter,List<GameCharacter>> effectFunc;

    public MultiplayerAction(String name, GameAction forMe, GameAction forOthers) {
        this.name = name;
        this.effectFunc = (me, otherChar) -> {
            forMe.executeOn(me);
            otherChar.forEach(forOthers::executeOn);
        };
    }

    public void executeOn(GameCharacter me, List<GameCharacter> others) {
        effectFunc.accept(me, others);
    }
}
