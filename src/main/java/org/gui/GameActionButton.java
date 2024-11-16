package org.gui;

import org.jetbrains.annotations.NotNull;
import org.main.GameAction;
import org.main.GameCharacter;

import javax.swing.*;

public class GameActionButton extends JButton {
    GameAction action;
    GameCharacter character;

    public GameActionButton(@NotNull GameAction action, @NotNull GameCharacter character){
        this.character = character;
        this.action = action;
        this.setText(action.name);
        this.addActionListener( _ -> action.executeOn(character));
    }
}
