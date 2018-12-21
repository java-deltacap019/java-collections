package com.deltacap019.list;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class LinkedListImpl implements CollectionImpl {

    private LinkedList<String> mLinkedList = new LinkedList<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mLinkedList.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        mLinkedList.add(null);
        mLinkedList.offer(null);
        mLinkedList.addFirst(null);
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 4; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mLinkedList) {
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
                    mLinkedList.add("element_" + timerCount);
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
        Iterator itr = mLinkedList.iterator();
        while (itr.hasNext())
        {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }

        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while descending iteration ]");
        Iterator itrDesc = mLinkedList.descendingIterator();
        while (itrDesc.hasNext())
        {
            String data = String.valueOf(itrDesc.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");
//        for (int i = 0; i < mLinkedList.size(); i++)
//        {
//            print(ConsoleColors.BLUE, mLinkedList.poll());
//        }

        print(ConsoleColors.BLUE, "Can't use as with we cant elements using index in LinkedList." +
                "With [PollFirst] we can access first element but it removes the element also as a result of which" +
                "we will not be able to access all the elements as value of counter [i] will become greater than the size of" +
                "treeSet" );
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mLinkedList.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mLinkedList.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mLinkedList) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mLinkedList.stream();
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
