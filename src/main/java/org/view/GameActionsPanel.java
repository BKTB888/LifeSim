package org.view;

import org.jetbrains.annotations.NotNull;
import org.model.actions.RunnableAction;
import org.model.GameCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameActionsPanel extends JPanel {

    public GameActionsPanel(@NotNull RunnableAction[] actions, GameCharacter character){
        this.setLayout(new GridLayout(2,2, 5, 10));
        JComboBox<RunnableAction> actionSelector = new JComboBox<>(actions);
        JButton selectButton = new JButton("Do The Deed!");
        selectButton.addActionListener(_ -> ((RunnableAction) Objects.requireNonNull(actionSelector.getSelectedItem())).run());

        this.add(actionSelector);
        this.add(selectButton);
    }
}
