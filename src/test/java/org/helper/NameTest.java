package org.helper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testToString() {
        Name name = new Name("Tivadar", "Bánfalvi-Kovács");
        assertEquals(name.toString(), "Tivadar Bánfalvi-Kovács");
    }

    @Test
    void fiveNames(){
        List<Name> maleNames = new ArrayList<>();
        List<Name> femaleNames = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            (i%2==0 ? maleNames : femaleNames).add(new Name(i%2==0));
        }
        System.out.println("Male names: " + maleNames);
        System.out.println("Female names: " + femaleNames);
    }
}