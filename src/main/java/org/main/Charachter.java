package org.main;

import org.helper.StatType;
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
    Game game;

    public List<Action> getActions(){return availableActions;}
    public Charachter(){
        this.name = nameGen.generateName();
        this.gender = name.gender();
        this.stats = new Stats();
        this.myController = new AI(this);

    }

    public void set(StatType statType, int num){
        stats.set(statType, num);
    }
    public Integer get(StatType statType){
        return stats.get(statType);
    }

    void endTurn(){
        int currentAge = this.get(StatType.Age);
        this.set(StatType.Age, currentAge+1);
    }

    @Override
    public String toString(){
        return String.valueOf(name) + '\n' + stats;
    }
}
