package org.gui;


import org.helper.ajbrown.namemachine.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.GameCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterPanelTest {
    GameCharacter character;
    CharacterPanel characterPanel;

    @BeforeEach
    public void setUp(){
        character = new GameCharacter("firstName", "lastName", Gender.FEMALE);
        characterPanel = new CharacterPanel(character);
    }

    @Test
    public void refreshTest(){
        character.setName("Nagy", "Fejű");
        characterPanel.refresh();
        assertEquals(String.valueOf(character),characterPanel.characterData.getText());
    }
}