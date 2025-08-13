import java.util.*;

public class MovingAverage {
    private int size;
    private Deque<Integer> window;
    private long sum;

    // For median
    private PriorityQueue<Integer> maxHeap; // smaller half
    private PriorityQueue<Integer> minHeap; // larger half

    // For min/max
    private TreeMap<Integer, Integer> multiset; // key: value, value: count

    public MovingAverage(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.multiset = new TreeMap<>();
    }

    public double next(int val) {
        window.offerLast(val);
        sum += val;
        addToHeaps(val);
        multiset.put(val, multiset.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int removed = window.pollFirst();
            sum -= removed;
            removeFromHeaps(removed);
            if (multiset.get(removed) == 1) {
                multiset.remove(removed);
            } else {
                multiset.put(removed, multiset.get(removed) - 1);
            }
        }

        return (double) sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public int getMin() {
        return multiset.firstKey();
    }

    public int getMax() {
        return multiset.lastKey();
    }

    private void addToHeaps(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeFromHeaps(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1)
            minHeap.offer(maxHeap.poll());
        while (minHeap.size() > maxHeap.size())
            maxHeap.offer(minHeap.poll());
    }
}