package com.deltacap019.list;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class VectorImpl implements CollectionImpl {

    private Vector<String> mVector = new Vector<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mVector.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        mVector.add(null);
        mVector.add(null);
    }

    @Override
    public void checkOrder() {
        for (int i = 1; i < 4; i++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mVector) {
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
                    mVector.add("element_" + timerCount);
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

        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while enumeration ]");
        Enumeration enumeration = mVector.elements();
        while (enumeration.hasMoreElements()) {
            String data = String.valueOf(enumeration.nextElement());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mVector.iterator();
        while (itr.hasNext()) {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");
        for (int i = 0; i < mVector.size(); i++) {
            print(ConsoleColors.BLUE, mVector.elementAt(i));
        }
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mVector.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mVector.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mVector) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mVector.stream();
        stream.forEach(element ->
        {
            print(ConsoleColors.BLUE, String.valueOf(element));
        });
    }

    @Override
    public void print(String color, String message) {
        System.out.println(color + message);
    }
}
