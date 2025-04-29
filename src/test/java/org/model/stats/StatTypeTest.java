package org.model.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatTypeTest {
    Stats stats;

    @BeforeEach
    void setUp() {
        stats = new Stats();
        for (StatType statType : StatType.values())
            stats.set(statType, 50);
    }

    @Test
    void placeInBounds() {
        stats.forEach(stat -> stat.setValue(Integer.MAX_VALUE));
        stats.forEach(stat -> assertEquals(
                stat.getKey().placeInBounds(Integer.MAX_VALUE),
                stat.getValue()
        ));

        stats.forEach(stat -> stat.setValue(Integer.MIN_VALUE));
        stats.forEach(stat -> assertEquals(
                stat.getKey().placeInBounds(Integer.MIN_VALUE),
                stat.getValue()
        ));
    }
}