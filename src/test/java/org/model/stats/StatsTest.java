package org.model.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatsTest {
    Stats stats;

    ///Set everything to 50
    @BeforeEach
    public void setUp(){
        stats = new Stats();
        for (StatType statType : StatType.values())
            stats.set(statType, 50);
    }

    @Test
    void get() {
        stats.forEach(stat -> assertEquals(50, stat.getValue()));
    }

    @Test
    void set() {

        stats.set(StatType.Smartness, 2000);
        assertEquals(100, stats.get(StatType.Smartness));

        stats.set(StatType.Looks, 55);
        assertEquals(55, stats.get(StatType.Looks));

        stats.set(StatType.Luck, -55);
        assertEquals(0, stats.get(StatType.Luck));
    }

    @Test
    void modify() {
        stats.modify(StatType.Smartness, 2000);
        assertEquals(100, stats.get(StatType.Smartness));

        stats.modify(StatType.Looks, 1);
        assertEquals(51, stats.get(StatType.Looks));

        stats.modify(StatType.Luck, -55);
        assertEquals(0, stats.get(StatType.Luck));

        stats.modify(Map.of(
                StatType.Happiness, 1,
                StatType.Health, 1));
        assertEquals(51, stats.get(StatType.Happiness));
        assertEquals(51, stats.get(StatType.Health));
    }

    @Test
    public void serializable() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(stats);
        objectOut.flush();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream objectIn = new ObjectInputStream(byteIn);
        Stats deserializedStats = (Stats) objectIn.readObject();

        // Assert: Check that the deserialized object is equal to the original
        assertNotNull(deserializedStats);
        assertEquals(stats, deserializedStats);
    }

}