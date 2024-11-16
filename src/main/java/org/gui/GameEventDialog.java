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

        event.getChoices().stream()
                .map(action -> new GameActionButton(action, character))
                .forEach(button -> {
                    button.addActionListener( _ -> this.dispose());
                    this.add(button);
                });
        this.pack();
        this.setVisible(true);
    }
}
