package freecoding.exercise.leetcode;

import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;

public class ValidateBinarySearchTree {
    //below successful case after consideration in toilet.
    public static boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        if(root.left != null) {
           if(getMax(root.left) >= root.val){
               return false;
           }
        }
        if(root.right != null){
            if(root.val >= getMin(root.right)){
                return false;
            }
        }

        return isValidBST(root.left)
              && isValidBST(root.right);
    }

    private static int getMax(TreeNode node) {
        for(;node.right != null; node = node.right);
        return node.val;
    }

    private static int getMin(TreeNode node) {
        for(;node.left != null; node = node.left);
        return node.val;
    }

    //below my fail case and takes too much time but fail.
    public static boolean isValidBST1(TreeNode root) {
        return isValidBST(new HashSet<>(),null, root);
    }

    public static boolean isValidBST(HashSet<Integer> set, TreeNode parent, TreeNode child) {
        if(child == null){
            return true;
        }
        if (child.left == null && child.right == null) {
            return !set.contains(child.val);
        }
        set.add(child.val);
        if(valid(parent, child)){
            if(isValidBST(set, child, child.left) && isValidBST(set, child, child.right))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    private static boolean valid(TreeNode parent, TreeNode child){
        if ((parent != null && child.left != null && child.right != null) && (child.left.val < parent.val && parent.val < child.right.val)) {
            return false;
        }

        if(child.left == null){
            return child.val < child.right.val;
        }
        if(null == child.right){
            return child.left.val < child.val;
        }

        if(child.left.val < child.val && child.val < child.right.val){
            return true;
        }
        return false;
    }

    @Test
    public void test9() {
        TreeNode node = new TreeNode(3,
                null,
                new TreeNode(30,
                        new TreeNode(10,
                                null,
                                new TreeNode(15,
                                        null,
                                        new TreeNode(45)))
                        , null)
        );

        assertTrue(!ValidateBinarySearchTree.isValidBST(node));
    }



    @Test
    public void test8() {
        TreeNode node = new TreeNode(3,
                new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(5, new TreeNode(4), new TreeNode(6))
        );

        assertTrue(!ValidateBinarySearchTree.isValidBST(node));
    }

    @Test
    public void test7() {
        TreeNode node = new TreeNode(10,
                new TreeNode(5),
                new TreeNode(15, new TreeNode(6), new TreeNode(20))
        );

        assertTrue(!ValidateBinarySearchTree.isValidBST(node));
    }

    @Test
    public void test4() {
        TreeNode node = new TreeNode(1,
                null,
                new TreeNode(1)
        );

        assertTrue(!ValidateBinarySearchTree.isValidBST(node));
    }

    @Test
    public void test6() {
        TreeNode node = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6))
        );

        assertTrue(!ValidateBinarySearchTree.isValidBST(node));
    }

    @Test
    public void test1() {
        TreeNode node = new TreeNode(3,
                new TreeNode(1),
                new TreeNode(5, new TreeNode(4), new TreeNode(6))
        );

        assertTrue(ValidateBinarySearchTree.isValidBST(node));
    }

    @Test
    public void test2() {
        TreeNode node = new TreeNode(2,
                new TreeNode(1),
                new TreeNode(3)
        );

        assertTrue(ValidateBinarySearchTree.isValidBST(node));
    }
}

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}