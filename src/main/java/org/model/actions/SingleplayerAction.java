package org.model.actions;

import org.model.GameCharacter;
import org.model.events.GameEvent;
import org.model.stats.StatType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class SingleplayerAction {
    public final String name;
    Consumer<GameCharacter> action;

    public SingleplayerAction(String name, Consumer<GameCharacter> action){
        this.name = name;
        this.action = action;
    }

    public void executeOn(GameCharacter character){
        action.accept(character);
    }

    @NotNull
    @Contract(value = "_, _, _ -> new", pure = true)
    public static SingleplayerAction createModifier(String name, StatType statType, int num){
        return new SingleplayerAction(name, character -> character.modifyStat(statType, num));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static SingleplayerAction createModifier(String name, final Map<StatType, Integer> statsMap){
        return new SingleplayerAction(name, character -> character.modifyStats(statsMap));
    }

    /// Needs review
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static SingleplayerAction createEventGiver(String name, @NotNull GameEvent event){
        return new SingleplayerAction(name, character -> character.receiveEvent(event));
    }

    @Override
    public String toString(){
        return name;
    }
}
