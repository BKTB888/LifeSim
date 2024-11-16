package org.gui;

import org.jetbrains.annotations.NotNull;
import org.main.GameCharacter;
import org.main.GameEvent;
import javax.swing.*;

public class GameEventDialog extends JDialog {

    public GameEventDialog(@NotNull GameEvent event, GameCharacter character){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(new JLabel(event.name));
        this.setTitle(event.name);
        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        event.getChoices().forEach(action -> {

            GameActionButton actionButton = new GameActionButton(action, character);
            actionButton.addActionListener( _ -> this.dispose());
            this.add(actionButton);

        });
        this.pack();
        this.setVisible(true);
    }
}
