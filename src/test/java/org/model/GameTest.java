package org.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    public void setUp(){
        game = new Game(10, 1);
    }

    @Test
    public void humansRemaining() {
        assertTrue(game.humansRemaining());

        Game game1= new Game(500);
        assertFalse(game1.humansRemaining());
    }
}