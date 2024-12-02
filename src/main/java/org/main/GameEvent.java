package org.main;

import java.util.ArrayList;
import java.util.List;

public class GameEvent {
    public final String name;
    List<GameAction> choices;

    public GameEvent(String name, List<GameAction> choices){
        this.name = name;
        this.choices = choices;
    }

    public GameEvent(String name, GameAction[] choices){
        this.name = name;
        this.choices = new ArrayList<>(List.of(choices));
    }

    public GameEvent(String name, GameAction choice){
        this.name = name;
        this.choices = new ArrayList<>(List.of(choice));
    }

    public List<GameAction> getChoices() {
        return choices;
    }
}
