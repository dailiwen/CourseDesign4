package traversal;

/**
 * 九宫格问题，行列斜和相同
 * 深度优先遍历实现
 * @author dailiwen
 * @date 2018/12/21
 */
public class Four {
    /**
     * 初始数据数组
     */
    private static int[] arry = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    /**
     * 判断数组，用于判断该数组是否被填写过
     * true:未被使用
     */
    private static boolean[] jug = new boolean[9];
    /**
     * 未判断的填写满的数组
     */
    private static int[][] result = new int[3][3];
    /**
     * 计数君
     */
    private static int count = 1;


    public static void main(String[] args) {
        int[][] tra = new int[3][3];
        for (int i = 0; i < jug.length; i++) {
            jug[i] = true;
        }
        dfs(0);
    }

    /**
     * 深度优先遍历所有可能情况
     * @param step 遍历层次，也可代表9个格子
     */
    public static void dfs(int step) {
        //当到了第9步，即证明九宫格已被填写完整，进行判断和打印
        if (step == 9) {
            if (isEqual(result)) {
                print(result);
            }
        }
        //深度优先遍历，填写九宫格
        for (int i = 0; i < 9; i++) {
            //若为true代表该数字未被使用过，可以填入九宫格
            if (jug[i]) {
                //标记为使用过
                jug[i] = false;
                //根据步数来填写九宫格
                result[step / 3][step % 3] = arry[i];
                //递归深度优先遍历
                dfs(step + 1);
                //将当前节点的数据标记为未使用
                jug[i] = true;
            }
        }
        //虽然没有return但当前方法执行完也会回到上个递归
    }

    /**
     * 暴力判断行竖列相加是否相等
     * @param jugAry
     * @return
     */
    public static boolean isEqual(int[][] jugAry) {
        //第一排求和
        int sum = jugAry[0][0] + jugAry[0][1] + jugAry[0][2];
        //第二排求和
        if (sum != jugAry[1][0] + jugAry[1][1] + jugAry[1][2])
            return false;
        //第三排求和
        if (sum != jugAry[2][0] + jugAry[2][1] + jugAry[2][2])
            return false;
        //第一列求和
        if (sum != jugAry[0][0] + jugAry[1][0] + jugAry[2][0])
            return false;
        //第二列求和
        if (sum != jugAry[0][1] + jugAry[1][1] + jugAry[2][1])
            return false;
        //第三列求和
        if (sum != jugAry[0][2] + jugAry[1][2] + jugAry[2][2])
            return false;
        //主对角线求和
        if (sum != jugAry[0][0] + jugAry[1][1] + jugAry[2][2])
            return false;
        //次对角线求和
        if (sum != jugAry[0][2] + jugAry[1][1] + jugAry[2][0])
            return false;
        return true;
    }

    /**
     * 打印结果
     * @param printAry
     */
    public static void print(int[][] printAry) {
        System.out.println("第" + count + "种情况是：");
        count++;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(printAry[i][j] + " ");
            }
            System.out.println();
        }
    }
}
