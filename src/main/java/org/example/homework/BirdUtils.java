package org.example.homework;

import java.util.Random;

public class BirdUtils {
    private static final int BOUND = 100;
    private static final String CUCKOO_WORD = "ку-ку";
    private static final String NIGHTINGALE_WORD = "чик-чирик";

    private BirdUtils() {
    }

    public static void callCuckoo() {
        int num = (new Random()).nextInt(BOUND);
        for (int i = 0; i < num; i++) {
            System.out.println(CUCKOO_WORD);
        }
    }

    public static void callNightingale() {
        int num = (new Random()).nextInt(BOUND);
        for (int i = 0; i < num; i++) {
            System.out.println(NIGHTINGALE_WORD);
        }
    }
}
