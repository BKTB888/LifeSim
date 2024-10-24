package org.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Globals {
    static class NameList {
        static List<String> maleNames;
        static List<String> femaleNames;

        static List<String> lastNames;

        static {
            try {
                // Read all lines and assign to the static member variable
                maleNames = Files.readAllLines(Path.of("/Users/banfalvikatalin/Downloads/BME/Nagy Házi 3/FantasyGame/src/main/java/org/helper/male-names.txt"));
                femaleNames = Files.readAllLines(Path.of("/Users/banfalvikatalin/Downloads/BME/Nagy Házi 3/FantasyGame/src/main/java/org/helper/female-names.txt"));
                lastNames = Files.readAllLines(Path.of("/Users/banfalvikatalin/Downloads/BME/Nagy Házi 3/FantasyGame/src/main/java/org/helper/last-names.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        public List<Name> getRandomNames(int howMany){
            List<Name> names = new ArrayList<>(howMany);
            int firstNameSize = maleNames.size();
            int lastNameSize = lastNames.size();

            for (Name name : names){
                String firstName = maleNames.get(StaticRandom.nextInt(firstNameSize));
                String lastName = lastNames.get(StaticRandom.nextInt(lastNameSize));
                name = new Name(firstName, lastName);
            }

            return names;
        }
        */
        public static Name getRandomName(Boolean isMale){
            List<String> firstNames = isMale ? maleNames : femaleNames;
            String firstName = firstNames.get(StaticRandom.nextInt(firstNames.size()));
            String lastName = lastNames.get(StaticRandom.nextInt(lastNames.size()));
            return new Name(firstName, lastName);
        }
    }
}
