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
    }

    @Override
    public void start() {
        humanFrame.setVisible(true);
        humanFrame.refresh();
        while (!endTurn) {
            Thread.onSpinWait();
        }
        endTurn = false;
    }

    @Override
    public void decide(GameEvent event) {
        humanFrame.setVisible(true);
        humanFrame.launchEvent(event);
    }

    public void disposeWindow(){
        humanFrame.dispose();
    }
}
