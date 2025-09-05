// LC40_CombinationSum2_Procurement.java
public class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 去重組合要排序
        backtrack(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) return;

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 跳過重複
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path); // II版: 每個數只能用一次
            path.remove(path.size() - 1);
        }
    }
}