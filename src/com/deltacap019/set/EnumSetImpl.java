package com.deltacap019.set;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class EnumSetImpl implements CollectionImpl {

    private EnumSet<Days> mEnumSet;
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mEnumSet = EnumSet.of(Days.Monday, Days.Tuesday, Days.Wednesday, Days.Thursday);
    }

    @Override
    public void checkNullAllowed() {
        //mEnumSet.add(null); // cannot be added
        //mEnumSet.add(null); // cannot be added
        mEnumSet.add(Days.Friday);
        mEnumSet.add(Days.Friday);
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 4; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (Days data : mEnumSet) {
                print(ConsoleColors.BLUE, data.name());
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
                    mEnumSet.add(Days.Saturday);
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
        Iterator itr = mEnumSet.iterator();
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
        mEnumSet.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element.name());
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mEnumSet.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element.name())));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (Days data : mEnumSet) {
            print(ConsoleColors.BLUE, data.name());
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mEnumSet.stream();
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
