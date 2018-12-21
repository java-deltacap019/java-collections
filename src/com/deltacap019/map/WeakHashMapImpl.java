package com.deltacap019.map;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class WeakHashMapImpl  implements CollectionImpl {

    private WeakHashMap<String, String> mWeakHashMap = new WeakHashMap<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {

        for (String arrDatum : arrData) {
            mWeakHashMap.put(new String(arrDatum), arrDatum); // using new String() here to create key as we want to store it on "Heap" instead of "pool".
        }
    }

    @Override
    public void checkNullAllowed() {
        mWeakHashMap.put(null, "nullKey 1");
        mWeakHashMap.put(null, "nullKey 2"); // will overwrite the above value of above statement
        mWeakHashMap.put(arrData[0], null);
        mWeakHashMap.put(arrData[1], null);

        Iterator itr = mWeakHashMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void checkOrder() {
        for (int i = 1; i < 5; i++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (Map.Entry entry : mWeakHashMap.entrySet()) {
                print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
            }
        }
        System.gc(); // will collect the keys referenced in mWeakHashMap.
    }

    @Override
    public void checkThreadSafety() {
        print(ConsoleColors.BLACK_BOLD, "Thread safety");

        for (int i = 1; i < 5; i++) {
            final Timer timer = new Timer(); // creates separate thread
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // each timer thread continues to execute in fixed interval
                    mWeakHashMap.put("element_" + timerCount, "element_" + timerCount);
                    print(ConsoleColors.CYAN, "Thread Safety element_" + timerCount + Thread.currentThread().getName());
                    timerCount++;
                    if (timerCount > 100) {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }, 0, 100);
        }
    }

    @Override
    public void specialFunctions() {

        mWeakHashMap.putIfAbsent("A", "A"); // do no insert if key already has value, but for "A" we have null as value so it will be updated.
        mWeakHashMap.putIfAbsent(null, "@@"); // do no insert if key already has value, for null here we already have value so it will be skipped.
        print(ConsoleColors.BLACK_BOLD, "[ Put if absent ]");
        iterateUsingWhile("");

        mWeakHashMap.replace("A", "##");
        print(ConsoleColors.BLACK_BOLD, "[ simple replace ]");
        iterateUsingWhile("");

        mWeakHashMap.replace("B", "A", "@@A@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with wrong old value ]");
        iterateUsingWhile("");

        mWeakHashMap.replace("C", "C", "@@C@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with right old value ]");
        iterateUsingWhile("");


        print(ConsoleColors.BLACK_BOLD, "[ compute ]");
        mWeakHashMap.compute("C", (K, V) ->
                (V != null && V.contains("@") ? V = K : "")); // manipulation to bne done here on K or V only
        // mWeakHashMap.replace() wont work as may be it works on clone copy.

        iterateUsingWhile("");
        //mWeakHashMap.computeIfAbsent();
        //mWeakHashMap.computeIfPresent();

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, (message != null) ? message : "Iteration using [ while ]");
        Iterator itr = mWeakHashMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");

        print(ConsoleColors.BLUE, "Cannot access random elements in HashMap as the order is not maintained in it");
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mWeakHashMap.entrySet().iterator().forEachRemaining(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mWeakHashMap.forEach((K, V) ->
        {
            print(ConsoleColors.BLUE, "KEY = " + K + " " + "VALUE = " + V);
        });
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (Map.Entry entry : mWeakHashMap.entrySet()) {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream<Map.Entry<String, String>> stream = mWeakHashMap.entrySet().stream();
        stream.forEach(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void print(String color, String message) {
        System.out.println(color + message);
    }

}
