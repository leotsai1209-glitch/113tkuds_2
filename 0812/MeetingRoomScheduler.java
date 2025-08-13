import java.util.*;

public class MeetingRoomScheduler {

    // ===== 第一題：最少需要幾間會議室 =====
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]); // 第一個會議的結束時間

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll(); // 原會議室可以重用
            }
            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size(); // heap大小 = 會議室數量
    }