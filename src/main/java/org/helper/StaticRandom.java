package org.helper;

import java.util.Random;

public class StaticRandom {
    public static Random rng = new Random(42);
    static public int nextInt(int bound){
        return rng.nextInt(bound);
    }
    static public double nextDouble(){
        return rng.nextDouble();
    }
    public static long nextLong(){
        return rng.nextLong();
    }
}
