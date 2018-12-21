import com.deltacap019.CollectionImpl;
import com.deltacap019.list.ArrayListImpl;
import com.deltacap019.list.LinkedListImpl;
import com.deltacap019.list.StackImpl;
import com.deltacap019.list.VectorImpl;
import com.deltacap019.map.*;
import com.deltacap019.queue.DequeArrayImpl;
import com.deltacap019.queue.DequeLinkedListImpl;
import com.deltacap019.queue.QueueLinkedListImpl;
import com.deltacap019.queue.QueuePriorityQueueImpl;
import com.deltacap019.set.EnumSetImpl;
import com.deltacap019.set.HashSetImpl;
import com.deltacap019.set.LinkedHashSetImpl;
import com.deltacap019.set.TreeSetImpl;

public class Main {

    public static void main(String[] args) {

        // ArrayList
        CollectionImpl collectionImpl;
        collectionImpl = new ArrayListImpl();

        // Linked List
        collectionImpl = new LinkedListImpl();

        // Vector
        collectionImpl = new VectorImpl();

        // Stack
        collectionImpl = new StackImpl();

        // EnumSet
        collectionImpl = new EnumSetImpl();

        // HashSet
        collectionImpl = new HashSetImpl();

        // LinkedHashSet
        collectionImpl = new LinkedHashSetImpl();

        // TreeSetImpl
        collectionImpl = new TreeSetImpl();

        // Hash Map
        collectionImpl = new HashMapImpl();

        // Linked HashMap
        collectionImpl = new LinkedHashMapImpl();

        // TreeMap
         collectionImpl = new TreeMapImpl();

         // HashTable
        collectionImpl = new HashTableImpl();

        // WeakHashMap
        collectionImpl = new WeakHashMapImpl();

        // EnumMap
        collectionImpl = new EnumMapImpl();

        // IdentityHashMap
        collectionImpl = new IdentityHashMapImpl();

        // Properties
        collectionImpl = new PropertiesImpl();

        // Queue using LinkedList
        collectionImpl = new QueueLinkedListImpl();

        // Queue using PriorityQueue
        collectionImpl = new QueuePriorityQueueImpl();

        // Deque using LinkedList
        collectionImpl = new DequeLinkedListImpl();

        // Deque using ArrayDeque
        //collectionImpl = new DequeArrayImpl();


        collectionImpl.prepareData();
        collectionImpl.checkNullAllowed();
        collectionImpl.specialFunctions();
        collectionImpl.checkOrder();
        collectionImpl.checkThreadSafety();
        collectionImpl.iterateUsingWhile(null);
        collectionImpl.iterateUsingFor();
        collectionImpl.iterationUsingForEachRemaining();
        collectionImpl.iterateUsingIterableForEach();
        collectionImpl.iterateUsingForEach();
        collectionImpl.iterateUsingStream();
    }
}
