import java.util.*;

public class BSTRangeQuerySystem {
    // 節點類別
    static class Node {
        int value;
        Node left, right;
        Node(int value) {
            this.value = value;
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
        Node root = new Node(15);
        insert(root, 10);
        insert(root, 20);
        insert(root, 8);
        insert(root, 12);
        insert(root, 17);
        insert(root, 25);

        int min = 10, max = 20;

        // 1. 範圍查詢
        System.out.println("=== 範圍查詢 [" + min + ", " + max + "] ===");
        List<Integer> rangeNodes = new ArrayList<>();
        rangeQuery(root, min, max, rangeNodes);
        System.out.println(rangeNodes);

        // 2. 範圍計數
        System.out.println("範圍計數: " + rangeCount(root, min, max));

        // 3. 範圍總和
        System.out.println("範圍總和: " + rangeSum(root, min, max));

        // 4. 最接近查詢
        int target = 18;
        System.out.println("最接近 " + target + " 的節點值: " + closestValue(root, target));
    }

    // ===== 建立 BST =====
    public static Node insert(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.value) root.left = insert(root.left, value);
        else if (value > root.value) root.right = insert(root.right, value);
        return root;
    }

    // ===== 1. 範圍查詢 =====
    public static void rangeQuery(Node root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.value > min) rangeQuery(root.left, min, max, result);
        if (root.value >= min && root.value <= max) result.add(root.value);
        if (root.value < max) rangeQuery(root.right, min, max, result);
    }

    // ===== 2. 範圍計數 =====
    public static int rangeCount(Node root, int min, int max) {
        if (root == null) return 0;
        if (root.value < min) return rangeCount(root.right, min, max);
        if (root.value > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // ===== 3. 範圍總和 =====
    public static int rangeSum(Node root, int min, int max) {
        if (root == null) return 0;
        if (root.value < min) return rangeSum(root.right, min, max);
        if (root.value > max) return rangeSum(root.left, min, max);
        return root.value + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // ===== 4. 最接近查詢 =====
    public static int closestValue(Node root, int target) {
        int closest = root.value;
        while (root != null) {
            if (Math.abs(root.value - target) < Math.abs(closest - target)) {
                closest = root.value;
            }
            if (target < root.value) {
                root = root.left;
            } else if (target > root.value) {
                root = root.right;
            } else {
                break; // 精確匹配
            }
        }
        return closest;
    }
}