import java.util.*;

public class BSTValidationAndRepair {
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
                10
               /  \
              5    8   <-- 8 放錯了，應該比 10 大
             / \
            2   20  <-- 20 放錯了，應該比 10 大
        */
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(20);

        // 1. 驗證是否為 BST
        System.out.println("是否為 BST: " + isValidBST(root));

        // 2. 找出不符合規則的節點
        System.out.println("不符合規則的節點: " + findInvalidNodes(root));

        // 3. 修復兩個節點錯誤的 BST
        Node root2 = new Node(10);
        root2.left = new Node(5);
        root2.right = new Node(8);
        root2.left.left = new Node(2);
        root2.left.right = new Node(20);
        fixSwappedNodes(root2);
        System.out.println("修復後是否為 BST: " + isValidBST(root2));

        // 4. 計算最少需要移除幾個節點才能變成 BST
        System.out.println("需要移除的節點數: " + minRemoveToBST(root));
    }

    // ===== 1. 驗證 BST =====
    public static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validate(node.left, min, node.value) &&
               validate(node.right, node.value, max);
    }

    // ===== 2. 找出不符合規則的節點（中序遍歷檢查） =====
    public static List<Integer> findInvalidNodes(Node root) {
        List<Integer> invalid = new ArrayList<>();
        findInvalidHelper(root, Long.MIN_VALUE, Long.MAX_VALUE, invalid);
        return invalid;
    }

    private static void findInvalidHelper(Node node, long min, long max, List<Integer> invalid) {
        if (node == null) return;
        if (node.value <= min || node.value >= max) {
            invalid.add(node.value);
        }
        findInvalidHelper(node.left, min, node.value, invalid);
        findInvalidHelper(node.right, node.value, max, invalid);
    }

    // ===== 3. 修復兩個節點錯誤的 BST =====
    static Node first, second, prev;
    public static void fixSwappedNodes(Node root) {
        first = second = prev = null;
        inorderDetect(root);
        if (first != null && second != null) {
            int temp = first.value;
            first.value = second.value;
            second.value = temp;
        }
    }

    private static void inorderDetect(Node node) {
        if (node == null) return;
        inorderDetect(node.left);
        if (prev != null && node.value < prev.value) {
            if (first == null) first = prev;
            second = node;
        }
        prev = node;
        inorderDetect(node.right);
    }

    // ===== 4. 計算最少移除節點數 =====
    public static int minRemoveToBST(Node root) {
        List<Integer> values = new ArrayList<>();
        inorderCollect(root, values);
        return values.size() - longestIncreasingSubsequence(values);
    }

    private static void inorderCollect(Node node, List<Integer> list) {
        if (node == null) return;
        inorderCollect(node.left, list);
        list.add(node.value);
        inorderCollect(node.right, list);
    }

    // 最長遞增子序列 (LIS)
    private static int longestIncreasingSubsequence(List<Integer> nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) pos = -(pos + 1);
            if (pos >= lis.size()) lis.add(num);
            else lis.set(pos, num);
        }
        return lis.size();
    }
}