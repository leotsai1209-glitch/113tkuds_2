import java.util.*;

public class AVLRangeQueryExercise {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    static class AVLTree {
        Node root;

        // ===== 插入節點 =====
        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null) return new Node(key);
            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node; // 重複不處理

            node.height = 1 + Math.max(height(node.left), height(node.right));
            return balance(node);
        }

        // ===== 範圍查詢 =====
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            inOrderRange(root, min, max, result);
            return result;
        }

        private void inOrderRange(Node node, int min, int max, List<Integer> result) {
            if (node == null) return;

            // 剪枝：左子樹可能有解才走訪
            if (node.key > min) {
                inOrderRange(node.left, min, max, result);
            }

            // 範圍內才加入
            if (node.key >= min && node.key <= max) {
                result.add(node.key);
            }

            // 剪枝：右子樹可能有解才走訪
            if (node.key < max) {
                inOrderRange(node.right, min, max, result);
            }
        }

        // ===== 基本 AVL 函式 =====
        private int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private int getBalance(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = 1 + Math.max(height(y.left), height(y.right));
            x.height = 1 + Math.max(height(x.left), height(x.right));

            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = 1 + Math.max(height(x.left), height(x.right));
            y.height = 1 + Math.max(height(y.left), height(y.right));

            return y;
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
    }

    // ===== 主程式測試 =====
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] keys = {10, 5, 1, 7, 15, 12, 20, 30};
        for (int key : keys) {
            tree.insert(key);
        }

        int min = 6, max = 20;
        List<Integer> result = tree.rangeQuery(min, max);
        System.out.println("在 [" + min + ", " + max + "] 範圍內的節點：");
        System.out.println(result);  // 期望輸出：[7, 10, 12, 15, 20]
    }
}