import java.util.*;

public class MergeKSortedArrays {

    // 包裝每個元素與它的陣列與索引位置
    static class Element implements Comparable<Element> {
        int val;       // 數值
        int row;       // 所屬陣列
        int col;       // 所屬陣列內的索引

        public Element(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        public int compareTo(Element other) {
            return Integer.compare(this.val, other.val); // Min Heap
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 初始化：將每個陣列的第一個元素放入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.val);

            // 加入該陣列的下一個元素
            int nextCol = current.col + 1;
            if (nextCol < arrays[current.row].length) {
                minHeap.offer(new Element(arrays[current.row][nextCol], current.row, nextCol));
            }
        }

        return result;
    }

    // 測試用主程式
    public static void main(String[] args) {
        int[][] input1 = { {1, 4, 5}, {1, 3, 4}, {2, 6} };
        int[][] input2 = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        int[][] input3 = { {1}, {0} };

        System.out.println(mergeKSortedArrays(input1)); // [1, 1, 2, 3, 4, 4, 5, 6]
        System.out.println(mergeKSortedArrays(input2)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(mergeKSortedArrays(input3)); // [0, 1]
    }
}