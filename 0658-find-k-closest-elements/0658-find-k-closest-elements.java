class Solution {
    //Uncomment anyone to check the results
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // return LC658.usingPriorityQueue(arr,k,x);
        return LC658.usingBinarySearch(arr,k,x);
    }
}

class LC658{
    static class Pair{
        int val, diff;
        Pair(int val, int diff){
            this.val=val;
            this.diff=diff;
        }
    }
    /*
        Intuition- Intuition behind the below approach is if we maintain the priority queue of size k,
        with some conditions, then the k elements in the PQ will be the k closest element.

        Now, you may think what are the conditions?
        • If we make the pair of element and diff of element from x(arr[i],x-arr[i]), and reverse sort the
          priority queue on the basis of diff, then on each iteration, we can check if the max diff in
          priority queue is less than or greater than the current element diff
        • This will give us the answer. Because, we have check the closest, and comparing diff in
          each iteration will give us the closest k elements.
    */
    public static List<Integer> usingPriorityQueue(int[] arr, int k, int x){
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->b.diff-a.diff);
        for(int i=0;i<arr.length;i++){
            if(pq.size()<k){
                pq.add(new Pair(arr[i],Math.abs(x-arr[i])));
            }else{
                int newDiff=Math.abs(arr[i]-x);
                if(pq.peek().diff>newDiff){
                    pq.remove();
                    pq.add(new Pair(arr[i],newDiff));
                }
            }
        }
        List<Integer> ans=new ArrayList<>();
        while(pq.size()>0){
            ans.add(pq.remove().val);
        }
        Collections.sort(ans);
        return ans;
    }
    public static int findNearestIndexUsingBinarySearch(int[] arr, int x){
        int lo=0, hi=arr.length-1;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(arr[mid]==x){
                return mid;
            }else if(arr[mid]<x){
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }
        return lo;
    }
    /*
        Binary Search Approach- This approach is based on the fact that if we search a value in array using
        binary search, then
        • If value is present it will give the index of that value in that array.
        • If the value is not present, then it will give the index of nearest value.

        We can take the advantage of above 2 things, and apply binary search to find k closest element. 

        • Find exact or nearest index using binary search.
        • Then use 2 pointers to iterate to the left and right of the array.
            Now, we can take 2 pointers in 2 ways-
            1- i=nearestIndex and j=nearestIndex+1
            2- i=nearestIndex-1 and j=nearestIndex
            1st way of taking pointers will fail, because binary search is giving higher_bound index as ans
            So, we have tho consider, left half from nearestIndex-1 and rightHalf from nearestIndex.
        • Then, simply use 2 pointers to iterate over array and add the answer to the answer. 
        • At last, sort the ans arraylist because it is not sorted as asked in the question.
    */
    public static List<Integer> usingBinarySearch(int[] arr, int k, int x){
        ArrayList<Integer> ans=new ArrayList<>();
        int nearestIndex=findNearestIndexUsingBinarySearch(arr,x);
        int i=nearestIndex-1, j=nearestIndex;
        while(k-->0){
            int left= i>=0?Math.abs(x-arr[i]):Integer.MAX_VALUE;
            int right= j<arr.length?Math.abs(x-arr[j]):Integer.MAX_VALUE;
            if(left<=right){
                ans.add(arr[i]);
                i--;
            }else{
                ans.add(arr[j]);
                j++;
            }
        }
        Collections.sort(ans);
        return ans;
    }
}