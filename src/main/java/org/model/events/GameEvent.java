package org.model.events;

import org.model.actions.SingleplayerAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEvent {
    public final String name;
    Collection<SingleplayerAction> choices;

    public GameEvent(String name, Collection<SingleplayerAction> choices){
        this.name = name;
        this.choices = choices;
    }

    public GameEvent(String name, SingleplayerAction choice){
        this.name = name;
        this.choices = new ArrayList<>(List.of(choice));
    }

    public Collection<SingleplayerAction> getChoices() {
        return choices;
    }
}
