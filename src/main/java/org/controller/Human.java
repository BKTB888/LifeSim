package org.controller;

import org.gui.GameFrame;
import org.main.GameEvent;
import org.main.GameCharacter;

public class Human extends Controller{
    GameFrame gameFrame;
    volatile Boolean endTurn = false;

    public Human(GameCharacter character){
        super(character);

        gameFrame = new GameFrame(character);
        gameFrame.addAgeListener( _ -> endTurn = true);
        gameFrame.setVisible(true);
    }

    @Override
    public void start() {
        gameFrame.refresh();
        while (!endTurn) {
            Thread.onSpinWait();
        }
        endTurn = false;
    }

    @Override
    public void decide(GameEvent event) {
        gameFrame.launchEvent(event);
    }
}
