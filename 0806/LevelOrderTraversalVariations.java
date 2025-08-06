import java.util.*;

public class LevelOrderTraversalVariations {
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
                1
               / \
              2   3
             / \   \
            4   5   6
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // 1. 每層儲存在不同 List 中
        System.out.println("=== 每層分開儲存 ===");
        System.out.println(levelOrderToLists(root));

        // 2. 之字形層序走訪
        System.out.println("\n=== 之字形層序走訪 ===");
        System.out.println(zigzagLevelOrder(root));

        // 3. 每層最後一個節點
        System.out.println("\n=== 每層最後一個節點 ===");
        printLastNodeEachLevel(root);

        // 4. 垂直層序走訪
        System.out.println("\n=== 垂直層序走訪 ===");
        System.out.println(verticalOrder(root));
    }

    // ===== 1. 每層儲存在不同 List 中 =====
    public static List<List<Integer>> levelOrderToLists(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.value);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // ===== 2. 之字形層序走訪 =====
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (leftToRight) {
                    level.addLast(node.value);
                } else {
                    level.addFirst(node.value);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // ===== 3. 每層最後一個節點 =====
    public static void printLastNodeEachLevel(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node lastNode = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                lastNode = node;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println(lastNode.value);
        }
    }

    // ===== 4. 垂直層序走訪 =====
    public static Map<Integer, List<Integer>> verticalOrder(Node root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        if (root == null) return map;

        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        queue.offer(root);
        cols.offer(0);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int col = cols.poll();
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.value);

            if (node.left != null) {
                queue.offer(node.left);
                cols.offer(col - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                cols.offer(col + 1);
            }
        }
        return map;
    }
}