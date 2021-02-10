package misc;

import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;
import datastructures.priorityqueues.ArrayHeapPriorityQueue;
import datastructures.priorityqueues.IPriorityQueue;

public class Sorter {
    /**
     * This method takes the input list and returns the greatest `k` elements in sorted order, from
     * least to greatest.
     *
     * If the input list contains fewer than `k` elements, return a list containing all
     * `input.length` elements in sorted order.
     *
     * Precondition: `input` does not contain `null`s or duplicate values (according to `equals`)
     * Postcondition: the input list has not been mutated
     *
     * @throws IllegalArgumentException  if k < 0
     * @throws IllegalArgumentException  if input is null
     */
    public static <T extends Comparable<T>> IList<T> topKSort(int k, IList<T> input) {
        /*
        Implementation notes:

        - This static method is a *generic method*. A generic method is similar to a generic class,
          except that the generic parameter is used only within this method.

          You can implement a generic method in basically the same way you implement generic
          classes: just use the `T` generic type as if it were a regular type.

        - You should implement this method by using your `ArrayHeapPriorityQueue` in order to
          achieve O(n log k) runtime.
        */
        if (k < 0 || input == null) {
            throw new IllegalArgumentException();
        }
        if (k == 0) {
            return new DoubleLinkedList<>();
        }
        IPriorityQueue<T> heap = new ArrayHeapPriorityQueue<T>();
        IList<T> result = new DoubleLinkedList<T>();
        for (T item : input) {
            if (item == null || heap.contains(item)) {
                throw new IllegalArgumentException();
            }
            if (heap.size() < k) {
                heap.add(item);
            } else if (item.compareTo(heap.peekMin()) > 0) {
                heap.removeMin();
                heap.add(item);
            }
        }
        while (!heap.isEmpty()) {
            result.add(heap.removeMin());
        }
        return result;
    }
}
