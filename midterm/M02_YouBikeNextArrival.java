import java.util.*;

public class M02_YouBikeNextArrival {

    // 將 "HH:mm" 時間格式轉換為分鐘數
    private static int timeToMinutes(String t) {
        String[] parts = t.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 將分鐘數轉換為 "HH:mm" 字串格式
    private static String minutesToTime(int m) {
        int h = m / 60;
        int min = m % 60;
        return String.format("%02d:%02d", h, min);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            times[i] = timeToMinutes(sc.nextLine().trim());
        }

        int query = timeToMinutes(sc.nextLine().trim());

        // 使用二分搜尋找第一個大於 query 的時間
        int left = 0, right = n - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > query) {
                ans = times[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (ans == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(minutesToTime(ans));
        }
    }
}