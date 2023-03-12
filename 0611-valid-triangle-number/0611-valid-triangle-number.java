
class Solution {
    public int triangleNumber(int[] nums) {
        return LC611.twoPointers(nums);
    }
}

class LC611{
    /*
        Any triplet can make a triangle which satisfy the below condition- 
        • Sum of two sides must be strictly greater than the third side.

        BruteForce Method- 
        In this we simple iterate over each element and check weather the above condition is 
        satisfied or not. If yes, do count++;
    */
    public static int bruteForce(int[] nums){
        int numberOfTriplets=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]>nums[k] && nums[j]+nums[k]>nums[i] && nums[i]+nums[k]>nums[j]){
                        numberOfTriplets++;
                    }
                }
            }
        }
        return numberOfTriplets;
    }
    /*
        Using pointers- 
        • Intuition is if we have an sorted array, then the iteration can be reduced. But can it be achieved?
        • If we fix one element to the highest possible value, then we have to check for other 2 values only, if they satisfy the condition. In this, we just need to check if the two chosen element is greater than the largest element. 
    */
    public static int twoPointers(int[] nums){
        Arrays.sort(nums);
        int numberOfTriplets=0;
        for(int i=nums.length-1;i>=0;i--){
            int left=0;
            int right=i-1;
            while(left<right){
                if(nums[left]+nums[right]>nums[i]){
                    numberOfTriplets+=(right-left);
                    right--;
                }else{
                    left++;
                }
            }
        }
        return numberOfTriplets;
    }
}