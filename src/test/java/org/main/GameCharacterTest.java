package org.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    public void endTurnTest(){
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.age = 42;
        gameCharacter.endTurn();
        assertEquals(43, gameCharacter.age);
    }

}