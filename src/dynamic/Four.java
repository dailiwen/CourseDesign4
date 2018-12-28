package dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 基因序列比较
 * https://blog.csdn.net/iteye_14050/article/details/82237560
 * @author dailiwen
 * @date 2018/12/21
 */
public class Four {
    /**
     * 分数规定
     */
    private static int[][] sort = new int[][]
            {
                {5, -1, -2, -1, -3},
                {-1, 5, -3, -2, -4},
                {-2, -3, 5, -2, -2},
                {-1, -2, -2, 5, -1},
                {-3, -4, -2, -1, 0}
            };
    /**
     * 通过键值对确定行和列，从而得到数据
     */
    private static Map<Character, Integer> key = new HashMap<>();

    /*
        初始化键值
     */
    static {
        key.put('A', 0);
        key.put('C', 1);
        key.put('G', 2);
        key.put('T', 3);
        key.put('-', 4);
    }

    /**
     * 列表数组
     */
    private static int[][] temp;
    static String gene1 = "AGTGATG";
    static String gene2 = "GTTAG";

    public static void main(String[] args) {
//        String gene1 = "AGTGATG";
//        String gene2 = "GTTAG";
        String gene1 = "ATG";
        String gene2 = "GAT";
        temp = new int[gene1.length() + 1][gene2.length() + 1];
        System.out.println(compare(gene1, gene2));
        print(temp);
//       System.out.println(compare(6, 4));
    }

    /**
     * 迭代实现
     * @param gene1
     * @param gene2
     * @return
     */
    public static int compare(String gene1, String gene2) {

        for (int i = 1; i <= gene1.length(); i++) {
            temp[i][0] = temp[i - 1][0] + sort[getValue(gene1.charAt(i - 1))][getValue('-')];
        }
        for (int i = 1; i <= gene2.length(); i++) {
            temp[0][i] = temp[0][i - 1] + sort[getValue('-')][getValue(gene2.charAt(i - 1))];
        }

        for(int i = 1; i <= gene1.length(); i++) {
            for(int j = 1;j <= gene2.length(); j++){
                //print(temp);
                temp[i][j] = max(
                        temp[i - 1][j - 1] + sort[getValue(gene1.charAt(i - 1))][getValue(gene2.charAt(j - 1))],
                        temp[i][j - 1] + sort[getValue('-')][getValue(gene2.charAt(j - 1))],
                        temp[i - 1][j] + sort[getValue(gene1.charAt(i - 1))][getValue('-')]
                );
            }
        }
        return temp[gene1.length()][gene2.length()];
    }

    /**
     * 递归实现
     * @param i
     * @param j
     * @return
     */
    public static int compare(int i, int j) {
        if (i < 0 && j < 0) {
            return 0;
        }
        if (i < 0 && j >= 0) {
            int sum = 0;
            for (int k = j; k >= 0 ; k--) {
                sum = sum + sort[getValue('-')][getValue(gene2.charAt(j))];
            }
            return sum;
        }
        if (j < 0 && i >= 0) {
            int sum = 0;
            for (int k = i; k >= 0 ; k--) {
                sum = sum + sort[getValue(gene1.charAt(i))][getValue('-')];
            }
            return sum;
        }
        return max(compare(i - 1, j) + sort[getValue(gene1.charAt(i))][getValue('-')],
                compare(i - 1, j - 1) + sort[getValue(gene1.charAt(i))][getValue(gene2.charAt(j))],
                compare(i, j - 1) + sort[getValue('-')][getValue(gene2.charAt(j))]
        );
    }

    /**
     * 求3个中最大值
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int max(int a, int b, int c) {
        int max = a;
        if (max < b) {
          max = b;
        }
        if (max < c) {
            max = c;
        }
        return max;
    }

    /**
     * 从map中获取value
     * @param mapKey
     * @return
     */
    public static int getValue(Character mapKey) {
        return key.get(mapKey);
    }

    /**
     * 二维数组打印
     * @param print
     */
    public static void print(int[][] print) {
        for (int i = 0; i < print.length; i++) {
            for (int j = 0; j < print[i].length; j++) {
                System.out.print(print[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
