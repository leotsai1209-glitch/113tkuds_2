import java.util.*;

public class TreeReconstruction {
    // 節點類別
    static class Node {
        int value;
        Node left, right;
        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // 測試資料
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        // 1. 前序 + 中序
        Node tree1 = buildTreeFromPreIn(preorder, inorder);
        System.out.print("前序+中序重建後的中序遍歷: ");
        printInOrder(tree1);
        System.out.println();

        // 2. 後序 + 中序
        Node tree2 = buildTreeFromPostIn(postorder, inorder);
        System.out.print("後序+中序重建後的中序遍歷: ");
        printInOrder(tree2);
        System.out.println();

        // 3. 層序（完全二元樹）
        Node tree3 = buildCompleteTreeFromLevel(levelOrder);
        System.out.print("層序重建完全二元樹後的中序遍歷: ");
        printInOrder(tree3);
        System.out.println();

        // 4. 驗證
        System.out.println("tree1 與 tree2 是否結構相同: " + isSameTree(tree1, tree2));
    }

    // ===== 1. 前序 + 中序 =====
    public static Node buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPreInHelper(preorder, 0, preorder.length - 1,
                                inorder, 0, inorder.length - 1, inMap);
    }

    private static Node buildPreInHelper(int[] pre, int ps, int pe,
                                         int[] in, int is, int ie,
                                         Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        Node root = new Node(pre[ps]);
        int inRoot = inMap.get(pre[ps]);
        int leftSize = inRoot - is;
        root.left = buildPreInHelper(pre, ps + 1, ps + leftSize, in, is, inRoot - 1, inMap);
        root.right = buildPreInHelper(pre, ps + leftSize + 1, pe, in, inRoot + 1, ie, inMap);
        return root;
    }

    // ===== 2. 後序 + 中序 =====
    public static Node buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPostInHelper(postorder, 0, postorder.length - 1,
                                 inorder, 0, inorder.length - 1, inMap);
    }

    private static Node buildPostInHelper(int[] post, int ps, int pe,
                                          int[] in, int is, int ie,
                                          Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        Node root = new Node(post[pe]);
        int inRoot = inMap.get(post[pe]);
        int leftSize = inRoot - is;
        root.left = buildPostInHelper(post, ps, ps + leftSize - 1, in, is, inRoot - 1, inMap);
        root.right = buildPostInHelper(post, ps + leftSize, pe - 1, in, inRoot + 1, ie, inMap);
        return root;
    }

    // ===== 3. 層序重建完全二元樹 =====
    public static Node buildCompleteTreeFromLevel(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        Node root = new Node(levelOrder[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < levelOrder.length) {
            Node curr = queue.poll();
            curr.left = new Node(levelOrder[i++]);
            queue.offer(curr.left);
            if (i < levelOrder.length) {
                curr.right = new Node(levelOrder[i++]);
                queue.offer(curr.right);
            }
        }
        return root;
    }

    // ===== 4. 驗證兩棵樹是否相同 =====
    public static boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.value == b.value &&
               isSameTree(a.left, b.left) &&
               isSameTree(a.right, b.right);
    }

    // ===== 中序列印 =====
    public static void printInOrder(Node root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }
}