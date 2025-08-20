import java.util.*;

public class M08_BSTRangedSum {

    // 定義樹節點結構
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 建立 BST（以層序，-1 表 null）
    public static TreeNode buildTree(int[] values) {
        if (values.length == 0 || values[0] == -1) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode curr = queue.poll();

            if (i < values.length && values[i] != -1) {
                curr.left = new TreeNode(values[i]);
                queue.offer(curr.left);
            }
            i++;

            if (i < values.length && values[i] != -1) {
                curr.right = new TreeNode(values[i]);
                queue.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    // BST 範圍總和：只走必要子樹
    public static int rangeSum(TreeNode root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L) {
            return rangeSum(root.right, L, R); // 太小，往右走
        } else if (root.val > R) {
            return rangeSum(root.left, L, R); // 太大，往左走
        } else {
            // 在區間內，加上自己 + 左子樹 + 右子樹
            return root.val + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();          // 節點數量
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = sc.nextInt();

        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(vals);
        int sum = rangeSum(root, L, R);
        System.out.println("Sum: " + sum);
    }
}