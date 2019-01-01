package other;

import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
 * 快速排序界面
 * @author dailiwen
 * @date 2018/12/26
 */
public class QuickSort extends Application {
    private int[] sort;
    private StringBuffer webPrint = new StringBuffer();
    private int temp;
    private int count = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        //提示标题
        Label label = new Label("快速排序：输入数字，用英文逗号分隔");
        label.setFont(Font.font(25));
        label.setLayoutX(60);
        label.setLayoutY(10);
        //输入框
        TextField textField = new TextField();
        textField.setFont(Font.font(20));
        textField.setLayoutX(10);
        textField.setLayoutY(50);
        textField.setPrefWidth(460);
        textField.setPrefHeight(50);
        //演示按钮
        Button button = new Button("演示");
        button.setLayoutX(480);
        button.setLayoutY(50);
        button.setFont(Font.font(16));
        button.setPrefSize(70, 50);
        //滚动框
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(110);
        scrollPane.setPrefWidth(540);
        scrollPane.setPrefHeight(380);
        //返回按钮
        Button back = new Button("返回");
        back.setLayoutX(120);
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
            String input = textField.getText();
            String[] split = input.split(",");
            sort = new int[split.length];
            for (int i = 0; i < sort.length; i++) {
                sort[i] = Integer.valueOf(split[i]);
            }
            System.out.println("初始：" + Arrays.toString(sort));
            quickSort();
            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();
            webEngine.loadContent(String.valueOf(webPrint));
            scrollPane.setContent(browser);
        });
        pane.getChildren().addAll(label, textField, button, scrollPane, back);

        Scene scene = new Scene(pane, 550, 550);
        primaryStage.setTitle("快速排序");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void quickSort() {
        quickSort(0, sort.length - 1);
    }

    private void quickSort(int left, int right) {
        int i, j, t;
        if (left > right)
            return;
        //temp中存的就是基准数
        temp = sort[left];
        i = left;
        j = right;
        //顺序很重要，要先从右边开始找
        print(left, right, 0);
        while (i != j) {
            while (sort[j] >= temp && i < j) {
                j--;
                print(i, j, 1);
            }
            //再找左边的
            count = 0;
            while (sort[i] <= temp && i < j) {
                i++;
                print(i, j, 2);
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = sort[i];
                sort[i] = sort[j];
                sort[j] = t;
                print(i, j, 3);
            }
        }
        //最终将基准数归位
        sort[left] = sort[i];
        sort[i] = temp;
        print(i, j, 4);
        quickSort(left, i - 1);//继续处理左边的，这里是一个递归的过程
        quickSort(i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    private void print(int left, int right, int type) {
        if (type == 0) {
            webPrint.append("<h2>基准线为：" + temp + "</h2>");
        }
        webPrint.append("<p>");
        for (int i = 0; i < sort.length; i++) {
            if (i == left) {
                webPrint.append("<span style=\"color:red;\">" + sort[i] + "&nbsp&nbsp</span>");
            } else if (i == right) {
                webPrint.append("<span style=\"color:blue;\">" + sort[i] + "&nbsp&nbsp</span>");
            } else {
                webPrint.append("<span>" + sort[i] + "&nbsp&nbsp</span>");
            }
        }
        switch (type) {
            case 0: {
                webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp新的递归开始</span>");
                break;
            }
            case 1: {
                webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp右端右移</span>");
                break;
            }
            case 2: {
                if (count == 0) {
                    webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp右端小于基准线,右端停止移动，左端左移</span>");
                    count++;
                }
                else {
                    webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp左端左移</span>");
                }
                break;
            }
            case 3: {
                webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp左端大于基准线,左端停止移动，左右交换</span>");
                break;
            }
            case 4: {
                webPrint.append("<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp左右端重叠,与基准交换</span>");
                break;
            }
        }
        webPrint.append("</p>");
    }
}
