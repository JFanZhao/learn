package org.ivan.learn.ds.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的遍历：前中后序遍历
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-27 10:41
 **/
public class BinaryTreeTraversal {

    /**
     * 构建二叉树
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.size() <= 0) {
            return null;
        }
        Integer data = inputList.removeFirst();
        //这里的判空很关键。如果元素是空，说明该节点不存在，跳出这一层递归；
        // 如果元素非空，继续递归构建该节点的左右孩子。
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 前序遍历：头，左，右的顺序
     * @param node
     */
    public static void preOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    /**
     * 中序遍历：左，头，右
     * @param node
     */
    public static void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTraversal(node.leftChild);
        System.out.println(node.data);
        preOrderTraversal(node.rightChild);
    }
    /**
     * 后序遍历：左，右，头
     * @param node
     */
    public static void postOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTraversal(node.leftChild);
        System.out.println(node.data);
        preOrderTraversal(node.rightChild);
    }
    /**
     * 树节点
     */
    private static class TreeNode{
        private int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        preOrderTraversal(treeNode);
        System.out.println("中序遍历：");
        inOrderTraversal(treeNode);
        System.out.println("后序遍历：");
        postOrderTraversal(treeNode);
    }
}
