import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) return 0;

        List<Integer> profits = new ArrayList<>();
        int n = prices.length;
        int buy = 0;

        while (buy < n - 1) {
            // 找到低點
            while (buy < n - 1 && prices[buy] >= prices[buy + 1]) buy++;
            int sell = buy + 1;
            // 找到高點
            while (sell < n && prices[sell] >= prices[sell - 1]) sell++;

            if (sell - 1 > buy) {
                int profit = prices[sell - 1] - prices[buy];
                profits.add(profit);
                buy = sell;
            } else {
                buy++;
            }
        }

        // 放入 max heap，挑出最大的 K 筆利潤
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int profit : profits) {
            maxHeap.offer(profit);
        }

        int totalProfit = 0;
        while (K > 0 && !maxHeap.isEmpty()) {
            totalProfit += maxHeap.poll();
            K--;
        }

        return totalProfit;
    }

    // 主程式：測試用
    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        System.out.println("最大利潤：" + maxProfit(prices1, 2)); // 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println("最大利潤：" + maxProfit(prices2, 2)); // 7

        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println("最大利潤：" + maxProfit(prices3, 2)); // 4
    }
}