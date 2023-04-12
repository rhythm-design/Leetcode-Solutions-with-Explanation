class Solution {
    /*
        Concept: At first, questions like this don't seem to solve by binary
        search. But, if are struggling with converting bruteforce to optimized
        solution, then binary seach can be a go to. But before applying this, we
        have to keep certain points in mind-
            • Is there is any specific answer for any set of solution? If yes,
            continue to next point.
            • Does that specific answer follows for every other permutation of
            the answer to the question? If yes, we are good to go now.

        But, let's understand more, how we can check for above two points- 
        Example- [7,2,5,10,8]  k=2

        In the question, we have to divide the array into k subarrays, such that
        in between the sum of all subarrays, the highest sum subarray should have
        be minimum. 
        • Like subarray1= [7,2,5,10] subarray2=[8]
          sum of subarray1= 24 subarray2=8   largest sum=> 24
        • Another way to split into subarray- 
            subarray1=[7,2,5] subarray2=[10,8]
            sum of subarray1=14 subarray2=[18] largest sum=> 18
        So, our answer would be 18 in between the above 2 mentioned outcomes

        Now, from the above example, we can say we can check for various values
        of largest sum, if that sum can be made by k subarrays, then it can be 
        the potential answer. Now, one way to do it is using linear search and
        check for the value, which can make k subarrays. But that would have 
        extremely big time complexity, since we are checking for values, which
        can go upto Integer.MAX_VALUE. 
        Here, we can apply binary search. We first have to think what can be the 
        lowest possible answer, and what can be the highest. Now, as the binary
        search concept says, we will calculate mid (highestSum in this case) and
        check will the highestSum(mid) can make k subarrays? If yes, then we know
        this mid(highestSum) can be the potential answer. But now, we have to find
        the minimum possible answer, so after getting one potential answer, we 
        will continue out search to find the even more low highestSum(mid).

        Now, a

    */
    public int splitArray(int[] nums, int k) {
        int lo=0, hi=0;

        for(int val: nums){
            lo=Math.max(lo,val);
            hi+=val;
        }

        int minimizedLargestSum=0;
        while(lo<=hi){
            int highestSum= lo+ (hi-lo)/2;       //mid of binary search


            //Sum every subarray, and if sum exceeds mid, we are now at next
            //subarray, so increase count and start sum from nums[i];
            int numberOfSubArrays=1;
            int sum=0;
            for(int i=0;i<nums.length;i++){
                sum+=nums[i];
                if(sum>highestSum){
                    sum=nums[i];
                    numberOfSubArrays++;
                }
            }

            //check if the number of subarrays formed taking highestSum as mid
            // is less than equal to k or larger than k. 
            if(numberOfSubArrays<=k){
                minimizedLargestSum=highestSum;
                hi=highestSum-1;
            }else{
                lo=highestSum+1;
            }
        }
        return minimizedLargestSum;
    }
}