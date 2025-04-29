package org.model.actions;

import org.model.GameCharacter;

import java.util.function.BiConsumer;

public class MultiplayerAction {
    String name;
    BiConsumer<GameCharacter,GameCharacter[]> action;

    public MultiplayerAction(String name, BiConsumer<GameCharacter, GameCharacter[]> action) {
        this.name = name;
        this.action = action;
    }

    public void executeOn(GameCharacter me, GameCharacter[] others) {
        action.accept(me, others);
    }
}
