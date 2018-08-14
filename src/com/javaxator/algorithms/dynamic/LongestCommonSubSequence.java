package com.javaxator.algorithms.dynamic;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubSequence<T> {

    public LongestCommonSubSequence() {
        // Nothing to do here.
    }


    // Now figure out how to read out the string.
    @FunctionalInterface
    interface BackTrackMethod<T> {
        List<T> apply(Integer[][] a, List<T> x, List<T> y, Integer i, Integer j);
    }

    /**
     *
     */
    List<T> findLongestCommonSubSequence(List<T> first, List<T> second) {
        // Use dynamic programming to find longest common sub-sequence
        // Guard against null / empty strings
        if (first == null || first.size() == 0) {
            return null;
        }
        if (second == null || second.size() == 0) {
            return null;
        }

        var a = new Integer[first.size() + 1][second.size() + 1];


        for (int i = 0; i < first.size() + 1; i++) {
            a[i][0] = 0;
        }
        for (int j = 0; j < second.size() + 1; j++) {
            a[0][j] = 0;
        }

        var len = 0;
        for (int i = 1; i <= first.size(); i++) {
            for (int j = 1; j <= second.size(); j++) {
                if (first.get(i - 1) == second.get(j - 1)) {
                    a[i][j] = a[i -1][j - 1] + 1;
                    len = len < a[i][j] ? a[i][j] : len;
                } else {
                    a[i][j] = Integer.max(a[i][j -1], a[i-1][j]);
                }
            }
        }


        class Closure<T> {
            public BackTrackMethod<T> func;
        }

        var closure = new Closure<T>();
        closure.func = (Integer[][] matrix, List<T> x, List<T> y, Integer i, Integer j) ->  {
            if ( i == 0  || j == 0) {
                return new ArrayList<T>(){};
            }
            if (x.get(i - 1) == y.get(j - 1) ) {
                var ret = closure.func.apply(matrix, x, y, i -1, j -1);
                ret.add(x.get(i - 1));
                return  ret;
            }
            if (matrix[i][j - 1] > matrix[i - 1][j]) {
                return closure.func.apply(matrix, x, y, i, j - 1);
            } else {
                return closure.func.apply(matrix, x, y, i - 1, j);
            }
        };

        List<T> blob = closure.func.apply(a, first, second, first.size(), second.size());
        System.out.println(len);
        return blob;

    }

    public static void main(String argv[]) {
        var foo = new LongestCommonSubSequence<Character>();

        var x =List.of( 'o', 'a', 'b', 'x', 'c', 'b', 'd', 'c', 't');
        var y = List.of( 'q', 'a', 'c', 'b', 'y', 'c', 'q');

        System.out.println(foo.findLongestCommonSubSequence(x, y));

    }


}
