import java.util.*;

public class M06_PalindromeClean {
    
    // 判斷是否為回文的主邏輯
    public static boolean isCleanPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();
        
        // 過濾非英文字母並轉小寫
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }

        String cleanedStr = cleaned.toString();
        int left = 0, right = cleanedStr.length() - 1;

        // 使用雙指標檢查是否回文
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (isCleanPalindrome(input)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}