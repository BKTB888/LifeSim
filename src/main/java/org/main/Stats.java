package org.main;

import org.helper.StatType;
import org.helper.StaticRandom;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    Map<StatType, Integer> stats;

    public Stats(){
        StatType[] statTypes = StatType.values();
        stats = new HashMap<>(statTypes.length);
        for (StatType statType : statTypes)
            stats.put(statType, StaticRandom.nextInt(100));
    }
    public Integer get(StatType type){
        return stats.get(type);
    }

    @Override
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        for (StatType statType : StatType.values())
            resultBuilder.append(statType).append(": ").append(stats.get(statType)).append('\n');
        resultBuilder.deleteCharAt(resultBuilder.length()-1);
        return resultBuilder.toString();
    }
}
