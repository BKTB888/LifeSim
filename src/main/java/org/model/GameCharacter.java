package org.model;

import org.controller.Human;
import org.model.actions.RunnableAction;
import org.model.events.GameEvent;
import org.model.events.GivenEvent;
import org.model.helper.Globals;
import org.model.helper.NameGen;
import org.model.stats.StatType;
import org.model.helper.ajbrown.namemachine.Gender;
import org.model.helper.ajbrown.namemachine.Name;
import org.controller.AI;
import org.controller.Controller;
import org.jetbrains.annotations.NotNull;
import org.model.stats.Stats;

import java.util.*;
import java.util.stream.Collectors;

public class GameCharacter {
    Name name;

    Stats stats;
    Set<RunnableAction> runnableActions = Arrays.stream(Globals.baseActions)
            .map(action ->new RunnableAction(action, this))
            .collect(Collectors.toCollection(HashSet::new));
    Controller myController;
    Game myGame;

    public Collection<RunnableAction> getActions(){return Collections.unmodifiableCollection(runnableActions);}
    public GameCharacter(Game myGame){
        this.name = NameGen.generateName();
        this.stats = new Stats();

        this.myGame = myGame;

        this.myController = new AI(this);
    }

    public GameCharacter(Game myGame, Gender gender){
        this.name = NameGen.generateName(gender);
        this.stats = new Stats();

        this.myGame = myGame;

        this.myController = new AI(this);
    }

    public GameCharacter(String firstName, String lastName, Gender gender, Game myGame){
        this.name = new Name(firstName, lastName, gender);
        this.myGame = myGame;
        this.stats = new Stats();
        this.myController = new AI(this);
    }

    public void setGame(Game game){
        this.myGame = game;
    }

    public void receiveEvent(GameEvent event){
        myController.decide(new GivenEvent(event, this));
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
                name.gender + '\n'
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if comparing the same reference
        if (o == null || getClass() != o.getClass()) return false; // Ensure class match

        GameCharacter character = (GameCharacter) o; // Cast to the specific class

        return Objects.equals(stats, character.stats);
    }
}
