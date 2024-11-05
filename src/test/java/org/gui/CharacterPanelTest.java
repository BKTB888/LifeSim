package org.gui;


import org.helper.ajbrown.namemachine.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.GameCharacter;

import javax.swing.*;

class CharacterPanelTest {
    JFrame frame;
    CharacterPanel characterPanel;
    GameCharacter character = new GameCharacter("firstName", "lastName", Gender.FEMALE);


    @BeforeEach
    public void setUp(){
        CharacterPanel characterPanel = new CharacterPanel(character);
        JFrame frame = new JFrame();
        frame.add(characterPanel);
        frame.pack();
    }

    /*
    @Test
    public void refresh(){
        character.setName("Nagy", "Fejű");
        characterPanel.characterData.getText();
    }

     */
}