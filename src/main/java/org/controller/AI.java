package org.controller;

import org.helper.StaticRandom;
import org.main.Action;
import org.main.Charachter;
import org.main.Event;

import java.util.Comparator;
import java.util.stream.Stream;

public class AI extends Controller{
    static Comparator<Action> baseLogic = (_, _) -> StaticRandom.nextInt(2) -1;
    Comparator<Action> decisionLogic = baseLogic;


    public AI(Charachter character) {
        this.myCharachter = character;
    }

    public static void setBaseLogic(Comparator<Action> decisionLogic){
        baseLogic = decisionLogic;
    }


    @Override
    public void start(){
        Action choice = myCharachter.getActions().stream().max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Charachter has no actions!"));

        choice.executeOn(myCharachter);
    }

    @Override
    public void decide(Event event){
        Stream<Action> actionStream = event.getChoices().stream();
        Action choice = actionStream.max(decisionLogic)
                .orElseThrow(() ->new IllegalArgumentException("Event has no actions!"));

        choice.executeOn(myCharachter);
    }
}
