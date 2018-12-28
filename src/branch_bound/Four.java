package branch_bound;

/**
 * 数独求解
 * @author dailiwen
 * @date 2018/12/26
 */
public class Four {
    /**
     * 数独数组
     */
    private static int[][]  array = {
            {0, 5, 0, 0, 6, 0, 0, 0, 0},
            {1, 2, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 9, 7, 0, 0, 6, 0, 0},
            {0, 7, 0, 0, 0, 3, 0, 0, 0},
            {5, 9, 0, 0, 0, 0, 0, 7, 2},
            {0, 0, 0, 4, 0, 0, 0, 5, 0},
            {0, 0, 3, 0, 0, 1, 5, 0, 0},
            {0, 0, 0, 0, 0, 6, 0, 1, 7},
            {0, 0, 0, 0, 2, 0, 0, 3, 0}
    };


    public static void main(String[] args) {
        //return false代表无解
        System.out.println(backTrace());
    }

    /**
     * 回溯
     * @return
     */
    private static boolean backTrace() {
        int[] p = next();
        if(p == null) {
            printArray();
            return true;
        } else {
            for(int i = 1; i <= 9; i++) {
                if(check(p[0], p[1], i)) {
                    set(p[0], p[1], i);
                    //递归回溯
                    if(backTrace()) {
                        return true;
                    } else {
                        //上级返回false，当前点填写错误，置0，进行下一次循环
                        erase(p[0], p[1]);
                    }
                }
            }
            //代表当前递归情况有误
            return false;
        }
    }

    /**
     * 取出不为0的位置下标
     * @return
     */
    public static int[] next() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(array[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        //代表数独数组已经被填满，即题目完成
        return null;
    }

    /**
     * 将指定下标设为指定value
     * @param x
     * @param y
     * @param value
     */
    public static void set(int x, int y, int value) {
        array[x][y] = value;
    }

    /**
     * 将指定下标设定为0
     * @param x
     * @param y
     */
    public static void erase(int x, int y) {
        array[x][y] = 0;
    }

    /**
     * 从行，列，自己所属九宫格来验证是否符合标准
     * @param row
     * @param line
     * @param value
     * @return
     */
    public static boolean check(int row, int line, int value) {
        //行列判断
        for(int i = 0; i < 9; i++) {
            if(array[row][i] == value || array[i][line] == value) {
                return false;
            }
        }
        //获取自己所在九宫格的左上角坐标
        int currentRow = row - (row % 3);
        int currentLine = line - (line % 3);
        //遍历验证自己九宫格是否符合标准
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[currentRow + i][currentLine + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 打印矩阵
     */
    public static void printArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
