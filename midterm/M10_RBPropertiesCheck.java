import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'R' or 'B'
        int index;

        Node(int val, char color, int index) {
            this.val = val;
            this.color = color;
            this.index = index;
        }
    }

    static Node[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String colorStr = sc.next();
            char color = (val == -1) ? 'B' : colorStr.charAt(0); // -1 視為黑色 NIL
            tree[i] = new Node(val, color, i);
        }

        if (n == 0 || tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        if (!checkRedRed(0)) return;

        if (checkBlackHeight(0) == -1) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    // 左右子節點索引
    private static int left(int i) {
        int l = 2 * i + 1;
        return (l < tree.length) ? l : -1;
    }

    private static int right(int i) {
        int r = 2 * i + 2;
        return (r < tree.length) ? r : -1;
    }

    // 檢查是否有紅紅相鄰
    private static boolean checkRedRed(int i) {
        if (i == -1 || tree[i].val == -1) return true;

        int l = left(i), r = right(i);
        Node node = tree[i];

        if (node.color == 'R') {
            if (l != -1 && tree[l].color == 'R') {
                System.out.println("RedRedViolation at index " + l);
                return false;
            }
            if (r != -1 && tree[r].color == 'R') {
                System.out.println("RedRedViolation at index " + r);
                return false;
            }
        }

        return checkRedRed(l) && checkRedRed(r);
    }

    // 回傳黑高度（不合法則回 -1）
    private static int checkBlackHeight(int i) {
        if (i == -1 || tree[i].val == -1) return 1; // NIL 節點視為黑色

        int l = left(i), r = right(i);
        int leftBH = checkBlackHeight(l);
        int rightBH = checkBlackHeight(r);

        if (leftBH == -1 || rightBH == -1 || leftBH != rightBH) return -1;
        return leftBH + (tree[i].color == 'B' ? 1 : 0);
    }
}