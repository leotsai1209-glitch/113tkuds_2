import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {  // 遇到右括號
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else {  // 左括號就推入堆疊
                stack.push(c);
            }
        }

        return stack.isEmpty(); // 最後應該堆疊為空
    }
}