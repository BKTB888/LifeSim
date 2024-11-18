package org.stats;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stats implements Iterable<Stat>{
    Map<StatType, Integer> stats = new EnumMap<>(StatType.class);

    public Stats(){
        for (StatType statType : StatType.values()) {
            Stat randomStat = statType.getRandom();
            stats.put(randomStat.getKey(), randomStat.getValue());
        }
    }

    public Integer get(StatType type){
        return stats.get(type);
    }

    public void set(StatType statType, int num) {
        stats.put(statType, statType.placeInBounds(num));
    }

    public void modify(StatType statType, int num){
        this.set(statType, this.get(statType) + num);
    }

    public void modify(@NotNull final Map<StatType, Integer> statMap){
        statMap.forEach(this::modify);
    }

    @Override
    public String toString(){
        return this.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }

    public Stream<Stat> stream(){
        return stats.entrySet().stream()
                .map(Stat::new);
    }

    @NotNull
    @Override
    public Iterator<Stat> iterator() {
        return stats.entrySet().stream()
                .map(Stat::new)
                .iterator();
    }
}
