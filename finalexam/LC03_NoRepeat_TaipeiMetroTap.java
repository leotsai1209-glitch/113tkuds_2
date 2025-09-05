import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            // 若該字元出現過，且在視窗內，更新 start
            if (map.containsKey(c) && map.get(c) >= start) {
                start = map.get(c) + 1;
            }

            // 更新該字元最新出現位置
            map.put(c, end);

            // 更新最大長度
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}