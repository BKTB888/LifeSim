package org.helper;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalsTest {

    //uniqueness check for NameList
    @Test
    void uniquenessCheck(){
        List<String> lastNames =Globals.NameList.lastNames;
        List<String> maleNames =Globals.NameList.maleNames;
        List<String> femaleNames =Globals.NameList.femaleNames;

        assertEquals(lastNames.size(), new HashSet<>(lastNames).size());
        System.out.println("Duplicates: ");
        var seen = new HashSet<>();
        int num = 1;
        for (var element : maleNames) {
            if (!seen.add(element)) {
                // If add returns false, it's a duplicate
                System.out.println(element +' '+ num);
            }
            ++num;
        }
        assertEquals(maleNames.size(), seen.size());
        seen = new HashSet<>();
        num = 1;
        for (var element : femaleNames) {
            if (!seen.add(element)) {
                // If add returns false, it's a duplicate
                System.out.println(element +' '+ num);
            }
            ++num;
        }
        assertEquals(femaleNames.size(), new HashSet<>(femaleNames).size());
    }
}