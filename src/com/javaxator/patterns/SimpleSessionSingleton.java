package com.javaxator.patterns;

public class SimpleSessionSingleton {

    private static SimpleSessionSingleton instance;

    private static int instanceCount = 0;

    private SimpleSessionSingleton() {
        instanceCount++;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public static SimpleSessionSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSessionSingleton();
        }
        return instance;
    }


}
