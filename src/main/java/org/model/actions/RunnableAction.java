package org.model.actions;

import org.jetbrains.annotations.NotNull;
import org.model.GameCharacter;

/// An action which doesn't require a character to be performed
public class RunnableAction {
    public final String name;
    Runnable action;

    /// A runnable from a singleplayer action
    public RunnableAction(@NotNull SingleplayerAction action, GameCharacter character) {
        this.name = action.name;
        this.action = () -> action.executeOn(character);
    }

    public RunnableAction(@NotNull MultiplayerAction action, GameCharacter me, GameCharacter[] others) {
        this.name = action.name;
        this.action = () -> action.executeOn(me, others);
    }

    public RunnableAction(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    public void run() {
        action.run();
    }

    public String toString() {
        return name;
    }
}
