package recursive;

import java.util.Scanner;

/**
 * 分别利用递归，备忘录，迭代方法求二项式系数
 * @author dailiwen
 * @date 2018/12/25
 */
public class One {

    private static int[][] binomial;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Ckn = C35
        System.out.print("输入k = ");
        int k = input.nextInt();
        System.out.print("输入n = ");
        int n = input.nextInt();
        System.out.println("递归方法所得二项式系数为：");
        System.out.println(getBinomialRec(k, n));

        binomial = new int[k + 1][n + 1];
        System.out.println("备忘录方法所得二项式系数为：");
        System.out.println(getBinomialRem(k, n));

        System.out.println("迭代方法所得二项式系数为：");
        System.out.println(getBinomialIter(k, n));
    }

    /**
     * 递归方法
     * @param k
     * @param n
     * @return -1 填写有误
     */
    public static int getBinomialRec(int k, int n) {
        int value;
        if(k > n)
            return -1;
        if(k == 0 || n == k) {
            value = 1;
        } else {
            value = getBinomialRec(k - 1,n - 1) + getBinomialRec(k,n - 1);
        }
        return value;
    }

    /**
     * 备忘录方法
     * @param k
     * @param n
     * @return
     */
    public static int getBinomialRem(int k, int n) {
        if(n < k)
            return -1;
        if(k == n)
            binomial[k][n] = 1;
        if(k == 0)
            binomial[0][n] = 1;
        if(binomial[k][n] != 0)
            return binomial[k][n];

        else
            binomial[k][n] = getBinomialRem(k - 1, n - 1) + getBinomialRem(k, n - 1);

        return binomial[k][n];
    }

    /**
     * 迭代方法
     * @param k
     * @param n
     * @return
     */
    public static int getBinomialIter(int k, int n) {
        int[][] r = new int[n + 1][k + 1];
        //因为若i为
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                if(i == j || j == 0)
                    r[i][j] = 1;
                else
                    r[i][j] = r[i - 1][j - 1] + r[i - 1][j];
            }
//            print(r);
        }

        return r[n][k];
    }

    /**
     * 打印结果
     * @param printAry
     */
    public static void print(int[][] printAry) {
        for (int i = 0; i < printAry.length; i++) {
            for (int j = 0; j < printAry[i].length; j++) {
                System.out.print(printAry[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
