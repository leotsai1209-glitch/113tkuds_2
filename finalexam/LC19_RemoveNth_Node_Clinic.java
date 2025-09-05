public class LC11_MaxArea_FuelHoliday {
    public int maxArea(int[] height) {
        int left = 0;                   // 左指針
        int right = height.length - 1;  // 右指針
        int maxArea = 0;                // 紀錄最大面積

        while (left < right) {
            // 計算當前的高度（取左右的最小值）
            int h = Math.min(height[left], height[right]);
            // 計算寬度（兩個指針的距離）
            int w = right - left;
            // 計算面積並更新最大值
            maxArea = Math.max(maxArea, h * w);

            // 移動較短的邊，嘗試找到更大的高度
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}