package org.gui;

import org.main.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class MainFrame extends JFrame {
    JButton newGameButton = new JButton("New Game");
    JButton loadGameButton = new JButton("Load Game");
    Game game;

    static final JDialog loadError;
    static {
        loadError = new JOptionPane("An error has occurred while reading the saved games." +
                " Please try again.", JOptionPane.ERROR_MESSAGE).createDialog("Load Error");
        loadError.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


    public MainFrame(int numOfCharacters, int numOfHumans){

        this.setTitle("LifeSim");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(newGameButton);
        this.add(loadGameButton);
        this.game = new Game(numOfCharacters, numOfHumans);

        newGameButton.addActionListener(_ -> {
            new Thread(() -> {
                game.start();
                this.setVisible(true);
            }).start();
            this.setVisible(false);
        });


        loadGameButton.addActionListener(_ -> {
            try (Stream<Path> fileStream = Files.list(Paths.get("saves"))) {
                String[] options = fileStream.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(String::valueOf)
                    .filter(name -> name.contains(".txt"))
                    .map(name -> name.substring(0, name.length() - 4))
                    .toArray(String[]::new);

                JDialog savedGames = new JDialog();
                savedGames.setTitle("Saved Games");
                savedGames.setLayout(new FlowLayout());
                JComboBox<String> selector = new JComboBox<>(options);
                JButton selectButton = new JButton("Select!");

                savedGames.add(selector);
                savedGames.add(selectButton);
                savedGames.pack();

                selectButton.addActionListener(_ -> {
                    try {
                        game.load((String) selector.getSelectedItem());
                        new Thread(() -> {
                            game.start();
                            this.setVisible(true);
                        }).start();
                        this.setVisible(false);
                        savedGames.dispose();
                    } catch (IOException | ClassNotFoundException ex) {
                        loadError.setVisible(true);
                    }
                });

                savedGames.setVisible(true);


            } catch (IOException e) {
                loadError.setVisible(true);
            }
        });

        this.pack();
    }
}
