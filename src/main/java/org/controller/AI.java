package org.controller;

import org.model.helper.StaticRandom;
import org.jetbrains.annotations.NotNull;
import org.model.actions.GameAction;
import org.model.GameCharacter;
import org.model.GameEvent;

import java.util.Comparator;
import java.util.stream.Stream;

public class AI extends Controller{
    static Comparator<GameAction> baseLogic = (_, _) -> StaticRandom.nextInt(2) -1;
    Comparator<GameAction> decisionLogic = baseLogic;

    public AI(GameCharacter character) {
        super(character);
    }

    public static void setBaseLogic(Comparator<GameAction> decisionLogic){
        baseLogic = decisionLogic;
    }


    @Override
    public void start(){
        GameAction choice = myCharacter.getActions().stream().max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Character has no actions!"));

        choice.executeOn(myCharacter);
    }

    @Override
    public void decide(@NotNull GameEvent event){
        Stream<GameAction> actionStream = event.getChoices().stream();
        GameAction choice = actionStream.max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Event has no actions!"));

        choice.executeOn(myCharacter);
    }
}
