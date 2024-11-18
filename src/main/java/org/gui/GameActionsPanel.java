package org.gui;

import org.jetbrains.annotations.NotNull;
import org.main.GameAction;
import org.main.GameCharacter;

import javax.swing.*;
import java.util.List;

public class GameActionsPanel extends JPanel {
    public GameActionsPanel(@NotNull List<GameAction> actions, GameCharacter character){
        actions.stream()
                .map(action -> new GameActionButton(action, character))
                .forEach(this::add);
    }
}
