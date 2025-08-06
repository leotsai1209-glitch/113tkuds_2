import java.util.*;

public class MultiWayTreeAndDecisionTree {
    // ===== 多路樹節點 =====
    static class MultiNode {
        String value;
        List<MultiNode> children = new ArrayList<>();
        MultiNode(String value) {
            this.value = value;
        }
        void addChild(MultiNode child) {
            children.add(child);
        }
    }

    public static void main(String[] args) {
        // 建立多路樹
        MultiNode root = new MultiNode("A");
        MultiNode b = new MultiNode("B");
        MultiNode c = new MultiNode("C");
        MultiNode d = new MultiNode("D");
        root.addChild(b);
        root.addChild(c);
        root.addChild(d);

        b.addChild(new MultiNode("E"));
        b.addChild(new MultiNode("F"));

        c.addChild(new MultiNode("G"));

        d.addChild(new MultiNode("H"));
        d.addChild(new MultiNode("I"));
        d.addChild(new MultiNode("J"));

        // 1. 深度優先走訪
        System.out.println("=== 多路樹 DFS ===");
        dfs(root);

        // 2. 廣度優先走訪
        System.out.println("\n=== 多路樹 BFS ===");
        bfs(root);

        // 3. 簡單決策樹（猜數字遊戲示例）
        System.out.println("\n=== 決策樹（猜數字遊戲） ===");
        DecisionNode start = new DecisionNode("你猜的數字大於 5 嗎？");
        start.yes = new DecisionNode("大於 8 嗎？");
        start.no = new DecisionNode("小於 3 嗎？");

        start.yes.yes = new DecisionNode("你的數字是 9！");
        start.yes.no = new DecisionNode("你的數字是 7！");

        start.no.yes = new DecisionNode("你的數字是 2！");
        start.no.no = new DecisionNode("你的數字是 4！");

        playDecisionTree(start, new Scanner(System.in));

        // 4. 計算高度與度數
        System.out.println("\n多路樹高度: " + getHeight(root));
        System.out.println("節點度數: ");
        printDegree(root);
    }

    // ===== 1. 深度優先走訪（DFS） =====
    public static void dfs(MultiNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiNode child : node.children) {
            dfs(child);
        }
    }

    // ===== 2. 廣度優先走訪（BFS） =====
    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiNode current = queue.poll();
            System.out.print(current.value + " ");
            for (MultiNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // ===== 3. 簡單決策樹 =====
    static class DecisionNode {
        String question;
        DecisionNode yes;
        DecisionNode no;
        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void playDecisionTree(DecisionNode node, Scanner sc) {
        if (node.yes == null && node.no == null) {
            System.out.println(node.question);
            return;
        }
        System.out.print(node.question + " (y/n): ");
        String ans = sc.nextLine().trim().toLowerCase();
        if (ans.equals("y")) {
            playDecisionTree(node.yes, sc);
        } else {
            playDecisionTree(node.no, sc);
        }
    }

    // ===== 4. 多路樹高度 =====
    public static int getHeight(MultiNode node) {
        if (node == null) return 0;
        int height = 0;
        for (MultiNode child : node.children) {
            height = Math.max(height, getHeight(child));
        }
        return height + 1;
    }

    // ===== 4. 每個節點的度數 =====
    public static void printDegree(MultiNode node) {
        if (node == null) return;
        System.out.println(node.value + ": " + node.children.size());
        for (MultiNode child : node.children) {
            printDegree(child);
        }
    }
}