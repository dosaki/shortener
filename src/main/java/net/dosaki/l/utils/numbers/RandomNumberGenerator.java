package net.dosaki.l.utils.numbers;

import java.util.Random;

public class RandomNumberGenerator {
    public static long newLong(){
        return Math.abs(new Random().nextLong());
    }
}
