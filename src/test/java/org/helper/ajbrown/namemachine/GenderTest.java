package org.helper.ajbrown.namemachine;

import org.junit.jupiter.api.Test;
import org.model.helper.ajbrown.namemachine.Gender;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void testToString() {
        assertEquals("Female", String.valueOf(Gender.FEMALE));
        assertEquals("Male", String.valueOf(Gender.MALE));
    }
}