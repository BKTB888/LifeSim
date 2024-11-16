package org.controller;


import org.main.GameCharacter;
import org.main.GameEvent;

public abstract class Controller {
    GameCharacter myCharacter;

    public Controller(GameCharacter character){
        this.myCharacter = character;
    }

    public abstract void start();
    public abstract void decide(GameEvent event);
}
