// LC39_CombinationSum_PPE.java
public class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path); // I版: 同一元素可重複
            path.remove(path.size() - 1);
        }
    }
}