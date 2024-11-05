package org.main;

import org.helper.StatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        stats.set(StatType.Money, 200);
        assertEquals(200, stats.get(StatType.Money));

        stats.set(StatType.Smartness, 2000);
        assertEquals(100, stats.get(StatType.Smartness));

        stats.set(StatType.Looks, 55);
        assertEquals(55, stats.get(StatType.Looks));
    }

    @Test
    void increment() {
        stats.increment(StatType.Age, 200);
        assertEquals(250, stats.get(StatType.Age));

        stats.increment(StatType.Smartness, 2000);
        assertEquals(100, stats.get(StatType.Smartness));

        stats.increment(StatType.Looks, 1);
        assertEquals(51, stats.get(StatType.Looks));
    }
}