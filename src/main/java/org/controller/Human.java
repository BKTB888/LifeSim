package org.controller;

import org.gui.HumanFrame;
import org.main.GameEvent;
import org.main.GameCharacter;

public class Human extends Controller{
    HumanFrame humanFrame;
    private final Object endTurnLock = new Object();

    public Human(GameCharacter character){
        super(character);

        humanFrame = new HumanFrame(character);
        humanFrame.addAgeListener(_ -> {
            synchronized (endTurnLock) {
                endTurnLock.notify(); // Notify within the synchronized block
            }
        });
    }

    @Override
    public void start() {
        humanFrame.setVisible(true);
        humanFrame.refresh();
        synchronized (endTurnLock){
            try {
                endTurnLock.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
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
