package org.main;


import org.helper.Globals;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        GameCharacter character = game.getRandomCharacter();

        character.makeHuman();
        character.giveEvent(Globals.baseEvents[0]);
    }
}