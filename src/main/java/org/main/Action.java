package org.main;

import java.util.function.Consumer;

public class Action {
    String name;
    Consumer<Charachter> action;

    public void executeOn(Charachter charachter){
        action.accept(charachter);
    }
}
