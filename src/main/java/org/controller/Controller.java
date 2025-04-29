package org.controller;


import org.model.GameCharacter;
import org.model.events.GivenEvent;

public abstract class Controller {
    GameCharacter myCharacter;

    public Controller(GameCharacter character){
        this.myCharacter = character;
    }

    public abstract void start();
    public abstract void decide(GivenEvent event);
}
