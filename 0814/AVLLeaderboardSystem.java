import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        int score;
        String name;
        Node left, right;
        int height;
        int size; // 以此節點為根的子樹大小

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    static class AVLTree {
        private Node root;
        private Map<String, Integer> nameToScore = new HashMap<>();

        // ===== 插入 / 更新分數 =====
        public void addOrUpdate(String name, int score) {
            if (nameToScore.containsKey(name)) {
                int oldScore = nameToScore.get(name);
                root = delete(root, oldScore, name);
            }
            nameToScore.put(name, score);
            root = insert(root, score, name);
        }

        private Node insert(Node node, int score, String name) {
            if (node == null) return new Node(name, score);

            if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
                node.left = insert(node.left, score, name);
            } else {
                node.right = insert(node.right, score, name);
            }

            update(node);
            return balance(node);
        }

        // ===== 刪除節點 =====
        private Node delete(Node node, int score, String name) {
            if (node == null) return null;

            if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
                node.left = delete(node.left, score, name);
            } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
                node.right = delete(node.right, score, name);
            } else {
                if (node.left == null || node.right == null) {
                    return node.left != null ? node.left : node.right;
                } else {
                    Node successor = minNode(node.right);
                    node.name = successor.name;
                    node.score = successor.score;
                    node.right = delete(node.right, successor.score, successor.name);
                }
            }

            update(node);
            return balance(node);
        }

        private Node minNode(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        // ===== 查詢排名 =====
        public int getRank(String name) {
            if (!nameToScore.containsKey(name)) return -1;
            int score = nameToScore.get(name);
            return getRank(root, score, name) + 1; // 排名從 1 開始
        }

        private int getRank(Node node, int score, String name) {
            if (node == null) return 0;

            if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
                return getRank(node.left, score, name);
            } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
                return size(node.left) + 1 + getRank(node.right, score, name);
            } else {
                return size(node.left);
            }
        }

        // ===== 查詢前 K 名玩家 =====
        public List<String> getTopK(int k) {
            List<String> result = new ArrayList<>();
            getTopK(root, result, k);
            return result;
        }

        private void getTopK(Node node, List<String> result, int k) {
            if (node == null || result.size() >= k) return;

            getTopK(node.left, result, k);
            if (result.size() < k) result.add(node.name + " (" + node.score + ")");
            getTopK(node.right, result, k);
        }

        // ===== AVL 輔助函式 =====
        private int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private int size(Node node) {
            return node == null ? 0 : node.size;
        }

        private void update(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }

        private int getBalance(Node node) {
            return height(node.left) - height(node.right);
        }

        private Node balance(Node node) {
            int balance = getBalance(node);

            if (balance > 1 && getBalance(node.left) >= 0)
                return rightRotate(node);
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && getBalance(node.right) <= 0)
                return leftRotate(node);
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            update(y);
            update(x);
            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            update(x);
            update(y);
            return y;
        }
    }

    // ===== 主程式測試 =====
    public static void main(String[] args) {
        AVLTree leaderboard = new AVLTree();
        leaderboard.addOrUpdate("Alice", 90);
        leaderboard.addOrUpdate("Bob", 85);
        leaderboard.addOrUpdate("Charlie", 95);
        leaderboard.addOrUpdate("David", 88);
        leaderboard.addOrUpdate("Eve", 92);

        System.out.println("Alice 的排名: " + leaderboard.getRank("Alice")); // 預期: 第3
        System.out.println("前3名玩家: " + leaderboard.getTopK(3)); // 預期: Charlie, Eve, Alice

        leaderboard.addOrUpdate("Alice", 97); // 更新分數
        System.out.println("更新後 Alice 的排名: " + leaderboard.getRank("Alice")); // 預期: 第1
        System.out.println("前3名玩家: " + leaderboard.getTopK(3));
    }
}