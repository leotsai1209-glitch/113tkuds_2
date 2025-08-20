import java.util.*;

public class M07_BinaryTreeLeftView {

    // 定義樹節點結構
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 用層序建立樹，輸入 -1 表示 null
    public static TreeNode buildTree(int[] values) {
        if (values.length == 0 || values[0] == -1) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode curr = queue.poll();

            // 加左子節點
            if (i < values.length && values[i] != -1) {
                curr.left = new TreeNode(values[i]);
                queue.offer(curr.left);
            }
            i++;

            // 加右子節點
            if (i < values.length && values[i] != -1) {
                curr.right = new TreeNode(values[i]);
                queue.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    // 輸出左視圖（每層最左邊節點）
    public static void printLeftView(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        System.out.print("LeftView:");

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) System.out.print(" " + curr.val);  // 第一個就是左視圖

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 節點總數
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt(); // 讀取層序序列，-1 表 null
        }

        TreeNode root = buildTree(vals);
        printLeftView(root);
    }
}