public class TreeMirrorAndSymmetry {
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
              2   2
             / \ / \
            3  4 4  3
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        // 1. 判斷是否對稱樹
        System.out.println("是否為對稱樹: " + isSymmetric(root));

        // 2. 鏡像樹
        Node mirror = mirrorTree(copyTree(root));
        System.out.println("鏡像樹與原樹互為鏡像: " + isMirror(root, mirror));

        // 3. 比較兩棵樹是否互為鏡像
        Node treeA = new Node(1);
        treeA.left = new Node(2);
        treeA.right = new Node(3);

        Node treeB = new Node(1);
        treeB.left = new Node(3);
        treeB.right = new Node(2);

        System.out.println("treeA 與 treeB 是否互為鏡像: " + isMirror(treeA, treeB));

        // 4. 檢查子樹
        Node subTree = new Node(2);
        subTree.left = new Node(4);
        subTree.right = new Node(3);

        System.out.println("subTree 是否為 root 的子樹: " + isSubtree(root, subTree));
    }

    // ===== 1. 判斷對稱樹 =====
    public static boolean isSymmetric(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // ===== 2. 將樹轉換為鏡像樹 =====
    public static Node mirrorTree(Node root) {
        if (root == null) return null;
        Node left = mirrorTree(root.left);
        Node right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // ===== 3. 判斷兩棵樹是否互為鏡像 =====
    public static boolean isMirror(Node t1, Node t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.value == t2.value) &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }

    // ===== 4. 檢查子樹 =====
    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.value == b.value) &&
               isSameTree(a.left, b.left) &&
               isSameTree(a.right, b.right);
    }

    // ===== 複製樹（用於保留原樹） =====
    public static Node copyTree(Node root) {
        if (root == null) return null;
        Node newNode = new Node(root.value);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        return newNode;
    }
}