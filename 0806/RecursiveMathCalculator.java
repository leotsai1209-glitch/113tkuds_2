public class RecursiveMathCalculator {

    public static void main(String[] args) {
        // 測試組合數
        System.out.println("C(5, 2) = " + combination(5, 2));

        // 測試卡塔蘭數
        System.out.println("Catalan(4) = " + catalan(4));

        // 測試漢諾塔步數
        System.out.println("Hanoi(3) 步數 = " + hanoi(3));

        // 測試回文數
        System.out.println("12321 是否回文: " + isPalindrome(12321));
        System.out.println("12345 是否回文: " + isPalindrome(12345));
    }

    // 1. 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 計算卡塔蘭數 Catalan(n)
    public static long catalan(int n) {
        if (n <= 1) return 1;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 計算漢諾塔移動步數
    public static long hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    // 4. 判斷回文數
    public static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }
}