package com.deltacap019.map;

import com.deltacap019.CollectionImpl;
import com.deltacap019.ConsoleColors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class PropertiesImpl implements CollectionImpl {

    private Properties  mPropertiesData = new Properties();
    private int timerCount = 1;

    @Override
    public void prepareData() {

        for (String arrDatum : arrData) {
            mPropertiesData.put(arrDatum, arrDatum);
        }
    }

    @Override
    public void checkNullAllowed() {
        //mPropertiesData.put(null, "nullKey 1"); // null key not allowed
        //mPropertiesData.put(new String(arrData[0]), null); // null values not allowed
        //mPropertiesData.put(arrData[1], null);

        Iterator itr = mPropertiesData.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void checkOrder() {
        for(int i = 1; i < 5; i ++) {
            print(ConsoleColors.BLACK_BOLD, " Order of elements : " + i);
            for (Map.Entry entry: mPropertiesData.entrySet()) {
                print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
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
                    mPropertiesData.put("element_" + timerCount, "element_" + timerCount);
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

        // read from properties file.

        try (FileReader fileReader = new FileReader("./src/com/deltaCap019/readdata.properties")) {
            Properties prop = new Properties();
            prop.load(fileReader);
            mPropertiesData.putAll(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mPropertiesData.putIfAbsent("A", "A"); // do no insert if key already has value, but for "A" we have null as value so it will be updated.
        print(ConsoleColors.BLACK_BOLD, "[ Put if absent ]");
        iterateUsingWhile("");

        mPropertiesData.replace("A", "##");
        print(ConsoleColors.BLACK_BOLD, "[ simple replace ]");
        iterateUsingWhile("");

        mPropertiesData.replace("B", "A", "@@A@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with wrong old value ]");
        iterateUsingWhile("");

        mPropertiesData.replace("C", "C", "@@C@@");
        print(ConsoleColors.BLACK_BOLD, "[ replace with right old value ]");
        iterateUsingWhile("");


        print(ConsoleColors.BLACK_BOLD, "[ compute ]");
        mPropertiesData.compute("C", (K, V) ->
                (V != null && String.valueOf(V).contains("@") ? V = K : "")); // manipulation to bne done here on K or V only
        // mPropertiesData.replace() wont work as may be it works on clone copy.

        iterateUsingWhile("");
        //mPropertiesData.computeIfAbsent();
        //mPropertiesData.computeIfPresent();

        // write properties to "readdata.properties" file
        try (FileWriter fileWriter = new FileWriter("./src/com/deltaCap019/writedata.properties")) {
            mPropertiesData.store(fileWriter,"Properties Data");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void iterateUsingWhile(String message) {
        print(ConsoleColors.BLACK_BOLD, (message != null) ? message :"Iteration using [ while ]");
        Iterator itr = mPropertiesData.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
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
        mPropertiesData.entrySet().iterator().forEachRemaining(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void iterateUsingIterableForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ iterable for each ]");
        mPropertiesData.forEach( (K, V) ->
        {
            print(ConsoleColors.BLUE, "KEY = " +  K +" " + "VALUE = " + V);
        });
    }

    @Override
    public void iterateUsingForEach() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ for each ]");
        for (Map.Entry entry : mPropertiesData.entrySet()) {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
        }
    }

    @Override
    public void iterateUsingStream() {
        print(ConsoleColors.BLACK_BOLD, "Iteration using [ stream ]");
        Stream<Map.Entry<Object, Object>> stream = mPropertiesData.entrySet().stream();
        stream.forEach(entry ->
        {
            print(ConsoleColors.BLUE, "KEY = " + entry.getKey() +" " + "VALUE = " + entry.getValue());
        });
    }

    @Override
    public void print(String color, String message) {
        System.out.println(color + message );
    }
}

