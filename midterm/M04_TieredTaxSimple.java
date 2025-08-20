import java.util.*;

public class M04_TieredTaxSimple {

    // 計算單一筆收入的稅額
    public static int calculateTax(int income) {
        int tax = 0;

        // 超過 1000000 部分 → 稅率 30%
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000; // 剩下再往下計算
        }

        // 超過 500000 部分 → 稅率 20%
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }

        // 超過 120000 部分 → 稅率 12%
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }

        // 剩下 0 ~ 120000 → 稅率 5%
        tax += income * 5 / 100;

        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 收入筆數
        int totalTax = 0;

        // 處理每一筆收入
        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = calculateTax(income); // 計算稅額
            totalTax += tax;
            System.out.println("Tax: " + tax); // 輸出個別稅額
        }

        // 輸出平均稅額（取整數）
        System.out.println("Average: " + (totalTax / n));
    }
}