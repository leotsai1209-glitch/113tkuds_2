public class AVLBasicExercise {
    // 節點定義
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點高度為1
        }
    }

    static class AVLTree {
        Node root;

        // 插入節點
        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else
                return node; // 不允許重複插入

            // 更新高度
            node.height = 1 + Math.max(height(node.left), height(node.right));

            // 計算平衡因子
            int balance = getBalance(node);

            // 執行旋轉保持平衡
            // LL型
            if (balance > 1 && key < node.left.key)
                return rotateRight(node);

            // RR型
            if (balance < -1 && key > node.right.key)
                return rotateLeft(node);

            // LR型
            if (balance > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            // RL型
            if (balance < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        // 左旋轉
        Node rotateLeft(Node y) {
            Node x = y.right;
            Node T2 = x.left;

            x.left = y;
            y.right = T2;

            // 更新高度
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // 右旋轉
        Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            // 更新高度
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // 回傳節點高度
        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        // 回傳平衡因子
        int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        // 搜尋節點
        boolean search(Node node, int key) {
            if (node == null) return false;
            if (key == node.key) return true;
            else if (key < node.key) return search(node.left, key);
            else return search(node.right, key);
        }

        // 檢查是否為合法的 AVL 樹
        boolean isValidAVL(Node node) {
            if (node == null) return true;

            int balance = getBalance(node);
            if (balance > 1 || balance < -1)
                return false;

            return isValidAVL(node.left) && isValidAVL(node.right);
        }

        // 對外方法：插入、搜尋、高度、檢查 AVL
        public void insert(int key) {
            root = insert(root, key);
        }

        public boolean search(int key) {
            return search(root, key);
        }

        public int getHeight() {
            return height(root);
        }

        public boolean isValidAVL() {
            return isValidAVL(root);
        }
    }

    // 主程式測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] keys = {10, 20, 30, 40, 50, 25};

        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("是否包含節點25？" + tree.search(25)); // true
        System.out.println("樹高：" + tree.getHeight());
        System.out.println("是有效AVL樹嗎？" + tree.isValidAVL());
    }
}