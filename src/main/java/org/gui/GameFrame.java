package org.gui;

import org.main.GameCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    CharacterPanel characterPanel;
    JButton ageButton = new JButton("Age");

    public GameFrame(GameCharacter character){
        this.setTitle("LifeSim");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        characterPanel = new CharacterPanel(character);

        this.add(characterPanel);
        this.add(ageButton);
        this.pack();
    }

    public void addAgeListener(ActionListener ageListener){
        ageButton.addActionListener(ageListener);
    }

    public void refresh(){
        characterPanel.refresh();
    }
}
