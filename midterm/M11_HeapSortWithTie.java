import java.util.*;

public class M11_HeapSortWithTie {

    static class Student {
        int score;
        int index;

        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    // MaxHeap 比較：若分數相同，比 index 小的優先
    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1, r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[largest]) > 0) largest = l;
        if (r < n && compare(arr[r], arr[largest]) > 0) largest = r;

        if (largest != i) {
            Student temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    // 比較邏輯：分數高者優先；若相同，比 index 小者優先
    private static int compare(Student a, Student b) {
        if (a.score != b.score) return Integer.compare(a.score, b.score);
        return Integer.compare(b.index, a.index); // index 小的較大，因為 MaxHeap
    }

    public static void heapSort(Student[] arr) {
        int n = arr.length;

        // 建堆（MaxHeap）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 排序
        for (int i = n - 1; i >= 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            students[i] = new Student(score, i);
        }

        heapSort(students);

        for (Student s : students) {
            System.out.print(s.score + " ");
        }
    }
}