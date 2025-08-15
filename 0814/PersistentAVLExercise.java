import java.util.*;

public class PersistentAVLExercise {

    // ==== 不可變節點設計 ====
    static class Node {
        final int val;
        final Node left, right;
        final int height;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        private static int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private int balanceFactor() {
            return height(left) - height(right);
        }
    }

    // ==== 核心 Persistent AVL Tree 類 ====
    static class PersistentAVL {
        private List<Node> versions = new ArrayList<>();

        public PersistentAVL() {
            versions.add(null); // 初始空樹為第0版
        }

        // 插入操作：傳回新版本編號
        public int insert(int version, int val) {
            Node newRoot = insert(versions.get(version), val);
            versions.add(newRoot);
            return versions.size() - 1;
        }

        // 查詢指定版本是否含有某值
        public boolean contains(int version, int val) {
            return contains(versions.get(version), val);
        }

        // 取得指定版本的根節點（可作 traversal）
        public Node getVersionRoot(int version) {
            return versions.get(version);
        }

        // 中序列印
        public void inOrderPrint(int version) {
            System.out.print("版本 " + version + "：");
            inOrder(versions.get(version));
            System.out.println();
        }

        // ==== AVL 插入邏輯 + 路徑複製 ====
        private Node insert(Node node, int val) {
            if (node == null) return new Node(val, null, null);

            if (val < node.val) {
                Node newLeft = insert(node.left, val);
                return balance(new Node(node.val, newLeft, node.right));
            } else if (val > node.val) {
                Node newRight = insert(node.right, val);
                return balance(new Node(node.val, node.left, newRight));
            } else {
                return node; // 值已存在，不插入
            }
        }

        private boolean contains(Node node, int val) {
            if (node == null) return false;
            if (val < node.val) return contains(node.left, val);
            if (val > node.val) return contains(node.right, val);
            return true;
        }

        private void inOrder(Node node) {
            if (node == null) return;
            inOrder(node.left);
            System.out.print(node.val + " ");
            inOrder(node.right);
        }

        // ==== AVL 平衡處理 ====
        private Node balance(Node node) {
            int bf = node.balanceFactor();

            if (bf > 1 && node.left != null && node.left.balanceFactor() >= 0)
                return rightRotate(node);
            if (bf > 1 && node.left != null && node.left.balanceFactor() < 0)
                return rightRotate(new Node(node.val, leftRotate(node.left), node.right));
            if (bf < -1 && node.right != null && node.right.balanceFactor() <= 0)
                return leftRotate(node);
            if (bf < -1 && node.right != null && node.right.balanceFactor() > 0)
                return leftRotate(new Node(node.val, node.left, rightRotate(node.right)));

            return node;
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node newLeft = x.right;
            return new Node(x.val, x.left, new Node(y.val, newLeft, y.right));
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node newRight = y.left;
            return new Node(y.val, new Node(x.val, x.left, newRight), y.right);
        }
    }

    // ==== 測試 ====
    public static void main(String[] args) {
        PersistentAVL tree = new PersistentAVL();

        int v1 = tree.insert(0, 10); // 版本1：插入10
        int v2 = tree.insert(v1, 5); // 版本2：插入5
        int v3 = tree.insert(v2, 20); // 版本3：插入20
        int v4 = tree.insert(v3, 3); // 版本4：插入3
        int v5 = tree.insert(v4, 7); // 版本5：插入7

        tree.inOrderPrint(0); // 空樹
        tree.inOrderPrint(v1); // 10
        tree.inOrderPrint(v2); // 5 10
        tree.inOrderPrint(v3); // 5 10 20
        tree.inOrderPrint(v5); // 3 5 7 10 20

        System.out.println("v3 是否含有 7？" + tree.contains(v3, 7)); // false
        System.out.println("v5 是否含有 7？" + tree.contains(v5, 7)); // true
    }
}