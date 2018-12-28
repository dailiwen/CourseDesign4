package other;/**
 * @author dailiwen
 * @date 2018/12/26
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 主界面
 * @author dailiwen
 * @date 2018/12/26
 */
public class MainInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Button quickSort = new Button("快速排序");
        Button mergeSort = new Button("归并排序");
        quickSort.setLayoutX(80);
        quickSort.setLayoutY(200);
        quickSort.setFont(Font.font(25));
        quickSort.setPrefSize(150,150);
        mergeSort.setLayoutX(280);
        mergeSort.setLayoutY(200);
        mergeSort.setFont(Font.font(25));
        mergeSort.setPrefSize(150,150);
        Text text1 = new Text(165,80,"排序演示");
        text1.setFont(Font.font(45));
        Text text2 = new Text(120,130,"请按下按钮 选择不同的排序演示");
        text2.setFont(Font.font(20));

        pane.getChildren().addAll(quickSort, mergeSort, text1, text2);

        //快速排序界面跳转
        quickSort.setOnAction(e -> {
            Platform.runLater(() -> {
                new QuickSort().start(new Stage());
                primaryStage.hide();
            });
        });

        //归并排序界面跳转
        mergeSort.setOnAction(e -> {
            Platform.runLater(() -> {
                new MergeSort().start(new Stage());
                primaryStage.hide();
            });
        });


        Scene scene = new Scene(pane,500,400);
        primaryStage.setTitle("排序演示");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
