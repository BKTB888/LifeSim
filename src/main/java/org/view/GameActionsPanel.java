package org.view;

import org.jetbrains.annotations.NotNull;
import org.model.actions.GameAction;
import org.model.GameCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class GameActionsPanel extends JPanel {

    public GameActionsPanel(@NotNull List<GameAction> actions, GameCharacter character){
        this.setLayout(new GridLayout(2,2, 5, 10));
        JComboBox<GameAction> actionSelector = new JComboBox<>(actions.toArray(GameAction[]::new));
        JButton selectButton = new JButton("Do The Deed!");
        selectButton.addActionListener(_ -> ((GameAction) Objects.requireNonNull(actionSelector.getSelectedItem())).executeOn(character));

        this.add(actionSelector);
        this.add(selectButton);
    }

    public GameActionsPanel(@NotNull GameAction[] actions, GameCharacter character){
        this.setLayout(new GridLayout(2,2, 5, 10));
        JComboBox<GameAction> actionSelector = new JComboBox<>(actions);
        JButton selectButton = new JButton("Do The Deed!");
        selectButton.addActionListener(_ -> ((GameAction) Objects.requireNonNull(actionSelector.getSelectedItem())).executeOn(character));

        this.add(actionSelector);
        this.add(selectButton);
    }
}
