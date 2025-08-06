import java.util.*;

public class BinaryTreeBasicOperations {
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
              5    15
             / \   / \
            2   7 12  20
        */
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(20);

        // 1. 總和與平均值
        int sum = sumNodes(root);
        int count = countNodes(root);
        double avg = (double) sum / count;
        System.out.println("節點總和: " + sum);
        System.out.println("節點平均值: " + avg);

        // 2. 最大值與最小值
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));

        // 3. 樹的寬度
        System.out.println("樹的寬度: " + treeWidth(root));

        // 4. 是否為完全二元樹
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }

    // ===== 1. 計算總和與平均值 =====
    public static int sumNodes(Node root) {
        if (root == null) return 0;
        return root.value + sumNodes(root.left) + sumNodes(root.right);
    }

    public static int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // ===== 2. 找最大值與最小值 =====
    public static int findMax(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.value, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.value, Math.min(findMin(root.left), findMin(root.right)));
    }

    // ===== 3. 計算樹的寬度（BFS） =====
    public static int treeWidth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // ===== 4. 判斷完全二元樹 =====
    public static boolean isCompleteTree(Node root) {
        if (root == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false; // 如果遇到空節點後還有節點，則不是完全二元樹
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}