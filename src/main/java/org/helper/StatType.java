package org.helper;

public enum StatType {
    Health("Health"),
    Happiness("Happiness"),
    Looks("Looks"),
    Strength("Strength"),
    Smartness("Smartness"),
    Luck("Luck");

    private final String name;

    StatType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
