package org.gui;

import org.main.GameCharacter;
import org.main.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    CharacterPanel characterPanel;
    JButton ageButton = new JButton("Age");
    GameCharacter character;

    public GameFrame(GameCharacter character){
        this.character = character;
        this.setTitle("LifeSim");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        characterPanel = new CharacterPanel(character);


        characterPanel.add(ageButton);
        this.add(characterPanel);
        this.pack();
    }

    public void addAgeListener(ActionListener ageListener){
        ageButton.addActionListener(ageListener);
    }

    public void refresh(){
        characterPanel.refresh();
    }

    public void launchEvent(GameEvent event){
        new GameEventDialog(event, character).addWindowStateListener( _ -> this.refresh());
    }
}
