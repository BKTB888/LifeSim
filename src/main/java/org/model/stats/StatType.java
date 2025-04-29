package org.model.stats;

import org.model.helper.StaticRandom;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public enum StatType {
    Age("Age") {
        @Override
        int placeInBounds(int num){
            return Math.max(0, num);
        }

        @Override
        String getStringStat(int num){
            return super.getStringStat(num) + (num < 2 ? " year old":" years old");
        }
    },
    Money("Money") {
        @NotNull
        @Override
        Stat getRandom(){
            return new Stat(this, StaticRandom.nextInt(10000));
        }

        @Override
        int placeInBounds(int num){
            return num;
        }

        @Override
        String getStringStat(int num){
            return '$' + NumberFormat.getNumberInstance(Locale.US).format(num) + '\n';
        }
    },
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

    String getStringStat(int num){
        return name + ": " + num;
    }

    @NotNull
    public static StatType[] baseValues() {
        return Arrays.stream(StatType.values()).filter(statType ->
            Arrays.stream(statType.getClass().getDeclaredMethods())
                    .allMatch(method -> method.getDeclaringClass() == StatType.class)
        ).toArray(StatType[]::new);
    }
    @Override
    public String toString(){
        return this.name;
    }
}
