package org.main;

import org.helper.ajbrown.namemachine.Gender;
import org.helper.ajbrown.namemachine.Name;
import org.helper.ajbrown.namemachine.NameGenerator;
import org.helper.ajbrown.namemachine.NameGeneratorOptions;
import org.controller.AI;
import org.controller.Controller;

import java.util.List;

public class Charachter {
    static NameGeneratorOptions nameOptions = new NameGeneratorOptions();
    static NameGenerator nameGen = new NameGenerator(nameOptions);
    static {
        nameOptions.setRandomSeed(42L);
    }

    Name name;
    Gender gender;

    Stats stats;
    List<Action> availableActions;
    Controller myController;

    public List<Action> getActions(){return availableActions;}
    public Charachter(){
        this.name = nameGen.generateName();
        this.gender = name.getGender();
        this.stats = new Stats();
        this.myController = new AI(this);

    }

    @Override
    public String toString(){
        return String.valueOf(name) + '\n' + stats;
    }
}
