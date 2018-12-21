package com.deltacap019;

public interface CollectionImpl {

    String[] arrData = new String[]{"B", "A", "C", "D", "E","Y", "X", "Z", "F", "G", "H","I", "J"};

    enum Days {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};

    void prepareData();

    void checkNullAllowed();

    void checkOrder();

    void checkThreadSafety();

    void specialFunctions();

    void iterateUsingWhile(String message);

    void iterateUsingFor();

    void iterationUsingForEachRemaining(); // comes from ITERATOR Java 1.8

    void iterateUsingIterableForEach(); // comes from ITERABLE Java 1.8

    void iterateUsingForEach();

    void iterateUsingStream(); // Java 1.8

    void print(String color, String message);
}
