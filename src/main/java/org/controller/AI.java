package org.controller;

import org.model.actions.RunnableAction;
import org.model.events.GivenEvent;
import org.model.helper.StaticRandom;
import org.jetbrains.annotations.NotNull;
import org.model.GameCharacter;

import java.util.Comparator;

public class AI extends Controller{
    static Comparator<RunnableAction> baseLogic = (_, _) -> StaticRandom.nextInt(2) -1;
    Comparator<RunnableAction> decisionLogic = baseLogic;

    public AI(GameCharacter character) {
        super(character);
    }

    public static void setBaseLogic(Comparator<RunnableAction> decisionLogic){
        baseLogic = decisionLogic;
    }


    @Override
    public void start(){
        RunnableAction choice = myCharacter.getActions().stream().max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Character has no actions!"));

        choice.run();
    }

    @Override
    public void decide(@NotNull GivenEvent event){
        var choice = event.getChoices().stream()
                .max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Event has no actions!"));

        choice.run();
    }
}
