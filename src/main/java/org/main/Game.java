package org.main;

import org.helper.Globals;
import org.helper.StaticRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game {
    List<GameCharacter> characters;
    List<GameEvent> events = Arrays.asList(Globals.baseEvents);
    boolean quit = false;

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
        for (GameCharacter character : characters) {
            for (GameEvent event : events)
                if (StaticRandom.nextDouble() < event.chanceFor(character))
                    character.giveEvent(event);
            character.start();
        }
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
        while (!quit){
            this.nextTurn();
        }
    }
}
