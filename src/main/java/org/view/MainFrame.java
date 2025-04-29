package org.view;

import org.model.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JButton newGameButton = new JButton("New Game");
    Game game;


    public MainFrame(int numOfCharacters, int numOfHumans){

        this.setTitle("LifeSim");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(newGameButton);
        this.game = new Game(numOfCharacters, numOfHumans);

        newGameButton.addActionListener(_ -> {
            new Thread(() -> {
                game.start();
                this.setVisible(true);
            }).start();
            this.setVisible(false);
        });

        this.pack();
    }
}
