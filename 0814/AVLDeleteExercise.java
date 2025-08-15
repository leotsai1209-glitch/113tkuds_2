public class AVLDeleteExercise {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    // ===== 基本工具 =====
    static int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    static int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // ===== 插入操作 =====
    static Node insert(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    // ===== 刪除操作 =====
    static Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // 1. 葉節點或只有一個子節點
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;
                root = temp;
            } else {
                // 2. 兩個子節點，找後繼節點替代
                Node successor = getMinValueNode(root.right);
                root.key = successor.key;
                root.right = delete(root.right, successor.key);
            }
        }

        if (root == null) return null;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        return balance(root);
    }

    static Node getMinValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // ===== AVL 平衡邏輯 =====
    static Node balance(Node node) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ===== 前序輸出 =====
    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // ===== 主程式測試 =====
    public static void main(String[] args) {
        Node root = null;

        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys)
            root = insert(root, key);

        System.out.print("原始樹 (前序)：");
        preOrder(root);
        System.out.println();

        System.out.println("刪除葉節點 5：");
        root = delete(root, 5);
        preOrder(root);
        System.out.println();

        System.out.println("刪除單子節點 25：");
        root = delete(root, 25);
        preOrder(root);
        System.out.println();

        System.out.println("刪除雙子節點 10：");
        root = delete(root, 10);
        preOrder(root);
        System.out.println();
    }
}