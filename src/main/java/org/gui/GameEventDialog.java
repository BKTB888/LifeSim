package org.gui;

import org.jetbrains.annotations.NotNull;
import org.main.GameCharacter;
import org.main.GameEvent;
import javax.swing.*;

public class GameEventDialog extends JDialog {

    public GameEventDialog(@NotNull GameEvent event, GameCharacter character){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle(event.name);

        //Event could get description
        JLabel description = new JLabel(event.name);
        description.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(description);
        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        event.getChoices().forEach(action -> {

            GameActionButton actionButton = new GameActionButton(action, character);
            actionButton.addActionListener( _ -> this.dispose());
            actionButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            this.add(actionButton);

        });
        this.pack();
        this.setVisible(true);
    }
}
