package org.view;

import org.model.GameCharacter;

import javax.swing.*;

public class GameCharacterPanel extends JPanel {
    JTextArea characterData;
    GameCharacter character;

    public GameCharacterPanel(GameCharacter character){
        this.character = character;
        characterData = new JTextArea(String.valueOf(character));
        characterData.setEditable(false);
        this.add(characterData);
    }

    public void refresh(){
        characterData.setText(String.valueOf(character));
    }
}
