package org.ivan.leetcode;

import java.util.List;

/**
 * 树节点
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-16 15:54
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    int depth;

    public int getDepth() {
        return depth;
    }

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode createTree(int rootIndex, List<Integer> values) {
        if (rootIndex >= values.size()) {
            return null;
        }
        TreeNode rootNode = new TreeNode();
        rootNode.val = values.get(rootIndex);
        rootNode.left = (createTree(2 * rootIndex + 1, values));
        rootNode.right = (createTree(2 * rootIndex + 2, values));
        return rootNode;
    }

    public static TreeNode generateTreeFromArray(Integer[] arr, int index) {
        if (index > arr.length - 1 || arr[index] == null) {
            return null;
        }
        TreeNode myTreeNode = new TreeNode(arr[index]);
        myTreeNode.left = generateTreeFromArray(arr, 2 * index + 1);
        myTreeNode.right = generateTreeFromArray(arr, 2 * index + 2);
        int leftDepth = myTreeNode.left == null ? 0 : myTreeNode.left.depth;
        int rightDepth = myTreeNode.right == null ? 0 : myTreeNode.right.depth;
        myTreeNode.depth = Math.max(leftDepth, rightDepth) + 1;
        return myTreeNode;
    }

}
