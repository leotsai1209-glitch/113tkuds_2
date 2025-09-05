public class LC11_MaxArea_FuelHoliday {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            maxArea = Math.max(maxArea, area);

            // 移動較短的那邊，試圖找更高的邊界
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        LC11_MaxArea_FuelHoliday solver = new LC11_MaxArea_FuelHoliday();
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("最大裝油區域: " + solver.maxArea(heights)); // Output: 49
    }
}