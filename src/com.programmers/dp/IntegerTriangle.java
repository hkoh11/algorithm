package com.programmers.dp;

import java.util.Arrays;

class IntegerTriangle {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43105

    /**
     *               7
     *            3     8
     *         8     1     0
     *      2     7     4     4
     *   4     5     2     6     5
     *
     * [조건]
     * 1. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능
     * 2. 삼각형의 높이는 1 이상 500 이하
     * 3. 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수
     *
     *                   7
     *               10     15
     *           18      16     15
     *       20      25     20      19
     *   24      30      27     26     24
     *
     * [결과값]
     * 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때,
     * 거쳐간 숫자의 최댓값을 return
     */

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution1(triangle));
        System.out.println(solution2(triangle));
    }

    public static int solution1(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;

        int[][] dp = new int[height][height];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < height; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < triangle[i].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
            }
        }

        for (int i = 0; i < height; i++) {
            answer = Math.max(dp[height - 1][i], answer);
        }

        return answer;
    }

    public static int solution2(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];

            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
        }

        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
}