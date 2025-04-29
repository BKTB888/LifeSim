package org.model;

import org.model.helper.ajbrown.namemachine.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.model.stats.StatType;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {
    GameCharacter gameCharacter;

    @BeforeEach
    public void setUp(){
        gameCharacter = new GameCharacter("Tivadar", "Bánfalvi-Kovács", Gender.MALE,new Game());
        gameCharacter.stats.forEach(stat -> stat.setValue(50));
    }
    @Test
    public void endTurnTest(){
        gameCharacter.endTurn();
        assertEquals(51, gameCharacter.getStat(StatType.Age));
    }

    @Test
    public void serializable() throws IOException, ClassNotFoundException {
        // Serialize
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(gameCharacter);
        objectOut.flush();

        // Deserialize
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream objectIn = new ObjectInputStream(byteIn);
        GameCharacter deserializedCharacter = (GameCharacter) objectIn.readObject();

        // Assertions
        assertNotNull(deserializedCharacter);
        assertEquals(gameCharacter, deserializedCharacter);
    }

}