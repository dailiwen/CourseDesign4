package traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 3水杯倒水问题，广度优先遍历
 * @author dailiwen
 * @date 2018/12/20
 */
public class Two {
    /**
     * 水壶的初始水量
     */
    private static int[] present = {0, 0, 8};
    /**
     * 水壶的容量
     */
    private static int[] capacity = {3, 5, 8};
    /**
     * 存放合适节点的ArrayList，用于打印
     */
    private static List<int[]> saveList = new ArrayList<>();
    /**
     * 动态存放水杯状态队列，用于广度优先遍历队列(先进先出)
     */
    private static Queue<int[]> cupQueue = new LinkedList<>();

    public static void main(String[] args) {
        Two cup = new Two();
        saveList.add(present);
        cup.bfs(present);
    }

    /**
     * 广度优先遍历
     * @param present
     */
    private void bfs(int[] present) {
        //i代表要倒出，j代表要接水
        for(int i = 0; i < present.length; i++) {
            //水壶可以倒出水
            if(present[i] != 0) {
                for(int j = 0; j < present.length; j++) {
                    //水壶可以接收水
                    if(present[j] < capacity[j] && j != i) {
                        int[] temp = new int[3];
                        System.arraycopy(present, 0, temp, 0, present.length);
                        //当倒完后水杯不够装时
                        if(temp[j] + temp[i] > capacity[j]) {
                            //倒水水杯未倒完
                            temp[i] = temp[j] + temp[i] - capacity[j];
                            //接水水杯已接满
                            temp[j] = capacity[j];
                        } else {
                            //接水水杯接完水
                            temp[j] = temp[j] + temp[i];
                            //倒水水杯已倒完
                            temp[i] = 0;
                        }
                        if(!isAppear(temp)) {
                            saveList.add(temp);
                            //加入节点队列中下一次进行倒水操作
                            cupQueue.offer(temp);
                            if(isEnd(temp)) {
//                                queue = new LinkedList<>();
                                print();
                            }
                        }
                    }
                }
            }
        }

        while(cupQueue.size() != 0){
            //顶部出队列，同时传值给方法，递归调用下一节点
            bfs(cupQueue.poll());
        }
    }

    /**
     * 当当前水杯状态数组中出现4时，符合题目要求停止
     * @param temp
     * @return
     */
    private boolean isEnd(int[] temp) {
        for(int i = 0; i < temp.length; i++){
            if(temp[i] == 4)
                return true;
        }
        return false;
    }

    /**
     * 避免重复节点出现
     * @param temp
     * @return
     */
    private boolean isAppear(int[] temp) {
        for(int i = 0; i < saveList.size(); i++) {
            if(Arrays.equals(saveList.get(i), temp))
                return true;
        }
        return false;
    }

    /**
     * 数组打印
     */
    private void print() {
        for(int i = 0; i < saveList.size(); i++) {
            for(int j = 0; j < saveList.get(i).length; j++) {
                System.out.print(saveList.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}
