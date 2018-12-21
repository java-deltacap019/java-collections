package com.deltacap019.set;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class HashSetImpl implements CollectionImpl {

    private HashSet<String> mHashSet = new HashSet<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        //mHashSet = new HashSet<>(Arrays.asList(arrData)); // other way of implementation
        mHashSet.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        mHashSet.add(null);
        mHashSet.add(null);
        mHashSet.add("Z");
        mHashSet.add("Z");
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 15; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mHashSet) {
                print(ConsoleColors.BLUE, data);
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
                    mHashSet.add("element_" + timerCount);
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

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mHashSet.iterator();
        while (itr.hasNext())
        {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");

        print(ConsoleColors.BLUE, "Cannot access random elements in SET as the order is not maintained in it");
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mHashSet.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mHashSet.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mHashSet) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mHashSet.stream();
        stream.forEach(element ->
        {
            print(ConsoleColors.BLUE, String.valueOf(element));
        });
    }

    @Override
    public void print(String color, String message) {
        System.out.println(color + message );
    }
}