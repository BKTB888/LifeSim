package org.main;

import org.controller.Controller;
import org.controller.Human;
import org.helper.Globals;
import org.helper.StaticRandom;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Game{

    List<GameCharacter> characters;
    transient List<GlobalEvent> events = Arrays.asList(Globals.baseEvents);
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

    public Game(@NotNull Game game) {
        this.characters = game.characters;
        characters.forEach(character -> character.setGame(this));
    }

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

    public void save(String name) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("saves/" + name + ".txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(characters);
    }

    public void load(String name) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("saves/" + name + ".txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        this.characters = (List<GameCharacter>) in.readObject();
        characters.forEach(character -> character.setGame(this));
        quit = false;
    }

    public void quit(){
        quit = true;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true; // Check if comparing the same reference
        if (o == null || getClass() != o.getClass()) return false; // Ensure class match

        return characters.equals(((Game) o).characters);
    }

    @Override
    public int hashCode(){
        return Objects.hash(characters);
    }
}
