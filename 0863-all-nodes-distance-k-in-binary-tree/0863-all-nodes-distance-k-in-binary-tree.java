/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<TreeNode> nodeToRootPath(TreeNode root, TreeNode target){
        if(root == null) return new ArrayList<TreeNode>();
        if(root == target){
            List<TreeNode> targetList = new ArrayList<>();
            targetList.add(target);
            return targetList;
        }
        List<TreeNode> left = nodeToRootPath(root.left,target);
        if(left.size()>0){
            left.add(root);
            return left;
        }
        List<TreeNode> right = nodeToRootPath(root.right,target);
        if(right.size()>0){
            right.add(root);
            return right;
        }
        return new ArrayList<TreeNode>();
    }
    
    public List<TreeNode> nodesKLevelDown(TreeNode root, int k, TreeNode blocker){
        if(root == null || k<0 || root == blocker) return new ArrayList<TreeNode>();
        if(k == 0){
            List<TreeNode> kLevelNodes = new ArrayList<>();
            kLevelNodes.add(root);
            return kLevelNodes;
        }
        List<TreeNode> left = nodesKLevelDown(root.left,k-1,blocker);
        List<TreeNode> right = nodesKLevelDown(root.right,k-1, blocker);
        left.addAll(right);
        return left;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> distanceK = new ArrayList<>();
        List<TreeNode> nodeToRootPath = nodeToRootPath(root,target);
        for(int i=0;i<nodeToRootPath.size();i++){
            List<TreeNode> levelNodes = nodesKLevelDown(nodeToRootPath.get(i),k-i, i==0?null: nodeToRootPath.get(i-1));
            for(TreeNode node: levelNodes){
                distanceK.add(node.val);
            }
        }
        return distanceK;
    }
}