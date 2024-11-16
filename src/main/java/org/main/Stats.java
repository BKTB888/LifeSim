package org.main;

import org.helper.StatType;
import org.helper.StaticRandom;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

public class Stats implements Iterable<Map.Entry<StatType, Integer>> {
    Map<StatType, Integer> stats = new EnumMap<>(StatType.class);

    public Stats(){
        StatType[] statTypes = StatType.values();
        for (StatType statType : statTypes)
            stats.put(statType, StaticRandom.nextInt(100));
    }

    public Integer get(StatType type){
        return stats.get(type);
    }

    public void set(StatType statType, int num) {
        if (num > 100)
            num = 100;

        else if (num < 0)
            num = 0;

        stats.put(statType, num);
    }

    public void modify(StatType statType, int num){
        this.set(statType, this.get(statType) + num);
    }

    public void modify(@NotNull final Map<StatType, Integer> statMap){
        statMap.forEach(this::modify);
    }

    @Override
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        for (StatType statType : StatType.values()) {
            resultBuilder.append(statType).append(": ");

            resultBuilder.append(stats.get(statType));

            resultBuilder.append('\n');
        }
        resultBuilder.deleteCharAt(resultBuilder.length()-1);
        return resultBuilder.toString();
    }

    //Could be improved
    @NotNull
    @Override
    public Iterator<Map.Entry<StatType, Integer>> iterator() {
        return stats.entrySet().iterator();
    }
}
