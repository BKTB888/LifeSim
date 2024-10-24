package org.controller;


import org.main.Charachter;
import org.main.Event;

public abstract class Controller {
    Charachter myCharachter;


    public abstract void start();
    public abstract void decide(Event event);
}
