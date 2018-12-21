package com.deltacap019.queue;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class QueueLinkedListImpl implements CollectionImpl {

    private Queue<String> mQueue = new LinkedList<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mQueue.addAll(Arrays.asList(arrData));
        iterateUsingWhile("Queue");
    }

    @Override
    public void checkNullAllowed() {
        mQueue.add(null);
        mQueue.offer(null);
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 4; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mQueue) {
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
                    mQueue.add("element_" + timerCount);
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

        Queue<String> emptyQueue = new LinkedList<>();

        print(ConsoleColors.BLACK_BOLD, "[ Empty Queue Poll ]");
        print(ConsoleColors.BLUE, emptyQueue.poll());

        print(ConsoleColors.BLACK_BOLD, "[ Empty Queue Peek ]");
        print(ConsoleColors.BLUE, emptyQueue.peek());

        //print(ConsoleColors.BLACK_BOLD, "[ Empty Queue element ]");
        //print(ConsoleColors.BLUE, emptyQueue.element()); // No Such element exception

        //print(ConsoleColors.BLACK_BOLD, "[ Empty Queue remove ]");
        //print(ConsoleColors.BLUE, emptyQueue.remove()); // No Such element exception

        print(ConsoleColors.BLACK_BOLD, "[ Queue Poll ]");
        print(ConsoleColors.BLUE, mQueue.poll());

        print(ConsoleColors.BLACK_BOLD, "[ Queue Peek ]");
        print(ConsoleColors.BLUE, mQueue.peek());

        print(ConsoleColors.BLACK_BOLD, "[ Queue element ]");
        print(ConsoleColors.BLUE, mQueue.element()); // No Such element exception

        print(ConsoleColors.BLACK_BOLD, "[ Queue remove ]");
        print(ConsoleColors.BLUE, mQueue.remove()); // No Such element exception
    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mQueue.iterator();
        while (itr.hasNext())
        {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");
//        for (int i = 0; i < mQueue.size(); i++)
//        {
//            print(ConsoleColors.BLUE, mQueue.poll());
//        }
        print(ConsoleColors.BLUE, "Can't use as with we cant elements using index." +
                "With [Poll] we can access first element but it removes the element also as a result of which" +
                "we will not be able to access all the elements as value of counter [i] will become greater than the size of" +
                "treeSet" );
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mQueue.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mQueue.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mQueue) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mQueue.stream();
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

