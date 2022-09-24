package org.ivan.leetcode;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-21 15:02
 **/
public class NumberOfIslands {
    /**
     * 深度优先搜索
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;

        if (grid == null || grid.length == 0) {
            return res;
        }

        //循环
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 搜索查找岛屿，参照二叉树的搜索，二叉树只需要搜索左右子树
     * 表格则需要上下左右四个方向
     * 当搜索到是岛屿的时候，设置成2 表示为已经搜索过，不要重复搜索
     * 则有三种状态 0 1 2
     *
     * @param grid
     * @param r
     * @param c
     */
    private void dfs(char[][] grid, int r, int c) {
        //是否越界
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }
        //不是1 停止搜索
        if (grid[r][c] != '1') {
            return;
        }

        //置为 2
        grid[r][c] = '2';

        //上下左右搜索
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }
}
