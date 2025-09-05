public class LC11_MaxArea_FuelHoliday {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            max = Math.max(max, area);

            // 根據較短的高度決定移動哪一邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    // 測試主程式
    public static void main(String[] args) {
        LC11_MaxArea_FuelHoliday solution = new LC11_MaxArea_FuelHoliday();
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println("最大油量區間面積為：" + solution.maxArea(heights));
    }
}