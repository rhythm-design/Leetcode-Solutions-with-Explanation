/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root==null) return root;
        Queue<Node> q=new ArrayDeque<>();
        q.add(root);
        while(q.size()>0){
            int cle=q.size();     //curent level elements
            Node rem=null;
            for(int i=0;i<cle;i++){
                rem=q.remove();
                rem.next=q.peek();
                //since the tree is perfect binary tree, so we need not to check for right child
                //as in CBT, if it has left child, then it will must have right child too
                /*
                if(rem.left!=null){
                    q.add(rem.left);
                }
                if(rem.right!=null){
                    q.add(rem.right);
                }
                */
                //above code can also be written as
                if(rem.left!=null){
                    q.add(rem.left);
                    q.add(rem.right);
                }
            }
            rem.next=null;
        }
        return root;
    }
}