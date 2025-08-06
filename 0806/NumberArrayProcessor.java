import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {1, 3, 5, 7};
        int[] arr3 = {2, 2, 3, 3, 3, 4, 5, 5};

        // 1. 移除重複元素
        System.out.println("=== 移除重複元素 ===");
        int[] unique = removeDuplicates(arr1);
        System.out.println(Arrays.toString(unique));

        // 2. 合併兩個已排序的陣列
        System.out.println("=== 合併已排序陣列 ===");
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println(Arrays.toString(merged));

        // 3. 找出出現頻率最高的元素
        System.out.println("=== 出現頻率最高的元素 ===");
        int mostFreq = findMostFrequent(arr3);
        System.out.println("最常出現的數字: " + mostFreq);

        // 4. 陣列分割
        System.out.println("=== 陣列分割 ===");
        int[][] split = splitArray(arr1);
        System.out.println("子陣列1: " + Arrays.toString(split[0]));
        System.out.println("子陣列2: " + Arrays.toString(split[1]));
    }

    // 1. 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    // 2. 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    // 3. 找出陣列中出現頻率最高的元素
    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int mostFreq = arr[0], maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFreq = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFreq;
    }

    // 4. 陣列分割成兩個相等（或近似相等）的子陣列
    public static int[][] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] first = Arrays.copyOfRange(arr, 0, mid);
        int[] second = Arrays.copyOfRange(arr, mid, arr.length);
        return new int[][]{first, second};
    }
}