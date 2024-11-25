package org.main;

import org.controller.Controller;
import org.controller.Human;
import org.helper.Globals;
import org.helper.StaticRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game {
    List<GameCharacter> characters;
    List<GameEvent> events = Arrays.asList(Globals.baseEvents);

    public Game(int numOfCharacters){
        this.characters = new ArrayList<>(numOfCharacters);
        for (int i=0; i<numOfCharacters; ++i)
            characters.add(new GameCharacter(this));
    }
    public Game(){
        Game game = new Game(500);
        this.characters = game.characters;
    }

    public GameCharacter getRandomCharacter(){
        return characters.get(StaticRandom.nextInt(characters.size()));
    }

    public List<GameCharacter> getCharacters(){
        return characters;
    }

    public List<GameCharacter> getCharacters(int numOf){
        return Stream.generate(this::getRandomCharacter)
                .limit(numOf)
                .toList();
    }

    public void nextTurn(){
        for (GameCharacter character : List.copyOf(characters)) {
            for (GameEvent event : events)
                if (StaticRandom.nextDouble() < event.chanceFor(character))
                    character.giveEvent(event);
        }
        characters.forEach(GameCharacter::start);
    }

    public void killCharacter(GameCharacter character){
        characters.remove(character);
    }

    @Override
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        for (GameCharacter gameCharacter : characters)
            resultBuilder.append(gameCharacter).append('\n');
        resultBuilder.deleteCharAt(resultBuilder.length()-1);
        return resultBuilder.toString();
    }

    public void start(){
        while (shouldQuit()){
            this.nextTurn();
        }
    }
    public boolean shouldQuit(){
        return characters.stream()
                .map(GameCharacter::getController)
                .map(Controller::getClass)
                .anyMatch(class1 -> class1.equals(Human.class));
    }
}
