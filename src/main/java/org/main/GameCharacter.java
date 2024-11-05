package org.main;

import org.controller.Human;
import org.helper.StatType;
import org.helper.ajbrown.namemachine.Gender;
import org.helper.ajbrown.namemachine.Name;
import org.helper.ajbrown.namemachine.NameGenerator;
import org.helper.ajbrown.namemachine.NameGeneratorOptions;
import org.controller.AI;
import org.controller.Controller;

import java.util.List;

public class GameCharacter {
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
    public GameCharacter(){
        this.name = nameGen.generateName();
        this.gender = name.gender;
        this.stats = new Stats();
        this.myController = new AI(this);
    }

    public GameCharacter(String firstName, String lastName, Gender gender){
        this.name = new Name(firstName, lastName, gender);
        this.gender = gender;
        this.stats = new Stats();
        this.myController = new AI(this);
    }

    public void giveEvent(Event event){
        myController.decide(event);
    }

    public void setStat(StatType statType, int num){
        stats.set(statType, num);
    }
    public Integer getStat(StatType statType){
        return stats.get(statType);
    }

    void endTurn(){
        int currentAge = this.getStat(StatType.Age);
        this.setStat(StatType.Age, currentAge+1);
    }

    @Override
    public String toString(){
        return name + "\n\n" + stats;
    }

    public void start() {
        myController.start();
        endTurn();
    }

    public void makeHuman(){
        myController = new Human(this);
    }

    public void setName(String firstName, String lastName){
        name.firstName = firstName;
        name.lastName = lastName;
    }
}
