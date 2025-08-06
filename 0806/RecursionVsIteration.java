public class RecursionVsIteration {
    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String str = "Hello World (Java)!";
        String brackets = "{[()]}";

        // 1. 二項式係數
        System.out.println("=== 二項式係數 ===");
        compareTime(
                () -> binomialRecursive(n, k),
                () -> binomialIterative(n, k)
        );

        // 2. 陣列乘積
        System.out.println("\n=== 陣列乘積 ===");
        compareTime(
                () -> arrayProductRecursive(arr, 0),
                () -> arrayProductIterative(arr)
        );

        // 3. 字串元音數量
        System.out.println("\n=== 字串元音數量 ===");
        compareTime(
                () -> countVowelsRecursive(str, 0),
                () -> countVowelsIterative(str)
        );

        // 4. 括號配對檢查
        System.out.println("\n=== 括號配對檢查 ===");
        compareTime(
                () -> checkBracketsRecursive(brackets, 0, 0, 0, 0),
                () -> checkBracketsIterative(brackets)
        );
    }

    // ========= 1. 二項式係數 =========
    public static long binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static long binomialIterative(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    // ========= 2. 陣列乘積 =========
    public static long arrayProductRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * arrayProductRecursive(arr, index + 1);
    }

    public static long arrayProductIterative(int[] arr) {
        long product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    // ========= 3. 元音字母數量 =========
    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        int count = "aeiou".indexOf(c) != -1 ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // ========= 4. 括號配對檢查 =========
    public static boolean checkBracketsRecursive(String str, int index, int round, int square, int curly) {
        if (index == str.length()) return round == 0 && square == 0 && curly == 0;
        char c = str.charAt(index);
        switch (c) {
            case '(' -> round++;
            case ')' -> round--;
            case '[' -> square++;
            case ']' -> square--;
            case '{' -> curly++;
            case '}' -> curly--;
        }
        if (round < 0 || square < 0 || curly < 0) return false;
        return checkBracketsRecursive(str, index + 1, round, square, curly);
    }

    public static boolean checkBracketsIterative(String str) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty();
    }

    // ========= 效能比較工具 =========
    @FunctionalInterface
    interface Task { Object run(); }

    public static void compareTime(Task recursive, Task iterative) {
        long start, end;

        start = System.nanoTime();
        Object resRec = recursive.run();
        end = System.nanoTime();
        System.out.println("遞迴結果: " + resRec + " | 時間: " + (end - start) + " ns");

        start = System.nanoTime();
        Object resItr = iterative.run();
        end = System.nanoTime();
        System.out.println("迭代結果: " + resItr + " | 時間: " + (end - start) + " ns");
    }
}