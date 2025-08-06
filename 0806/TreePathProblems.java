import java.util.*;

public class TreePathProblems {
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
              5    12
             / \     \
            4   7     15
        */
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(12);
        root.left.left = new Node(4);
        root.left.right = new Node(7);
        root.right.right = new Node(15);

        // 1. 所有根到葉路徑
        System.out.println("=== 所有根到葉路徑 ===");
        List<List<Integer>> allPaths = new ArrayList<>();
        findAllPaths(root, new ArrayList<>(), allPaths);
        System.out.println(allPaths);

        // 2. 是否存在和為目標值的根到葉路徑
        int targetSum = 22;
        System.out.println("\n是否存在和為 " + targetSum + " 的根到葉路徑: " + hasPathSum(root, targetSum));

        // 3. 和最大的根到葉路徑
        System.out.println("\n和最大的根到葉路徑: " + maxSumRootToLeafPath(root));

        // 4. 任意兩節點間的最大路徑和
        System.out.println("\n樹的最大路徑和: " + maxPathSum(root));
    }

    // ===== 1. 找出從根節點到所有葉節點的路徑 =====
    public static void findAllPaths(Node root, List<Integer> current, List<List<Integer>> result) {
        if (root == null) return;
        current.add(root.value);
        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(current));
        } else {
            findAllPaths(root.left, current, result);
            findAllPaths(root.right, current, result);
        }
        current.remove(current.size() - 1); // 回溯
    }

    // ===== 2. 判斷是否存在和為目標值的根到葉路徑 =====
    public static boolean hasPathSum(Node root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return target == root.value;
        }
        return hasPathSum(root.left, target - root.value) ||
               hasPathSum(root.right, target - root.value);
    }

    // ===== 3. 找出和最大的根到葉路徑 =====
    public static List<Integer> maxSumRootToLeafPath(Node root) {
        List<Integer> bestPath = new ArrayList<>();
        maxSumRootToLeafHelper(root, new ArrayList<>(), 0, bestPath, new int[]{Integer.MIN_VALUE});
        return bestPath;
    }

    private static void maxSumRootToLeafHelper(Node root, List<Integer> current, int sum,
                                               List<Integer> bestPath, int[] maxSum) {
        if (root == null) return;
        current.add(root.value);
        sum += root.value;
        if (root.left == null && root.right == null) {
            if (sum > maxSum[0]) {
                maxSum[0] = sum;
                bestPath.clear();
                bestPath.addAll(current);
            }
        } else {
            maxSumRootToLeafHelper(root.left, current, sum, bestPath, maxSum);
            maxSumRootToLeafHelper(root.right, current, sum, bestPath, maxSum);
        }
        current.remove(current.size() - 1);
    }

    // ===== 4. 計算任意兩節點間的最大路徑和（樹的直徑類似概念） =====
    public static int maxPathSum(Node root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }

    private static int maxPathSumHelper(Node root, int[] maxSum) {
        if (root == null) return 0;
        int leftGain = Math.max(0, maxPathSumHelper(root.left, maxSum));
        int rightGain = Math.max(0, maxPathSumHelper(root.right, maxSum));
        maxSum[0] = Math.max(maxSum[0], leftGain + rightGain + root.value);
        return Math.max(leftGain, rightGain) + root.value;
    }
}