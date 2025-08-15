class AVLRotationExercise {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    static int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    static int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 右旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 左旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node node, int key) {
        // 普通 BST 插入
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // 1. 左左
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // 2. 右右
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // 3. 左右
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 4. 右左
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // ===== 測試各種旋轉情況 =====
    public static void main(String[] args) {
        Node root = null;

        System.out.println("🔁 測試 LL（右旋）:");
        root = null;
        root = insert(root, 30);
        root = insert(root, 20);
        root = insert(root, 10); // LL -> 右旋
        preOrder(root);
        System.out.println("\n");

        System.out.println("🔁 測試 RR（左旋）:");
        root = null;
        root = insert(root, 10);
        root = insert(root, 20);
        root = insert(root, 30); // RR -> 左旋
        preOrder(root);
        System.out.println("\n");

        System.out.println("🔁 測試 LR（左右旋）:");
        root = null;
        root = insert(root, 30);
        root = insert(root, 10);
        root = insert(root, 20); // LR -> 左右旋
        preOrder(root);
        System.out.println("\n");

        System.out.println("🔁 測試 RL（右左旋）:");
        root = null;
        root = insert(root, 10);
        root = insert(root, 30);
        root = insert(root, 20); // RL -> 右左旋
        preOrder(root);
        System.out.println("\n");
    }
}