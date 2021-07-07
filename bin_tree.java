import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

 class Tree {
    static Scanner s = null;
    public static void main(String[] args) {
	// write your code here
     s = new Scanner(System.in);
     Node root = createTree();
     Height h = new Height(0);
        System.out.println(diameterOfTree(root));
        System.out.println(heightOftree(root));
        System.out.println(isBalanced(root,h));
    }
    static Node createTree(){
        Node root = null;
        System.out.println("Enter data:");
       int data = s.nextInt();
       if(data==-1) return null;
       root = new Node(data);
        System.out.println("Enter left for "+data);
        root.left=createTree();
        System.out.println("Enter right for "+data);
        root.right=createTree();
        return root;
    }
    static void inOrder(Node root){
        if(root==null) return;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }
     static void preOrder(Node root){
         if(root==null) return;
         System.out.print(root.data+" ");
         preOrder(root.left);
         preOrder(root.right);
     }
     static void postOrder(Node root){
         if(root==null) return;
         postOrder(root.left);
         postOrder(root.right);
         System.out.print(root.data+" ");
     }
     static void levelOrder(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node temp = q.peek();
            q.remove();
            if(temp!=null){
                System.out.print(temp.data+" ");
            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null)
                q.add(temp.right);
            }else if(!q.isEmpty())q.add(null);
        }
     }
    static int sumAtK(Node root,int order){
        if(root==null) return 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);q.add(null);
        int level=0;int sum=0;
        while(!q.isEmpty()){
            Node temp = q.peek();
            q.remove();
            if(temp!=null){
                if(level==order)sum+=temp.data;
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
            }else if(!q.isEmpty()){
                q.add(null);
                level++;
            }
        }return sum;
     }
     static int countNodes(Node root){
        if(root==null) return 0;
        return countNodes(root.left)+countNodes(root.right)+1;
     }
     static int heightOftree(Node root){
        if(root==null) return 0;
        int l = heightOftree(root.left);
        int r = heightOftree(root.right);
        if(l>r) return l+1;
        else return r+1;
     }
     static  int diameterOfTree(Node root){
        if(root==null) return 0;
        int l = heightOftree(root.left);
        int r = heightOftree(root.right);
        int c = l+r+1;
        int lD = diameterOfTree(root.left);
        int rD = diameterOfTree(root.left);
        return Integer.max(c,Integer.max(lD,rD));

     }
     static void sumReplacement(Node root){
        if(root==null) return ;
        sumReplacement(root.left);
        sumReplacement(root.right);
        if(root.left!=null)root.data=root.data+root.left.data;
         if(root.right!=null)root.data=root.data+root.right.data;

     }
     static boolean isBalanced(Node root,Height height){
        if(root==null) {return true;}
        Height lh = new Height(0);
        Height rh = new Height(0);


        if(!isBalanced(root.left,lh))return false;
       if(!isBalanced(root.right,lh))return false;
//       int lh= heightOftree(root.left);
//       int rh= heightOftree(root.right);
         int l = lh.height;int r = rh.height;
         height.height = (Math.max(l, r))+1;
         return Math.abs(l- r) <= 1;
     }
     public int diameterOfBinaryTree(Node root,Height h) {
         if(root==null) return 0;
         Height lh = new Height(0);
         Height rh = new Height(0);
         h.height = Integer.max(lh.height,rh.height)+1;

         int l =diameterOfBinaryTree(root.left,lh);
         int r = diameterOfBinaryTree(root.right,rh);

         int c = lh.height+1+rh.height;

         return Integer.max(c,Integer.max(l,r));


     }
}
class Node{
    Node left,right;
    int data;
    public Node(int data){
        this.data=data;
    }
}
class Height{
     int height =0;
     public Height(int height){
         this.height=height;
     }
}
