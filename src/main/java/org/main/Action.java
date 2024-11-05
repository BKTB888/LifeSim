package org.main;

import java.util.function.Consumer;

public class Action {
    String name;
    Consumer<GameCharacter> action;

    public Action(String name, Consumer<GameCharacter> action){
        this.name = name;
        this.action = action;
    }

    public void executeOn(GameCharacter gameCharacter){
        action.accept(gameCharacter);
    }
}
