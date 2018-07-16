package com.javaxator.runners;

import com.javaxator.patterns.singleton.ISession;
import com.javaxator.patterns.singleton.SimpleSessionSingleton;
import com.javaxator.patterns.singleton.ThreadSafeSessionSingleton;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonRunner {

    @FunctionalInterface
    private interface ISessionProvider {
        ISession getInstance();
    }

    public static void main(String argv[]) throws InterruptedException   {


        System.out.println("Hello, world!");

        SingletonRunner runner = new SingletonRunner();
        runner.runTest(() -> SimpleSessionSingleton.getInstance(), "Simple");
        runner.runTest(() -> ThreadSafeSessionSingleton.getInstance(), "ThreadSafe");

    }


    void runTest(ISessionProvider provider, String name) throws InterruptedException  {

        // Create like A THOUSAND THREADS, each one will have simply attempt to get an instance of the session.
        ExecutorService executor = Executors.newFixedThreadPool(100);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 100; i++ ) {
            callables.add( () -> provider.getInstance().getInstanceCount() );
        }

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        executor.shutdown();
        System.out.println("Name of test: " + name);
        System.out.println("Sessions created:" + provider.getInstance().getInstanceCount());
    }
}
