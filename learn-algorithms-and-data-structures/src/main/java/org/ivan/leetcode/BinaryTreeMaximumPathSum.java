package org.ivan.leetcode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-14 21:22
 **/
public class BinaryTreeMaximumPathSum {
    /**
     * 设置一个函数求最大贡献值，空节点的贡献值为0，非空节点则为左右子树的最大贡献值与当前值的和
     *
     * @param root
     * @return
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    /**
     * 递归最大贡献值
     * 如果节点为空，最大贡献值为0
     * 分别计算做哦字数和右子树的最大贡献值
     * 累加最大路径和：当前值加上左右子树的贡献值
     * 节点最大贡献值等于当前节点值与左右子树最大贡献值的和
     * 返回最大贡献值
     *
     * @param root
     */
    private int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左子树最大贡献值
        int leftMaxGain = Math.max(maxGain(root.left), 0);
        //计算右子树的最大贡献值
        int rightMaxGain = Math.max(maxGain(root.right), 0);

        //累加当前最大路径和
        int pricePath = root.val + leftMaxGain + rightMaxGain;
        maxSum = Math.max(pricePath, maxSum);
        return root.val + Math.max(leftMaxGain, rightMaxGain);

    }
}
