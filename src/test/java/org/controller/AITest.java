package org.controller;

import org.junit.jupiter.api.Test;
import org.main.Charachter;
import org.helper.StaticRandom;

import static org.junit.jupiter.api.Assertions.*;

class AITest {

    @Test
    void constructorTest1(){
        assertThrows(IllegalStateException.class, () -> {
            // Call the method that should throw the exception
            new AI(new Charachter());
        });
    }

    @Test
    void constructorTest2(){
        //Now it will be set everywhere
        AI.setBaseLogic((_, _) -> StaticRandom.nextInt(3)-1);

        assertDoesNotThrow( () -> {
            new AI(new Charachter());
        });
    }


}