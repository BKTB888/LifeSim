package org.main;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        GameCharacter character = game.getRandomCharacter();

        character.makeHuman();
    }
}