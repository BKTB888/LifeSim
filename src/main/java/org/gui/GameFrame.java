package org.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.main.GameCharacter;
import org.main.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    CharacterPanel characterPanel;
    JButton ageButton = new JButton("Age");
    JButton quit = new JButton("Quit");
    GameCharacter character;

    public GameFrame(GameCharacter character){
        this.character = character;
        this.setTitle("LifeSim");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        characterPanel = new CharacterPanel(character);

        JButton availableActions = new JButton("Actions");
        availableActions.addActionListener(_ -> {
            characterPanel.setVisible(false);
            this.add(addCloseButton(new GameActionsPanel(character.getActions(), character)));
            this.pack();
        });

        characterPanel.add(quit);
        characterPanel.add(availableActions);
        characterPanel.add(ageButton);
        this.add(characterPanel);
        this.pack();
    }

    @NotNull
    @Contract("_ -> param1")
    private JComponent addCloseButton(@NotNull JComponent component){
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(_ -> {
            this.remove(component);
            characterPanel.setVisible(true);
            this.refresh();
            this.pack();
        });
        component.add(closeButton, BorderLayout.SOUTH);
        return component;
    }

    public void addAgeListener(ActionListener ageListener){
        ageButton.addActionListener(ageListener);
    }

    public void addQuitListener(ActionListener quitListener){
        quit.addActionListener(quitListener);
    }

    public void refresh(){
        characterPanel.refresh();
    }

    public void launchEvent(GameEvent event){
        new GameEventDialog(event, character).addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refresh();
            }
        });
    }
}
