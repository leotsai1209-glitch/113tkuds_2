import java.util.*;

public class KthSmallestElement {

    public static int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大的
            }
        }

        return maxHeap.peek(); // 第 k 小
    }

    // 測試用主程式
    public static void main(String[] args) {
        int[] arr = { 7, 10, 4, 3, 20, 15 };
        int k = 3;

        int kthSmallest = findKthSmallest(arr, k);
        System.out.println("第 " + k + " 小的元素是: " + kthSmallest);
    }
}