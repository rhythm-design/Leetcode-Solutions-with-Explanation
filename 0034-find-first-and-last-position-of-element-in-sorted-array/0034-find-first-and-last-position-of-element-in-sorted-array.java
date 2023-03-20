class Solution {
    public int[] searchRange(int[] nums, int target) {
        return LC34.binarySearch(nums,target);
    }
}

class LC34{
    /*
        Binary Search Approach- In binary Search approach, we take advantage of how binary search finds
        an element in the sorted array. If the target is less, then it moves to the right of mid, and 
        moves to left of mid if target is smaller than mid element. So, we take advantage of it. 
        Even after target element is found, we will still continue with binary search to get the leftmost
        and rightmost index of the element in the sorted array. 
        Time Complexity- O(logn)
        Space Complexity- O(1)
    */
    public static int[] binarySearch(int[] nums, int target){
        int lo=0, hi=nums.length-1;
        int[] ans= new int[2];
        Arrays.fill(ans,-1);
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(nums[mid]==target){
                ans[0]=mid;
                hi=mid-1;
            }else if(nums[mid]>target){
                hi=mid-1;
            }else{
                lo=mid+1;
            }
        }
        lo=0; 
        hi=nums.length-1;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(nums[mid]==target){
                ans[1]=mid;
                lo=mid+1;
            }else if(nums[mid]>target){
                hi=mid-1;
            }else{
                lo=mid+1;
            }
        }
        return ans;
    }
    /*
        Bruteforce approach- In this we take two pointers, one at the start of array annd another at the 
        end of the array. Once any pointer becomes equal to target, we update the value of index in ans
        array and break out from the loop.
        Time Complexity- O(n)
        Space Complexity- O(1)
    */
    public static int[] bruteForce(int[] nums, int target){
        int i=0, j=nums.length-1;
        int[] ans= new int[2];
        Arrays.fill(ans,-1);
        while(i<nums.length && j>=0){
            if(nums[i]==target){
                ans[0]= ans[0]==-1?i:ans[0];
            }
            if(nums[j]==target){
                ans[1]= ans[1]==-1?j:ans[1];
            }
            i++;
            j--;
        }
        return ans;
    }
}