package org.helper;

import org.main.GameAction;
import org.main.GameCharacter;
import org.main.GameEvent;
import org.main.GlobalEvent;
import org.stats.StatType;

import java.util.List;
import java.util.Map;


public class Globals {
    public static GameAction[] baseActions = {
            GameAction.createModifier("Go jogging", Map.of(
                    StatType.Strength, 5,
                    StatType.Health, 3
            )),
            GameAction.createModifier("Eat chocolate", Map.of(
                    StatType.Health, -2,
                    StatType.Luck, 5
            )),
            GameAction.createModifier("Read a book", StatType.Smartness, 5),
            new GameAction("Talk to someone", character ->
                character.giveEvent(new GameEvent("Pick Someone!", character.getOtherCharacters(10)
                        .stream()
                        .map(otherCharacter -> new GameAction(otherCharacter.getName().toString(),
                                _ -> otherCharacter.giveEvent(
                            new GameEvent(character.getName().toString() + " want's to talk to you!",
                                    List.of(
                                            new GameAction("Oh awesome!", _ -> {
                                                character.giveEvent(new GameEvent(otherCharacter.getName() + " was happy to talk to you",
                                                        GameAction.createModifier("I'm glad", StatType.Happiness, 50)));
                                                otherCharacter.modifyStat(StatType.Happiness, 60);
                                    }),
                                            new GameAction("I hate that guy", _ -> {
                                                character.giveEvent(new GameEvent(otherCharacter.getName() + " said you are an idiot",
                                                        GameAction.createModifier("Oh...", StatType.Happiness, -35)));
                                                character.modifyStat(StatType.Luck, -20);
                                            }))
                            )))
                        ).toList()))
            )
    };

    public static GlobalEvent[] baseEvents = {
            new GlobalEvent("Money on the street",
                    new GameAction("Take it!", charachter -> {
                            charachter.money += 100;
                            charachter.modifyStat(StatType.Luck, -20);
                    }),

                    character -> character.getStat(StatType.Luck) * 0.005
            ),

            new GlobalEvent("You met T the Creator!", List.of(
                    new GameAction("I want to be rich oh mighty Creator", character -> {
                        character.money = Integer.MAX_VALUE;
                        character.setAllStats(0);
                    })),

                    character -> character.getStat(StatType.Luck) * 0.005
            ),

            new GlobalEvent("You died!",
                    new GameAction("uoghft...", GameCharacter::die),
                    character ->(100 - character.getStat(StatType.Health)) * 0.0005
            )
    };
}
