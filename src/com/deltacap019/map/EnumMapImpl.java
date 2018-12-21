package com.deltacap019.map;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class EnumMapImpl  implements CollectionImpl {

    private EnumMap<Days, String> mEnumMap = new EnumMap<Days, String>(Days.class);
    private int timerCount = 1;

    @Override
    public void prepareData() {

        for (Days day : Days.values()) {
            mEnumMap.put(day, String.valueOf(day));
        }
    }

    @Override
    public void checkNullAllowed() {
        //mEnumMap.put(null, "nullKey 1"); // cannot ad null key
        mEnumMap.put(Days.Monday, null);
        mEnumMap.put(Days.Tuesday, null);

        Iterator itr = mEnumMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void checkOrder() {
        for (int i = 1; i < 5; i++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (Map.Entry entry : mEnumMap.entrySet()) {
                print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
            }
        }
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
                    mEnumMap.put(Days.Saturday, "Thread Safety element_" + timerCount + Thread.currentThread().getName());
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

        mEnumMap.putIfAbsent(Days.Sunday, "A"); // do no insert if key already has value, but for "A" we have null as value so it will be updated.
        print(ConsoleColors.BLACK_BOLD, "[ Put if absent ]");
        iterateUsingWhile("");

        mEnumMap.replace(Days.Monday, "##");
        print(ConsoleColors.BLACK_BOLD, "[ simple replace ]");
        iterateUsingWhile("");

        mEnumMap.replace(Days.Monday, "A", "@@A@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with wrong old value ]");
        iterateUsingWhile("");

        mEnumMap.replace(Days.Monday, "##", "@@Monday@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with right old value ]");
        iterateUsingWhile("");


        print(ConsoleColors.BLACK_BOLD, "[ compute ]");
        mEnumMap.compute(Days.Monday, (K, V) ->
                (V != null && V.contains("@") ? V = Days.Monday.name() : "")); // manipulation to bne done here on K or V only
        // mEnumMap.replace() wont work as may be it works on clone copy.

        iterateUsingWhile("");
        //mEnumMap.computeIfAbsent();
        //mEnumMap.computeIfPresent();

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, (message != null) ? message : "Iteration using [ while ]");
        Iterator itr = mEnumMap.entrySet().iterator();
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
        mEnumMap.entrySet().iterator().forEachRemaining(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mEnumMap.forEach((K, V) ->
        {
            print(ConsoleColors.BLUE, "KEY = " + K + " " + "VALUE = " + V);
        });
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (Map.Entry entry : mEnumMap.entrySet()) {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream<Map.Entry<Days, String>> stream = mEnumMap.entrySet().stream();
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
