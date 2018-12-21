package com.deltacap019.queue;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class DequeArrayImpl  implements CollectionImpl {

    private Deque<String> mDeque = new ArrayDeque<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        mDeque.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        //mDeque.add(null); // null not allowed
        //mDeque.offer(null); null not allowed
        iterateUsingWhile("DeQueue");
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 4; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mDeque) {
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
                    mDeque.add("element_" + timerCount);
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

        // Empty Queue
        Deque<String> emptyQueue = new ArrayDeque<>();

        print(ConsoleColors.BLACK_BOLD, "[ Queue poll() ]");
        print(ConsoleColors.BLUE, emptyQueue.poll());

        print(ConsoleColors.BLACK_BOLD, "[ Queue pollFirst ]");
        print(ConsoleColors.BLUE, emptyQueue.pollFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue pollLast ]");
        print(ConsoleColors.BLUE, emptyQueue.pollLast());

        print(ConsoleColors.BLACK_BOLD, "[ Queue Peek ]");
        print(ConsoleColors.BLUE, emptyQueue.peek());

        print(ConsoleColors.BLACK_BOLD, "[ Queue peekFirst() ]");
        print(ConsoleColors.BLUE, emptyQueue.peekFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue peekLast() ]");
        print(ConsoleColors.BLUE, emptyQueue.peekLast());

        //print(ConsoleColors.BLACK_BOLD, "[ Queue element() ]");
        //print(ConsoleColors.BLUE, emptyQueue.element()); // throws exception if queue is empty

        //print(ConsoleColors.BLACK_BOLD, "[ Queue getFirst() ]");
        //print(ConsoleColors.BLUE, emptyQueue.getFirst()); // throws exception if queue is empty

        //print(ConsoleColors.BLACK_BOLD, "[ Queue getLast() ]");
        //print(ConsoleColors.BLUE, emptyQueue.getLast()); // throws exception if queue is empty

        //print(ConsoleColors.BLACK_BOLD, "[ Queue remove() ]");
        //print(ConsoleColors.BLUE, emptyQueue.remove()); // throws exception if queue is empty

        //print(ConsoleColors.BLACK_BOLD, "[ Queue removeFirst() ]");
        //print(ConsoleColors.BLUE, emptyQueue.removeFirst()); // throws exception if queue is empty

        //print(ConsoleColors.BLACK_BOLD, "[ Queue removeLast() ]");
        //print(ConsoleColors.BLUE, emptyQueue.removeLast()); // throws exception if queue is empty



        // Non Empty Queue
        print(ConsoleColors.BLACK_BOLD, "[ Queue poll() ]");
        print(ConsoleColors.BLUE, mDeque.poll());

        print(ConsoleColors.BLACK_BOLD, "[ Queue pollFirst ]");
        print(ConsoleColors.BLUE, mDeque.pollFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue pollLast ]");
        print(ConsoleColors.BLUE, mDeque.pollLast());

        print(ConsoleColors.BLACK_BOLD, "[ Queue Peek ]");
        print(ConsoleColors.BLUE, mDeque.peek());

        print(ConsoleColors.BLACK_BOLD, "[ Queue peekFirst() ]");
        print(ConsoleColors.BLUE, mDeque.peekFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue peekLast() ]");
        print(ConsoleColors.BLUE, mDeque.peekLast());

        print(ConsoleColors.BLACK_BOLD, "[ Queue element() ]");
        print(ConsoleColors.BLUE, mDeque.element());

        print(ConsoleColors.BLACK_BOLD, "[ Queue getFirst() ]");
        print(ConsoleColors.BLUE, mDeque.getFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue getLast() ]");
        print(ConsoleColors.BLUE, mDeque.getLast());

        print(ConsoleColors.BLACK_BOLD, "[ Queue remove() ]");
        print(ConsoleColors.BLUE, mDeque.remove());

        print(ConsoleColors.BLACK_BOLD, "[ Queue removeFirst() ]");
        print(ConsoleColors.BLUE, mDeque.removeFirst());

        print(ConsoleColors.BLACK_BOLD, "[ Queue removeLast() ]");
        print(ConsoleColors.BLUE, mDeque.removeLast());

        print(ConsoleColors.BLACK_BOLD, "Descending iteration using [ while ]");
        Iterator itr = mDeque.descendingIterator();
        while (itr.hasNext())
        {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }

//        print(ConsoleColors.BLACK_BOLD, "Actual order of list using [ while ]");
        // Won't work with iterator will keep on returning null elements, as it's array there is size predefined for it.
        // we are not using here iterator.next() to access element  but we are using poll() and it will keeps returning null
        // from array's allocated size or from location it is currently in.
        // Iterator maintains "cursor" = current index like "i" counter in for loop.
        //                    "fence" = length of total elements.
        // in this case without itr.next() the cursor values top incrementing.
        // InCase of Linked List implementation it works because hasNext() maintains "Next Node"
        /*Iterator itr1 = mDeque.iterator();
        while (itr1.hasNext())
        {
            String data = mDeque.poll();
            print(ConsoleColors.BLUE, data);
        }*/

        prepareData();
    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mDeque.iterator();
        while (itr.hasNext())
        {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");
//        for (int i = 0; i < mDeque.size(); i++)
//        {
//            print(ConsoleColors.BLUE, mDeque.poll());
//        }
        print(ConsoleColors.BLUE, "Can't use as with we cant elements using index." +
                "With [Poll] we can access first element but it removes the element also as a result of which" +
                "we will not be able to access all the elements as value of counter [i] will become greater than the size of" +
                "deque" );
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mDeque.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mDeque.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mDeque) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mDeque.stream();
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


