public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 計算平均、最高、最低
        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = (double) sum / scores.length;

        // 統計各等第人數
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        // 計算高於平均的人數
        int aboveAvg = 0;
        for (int score : scores) {
            if (score > avg) aboveAvg++;
        }

        // 列印成績報表
        System.out.println("==== 成績報表 ====");
        System.out.print("成績列表: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n平均分: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("A 等級人數: " + countA);
        System.out.println("B 等級人數: " + countB);
        System.out.println("C 等級人數: " + countC);
        System.out.println("D 等級人數: " + countD);
        System.out.println("F 等級人數: " + countF);
        System.out.println("高於平均分人數: " + aboveAvg);
    }
}