package org.model.helper;

import org.model.*;
import org.model.actions.SingleplayerAction;
import org.model.events.GameEvent;
import org.model.events.GlobalEvent;
import org.model.stats.StatType;

import java.util.List;
import java.util.Map;


public class Globals {
    public static SingleplayerAction[] baseActions = {
            SingleplayerAction.createModifier("Go jogging", Map.of(
                    StatType.Strength, 5,
                    StatType.Health, 3
            )),
            SingleplayerAction.createModifier("Eat chocolate", Map.of(
                    StatType.Health, -2,
                    StatType.Luck, 5
            )),
            SingleplayerAction.createModifier("Read a book", StatType.Smartness, 5),
            new SingleplayerAction("Talk to someone", character ->
                    character.receiveEvent(new GameEvent("Pick Someone!", character.getOtherCharacters(10)
                            .stream()
                            .map(otherCharacter -> new SingleplayerAction(otherCharacter.getName().toString(),
                                    _ -> otherCharacter.receiveEvent(
                                            new GameEvent(character.getName().toString() + " want's to talk to you!",
                                                    List.of(
                                                            new SingleplayerAction("Oh awesome!", _ -> {
                                                                character.receiveEvent(new GameEvent(otherCharacter.getName() + " was happy to talk to you",
                                                                        SingleplayerAction.createModifier("I'm glad", StatType.Happiness, 50)));
                                                                otherCharacter.modifyStat(StatType.Happiness, 60);
                                                            }),
                                                            new SingleplayerAction("I hate that guy", _ -> {
                                                                character.receiveEvent(new GameEvent(otherCharacter.getName() + " said you are an idiot",
                                                                        SingleplayerAction.createModifier("Oh...", StatType.Happiness, -35)));
                                                                character.modifyStat(StatType.Luck, -20);
                                                            })
                                                    )
                                            ))
                                    )
                            ).toList()
                    )
                )
            ),
            new SingleplayerAction("Check someone out", character ->
                    character.receiveEvent(new GameEvent("Pick Someone!", character.getOtherCharacters(10)
                            .stream()
                            .map(otherCharacter -> new SingleplayerAction(otherCharacter.getName().toString(),
                                    _ -> otherCharacter.receiveEvent(
                                            new GameEvent(character.getName().toString() + " want's to talk to you!",
                                                    List.of(
                                                            new SingleplayerAction("Oh awesome!", _ -> {
                                                                character.receiveEvent(new GameEvent(otherCharacter.getName() + " was happy to talk to you",
                                                                        SingleplayerAction.createModifier("I'm glad", StatType.Happiness, 50)));
                                                                otherCharacter.modifyStat(StatType.Happiness, 60);
                                                            }),
                                                            new SingleplayerAction("I hate that guy", _ -> {
                                                                character.receiveEvent(new GameEvent(otherCharacter.getName() + " said you are an idiot",
                                                                        SingleplayerAction.createModifier("Oh...", StatType.Happiness, -35)));
                                                                character.modifyStat(StatType.Luck, -20);
                                                            }))
                                            )))
                            ).toList())
                    )
            )
    };

    public static GlobalEvent[] baseEvents = {
            new GlobalEvent("Money on the street",
                    SingleplayerAction.createModifier("Take it!", Map.of(
                            StatType.Money,100,
                            StatType.Luck, -20
                    )),

                    character -> character.getStat(StatType.Luck) * 0.005
            ),

            new GlobalEvent("You met T the Creator!", List.of(
                    new SingleplayerAction("I want to be rich oh mighty Creator", character -> {
                        character.setStat(StatType.Money, Integer.MAX_VALUE);
                        character.setAllBaseStats(0);
                    })),

                    character -> character.getStat(StatType.Luck) * 0.005
            ),

            new GlobalEvent("You died!",
                    new SingleplayerAction("uoghft...", GameCharacter::die),
                    character ->(100 - character.getStat(StatType.Health)) * 0.0005
            )
    };
}
