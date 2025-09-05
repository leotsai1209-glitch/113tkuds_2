public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        double[] A = {1.0, 3.0}; // 第⼀筆資料（已排序）
        double[] B = {2.0};      // 第⼆筆資料（已排序）

        System.out.println("中位數為: " + findMedianSortedArrays(A, B));
    }

    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        // 保證 nums1 是較短的那個陣列
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;

        while (left <= right) {
            int i = (left + right) / 2;     // nums1 左邊取 i 個
            int j = totalLeft - i;         // nums2 左邊取 j 個

            double nums1LeftMax = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double nums1RightMin = (i == m) ? Double.POSITIVE_INFINITY : nums1[i];

            double nums2LeftMax = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double nums2RightMin = (j == n) ? Double.POSITIVE_INFINITY : nums2[j];

            // 確保切割正確
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (Math.max(nums1LeftMax, nums2LeftMax) +
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1; // nums1 左邊太多，往左縮小
            } else {
                left = i + 1; // nums1 左邊太少，往右增加
            }
        }

        throw new IllegalArgumentException("輸入格式錯誤");
    }
}