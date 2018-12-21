package com.deltacap019.set;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class TreeSetImpl  implements CollectionImpl {

    private TreeSet<String> mTreeSet = new TreeSet<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {
        //mTreeSet = new HashSet<>(Arrays.asList(arrData)); // other way of implementation
        mTreeSet.addAll(Arrays.asList(arrData));
    }

    @Override
    public void checkNullAllowed() {
        //mTreeSet.add(null); can't have null
        //mTreeSet.add(null);
        mTreeSet.add("Z");
        mTreeSet.add("Z");
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 10; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (String data : mTreeSet) {
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
                    mTreeSet.add("element_" + timerCount);
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

        print(ConsoleColors.BLACK_BOLD, "Ceiling");
        print(ConsoleColors.BLUE, mTreeSet.ceiling("C"));

        print(ConsoleColors.BLACK_BOLD, "Floor");
        print(ConsoleColors.BLUE, mTreeSet.floor("C"));

        print(ConsoleColors.BLACK_BOLD, "Higher");
        print(ConsoleColors.BLUE, mTreeSet.higher("C"));

        print(ConsoleColors.BLACK_BOLD, "Lower");
        print(ConsoleColors.BLUE, mTreeSet.lower("C"));

        print(ConsoleColors.BLACK_BOLD, "Descending iterator");
        Iterator descItr = mTreeSet.descendingIterator();

        while (descItr.hasNext()){
            print(ConsoleColors.BLUE, String.valueOf(descItr.next()));
        }

        print(ConsoleColors.BLACK_BOLD, "Descending set");
        NavigableSet<String> descSet = mTreeSet.descendingSet();
        for (String element :
                descSet) {
            print(ConsoleColors.BLUE, element);
        }

        print(ConsoleColors.BLACK_BOLD, "Split Iterator");
        Spliterator<String> splitIterator = mTreeSet.spliterator();
        splitIterator.forEachRemaining(element -> {
            print(ConsoleColors.BLUE, element);
        });

        print(ConsoleColors.BLACK_BOLD, "Head set");
        print(ConsoleColors.BLUE, mTreeSet.headSet("C").toString());

        print(ConsoleColors.BLACK_BOLD, "Tail set");
        print(ConsoleColors.BLUE, mTreeSet.tailSet("C").toString());

        print(ConsoleColors.BLACK_BOLD, "Sub set");
        print(ConsoleColors.BLUE, mTreeSet.subSet("C", "F").toString());

        print(ConsoleColors.BLACK_BOLD, "Poll first");
        print(ConsoleColors.BLUE, mTreeSet.pollFirst());

        print(ConsoleColors.BLACK_BOLD, "Poll last");
        print(ConsoleColors.BLUE, mTreeSet.pollLast());

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ while ]");
        Iterator itr = mTreeSet.iterator();
        while (itr.hasNext()) {
            String data = String.valueOf(itr.next());
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingFor() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for ]");

//        for (int i = 0; i < mTreeSet.size(); i++)
//        {
//            print(ConsoleColors.BLUE, mTreeSet.pollFirst());
//        }

        print(ConsoleColors.BLUE, "Can't use as with we cant elements using index in SET." +
                "With [PollFirst] we can access first element but it removes the element also as a result of which" +
                "we will not be able to access all the elements as value of counter [i] will become greater than the size of" +
                "treeSet" );
    }

    @Override
    public void iterationUsingForEachRemaining() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each remaining ]");
        mTreeSet.iterator().forEachRemaining(element ->
        {
            print(ConsoleColors.BLUE, element);
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mTreeSet.forEach(element -> print(ConsoleColors.BLUE, String.valueOf(element)));
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (String data : mTreeSet) {
            print(ConsoleColors.BLUE, data);
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream stream = mTreeSet.stream();
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