package com.javaxator.patterns;

public class ThreadSafeSessionSingleton {

    private ThreadSafeSessionSingleton() {
        instanceCount++;
    }

    private static int instanceCount = 0;

    private static volatile ThreadSafeSessionSingleton instance;

    public static ThreadSafeSessionSingleton getInstance() {
        // If instance is not null simply return it.
        if (instance != null) {
            return instance;
        }

        // Instance is null... Get a lock on it, and then, if it is still null, create the new singleton.
        synchronized (instance) {
            if (instance == null) {
                instance = new ThreadSafeSessionSingleton();
            }
        }
        return instance;
    }

}
