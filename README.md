# OOP CA4

### CA Part 1

#### Operations + Time Complexities

- ##### ArrayList
   - add: `O(1) or O(1)`
   - get: `O(1)`

- ##### HashMap
   - put: `O(1)`
   - get: `O(1)`

- ##### TreeMap
   - put: `O(log n)`
   - get: `O(log n)`

- ##### PriorityQueue
   - addAll: `O(n log n)` as the initial size of the underlying array was provided.
      - > https://stackoverflow.com/questions/47420638/time-complexity-of-java-priorityqueue-heap-insertion-of-n-elements
   - add: `O(n)`
   - remove: `O(n)`
   - forEach: `O(n)` as it return an iterator.
