public class LC26_RemoveDuplicates_Scores {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }

        return write;
    }
}