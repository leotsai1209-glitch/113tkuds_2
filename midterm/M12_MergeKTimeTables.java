import java.util.*;

public class M12_MergeKTimeTables {

    static class TimeEntry {
        int time;
        int listIndex;
        int elementIndex;

        TimeEntry(int time, int listIndex, int elementIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> currentList = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                currentList.add(sc.nextInt());
            }
            lists.add(currentList);
        }

        PriorityQueue<TimeEntry> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        // 將每個列表的第一個元素加入 PQ
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new TimeEntry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            TimeEntry curr = pq.poll();
            result.add(curr.time);

            int nextIndex = curr.elementIndex + 1;
            if (nextIndex < lists.get(curr.listIndex).size()) {
                int nextTime = lists.get(curr.listIndex).get(nextIndex);
                pq.offer(new TimeEntry(nextTime, curr.listIndex, nextIndex));
            }
        }

        for (int t : result) {
            System.out.print(t + " ");
        }
    }
}