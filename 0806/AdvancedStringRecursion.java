import java.util.HashSet;
import java.util.Set;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        // 1. 遞迴產生字串所有排列組合
        System.out.println("=== 字串排列組合 ===");
        permute("ABC", "");

        // 2. 遞迴字串匹配
        System.out.println("\n=== 字串匹配 ===");
        System.out.println(match("abcdef", "cd", 0)); // true
        System.out.println(match("abcdef", "gh", 0)); // false

        // 3. 遞迴移除重複字符
        System.out.println("\n=== 移除重複字符 ===");
        System.out.println(removeDuplicates("aabbccdde", 0, new StringBuilder(), new HashSet<>()));

        // 4. 遞迴計算所有子字串組合
        System.out.println("\n=== 所有子字串 ===");
        substrings("abc", 0, 0);
    }

    // 1. 遞迴產生字串所有排列組合
    public static void permute(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i));
        }
    }

    // 2. 遞迴字串匹配（檢查 target 是否為 text 子字串）
    public static boolean match(String text, String target, int index) {
        if (index + target.length() > text.length()) return false;
        if (text.substring(index, index + target.length()).equals(target)) return true;
        return match(text, target, index + 1);
    }

    // 3. 遞迴移除重複字符
    public static String removeDuplicates(String str, int index, StringBuilder sb, Set<Character> seen) {
        if (index == str.length()) return sb.toString();
        char c = str.charAt(index);
        if (!seen.contains(c)) {
            seen.add(c);
            sb.append(c);
        }
        return removeDuplicates(str, index + 1, sb, seen);
    }

    // 4. 遞迴計算字串的所有子字串組合
    public static void substrings(String str, int start, int end) {
        if (start == str.length()) return;
        if (end == str.length()) {
            substrings(str, start + 1, start + 1);
        } else {
            System.out.println(str.substring(start, end + 1));
            substrings(str, start, end + 1);
        }
    }
}