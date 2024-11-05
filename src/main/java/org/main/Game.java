package org.main;

import org.helper.StaticRandom;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<GameCharacter> characters;
    List<Event> events;

    public Game(int numOfCharacters){
        this.characters = new ArrayList<>(numOfCharacters);
        for (int i=0; i<numOfCharacters; ++i)
            characters.add(new GameCharacter());
    }
    public Game(){
        Game game = new Game(500);
        this.characters = game.characters;
    }

    public GameCharacter getRandomCharacter(){
        return characters.get(StaticRandom.nextInt(characters.size()));
    }

    public void nextTurn(){
        for (GameCharacter character : characters) {
            for (Event event : events)
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
}
