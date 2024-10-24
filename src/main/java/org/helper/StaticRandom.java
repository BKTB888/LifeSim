package org.helper;

import java.util.Random;

public class StaticRandom {
    static Random rng = new Random(42);
    static public int nextInt(int bound){
        return rng.nextInt(bound);
    }
}
