package org.main;

import org.helper.StatType;
import org.helper.StaticRandom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Stats implements Iterable<Map.Entry<StatType, Integer>> {
    Map<StatType, Integer> stats;

    public Stats(){
        StatType[] statTypes = StatType.values();
        stats = new HashMap<>(statTypes.length);
        for (StatType statType : statTypes) {
            if (statType == StatType.Money)
                stats.put(statType, StaticRandom.nextInt(10000));
            else
                stats.put(statType, StaticRandom.nextInt(100));
        }
    }

    public Integer get(StatType type){
        return stats.get(type);
    }

    public void set(StatType statType, int num) {
        stats.put(statType, num);
        if (    stats.get(statType) >= 100 &&
                statType != StatType.Money &&
                statType != StatType.Age)

            stats.put(statType, 100);
    }

    public void increment(StatType statType, int num){
        this.set(statType, this.get(statType) + num);
    }

    @Override
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        for (StatType statType : StatType.values()) {
            resultBuilder.append(statType).append(": ");

            if (statType == StatType.Money)
                resultBuilder.append("$");

            resultBuilder.append(stats.get(statType));

            if (statType == StatType.Age)
                resultBuilder.append(" years old");

            resultBuilder.append('\n');
        }
        resultBuilder.deleteCharAt(resultBuilder.length()-1);
        return resultBuilder.toString();
    }

    //Could be improved
    @Override
    public Iterator<Map.Entry<StatType, Integer>> iterator() {
        return stats.entrySet().iterator();
    }
}
