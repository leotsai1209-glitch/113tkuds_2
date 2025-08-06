import java.util.*;

public class BSTKthElement {
    // 節點類別（包含子樹節點數，用於快速查詢）
    static class Node {
        int value;
        Node left, right;
        int size; // 當前節點為根的子樹節點數

        Node(int value) {
            this.value = value;
            this.size = 1;
        }
    }

    public static void main(String[] args) {
        /*
                15
               /  \
              10   20
             / \   / \
            8  12 17  25
        */
        Node root = null;
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        for (int v : values) {
            root = insert(root, v);
        }

        // 1. 第 k 小
        System.out.println("第 3 小元素: " + kthSmallest(root, 3));

        // 2. 第 k 大
        System.out.println("第 2 大元素: " + kthLargest(root, 2));

        // 3. 第 k 小到第 j 小的所有元素
        System.out.println("第 2 小到第 5 小元素: " + rangeKth(root, 2, 5));

        // 4. 動態插入刪除後的第 k 小
        root = insert(root, 5);
        root = delete(root, 10);
        System.out.println("插入 5 並刪除 10 後，第 3 小元素: " + kthSmallest(root, 3));
    }

    // ===== 工具：更新節點大小 =====
    public static int getSize(Node root) {
        return root == null ? 0 : root.size;
    }

    public static void updateSize(Node root) {
        if (root != null) {
            root.size = 1 + getSize(root.left) + getSize(root.right);
        }
    }

    // ===== 插入 =====
    public static Node insert(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.value) root.left = insert(root.left, value);
        else if (value > root.value) root.right = insert(root.right, value);
        updateSize(root);
        return root;
    }

    // ===== 刪除 =====
    public static Node delete(Node root, int value) {
        if (root == null) return null;
        if (value < root.value) root.left = delete(root.left, value);
        else if (value > root.value) root.right = delete(root.right, value);
        else {
            // 找到節點
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node successor = minNode(root.right);
            root.value = successor.value;
            root.right = delete(root.right, successor.value);
        }
        updateSize(root);
        return root;
    }

    public static Node minNode(Node root) {
        while (root.left != null) root = root.left;
        return root;
    }

    // ===== 1. 第 k 小 =====
    public static int kthSmallest(Node root, int k) {
        if (root == null) return -1;
        int leftSize = getSize(root.left);
        if (k <= leftSize) return kthSmallest(root.left, k);
        else if (k == leftSize + 1) return root.value;
        else return kthSmallest(root.right, k - leftSize - 1);
    }

    // ===== 2. 第 k 大 =====
    public static int kthLargest(Node root, int k) {
        int rightSize = getSize(root.right);
        if (k <= rightSize) return kthLargest(root.right, k);
        else if (k == rightSize + 1) return root.value;
        else return kthLargest(root.left, k - rightSize - 1);
    }

    // ===== 3. 第 k 小到第 j 小 =====
    public static List<Integer> rangeKth(Node root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        for (int i = k; i <= j; i++) {
            result.add(kthSmallest(root, i));
        }
        return result;
    }
}