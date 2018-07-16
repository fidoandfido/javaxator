package com.javaxator.patterns.singleton;

import java.util.Random;

public class SimpleSessionSingleton implements ISession {

    private static SimpleSessionSingleton instance;

    private static int instanceCount = 0;

    private int[] myRandoms = new int[1000];

    private SimpleSessionSingleton() {
        // Lets do some really expensive initialisation here...
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                myRandoms[i] = random.nextInt();
            }
        }
        instanceCount++;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public static ISession getInstance() {
        if (instance == null) {
            instance = new SimpleSessionSingleton();
        }
        return instance;
    }


}
