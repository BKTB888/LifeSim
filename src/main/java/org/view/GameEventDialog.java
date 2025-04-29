package org.view;

import org.jetbrains.annotations.NotNull;
import org.model.GameCharacter;
import org.model.events.GivenEvent;

import javax.swing.*;

public class GameEventDialog extends JDialog {

    public GameEventDialog(@NotNull GivenEvent event, GameCharacter character){
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
