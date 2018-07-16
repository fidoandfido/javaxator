package com.javaxator.patterns.singleton;

import java.util.Random;

public class ThreadSafeSessionSingleton implements ISession{
    private static int instanceCount = 0;

    private int[] myRandoms = new int[1000];

    private ThreadSafeSessionSingleton() {
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


    private static ThreadSafeSessionSingleton instance;

    public static ISession getInstance() {
        // If instance is not null simply return it.
        if (instance != null) {
            return instance;
        }

        // Instance is null... Get a lock on it, and then, if it is still null, create the new singleton.
        synchronized (ThreadSafeSessionSingleton.class) {
            if (instance == null) {
                instance = new ThreadSafeSessionSingleton();
            }
        }
        return instance;
    }

}
