package dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 租船问题
 * 动态规划实现。也可以看成一个加权图，利用Floyd等算法，求最短路径
 * 划分为分别往后求为从起始站到后面每个站点的最划算情况
 * @author dailiwen
 * @date 2018/12/21
 */
public class Two {
    /**
     * 船的总站数
     */
    private static int total;
    /**
     * 距离存储数组
     * 代表含义：从列的数字看向行的数字，代表从y站(起点)出发到x站(终点)，数字即代表当前走法的价值
     */
    private static int[][] distanceAry;
    /**
     * 分别存储每个第一个站到后面站的最小价值
     */
    private static int[] temp;

    public static void main(String[] args) {
        /*
            标准填写
            4
            5 14 23
            5 12
            8
         */
        Scanner scanner = new Scanner(System.in);
        total = scanner.nextInt();
        scanner.nextLine();
        distanceAry = new int[total][total];
        temp = new int[total];
        for (int i = 0; i < total - 1; i++) {
            //处理输入，形成二维数组
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            for (int j = 0; j < split.length; j++) {
                distanceAry[i][j + 1 + i] = Integer.valueOf(split[j]);
            }
        }
        print(distanceAry);
        System.out.println("---------");
        System.out.println(charter());
        System.out.println("---------");
        System.out.println(Arrays.toString(temp));

    }

    /**
     * 租船问题动态规划实现
     * @return
     */
    public static int charter() {
        //i每一次循环代表终点往后移动一个点
        for (int i = 0; i < total; i++) {
            int minNum = distanceAry[0][i];
            //j循环遍历得temp数组中最小值，同时也相当于代表起点往后移
            for (int j = 0; j < i; j++) {
                /*
                  动态规划体现
                  因为temp数组的0号位置始终为0，所有用0号位来求直达移动
                  而temp数组0号位之后的分别存储的是最短的移动距离
                  在 之前求得的最小值 和 当前起点的直达价格+前面段的最小值 之中进行比较
                  得到当前的最下移动距离
                 */
                minNum = min(minNum, temp[j] + distanceAry[j][i]);
        }
            //记录进入数组
            temp[i] = minNum;
        }
        //返回最小价值数组最后一个即是最后答案
        return temp[total - 1];
    }

    /**
     * 两个数找最小
     * @param a
     * @param b
     * @return
     */
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * 二维数组打印
     * @param print
     */
    public static void print(int[][] print) {
        for (int i = 0; i < print.length; i++) {
            for (int j = 0; j < print[i].length; j++) {
                System.out.print(print[i][j] + " ");
            }
            System.out.println();
        }
    }
}
