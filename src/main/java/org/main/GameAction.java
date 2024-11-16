package org.main;

import org.helper.StatType;
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

    public void executeOn(GameCharacter gameCharacter){
        action.accept(gameCharacter);
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
}
