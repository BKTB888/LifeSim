package org.view;

import org.jetbrains.annotations.NotNull;
import org.model.actions.RunnableAction;
import org.model.GameCharacter;

import javax.swing.*;

public class GameActionButton extends JButton {

    public GameActionButton(@NotNull RunnableAction action, @NotNull GameCharacter character){
        this.setText(action.name);
        this.addActionListener( _ -> action.run());
    }
}
