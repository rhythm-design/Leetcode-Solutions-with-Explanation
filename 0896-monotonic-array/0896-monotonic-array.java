class Solution {
    /*
        As the question says, strictly increasing array or decreasing array is said to be as monotonic array.
        
        So, what can be the simplest approach to check if the array is strictly increasing or decreasing?
        
        Approach- 
        We can simply check every adjacent neighbours if they smaller or larger than the other
    */
    
    //2 pass solution
    public boolean twoPassMonotonic(int[] nums){
        boolean monotonicIncreasing=true;
        for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]) monotonicIncreasing=false;
        }
        if(monotonicIncreasing) return true;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]) return false;
        }
        return true;
    }
    public boolean onePassMonotonic(int[] nums){
        boolean monotonicIncreasing=true, monotonicDecreasing=true;
        for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]) monotonicIncreasing=false;
            if(nums[i]>nums[i-1]) monotonicDecreasing=false;
        }
        return monotonicIncreasing || monotonicDecreasing;
    }
    public boolean isMonotonic(int[] nums) {
       return onePassMonotonic(nums);
    }
}