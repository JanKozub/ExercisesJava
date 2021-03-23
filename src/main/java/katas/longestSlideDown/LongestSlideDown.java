package katas.longestSlideDown;

/*
Lyrics...
Pyramids are amazing! Both in architectural and mathematical sense. If you have a computer, you can mess with
pyramids even if you are not in Egypt at the time. For example, let's consider the following problem. Imagine that you have a pyramid built of numbers, like this one here:

   /3/
  \7\ 4
 2 \4\ 6
8 5 \9\ 3
Here comes the task...
Let's say that the 'slide down' is the maximum sum of consecutive numbers from the top to the bottom of the pyramid. As you can see, the longest 'slide down' is 3 + 7 + 4 + 9 = 23

Your task is to write a function longestSlideDown (in ruby: longest_slide_down) that takes a pyramid representation as argument and returns its' largest 'slide down'. For example,

longestSlideDown [[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]] => 23
By the way...
My tests include some extraordinarily high pyramids so as you can guess, brute-force method is a bad idea unless you have a few centuries to waste.
You must come up with something more clever than that.

(c) This task is a lyrical version of the Problem 18 and/or Problem 67 on ProjectEuler.


 */

import java.util.Arrays;

public class LongestSlideDown {

    public static void main(String[] args) {
//        int[][] test = new int[][]{
//                                            {75},
//                                          {95, 64},
//                                        {17, 47, 82},
//                                      {18, 35, 87, 10},
//                                    {20, 04, 82, 47, 65},
//                                  {19, 01, 23, 75, 03, 34},
//                                {88, 02, 77, 73, 07, 63, 67},
//                              {99, 65, 04, 28, 06, 16, 70, 92},
//                            {41, 41, 26, 56, 83, 40, 80, 70, 33},
//                          {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
//                        {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
//                      {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
//                    {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
//                {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
//                {04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23},
//        };
//
//        System.out.println(longestSlideDown(test)); //1074

        int[][] test = new int[][]{{75},
                {95, 64},
                {17, 47, 82},
                {18, 35, 87, 10},
                {20, 4, 82, 47, 65},
                {19, 1, 23, 75, 3, 34},
                {88, 2, 77, 73, 7, 63, 67},
                {99, 65, 4, 28, 6, 16, 70, 92},
                {41, 41, 26, 56, 83, 40, 80, 70, 33},
                {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23},
        };

        int[][] test2 = new int[][]{
                {3},
                {7, 4},
                {2, 4, 6},
                {8, 5, 9, 3}
        };
        /*
                    {3},
                  {10, 7},
                 {12, 14, 13},
                {20, 19, 23, 16}

         */

        System.out.println(longestSlideDown(test)); //23
    }

    public static int longestSlideDown(int[][] pyramid) {
        for (int i = 0; i < pyramid.length - 1; i++) {
            sumNextRow(pyramid[i], pyramid[i + 1]);
        }

        int result = 0;
        int[] lastRow = pyramid[pyramid.length - 1];
        for (int i : lastRow) {
            if (i > result)
                result = i;
        }

        return result;
    }

    public static void sumNextRow(int[] lastRowSum, int[] nextRow) {
        nextRow[0] += lastRowSum[0];
        nextRow[nextRow.length-1] += lastRowSum[lastRowSum.length-1];

        for (int i = 1; i < nextRow.length-1; i++) {
            int sum1 = nextRow[i] + lastRowSum[i-1];
            int sum2 = nextRow[i] + lastRowSum[i];
            nextRow[i] = Math.max(sum1, sum2);
        }
    }

}
