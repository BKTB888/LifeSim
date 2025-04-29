package org.main;

import org.stats.StatType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class GameAction {
    public final String name;
    Consumer<GameCharacter> action;

    public GameAction(String name, Consumer<GameCharacter> action){
        this.name = name;
        this.action = action;
    }

    public void executeOn(GameCharacter character){
        action.accept(character);
    }

    @NotNull
    @Contract(value = "_, _, _ -> new", pure = true)
    public static GameAction createModifier(String name, StatType statType, int num){
        return new GameAction(name, character -> character.modifyStat(statType, num));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static GameAction createModifier(String name, final Map<StatType, Integer> statsMap){
        return new GameAction(name, character -> character.modifyStats(statsMap));
    }

    /// Needs review
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static GameAction createEventGiver(String name, @NotNull GameEvent event){
        return new GameAction(name, character -> character.giveEvent(event));
    }

    @Override
    public String toString(){
        return name;
    }
}
