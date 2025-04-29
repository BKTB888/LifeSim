package org.model;

import org.controller.Controller;
import org.controller.Human;
import org.model.helper.Globals;
import org.model.helper.StaticRandom;

import java.util.*;

public class Game{

    List<GameCharacter> characters;
    List<GlobalEvent> events = Arrays.asList(Globals.baseEvents);
    private volatile boolean quit = false;

    public Game(int numOfCharacters){
        this.characters = new ArrayList<>(numOfCharacters);
        for (int i=0; i<numOfCharacters; ++i)
            characters.add(new GameCharacter(this));
    }
    public Game(int numOfCharacters,int numOfHumans){
        Game temp= new Game(numOfCharacters);
        this.characters = temp.characters;
        characters.forEach(character -> character.setGame(this));
        this.events = temp.events;

        this.getRandomCharacters(numOfHumans).forEach(GameCharacter::makeHuman);
    }
    public Game(){
        Game game = new Game(500);
        this.characters = game.characters;
        characters.forEach(character -> character.setGame(this));
    }

    // these had some problems, with the fact that it's not sure if they can give back proper a character
    public GameCharacter getRandomCharacter(){
        return characters.get(StaticRandom.nextInt(characters.size()));
    }

    public List<GameCharacter> getCharacters(){
        return characters;
    }

    public List<GameCharacter> getRandomCharacters(int numOf){
        List<GameCharacter> shuffledList = new ArrayList<>(characters);
        Collections.shuffle(shuffledList, StaticRandom.rng);
        return shuffledList.subList(0, numOf);
    }

    public void nextTurn(){
        for (GameCharacter character : List.copyOf(characters)) {
            for (GlobalEvent event : events)
                if (StaticRandom.nextDouble() < event.chanceFor(character))
                    character.giveEvent(event);
        }
        characters.forEach(GameCharacter::start);
    }

    public void killCharacter(GameCharacter character){
        characters.remove(character);
    }

    public void start(){
        while (humansRemaining() && !quit)
            this.nextTurn();
    }
    public boolean humansRemaining(){
        return characters.stream()
                .map(GameCharacter::getController)
                .map(Controller::getClass)
                .anyMatch(class1 -> class1.equals(Human.class));
    }

    public void quit(){
        quit = true;
    }

    // Not sure about it
    @Override
    public boolean equals(Object o){
        if (this == o) return true; // Check if comparing the same reference
        if (o == null || getClass() != o.getClass()) return false; // Ensure class match

        return characters.equals(((Game) o).characters);
    }
}
