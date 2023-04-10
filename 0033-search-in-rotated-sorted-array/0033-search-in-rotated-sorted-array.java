class Solution {
    /*
        Approach- We will use the concept of binary search in it. If we have a proper context
        how, binary search finds an element in the sorted array, then this question is a cup
        of tea.
        We just calculate mid, and then check for
            • If array from lo to mid is sorted?
            • ELse if array from mid to hi is sorted
        These are the only two case which can happen in binary seach.

        Now, after finding the part which is sorted, we find if in that sorted half, 
        target can exist. If yes, we will search in that part of array and leave the
        rest of the array. 
        [4,5,6,1,2,3]
    */
    public int search(int[] nums, int target) {
        int lo=0, hi=nums.length-1;

        while(lo<=hi){
            int mid= (lo+hi)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>=nums[lo]){
                //lo to mid elements is sorted
                if(nums[mid]>=target && nums[lo]<=target){
                    //check if target can exist in that part of array
                    hi=mid-1;
                }else{
                    lo=mid+1;
                }
            }else if(nums[mid]<=nums[hi]){
                //mid to hi elements is sorted
                if(target>=nums[mid] && target<=nums[hi]){
                    //check if target can exist in that part of array
                    lo=mid+1;
                }else{
                    hi=mid-1;
                }
            }
        }
        return -1;
    }
}