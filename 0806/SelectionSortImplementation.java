import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        System.out.println("=== 選擇排序 ===");
        selectionSort(arr.clone()); // 用 clone 保護原陣列

        System.out.println("\n=== 氣泡排序 ===");
        bubbleSort(arr.clone());
    }

    // 選擇排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // 找最小值
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 交換
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);
    }

    // 氣泡排序（用來比較效能）
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);
    }
}