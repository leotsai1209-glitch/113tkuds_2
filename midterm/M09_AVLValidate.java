import java.util.*;

public class M09_AVLValidate {

    // 定義節點結構
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 建立樹（層序建樹，-1 表 null）
    public static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;

        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < vals.length) {
            TreeNode curr = queue.poll();

            if (i < vals.length && vals[i] != -1) {
                curr.left = new TreeNode(vals[i]);
                queue.offer(curr.left);
            }
            i++;

            if (i < vals.length && vals[i] != -1) {
                curr.right = new TreeNode(vals[i]);
                queue.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    // 檢查 BST 規則（帶上下界）
    public static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // 回傳高度；若不平衡則回傳 -1
    public static int checkAVL(TreeNode node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        int rh = checkAVL(node.right);

        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = sc.nextInt();

        TreeNode root = buildTree(vals);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }
}