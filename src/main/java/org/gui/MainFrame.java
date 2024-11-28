package org.gui;

import org.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class MainFrame extends JFrame {
    JButton newGameButton = new JButton("New Game");
    JButton loadGameButton = new JButton("Load Game");

    static final JDialog loadError;
    static {
        loadError = new JOptionPane("An error has occurred while reading the saved games." +
                "Please try again.",JOptionPane.ERROR_MESSAGE).createDialog("Load Error");
        loadError.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


    public MainFrame(){
        this.setTitle("LifeSim");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(newGameButton);
        this.add(loadGameButton);
        newGameButton.addActionListener(_ -> {
            new Thread(() -> new Game(500, 1).start()).start();
            this.dispose();
        });


        loadGameButton.addActionListener(_ -> {
            try (Stream<Path> fileStream = Files.list(Paths.get("saves"))) {
                fileStream.filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(String::valueOf)
                        .filter(name -> name.contains(".txt"))
                        .map(name -> name.substring(0, name.length() - 4))
                        .map(JButton::new)
                        .forEach(button -> button.addActionListener(_ -> {
                            try {
                                new Game().load(button.getText() + ".txt");
                            } catch (IOException | ClassNotFoundException ex) {
                                loadError.setVisible(true);
                            }
                        }));


            } catch (IOException e) {
                loadError.setVisible(true);
            }
        });

        this.pack();
    }

    public void newGameListener(ActionListener listener){
        newGameButton.addActionListener(listener);
    }
}
