public class ValidMaxHeapChecker {

    public static String checkMaxHeap(int[] arr) {
        int n = arr.length;

        // 只需要檢查到最後一個非葉節點
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 檢查左子節點
            if (left < n && arr[i] < arr[left]) {
                return "Invalid Max Heap at index: " + i;
            }

            // 檢查右子節點
            if (right < n && arr[i] < arr[right]) {
                return "Invalid Max Heap at index: " + i;
            }
        }

        return "Valid Max Heap";
    }

    public static void main(String[] args) {
        int[] arr1 = {100, 50, 30, 20, 40, 10};  // Valid
        int[] arr2 = {100, 50, 30, 20, 110, 10}; // Invalid (110 > 50)

        System.out.println(checkMaxHeap(arr1));
        System.out.println(checkMaxHeap(arr2));
    }
}