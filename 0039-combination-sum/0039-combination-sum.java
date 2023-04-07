class Solution {
    /*
        Approach: Similar to the coin change type of type of problems. In this, if we can choose a candidate unlimited number of time, then on every call we need not to jump to next candidate if we are including that candidate in group. If the target is exceeded, that means we no longer need to include that candidate in the group because it will be impossible to make target from it. So, move to the next candidate in exclude call (index+1). We need to backtrack in post area of include call, so that the candidate can be removed from the list (as we no longer need that particular occurence of the candidate.)
    */
    public void helper(int[] candidates, int target, List<Integer> combination, List<List<Integer>> combinations, int index){
        if(index==candidates.length || target<0) return;
        if(target==0){
            List<Integer> newList=new ArrayList<>(combination);
            combinations.add(newList);
            return;
        }
        
        combination.add(candidates[index]);
        helper(candidates, target-candidates[index], combination, combinations, index);
        combination.remove(combination.size()-1);
        helper(candidates, target,combination, combinations, index+1); 
        
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations= new ArrayList<>();
        List<Integer> combination= new ArrayList<>();
        helper(candidates,target, combination, combinations,0);
        return combinations;
    }
}