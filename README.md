# Java Collections

Implementation of different java collections so as to have better understanding of them.

###### Interfaces in Collections framework:

- Collection
    - extends Iterable

- List: extends Collection
    - Ordered collection of objects.

- Set: extends Collection
    - Set -> Sorted Set -> Navigable Set
    - Unique elements. (Ignores if same element is added again)
    - Unordered collection of objects.
    - Some com.deltacap019.set implementations order elements.

- Map
    - Set of "Key" "Value" pairs.
    - Map -> Sorted Map -> Navigable Map
    - Only Java objects can be used as keys and values in a Java Map. If primitive type is provided it is Auto-Boxed.
    - "getOrDefault()" api: returns default value if key is not available
    - "replace()" api: only insert the new value if there is already an existing value mapped to the key

- Stack: extends Vector

- Queue: extends Collection
    - Queue -> Dequeue

- Dequeue: extends Queue

- Iterator
    - Iterator over collection, they differ from *Enumeration*
    - Represents component that is capable of iterating a Java collections.

- Iterable
  - Implementing this interface allows an object to be the target of the "for-each loop" statement.
  - Similar to iterator in responsibility.
  - Allows java collection to be iterated using For-Each loop.


###### Summary of Collections

- **Linked List** 
    - implements Deque which itself implements Queue hence it contains methods like peek, poll etc.
    - **getFirst()** vs **peekFirst()** and **getLast()** vs **peekLast()**
        - "**get**" can throw exception if collection is empty but "**peek**" does not.
    - **removeFirst()** vs **pollFirst** and **removeLast()** vs **pollLast()**
        - "remove" throws and exception if collection is empty but "poll" does not.
    - **addFirst()** vs **offerFirst()** and **addLast()** vs **offerLast()**
        - add throws an exception of fails to insert element, but "offer" does not it silently fails.

- **ArrayList**:
    - increments 50% of existing size.
    - fast as it is not synchronised.
    - uses iterator to iterate elements.
    - better for storing and accessing data as it has to shift remaining elements on removal of element,as internally it uses dynamic array.
- **Vector**:
    - Legacy class
    - increments 100% of existing size.
    - Is synchronised but still can give "concurrent modification exception"
    - can use enumeration or iterator to iterate its elements.
    
- **Stack**: 
    - extends Vector.
    
- **LinkedList**:
    - can be used as both "list" and "queue" as it implements both interfaces.
    - better for manipulation of data as it needs not to shift elements on removal of item.

- **EnumSet**:
    - specialized Set implementation for use with "Enum types".
    - cannot contain null.
    - duplicate items cannot be added.

- **HashSet**:
    - can contain null
    - duplicate items cannot be added.
    - order not maintained.
    - Order changes when some manipulation (insert or remove) happens to set.

- **LinkedHashSet**:
    - maintains insertion order as it is based on linked list.

- **NavigableSet**:
    - it's an interface, hence cannot be used directly, custom implementation is required.
    - cannot contain null

- **SortedSet**:
    - it's an interface, hence cannot be used directly, custom implementation is required.
    - cannot contain null

- **TreeSet**: <- **NavigableSet** <- **SortedSet** <- **Set** <- **Collection** <- **Iterable**
    - fast retrieval.
    - maintains ascending order
    - cannot contain null.
    - Not synchronised: along with "ConcurrentModification" Exception it will also raise "NullPointer" Exception as for TreeMap one thread
        remains busy in maintaining the structure of tree.
    - Split iterator supports parallel programming.

- **HashMap**:
    - Does not implement Collections
    - cannot be traversed so we need to get "EntrySet" or "KeySet" which is a set and we can get "iterator" from the set.
    - Allow null keys.
    - Not synchronised.
    - Can be made synchronised using Collections.synchronizedMap(hashMap)

- **LinkedHashMap**:
    - Allow null keys.
    - Maintains insertion order as it is based on linked list.

- **TreeMap**:
    - Does not allow null keys/value.
    - Not synchronised: along with "ConcurrentModification" Exception it will also raise "NullPointer" Exception as for TreeMap one thread
              remains busy in maintaining the structure of tree.

- **HashTable**:
    - synchronised
    - inherits Dictionary class
    - can be traversed by "enumerator" or "iterator".
    - does not allow null key/value.
    
- **WeakHashMap**:
    - not synchronised.
    - contains weak references of the Keys so that GarbageCollector can collect it anytime.
   
- **EnumMap**
    - not synchronised.
    - cannot have null key as we have ENUMS as keys.
    
- **IdentityHashMapImpl**
    - uses "==" to compare the keys and values.
    - Ex. for this "KEY" and new String ("KEY") are different values as it uses "==" to compare them.
    - for HashMap both will be considered as one and will update values of each other based on which comes later.

- **PropertiesImpl**
    - subclass of HashTable
    - used for saving data which changes very frequently.
    - HashTable sub class hence does not allow null key/value.

- **Queue using LinkedList**
    - nulls allowed 
    - maintains insertion order
    
- **PriorityQueue**
    - nulls not allowed.
    - with iterator order is not guaranteed
    - if we peek() or poll() than we get elements in Lexicographical or based on Comparator provided to it.

- **Deque using Linked List**
    - nulls not allowed
    - not synchronised
    - maintains insertion order
    
- **Deque using ArrayDeque**
    - nulls not allowed
    - not synchronised
    - same as Deque linked list implementation, but it uses array under the hood ans hence amortised time is faster than linked list implementation.
    
    
###### Package Implementation Order

### 1. List
- ArrayListImpl
- LinkedListImpl
- VectorImpl
- StackImpl

### 2. Set
- EnumSetImpl
- HashSetImpl
- LinkedHashSetImpl
- TreeSetImpl
- SortedSetImpl
- NavigableSetImpl

### 3. Map
- HashMapImpl
- HashTableImpl
- EnumMapImpl
- IdentityHashMapImpl
- LinkedHashMapImpl
- PropertiesImpl
- TreeMapImpl
- WeakHashMapImpl
- SortedMapImpl
- NavigableMapImpl

### 4. Queue
- QueueLinkedListImpl
- QueuePriorityQueueImpl
- DequeLinkedListImpl
- DequeArrayImpl

### 5. Others


# Pending

- Custom Comparator and Iterator.
- Collections class functions implementation.
- Priority Queue using Comparator

