// 題目：自底向上建堆（MaxHeap 或 MinHeap）
// 時間複雜度：O(n)，因為對每個非葉節點做 heapify，平均次數遞減（由底部往上）

import java.util.*;

public class M01_BuildHeap {
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, type);
        }
    }

    private static void heapify(int[] arr, int n, int i, String type) {
        int extreme = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (type.equals("max")) {
            if (l < n && arr[l] > arr[extreme]) extreme = l;
            if (r < n && arr[r] > arr[extreme]) extreme = r;
        } else {
            if (l < n && arr[l] < arr[extreme]) extreme = l;
            if (r < n && arr[r] < arr[extreme]) extreme = r;
        }

        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            heapify(arr, n, extreme, type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();       // 輸入 max 或 min
        int n = sc.nextInt();          // 輸入元素數量
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        buildHeap(arr, type);
        for (int x : arr) System.out.print(x + " ");
    }
}