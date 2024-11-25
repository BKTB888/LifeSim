package org.gui;


import org.helper.ajbrown.namemachine.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.Game;
import org.main.GameCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterPanelTest {
    Game game = new Game();
    GameCharacter character;
    GameCharacterPanel characterPanel;

    @BeforeEach
    public void setUp(){
        character = new GameCharacter("firstName", "lastName", Gender.FEMALE, game);
        characterPanel = new GameCharacterPanel(character);
    }

    @Test
    public void refreshTest(){
        character.setName("Nagy", "Fejű");
        characterPanel.refresh();
        assertEquals(String.valueOf(character),characterPanel.characterData.getText());
    }
}