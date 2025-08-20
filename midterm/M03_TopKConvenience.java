import java.util.*;

public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;

        Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine(); // consume newline

        PriorityQueue<Item> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty; // 小的優先
            return a.name.compareTo(b.name);          // 同數量，字典序
        });

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            String name = line[0];
            int qty = Integer.parseInt(line[1]);

            minHeap.offer(new Item(name, qty));
            if (minHeap.size() > k) {
                minHeap.poll(); // 保持 heap 大小為 k
            }
        }

        List<Item> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        Collections.reverse(result); // 轉成高到低
        for (Item item : result) {
            System.out.println(item.name + " " + item.qty);
        }
    }
}