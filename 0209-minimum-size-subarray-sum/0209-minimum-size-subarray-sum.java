class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        MultipleSolutions sol=new MultipleSolutions();
        
        //return sol.bruteForce(target,nums);
        
        // return sol.binarySearch(target,nums);
        
        return sol.slidingWindow(target,nums);
    }
}
class MultipleSolutions{
    /*
        For all the possible subarray, find the sum of every subarray and check if the sum 
        is greater than or equal to target. If any subarray exists, with sum greater or equal to target
        then compare it with minLength.
    */
    public int bruteForce(int target, int[] nums){
        int minLength=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                if(sum>=target){
                    minLength=Math.min(minLength,j-i+1);
                    /*break out from the loop as the array is having only positive number,
                    so iterating further would only increase the length and sum respectively which is 
                    redundant calculation
                    */
                    break;
                }
            }
        }
        return minLength==Integer.MAX_VALUE?0:minLength;
    }
    
    /*
        • In this we will use the concept of binary search on answers. 
        • We know 2 things-
            Minimum possible length= 1
            Maximum possible length= n (length of array)
        • So, the answer can only lie between 1 to n.
        • If we choose more numbers, then sum will increase. This means choosing more number is 
          directly proportional to the sum of numbers. So, it is monotonic increasing function.
        • We will use binary search with lo=1 and hi=n. And for every mid, we check if the sum>=target
          can be achieved or not. If sum can be achieved, we will move left in array(i.e. hi=mid) because
          we want to find minimum length. And if the condition can't be achieved move lo to mid+1;
        • For every mid, to check sum>=target, we will use sliding window technique to find the sum of each
          subarray of length mid. (Named as maximumPossibleSum method here)
        
    */
    public int binarySearch(int target, int[] nums){
        int minLength=Integer.MAX_VALUE;
        int lo=1, hi=nums.length;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(lo==hi && maxPossibleSum(nums,target,mid) ){
                minLength=Math.min(minLength,hi);
                break;
            }
            //Check the maximum sum possible for length mid
            if(maxPossibleSum(nums,target,mid)){
                hi=mid;
                minLength=Math.min(minLength,hi);
            }else{
                lo=mid+1;
            }
        }
        return minLength==Integer.MAX_VALUE?0:minLength;
    }
    private boolean maxPossibleSum(int[] nums, int target, int mid){
        int sum=0;
        int start=0;
        for(int i=0;i<mid;i++) sum+=nums[i];
        for(int i=mid;i<nums.length;i++){
            if(sum>=target){
                return true;
            }else{
                sum-=nums[start++];
                sum+=nums[i];
            }
        }
        return sum>=target;
    }
    /*
        • For sliding window, we will check for smallest window present with sum>=target. 
    */
    public int slidingWindow(int target, int[] nums){
        int i=0,j=0,sum=0,length=Integer.MAX_VALUE;
        while(j<nums.length){
            if(sum<target){
                sum+=nums[j++];
            }else{
                length=Math.min(length,j-i);
                sum-=nums[i++];
            }
        }
        while(i<nums.length && sum>=target){
            length=Math.min(length,j-i);
            sum-=nums[i++];
        }
        return length==Integer.MAX_VALUE?0:length;
    }
}