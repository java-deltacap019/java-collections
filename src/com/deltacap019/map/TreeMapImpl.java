package com.deltacap019.map;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.util.*;
import java.util.stream.Stream;

public class TreeMapImpl implements CollectionImpl {

    private TreeMap<String, String> mTreeMap = new TreeMap<>();
    private int timerCount = 1;

    @Override
    public void prepareData() {

        for (String arrDatum : arrData) {
            mTreeMap.put(arrDatum, arrDatum);
        }
    }

    @Override
    public void checkNullAllowed() {
        //mTreeMap.put(null, "nullKey 1"); // cannot contain null key
        //mTreeMap.put(null, "nullKey 2"); // cannot contain null key
        mTreeMap.put(arrData[0], null);
        mTreeMap.put(arrData[1], null);

        Iterator itr = mTreeMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void checkOrder() {
        for (int i = 1; i < 5; i++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (Map.Entry entry : mTreeMap.entrySet()) {
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
                    mTreeMap.put("element_" + timerCount, "element_" + timerCount);
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

        mTreeMap.putIfAbsent("A", "A"); // do no insert if key already has value, but for "A" we have null as value so it will be updated.

        print(ConsoleColors.BLACK_BOLD, "[ Put if absent ]");
        iterateUsingWhile("");

        mTreeMap.replace("A", "##");
        print(ConsoleColors.BLACK_BOLD, "[ simple replace ]");
        iterateUsingWhile("");

        mTreeMap.replace("B", "A", "@@A@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with wrong old value ]");
        iterateUsingWhile("");

        mTreeMap.replace("C", "C", "@@C@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with right old value ]");
        iterateUsingWhile("");iterateUsingWhile("");


        print(ConsoleColors.BLACK_BOLD, "[ compute ]");
        mTreeMap.compute("C", (K, V) ->
                (V != null && V.contains("@") ? V = K : "")); // manipulation to bne done here on K or V only
        // mTreeMap.replace() wont work as may be it works on clone copy.
        iterateUsingWhile("");

        //mTreeMap.computeIfAbsent();
        //mTreeMap.computeIfPresent();


        print(ConsoleColors.BLACK_BOLD, "[ ceiling key ]");
        print(ConsoleColors.BLUE, mTreeMap.ceilingKey("C"));

        print(ConsoleColors.BLACK_BOLD, "[ ceiling entry ]");
        Map.Entry<String, String> ceilingEntry = mTreeMap.ceilingEntry("C");
        print(ConsoleColors.BLUE, "KEY: " + ceilingEntry.getKey() + "VALUE: " + ceilingEntry.getValue() );

        print(ConsoleColors.BLACK_BOLD, "[ floor entry ]");
        Map.Entry<String, String> floorEntry = mTreeMap.floorEntry("C");
        print(ConsoleColors.BLUE, "KEY: " + floorEntry.getKey() + " VALUE: " + floorEntry.getValue() );

        print(ConsoleColors.BLACK_BOLD, "[ descending key set ]");
        Set<String> descendingKeySet = mTreeMap.descendingKeySet();
        Iterator itrDescKeySet = descendingKeySet.iterator();
        while (itrDescKeySet.hasNext()) {
            print(ConsoleColors.BLUE, "KEY = " + itrDescKeySet.next());
        }

        print(ConsoleColors.BLACK_BOLD, "[ descending map ]");
        NavigableMap<String, String> descHashMap =  mTreeMap.descendingMap();
        Iterator itrDescMap = descHashMap.entrySet().iterator();
        while (itrDescMap.hasNext()) {
            Map.Entry entry = (Map.Entry) itrDescMap.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
        }

        print(ConsoleColors.BLACK_BOLD, "[ first entry ]");
        Map.Entry<String, String> firstEntry = mTreeMap.firstEntry();
        print(ConsoleColors.BLUE, "KEY: " + firstEntry.getKey() + " VALUE: " + firstEntry.getValue());

        print(ConsoleColors.BLACK_BOLD, "[ last entry ]");
        Map.Entry<String, String> lastEntry = mTreeMap.lastEntry();
        print(ConsoleColors.BLUE, "KEY: " + lastEntry.getKey() + " VALUE: " + lastEntry.getValue() );

        print(ConsoleColors.BLACK_BOLD, "[ head map ]");
        SortedMap<String, String> headMap =  mTreeMap.headMap("C");
        Iterator itrHeadMap = headMap.entrySet().iterator();
        while (itrHeadMap.hasNext()) {
            Map.Entry entry = (Map.Entry) itrHeadMap.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
        }

        print(ConsoleColors.BLACK_BOLD, "[ tail map ]");
        SortedMap<String, String> tailMap =  mTreeMap.tailMap("C");
        Iterator itrTailMap = tailMap.entrySet().iterator();
        while (itrTailMap.hasNext()) {
            Map.Entry entry = (Map.Entry) itrTailMap.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
        }

        print(ConsoleColors.BLACK_BOLD, "[ higher entry ]");
        Map.Entry<String, String> higherEntry = mTreeMap.higherEntry("C");
        print(ConsoleColors.BLUE, "KEY: " + higherEntry.getKey() + " VALUE: " + higherEntry.getValue());

        print(ConsoleColors.BLACK_BOLD, "[ lower entry ]");
        Map.Entry<String, String> lowerEntry = mTreeMap.lowerEntry("C");
        print(ConsoleColors.BLUE, "KEY: " + lowerEntry.getKey() + " VALUE: " + lowerEntry.getValue());

        print(ConsoleColors.BLACK_BOLD, "[ higher key ]");
        print(ConsoleColors.BLUE, mTreeMap.higherKey("C"));

        print(ConsoleColors.BLACK_BOLD, "[ lower key ]");
        print(ConsoleColors.BLUE, mTreeMap.lowerKey("C"));

        print(ConsoleColors.BLACK_BOLD, "[ poll first entry ]");
        Map.Entry<String, String> pollFirstEntry = mTreeMap.pollFirstEntry();
        print(ConsoleColors.BLUE, "KEY: " + pollFirstEntry.getKey() + " VALUE: " + pollFirstEntry.getValue());

        print(ConsoleColors.BLACK_BOLD, "[ poll last entry ]");
        Map.Entry<String, String> pollLastEntry = mTreeMap.pollLastEntry();
        print(ConsoleColors.BLUE, "KEY: " + pollLastEntry.getKey() + " VALUE: " + pollLastEntry.getValue());

        print(ConsoleColors.BLACK_BOLD, "[ sub map with existing elements]");
        SortedMap<String, String> subMap =  mTreeMap.subMap("C", "H");
        Iterator itrSubMap = subMap.entrySet().iterator();
        while (itrSubMap.hasNext()) {
            Map.Entry entry = (Map.Entry) itrSubMap.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
        }

        print(ConsoleColors.BLACK_BOLD, "[ sub map with non existing (TO) element]");
        subMap =  mTreeMap.subMap("C", "K");
        itrSubMap = subMap.entrySet().iterator();
        while (itrSubMap.hasNext()) {
            Map.Entry entry = (Map.Entry) itrSubMap.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
        }

        print(ConsoleColors.BLACK_BOLD, "[ sub map with (TO) element] existing before (FROM) element"); // will throw IllegalArgumentException (fromKey > toKey)
//        subMap = mTreeMap.subMap("C", "A");
//        itrSubMap = subMap.entrySet().iterator();
//        while (itrSubMap.hasNext()) {
//            Map.Entry entry = (Map.Entry) itrSubMap.next();
//            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + " VALUE = " + entry.getValue());
//        }

        print(ConsoleColors.BLACK_BOLD, "[ first key ]");
        print(ConsoleColors.BLUE, mTreeMap.firstKey());

        print(ConsoleColors.BLACK_BOLD, "[ last key ]");
        print(ConsoleColors.BLUE, mTreeMap.lastKey());

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, (message != null) ? message :"Iteration using [ while ]");
        Iterator itr = mTreeMap.entrySet().iterator();
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
        mTreeMap.entrySet().iterator().forEachRemaining(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mTreeMap.forEach((K, V) ->
        {
            print(ConsoleColors.BLUE, "KEY = " + K + " " + "VALUE = " + V);
        });
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (Map.Entry entry : mTreeMap.entrySet()) {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() + " " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream<Map.Entry<String, String>> stream = mTreeMap.entrySet().stream();
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

