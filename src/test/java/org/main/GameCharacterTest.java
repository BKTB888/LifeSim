package org.main;

import org.helper.StatType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    public void endTurnTest(){
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setStat(StatType.Age, 42);
        gameCharacter.endTurn();
        assertEquals(gameCharacter.getStat(StatType.Age), 43);
    }

}