package org.model.helper;

import org.model.helper.ajbrown.namemachine.Gender;
import org.model.helper.ajbrown.namemachine.Name;
import org.model.helper.ajbrown.namemachine.NameGenerator;
import org.model.helper.ajbrown.namemachine.NameGeneratorOptions;

public class NameGen {
    private static final NameGeneratorOptions nameOptions = new NameGeneratorOptions();
    static {
        nameOptions.setRandomSeed(StaticRandom.nextLong());
        nameOptions.setGenderWeight(50);
    }
    private static final NameGenerator nameGen = new NameGenerator(nameOptions);
    public static Name generateName() {
        return nameGen.generateName();
    }

    public static Name generateName(Gender gender) {
        return nameGen.generateName(gender);
    }
}
