import java.util.*;

public class MultiLevelCacheSystem {
    class Node {
        int key;
        String value;
        int freq;
        long time;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.time = System.nanoTime();
        }

        void accessed() {
            this.freq++;
            this.time = System.nanoTime();
        }
    }

    class CacheLevel {
        int capacity;
        int cost;
        Map<Integer, Node> map;
        PriorityQueue<Node> heap;

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>(
                (a, b) -> a.freq == b.freq ? Long.compare(a.time, b.time) : Integer.compare(a.freq, b.freq)
            );
        }

        boolean contains(int key) {
            return map.containsKey(key);
        }

        Node get(int key) {
            Node node = map.get(key);
            if (node != null) {
                heap.remove(node);
                node.accessed();
                heap.offer(node);
            }
            return node;
        }

        void put(Node node) {
            if (map.size() >= capacity) {
                evict();
            }
            map.put(node.key, node);
            heap.offer(node);
        }

        void remove(int key) {
            Node node = map.remove(key);
            if (node != null) {
                heap.remove(node);
            }
        }

        Node evict() {
            Node toRemove = heap.poll();
            if (toRemove != null) {
                map.remove(toRemove.key);
            }
            return toRemove;
        }
    }

    private CacheLevel L1, L2, L3;

    public MultiLevelCacheSystem() {
        L1 = new CacheLevel(2, 1);
        L2 = new CacheLevel(5, 3);
        L3 = new CacheLevel(10, 10);
    }

    public String get(int key) {
        if (L1.contains(key)) {
            return L1.get(key).value;
        } else if (L2.contains(key)) {
            Node node = L2.get(key);
            L2.remove(key);
            moveUp(node, L1);  // move to L1
            return node.value;
        } else if (L3.contains(key)) {
            Node node = L3.get(key);
            L3.remove(key);
            moveUp(node, L2);  // move to L2
            return node.value;
        } else {
            return null;
        }
    }

    public void put(int key, String value) {
        // 若已存在，直接更新 value 並提升位置
        if (L1.contains(key)) {
            Node node = L1.get(key);
            node.value = value;
        } else if (L2.contains(key)) {
            Node node = L2.get(key);
            L2.remove(key);
            node.value = value;
            moveUp(node, L1);
        } else if (L3.contains(key)) {
            Node node = L3.get(key);
            L3.remove(key);
            node.value = value;
            moveUp(node, L2);
        } else {
            Node node = new Node(key, value);
            insertNew(node);
        }
    }

    private void insertNew(Node node) {
        if (L1.map.size() < L1.capacity) {
            L1.put(node);
        } else if (L2.map.size() < L2.capacity) {
            L2.put(node);
        } else {
            if (L3.map.size() >= L3.capacity) {
                L3.evict();
            }
            L3.put(node);
        }
    }

    private void moveUp(Node node, CacheLevel target) {
        if (target.map.size() >= target.capacity) {
            Node evicted = target.evict();
            // 移到下一層
            if (target == L1) {
                L2.put(evicted);
            } else if (target == L2) {
                L3.put(evicted);
            }
        }
        target.put(node);
    }

    public void printState() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printState();

        cache.get(1); cache.get(1); cache.get(2);
        cache.printState();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printState();
    }
}