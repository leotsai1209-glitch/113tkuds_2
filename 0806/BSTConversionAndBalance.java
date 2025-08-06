
public class BSTConversionAndBalance {
    // 節點類別（同時可作為雙向鏈結串列節點）
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
              6    14
             / \   / \
            4   8 12  16
        */
        Node root = null;
        int[] values = {10, 6, 14, 4, 8, 12, 16};
        for (int v : values) {
            root = insert(root, v);
        }

        // 1. BST 轉排序雙向鏈結串列
        System.out.println("=== BST 轉排序雙向鏈結串列 ===");
        Node head = bstToDoublyLinkedList(root);
        printDoublyLinkedList(head);

        // 2. 排序陣列轉平衡 BST
        System.out.println("\n=== 排序陣列轉平衡 BST ===");
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        Node balanced = sortedArrayToBST(sortedArr, 0, sortedArr.length - 1);
        printInOrder(balanced);

        // 3. 檢查平衡並計算平衡因子
        System.out.println("\n\n=== 檢查 BST 是否平衡 ===");
        System.out.println("是否平衡: " + isBalanced(balanced));
        System.out.println("平衡因子: " + getBalanceFactor(balanced));

        // 4. 將每個節點改為 >= 該值的總和
        System.out.println("\n=== 節點改為大於等於該值的總和 ===");
        int[] values2 = {5, 2, 13};
        Node root2 = null;
        for (int v : values2) {
            root2 = insert(root2, v);
        }
        convertToGreaterSum(root2);
        printInOrder(root2);
    }

    // ===== 插入 BST =====
    public static Node insert(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.value) root.left = insert(root.left, value);
        else if (value > root.value) root.right = insert(root.right, value);
        return root;
    }

    // ===== 1. BST 轉排序雙向鏈結串列 =====
    static Node prev = null, head = null;
    public static Node bstToDoublyLinkedList(Node root) {
        prev = null;
        head = null;
        bstToDLLHelper(root);
        return head;
    }

    private static void bstToDLLHelper(Node node) {
        if (node == null) return;
        bstToDLLHelper(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        bstToDLLHelper(node.right);
    }

    public static void printDoublyLinkedList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
    }

    // ===== 2. 排序陣列轉平衡 BST =====
    public static Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end);
        return root;
    }

    // ===== 3. 檢查 BST 是否平衡 =====
    public static boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(Node root) {
        if (root == null) return 0;
        int left = checkHeight(root.left);
        if (left == -1) return -1;
        int right = checkHeight(root.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    // 計算平衡因子（左高 - 右高）
    public static int getBalanceFactor(Node root) {
        if (root == null) return 0;
        return height(root.left) - height(root.right);
    }

    private static int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // ===== 4. 節點值改為 >= 該值的總和 =====
    static int sum = 0;
    public static void convertToGreaterSum(Node root) {
        if (root == null) return;
        convertToGreaterSum(root.right);
        sum += root.value;
        root.value = sum;
        convertToGreaterSum(root.left);
    }

    // ===== 中序列印 =====
    public static void printInOrder(Node root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }
}