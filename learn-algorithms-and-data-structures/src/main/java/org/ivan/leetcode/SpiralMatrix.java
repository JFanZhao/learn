package org.ivan.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * https://leetcode.cn/problems/spiral-matrix/
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-04 22:23
 **/
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }

        int left = 0;
        int right = matrix[0].length-1;
        int top =0;
        int bottom = matrix.length -1;
        int numEle = matrix.length * matrix[0].length;
        while (numEle >= 1) {
            for (int i = left; i <= right && numEle >= 1; i++) {
                order.add(matrix[top][i]);
                numEle--;
            }
            top++;
            for (int i = top; i <=bottom && numEle >= 1; i++) {
                order.add(matrix[i][right]);
                numEle --;
            }
            right--;
            for (int i = right; i >=left && numEle >= 1; i--) {
                order.add(matrix[bottom][i]);
                numEle--;

            }
            bottom--;
            for (int i = bottom; i >=top && numEle >= 1; i--) {
                order.add(matrix[i][left]);
                numEle--;
            }
            left++;
        }

        return order;
    }
}
