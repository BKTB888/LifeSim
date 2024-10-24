package org.main;

import org.controller.Controller;
import org.helper.Name;

import java.util.List;

public class Charachter {
    Name name;
    Stats stats;
    List<Action> availableActions;
    Controller myController;
    Boolean isMale;

    public List<Action> getActions(){return availableActions;}
}
