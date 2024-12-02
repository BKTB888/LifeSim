package org.main;

import org.controller.Human;
import org.helper.Globals;
import org.stats.StatType;
import org.helper.StaticRandom;
import org.helper.ajbrown.namemachine.Gender;
import org.helper.ajbrown.namemachine.Name;
import org.helper.ajbrown.namemachine.NameGenerator;
import org.helper.ajbrown.namemachine.NameGeneratorOptions;
import org.controller.AI;
import org.controller.Controller;
import org.jetbrains.annotations.NotNull;
import org.stats.Stats;

import java.io.*;
import java.util.*;

public class GameCharacter implements Serializable{
    private static final NameGeneratorOptions nameOptions = new NameGeneratorOptions();
    private static final NameGenerator nameGen;
    static {
        nameOptions.setRandomSeed(StaticRandom.nextLong());
        nameOptions.setGenderWeight(50);
        nameGen  = new NameGenerator(nameOptions);
    }

    Name name;
    Gender gender;

    Stats stats;
    transient List<GameAction> availableActions = Arrays.asList(Globals.baseActions);
    transient Controller myController;
    transient Game myGame;

    public List<GameAction> getActions(){return availableActions;}
    public GameCharacter(Game myGame){
        this.name = nameGen.generateName();
        this.gender = name.gender;
        this.stats = new Stats();

        this.myGame = myGame;

        this.myController = new AI(this);
    }

    public GameCharacter(String firstName, String lastName, Gender gender, Game myGame){
        this.name = new Name(firstName, lastName, gender);
        this.gender = gender;
        this.myGame = myGame;
        this.stats = new Stats();
        this.myController = new AI(this);
    }

    public void setGame(Game game){
        this.myGame = game;
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

    public Name getName(){
        return name;
    }

    public Controller getController(){
        return myController;
    }

    void endTurn(){
        stats.modify(StatType.Age, 1);
    }

    @Override
    public String toString(){
        return  String.valueOf(name) + '\n' +
                gender + '\n'
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

    public List<GameCharacter> getOtherCharacters(int numOf){
        return myGame.getRandomCharacters(numOf)
                .stream()
                .filter(character -> character != this)
                .toList();
    }

    public void die(){
        myGame.killCharacter(this);
        if (myController instanceof Human){
            ((Human) myController).disposeWindow();
        }
    }
    public void setAllBaseStats(int num){
        Arrays.stream(StatType.baseValues()).forEach(statType -> stats.set(statType, num));
    }
    public void modifyStat(StatType statType, int num){
        stats.modify(statType, num);
    }
    public void modifyStats(@NotNull final Map<StatType, Integer> statMap){
        statMap.forEach(stats::modify);
    }

    @Serial
    private void writeObject(@NotNull ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.write(myController instanceof Human ? 1 : 0);
    }

    @Serial
    private void readObject(@NotNull ObjectInputStream in) throws IOException, ClassNotFoundException{
        availableActions = List.of(Globals.baseActions);

        in.defaultReadObject();
        if (in.read() == 1)
            myController = new Human(this);
        else {
            myController = new AI(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if comparing the same reference
        if (o == null || getClass() != o.getClass()) return false; // Ensure class match

        GameCharacter character = (GameCharacter) o; // Cast to the specific class

        return Objects.equals(stats, character.stats);
    }

    public Game getGame(){
        return myGame;
    }

    @Override
    public int hashCode(){
        return Objects.hash(stats);
    }
}
