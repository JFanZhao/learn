package org.ivan.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-16 15:52
 **/
public class BinaryTreeTravel {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isLeftOrder = true;
        while (!queue.isEmpty()) {
            int n = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val+" ");
                if (isLeftOrder){
                    deque.addLast(node.val);
                }else {
                    deque.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(new LinkedList<>(deque));
            isLeftOrder = !isLeftOrder;

        }
        return result;
    }

    public static void main(String[] args) {

        Integer[] integers = {3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.generateTreeFromArray(integers,0);
        List<List<Integer>> lists = new BinaryTreeTravel().levelOrder(root);
        System.out.println(lists);

    }
}
