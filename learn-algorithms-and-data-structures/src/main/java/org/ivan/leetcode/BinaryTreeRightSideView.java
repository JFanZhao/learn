package org.ivan.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-18 16:00
 **/
public class BinaryTreeRightSideView {
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return res;
        }
        bfs(root);
        return res;
    }

    /**
     * 广度 层次遍历
     * 每层的最后一个节点加入结果集
     *
     * @param root
     * @param
     */
    private void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (i == size - 1) {
                    res.add(poll.val);
                }
            }
        }
    }

    /**
     * 深度优先搜索，按照根右左的顺序访问，每个深度的第一个节点放入结果集
     * @param root
     * @param depth
     */
    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        //如果当前的深度和结果集大小一致，该深度下第一个被访问的节点加入结果集
        if (depth == res.size()) {
            res.add(root.val);
        }
        depth++;
        //先访问右节点
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
