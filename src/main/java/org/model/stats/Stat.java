package org.model.stats;

import java.util.AbstractMap;
import java.util.Map;

public class Stat implements Map.Entry<StatType, Integer> {
    Map.Entry<StatType, Integer> entry;


    public Stat(StatType key, Integer value) {
        entry = new AbstractMap.SimpleEntry<>(key, value);
    }

    public Stat(Map.Entry<StatType, Integer> entry){
        this.entry = entry;
    }

    @Override
    public String toString(){
        return getKey().getStringStat(getValue());
    }

    @Override
    public StatType getKey() {
        return entry.getKey();
    }

    @Override
    public Integer getValue() {
        return entry.getValue();
    }

    @Override
    public Integer setValue(Integer value){
        return entry.setValue(getKey().placeInBounds(value));
    }

}
