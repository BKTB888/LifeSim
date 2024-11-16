package org.main;

import org.controller.Human;
import org.helper.Globals;
import org.helper.StatType;
import org.helper.StaticRandom;
import org.helper.ajbrown.namemachine.Gender;
import org.helper.ajbrown.namemachine.Name;
import org.helper.ajbrown.namemachine.NameGenerator;
import org.helper.ajbrown.namemachine.NameGeneratorOptions;
import org.controller.AI;
import org.controller.Controller;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GameCharacter {
    static NameGeneratorOptions nameOptions = new NameGeneratorOptions();
    static NameGenerator nameGen;
    static {
        nameOptions.setRandomSeed(42L);
        nameOptions.setGenderWeight(50);
        nameGen  = new NameGenerator(nameOptions);
    }

    Name name;
    Gender gender;

    Stats stats;
    int age;
    public int money;
    List<GameAction> availableActions = Arrays.asList(Globals.baseActions);
    Controller myController;

    public List<GameAction> getActions(){return availableActions;}
    public GameCharacter(){
        this.name = nameGen.generateName();
        this.gender = name.gender;
        this.stats = new Stats();

        this.age = StaticRandom.nextInt(100);
        this.money = StaticRandom.nextInt(10000);

        this.myController = new AI(this);
    }

    public GameCharacter(String firstName, String lastName, Gender gender){
        this.name = new Name(firstName, lastName, gender);
        this.gender = gender;
        this.stats = new Stats();
        this.myController = new AI(this);
    }

    public void giveEvent(GameEvent event){
        myController.decide(event);
    }

    public void setStat(StatType statType, int num){
        stats.set(statType, num);
    }
    public Integer getStat(StatType statType){
        return stats.get(statType);
    }

    void endTurn(){
        ++age;
    }

    @Override
    public String toString(){
        return  String.valueOf(name) + '\n' +
                gender + '\n' +
                age + " years old" + '\n' +
                '$' + NumberFormat.getNumberInstance(Locale.US).format(money) + "\n\n"
                + stats;
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

    public void modifyStat(StatType statType, int num){
        stats.modify(statType, num);
    }
    public void modifyStats(@NotNull final Map<StatType, Integer> statMap){
        statMap.forEach(stats::modify);
    }
}
