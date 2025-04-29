package org.model.events;

import org.jetbrains.annotations.NotNull;
import org.model.GameCharacter;
import org.model.actions.RunnableAction;

import java.util.Collection;

public class GivenEvent {
    public final String name;
    Collection<RunnableAction> choices;

    public GivenEvent(@NotNull GameEvent gameEvent, GameCharacter character){
        this.name = gameEvent.name;
        this.choices = gameEvent.getChoices()
                .stream()
                .map(action -> new RunnableAction(action, character))
                .toList();
    }

    public Collection<RunnableAction> getChoices() {
        return choices;
    }
}
