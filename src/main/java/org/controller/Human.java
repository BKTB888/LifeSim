package org.controller;

import org.gui.HumanFrame;
import org.main.GameEvent;
import org.main.GameCharacter;

public class Human extends Controller{
    HumanFrame humanFrame;
    volatile Boolean endTurn = false;

    public Human(GameCharacter character){
        super(character);

        humanFrame = new HumanFrame(character);
        humanFrame.addAgeListener(_ -> endTurn = true);
        humanFrame.setVisible(true);
    }

    @Override
    public void start() {
        humanFrame.refresh();
        while (!endTurn) {
            Thread.onSpinWait();
        }
        endTurn = false;
    }

    @Override
    public void decide(GameEvent event) {
        humanFrame.launchEvent(event);
    }
}
