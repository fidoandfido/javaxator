package com.javaxator.runners;

import com.javaxator.patterns.SimpleSessionSingleton;
import com.javaxator.patterns.ThreadSafeSessionSingleton;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingletonRunner {



    public static void main(String argv[]) throws InterruptedException {

        System.out.println("Hello, world!");

        // Create like A THOUSAND THREADS, each one will have simply attempt to get an instance of the session.

        ExecutorService executor = Executors.newFixedThreadPool(100);

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 1000000; i++ ) {
            callables.add( () -> SimpleSessionSingleton.getInstance().getInstanceCount() );
        }

        Comparator<OptionalInt> optIntComparator = (o1, o2) -> {
            if (o1.isPresent() && o2.isPresent()) {
                int i1 = o1.getAsInt();
                int i2 = o2.getAsInt();
                return (Integer.compare(i1,i2));
            }
            if (o2.isPresent()) {
                return -1;
            }
            if (o1.isPresent()) {
                return 1;
            }
            return 0;
        };

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });

        System.out.println("Sessions created:" + SimpleSessionSingleton.getInstance().getInstanceCount());

    }
}
