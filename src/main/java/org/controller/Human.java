package org.controller;

import org.model.events.GivenEvent;
import org.view.HumanFrame;
import org.model.GameCharacter;

/// Needs refactor to be MVC
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
    public void decide(GivenEvent event) {
        humanFrame.setVisible(true);
        humanFrame.launchEvent(event);
    }

    public void disposeWindow(){
        humanFrame.dispose();
    }
}
