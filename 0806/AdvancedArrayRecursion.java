import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};

        // 1. 快速排序
        System.out.println("=== 快速排序 ===");
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        // 2. 遞迴合併兩個已排序陣列
        System.out.println("=== 遞迴合併已排序陣列 ===");
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeSortedArrays(sorted1, sorted2, 0, 0, new int[sorted1.length + sorted2.length], 0);
        System.out.println(Arrays.toString(merged));

        // 3. 遞迴找第 k 小元素
        System.out.println("=== 第 3 小元素 ===");
        int[] arr2 = {7, 10, 4, 3, 20, 15};
        System.out.println(findKthSmallest(arr2, 0, arr2.length - 1, 3));

        // 4. 遞迴檢查子序列總和
        System.out.println("=== 子序列總和檢查 ===");
        int[] nums = {2, 4, 8};
        int target = 6;
        System.out.println(hasSubsequenceSum(nums, target, 0));
    }

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);
        quickSort(arr, left, index - 1);
        quickSort(arr, index, right);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    // 2. 遞迴合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2, int i, int j, int[] result, int k) {
        if (i == arr1.length && j == arr2.length) return result;
        if (i == arr1.length) {
            result[k] = arr2[j];
            return mergeSortedArrays(arr1, arr2, i, j + 1, result, k + 1);
        }
        if (j == arr2.length) {
            result[k] = arr1[i];
            return mergeSortedArrays(arr1, arr2, i + 1, j, result, k + 1);
        }
        if (arr1[i] <= arr2[j]) {
            result[k] = arr1[i];
            return mergeSortedArrays(arr1, arr2, i + 1, j, result, k + 1);
        } else {
            result[k] = arr2[j];
            return mergeSortedArrays(arr1, arr2, i, j + 1, result, k + 1);
        }
    }

    // 3. 遞迴尋找陣列中的第 k 小元素（快速選擇法）
    public static int findKthSmallest(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);
        int count = index - left;
        if (k <= count) {
            return findKthSmallest(arr, left, index - 1, k);
        } else {
            return findKthSmallest(arr, index, right, k - count);
        }
    }

    // 4. 遞迴檢查陣列是否存在子序列總和等於目標值
    public static boolean hasSubsequenceSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        // 選這個數 或 不選這個數
        return hasSubsequenceSum(arr, target - arr[index], index + 1) ||
               hasSubsequenceSum(arr, target, index + 1);
    }
}