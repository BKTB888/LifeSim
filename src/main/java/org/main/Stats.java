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
}
