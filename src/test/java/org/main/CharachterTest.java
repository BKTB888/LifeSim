package org.main;

import org.helper.StatType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharachterTest {

    @Test
    public void endTurnTest(){
        Charachter charachter = new Charachter();
        charachter.set(StatType.Age, 42);
        charachter.endTurn();
        assertEquals(charachter.get(StatType.Age), 43);
    }

}