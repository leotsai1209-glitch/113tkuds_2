import java.util.*;

public class M05_GCD_LCM_Recursive {

    // 使用遞迴的歐幾里得演算法求最大公因數（GCD）
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 使用 GCD 計算最小公倍數（LCM）
    public static long lcm(long a, long b) {
        // 為了避免乘法溢位，先除後乘
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = lcm(a, b);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}