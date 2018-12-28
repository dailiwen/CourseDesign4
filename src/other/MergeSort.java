package other;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 归并排序界面
 * https://www.cnblogs.com/jingmoxukong/p/4308823.html
 * @author dailiwen
 * @date 2018/12/26
 */
public class MergeSort extends Application {
    int[] sort;
    StringBuffer webPrint = new StringBuffer();
    int gap;
    int count = 0;
    List<String> tree = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        //提示标题
        Label label = new Label("归并排序：输入数字，用英文逗号分隔");
        label.setFont(Font.font(25));
        label.setLayoutX(110     );
        label.setLayoutY(10);
        //输入框
        TextField textField = new TextField();
        textField.setFont(Font.font(20));
        textField.setLayoutX(10);
        textField.setLayoutY(50);
        textField.setPrefWidth(560);
        textField.setPrefHeight(50);
        //演示按钮
        Button button = new Button("演示");
        button.setLayoutX(580);
        button.setLayoutY(50);
        button.setFont(Font.font(16));
        button.setPrefSize(70, 50);
        //滚动框
        ScrollPane scrollPane = new ScrollPane();
        //水平绝不滚动
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(110);
        scrollPane.setPrefWidth(640);
        scrollPane.setPrefHeight(380);
        //返回按钮
        Button back = new Button("返回");
        back.setLayoutX(180);
        back.setLayoutY(500);
        back.setFont(Font.font(25));
        back.setPrefSize(320, 50);
        back.setOnAction(e -> {
            Platform.runLater(() -> {
                new MainInterface().start(new Stage());
                primaryStage.hide();
            });
        });

        button.setOnAction(e -> {
            //获取输入框输入，处理为数组
            String input = textField.getText();
            String[] split = input.split(",");
            sort = new int[split.length];
            for (int i = 0; i < sort.length; i++) {
                sort[i] = Integer.valueOf(split[i]);
            }
            sort(sort);

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();
            webEngine.loadContent(String.valueOf(webPrint));
            scrollPane.setContent(browser);
        });
        pane.getChildren().addAll(label, textField, button, scrollPane, back);

        Scene scene = new Scene(pane, 650, 550);
        primaryStage.setTitle("归并排序");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * 归并时改变排序，进行归并
     * @param array
     * @param low
     * @param mid
     * @param high
     */
    public void Merge(int[] array, int low, int mid, int high) {
        int i = low; // i是第一段序列的下标
        int j = mid + 1; // j是第二段序列的下标
        int k = 0; // k是临时存放合并序列的下标
        int[] array2 = new int[high - low + 1]; // array2是临时合并序列

        // 扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            // 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
            if (array[i] <= array[j]) {
                array2[k] = array[i];
                i++;
                k++;
            } else {
                array2[k] = array[j];
                j++;
                k++;
            }
        }

        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            array2[k] = array[i];
            i++;
            k++;
        }

        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= high) {
            array2[k] = array[j];
            j++;
            k++;
        }

        // 将合并序列复制到原始序列中
        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }
    }

    /**
     * 现有的数组进行归并
     * @param array
     * @param gap
     * @param length
     */
    private void MergePass(int[] array, int gap, int length) {
        int i;

        // 归并gap长度的两个相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            Merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }

        // 余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            Merge(array, i, i + gap - 1, length - 1);
        }
    }

    /**
     * 归并排序入口，传入数组
     * @param list
     */
    private void sort(int[] list) {
        printBegin(list);
        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            print(gap, list);
            MergePass(list, gap, list.length);
        }
        printEnd(list);
    }

    /**
     * 过程打印
     * @param gap
     * @param list
     */
    private void print(int gap, int[] list) {
        List<int[]> save = new ArrayList<>();
        int circle;
        if (list.length % gap == 0) {
            circle = list.length / gap;
        } else {
            circle = list.length / gap + 1;
        }
        int count = 0;
        for (int i = 0; i < circle; i++) {
            int[] temp;
            if (i == circle - 1) {
                temp = new int[list.length - gap * i];
            } else {
                temp = new int[gap];
            }
            int count1 = 0;
            while (count1 < gap) {
                if (count == list.length)
                    break;
                temp[count1] = list[count];
                count++;
                count1++;
            }
            save.add(temp);
        }
        webPrint.append("<p>");
        for (int i = 0; i < save.size(); i++) {
            webPrint.append("<span style=\"text-decoration:underline\">");
            for (int j = 0; j < save.get(i).length; j++) {
                if (save.get(i).length - 1 == j)
                    webPrint.append(save.get(i)[j]);
                else
                    webPrint.append(save.get(i)[j] + "&nbsp&nbsp");
            }
            webPrint.append("</span>");
            webPrint.append("<span>&nbsp&nbsp</span>");
        }
        if (gap == 1) {
            webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp最开始把数组划分为" + save.size() + "个数组，每个数组中一个数</span>");
        } else if (list.length % gap == 0) {
            webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp合并为每组" + gap + "个数</span>")
                    .append("，共" + save.size() + "组</span>")
                    .append("，组内排序");
        } else {
            webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp合并为每组" + gap + "个数</span>")
                    .append("，共" + (save.size() - 1) + "组</span>")
                    .append("，最后一组" + (list.length - gap * (circle - 1)) + "个数")
                    .append("，组内排序");
        }
        webPrint.append("</p>");
    }

    /**
     * 初始数组打印
     * @param list
     */
    private void printBegin(int[] list) {
        webPrint.append("<p>");
        webPrint.append("<b>初始数组：</b>");
        for (int i = 0; i < list.length; i++) {
            webPrint.append("<b>" + list[i] + "&nbsp&nbsp</b>");
        }
        webPrint.append("</p>");
    }

    /**
     * 结束数组打印
     * @param list
     */
    private void printEnd(int[] list) {
        webPrint.append("<p>");
        webPrint.append("<b>最后结果：</b>");
        for (int i = 0; i < list.length; i++) {
            webPrint.append("<b>" + list[i] + "&nbsp&nbsp</b>");
        }
        webPrint.append("</p>");
    }
}
