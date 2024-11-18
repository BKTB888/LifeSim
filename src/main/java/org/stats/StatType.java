package org.stats;

import org.helper.StaticRandom;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Contract(" -> new")
    Stat getRandom(){
        return new Stat(this, StaticRandom.nextInt(100));
    }

    int placeInBounds(int num){
        return Math.max(0, Math.min(100, num));
    }

    @Override
    public String toString(){
        return this.name;
    }
}
