package org.helper;

import org.main.GameAction;
import org.main.GameEvent;
import org.stats.StatType;

import java.util.Map;


public class Globals {
    public static GameAction[] baseActions = new GameAction[]{
            GameAction.createModifier("Go jogging", Map.of(
                    StatType.Strength, 5,
                    StatType.Health, 3
            )),
            GameAction.createModifier("Eat chocolate", Map.of(
                    StatType.Health, -2,
                    StatType.Luck, 5
            )),
            GameAction.createModifier("Read a book", StatType.Smartness, 5)
    };

    public static GameEvent[] baseEvents = new GameEvent[]{
            new GameEvent("Money on the street",
                    new GameAction("Take it!", charachter -> {
                            charachter.money += 100;
                            charachter.setStat(StatType.Luck, -20);
                    }),

                    character -> character.getStat(StatType.Luck)* 0.005
            )
    };
}
