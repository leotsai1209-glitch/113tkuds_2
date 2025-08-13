import java.util.*;

public class PriorityQueueWithHeap {

    // 任務類別
    static class Task {
        String name;
        int priority;

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return name + " (Priority: " + priority + ")";
        }
    }

    // 用於最大堆的比較器（priority 越大越優先）
    static class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2) {
            return Integer.compare(t2.priority, t1.priority); // 降冪排序
        }
    }

    private PriorityQueue<Task> heap;
    private Map<String, Task> taskMap; // 用來支援變更優先級

    public PriorityQueueWithHeap() {
        heap = new PriorityQueue<>(new TaskComparator());
        taskMap = new HashMap<>();
    }

    // 新增任務
    public void addTask(String name, int priority) {
        Task task = new Task(name, priority);
        heap.offer(task);
        taskMap.put(name, task);
    }

    // 執行下一個優先級最高的任務
    public Task executeNext() {
        Task task = heap.poll();
        if (task != null) {
            taskMap.remove(task.name);
        }
        return task;
    }

    // 查看下一個任務（不移除）
    public Task peek() {
        return heap.peek();
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        if (!taskMap.containsKey(name)) {
            System.out.println("No such task: " + name);
            return;
        }

        Task oldTask = taskMap.get(name);
        heap.remove(oldTask); // 重新加入，讓 heap 維持正確順序
        Task newTask = new Task(name, newPriority);
        heap.offer(newTask);
        taskMap.put(name, newTask);
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("TaskA", 5);
        pq.addTask("TaskB", 3);
        pq.addTask("TaskC", 10);

        System.out.println("Next task: " + pq.peek()); // TaskC

        pq.changePriority("TaskB", 15);
        System.out.println("Next task after priority change: " + pq.peek()); // TaskB

        System.out.println("Executed: " + pq.executeNext()); // TaskB
        System.out.println("Executed: " + pq.executeNext()); // TaskC
    }
}