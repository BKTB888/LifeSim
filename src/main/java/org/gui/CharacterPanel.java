package org.gui;

import org.main.GameCharacter;

import javax.swing.*;

public class CharacterPanel extends JPanel {
    JTextArea characterData;
    GameCharacter character;

    public CharacterPanel(GameCharacter character){
        this.character = character;
        characterData = new JTextArea(String.valueOf(character));
        characterData.setEditable(false);
        this.add(characterData);
    }

    public void refresh(){
        characterData.setText(String.valueOf(character));
    }
}
