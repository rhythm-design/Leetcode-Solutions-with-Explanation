class Solution {
    /* Question Type- Binary Search on Answer */
    public int smallestDivisor(int[] nums, int threshold) {
        /*
            maximum number from which we can divide the nums is the max of the nums
            Omitting loop to check max in the array, and taking the highest constraints
            of nums[i]. This will not effect time complexity since in binary search we
            discard the half of values in one iteration.Hence, making it incredibly
            fast algorithm.
        */
        int lo=0, hi=(int)1e6;

        int ans=-1;
        while(lo<=hi){

            int divisor= lo+ (hi-lo)/2;

            //Calculate the ceil sum of the elements of the array
            int sum=0;
            for(int i=0;i<nums.length;i++){
                sum+=Math.ceil(nums[i]*1.0/divisor);
            }

            if(sum<=threshold){
                ans=divisor;
                hi=divisor-1;
            }else{
                lo=divisor+1;
            }
        }
        return ans;
    }
}