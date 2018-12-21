package com.deltacap019.list;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class StackImpl  implements CollectionImpl {

    private Stack<String> mStack = new Stack<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mStack.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        mStack.add(null);
        mStack.push("Pushed");
    }

    @Override
    public void checkOrder() {
        for (int i = 1; i < 4; i++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mStack) {
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
                    mStack.add("element_" + timerCount);
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
        Stack<String> emptyStack = new Stack<>();

        //print(ConsoleColors.BLACK_BOLD, "[ Empty Queue Pop ]");
        //print(ConsoleColors.BLUE, emptyStack.pop()); // EmptyStack Exception

        //print(ConsoleColors.BLACK_BOLD, "[ Empty Queue Peek ]");
        //print(ConsoleColors.BLUE, emptyStack.peek());  // EmptyStack Exception

        print(ConsoleColors.BLACK_BOLD, "[ Queue Pop ]");
        print(ConsoleColors.BLUE, mStack.pop());

        print(ConsoleColors.BLACK_BOLD, "[ Queue Peek ]");
        print(ConsoleColors.BLUE, mStack.peek());


        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while enumeration ]");
        Enumeration enumeration = mStack.elements();
        while (enumeration.hasMoreElements()) {
            String data = String.valueOf(enumeration.nextElement());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mStack.iterator();
        while (itr.hasNext()) {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");
//        for (int i = 0; i < mStack.size(); i++)
//        {
//            print(ConsoleColors.BLUE, mStack.pop());
//        }

        print(ConsoleColors.BLUE, "Can't use as with we cant elements using index in STACK." +
                "With [PollFirst] we can access first element but it removes the element also as a result of which" +
                "we will not be able to access all the elements as value of counter [i] will become greater than the size of" +
                "treeSet");
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mStack.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mStack.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mStack) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mStack.stream();
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