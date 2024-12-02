package org.gui;

import org.jetbrains.annotations.NotNull;
import org.main.GameCharacter;
import org.main.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class HumanFrame extends JFrame{
    GameCharacterPanel characterPanel;
    JButton ageButton = new JButton("Age");
    GameCharacter character;
    JComponent currentPanel;

    public HumanFrame(@NotNull GameCharacter character){
        this.character = character;
        this.setTitle(character.getName().toString());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to save?", // The message
                        "Saving the game",           // The title
                        JOptionPane.YES_NO_CANCEL_OPTION // Yes and No buttons
                );

                switch (response) {
                    case JOptionPane.YES_OPTION -> {
                        String name = JOptionPane.showInputDialog(
                                null,
                                "Name of the save game:",
                                "Saving",
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (name == null) return;

                        try {
                            character.getGame().save(name);
                        } catch (IOException _) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "An error occurred while saving your game!",
                                    "Save Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                        JOptionPane.showMessageDialog(
                                null,
                                "Game Saved",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        character.getGame().quit();
                        ageButton.doClick();
                        HumanFrame.this.dispose();
                    }
                    case JOptionPane.NO_OPTION -> {
                        character.getGame().quit();
                        ageButton.doClick();
                        HumanFrame.this.dispose();
                    }
                }
            }
        });
        this.setLayout(new FlowLayout());
        characterPanel = new GameCharacterPanel(character);
        currentPanel = characterPanel;

        JButton availableActions = new JButton("Actions");
        JButton othersButton = new JButton("Other Characters");
        GameActionsPanel actionsPanel = new GameActionsPanel(character.getActions(), character);
        actionsPanel.setVisible(false);
        this.add(actionsPanel);
        actionsPanel.add(othersButton);
        addCloseButton(actionsPanel);

        availableActions.addActionListener(_ -> {
            currentPanel.setVisible(false);
            currentPanel = actionsPanel;
            currentPanel.setVisible(true);
            this.pack();
        });

        characterPanel.add(availableActions);
        characterPanel.add(ageButton);

        JPanel othersPanel = new JPanel();
        othersPanel.setVisible(false);
        othersPanel.setLayout(new BoxLayout(othersPanel, BoxLayout.Y_AXIS));

        this.add(othersPanel);

        othersButton.addActionListener(_ -> {
            currentPanel.setVisible(false); // Hide the current visible panel
            currentPanel = othersPanel; // Switch to the new panel
            othersPanel.removeAll();
            for (GameCharacter character1 : character.getOtherCharacters(10)) {
                JButton characterButton = new JButton(character1.getName().toString());
                characterButton.addActionListener(__ -> {
                    JPanel characterPanel = new GameCharacterPanel(character1);
                    addCloseButton(characterPanel).addActionListener(___ -> {
                        this.remove(characterPanel);
                        characterPanel.setVisible(false);
                        othersPanel.setVisible(true);
                        currentPanel = othersPanel;
                    });
                    currentPanel.setVisible(false);
                    currentPanel = characterPanel;
                    this.add(characterPanel);
                    this.pack();
                });
                othersPanel.add(characterButton);
            }
            addCloseButton(othersPanel);
            currentPanel.setVisible(true);
            this.pack();
        });

        this.add(characterPanel);
        this.pack();
    }

    @NotNull
    private JButton addCloseButton(@NotNull JComponent component){
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(_ -> {
            currentPanel.setVisible(false);
            characterPanel.setVisible(true);
            currentPanel = characterPanel;
            this.refresh();
            this.pack();
        });
        component.add(closeButton);
        return closeButton;
    }

    public void addAgeListener(ActionListener ageListener){
        ageButton.addActionListener(ageListener);
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
