package recursive;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * 利用JavaFx绘制一个简单的分形树
 * @author dailiwen
 * @date 2018/12/25
 */
public class Two extends Application {
    BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        final Scene scene = new Scene(root,500, 400);
        scene.setFill(null);
        //获取舞台宽度
        double width = scene.getWidth();
        //获取舞台高度
        double height = scene.getHeight();

        //树干起始点
        double startX = width / 2;
        double startY = height;
        //树干终止点
        double endX = width / 2;
        double endY = height - height / 3;
        //树干绘制
        Line line = new Line(startX, startY, endX, endY);
        //利用Point2D类求两点距离
        Point2D p1 = new Point2D(startX, startY);
        Point2D p2 = new Point2D(endX, endY);
        //减少距离为分支递归做准备
        double distance = p1.distance(p2) / 4 * 3;
        //添加树干到舞台
        root.getChildren().add(line);
        //递归开始
        addBranch(endX, endY, distance);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addBranch(double startX, double startY, double distance) {
        if (distance < 10.0) {
            return;
        }

        distance = distance / 4 * 3;
        double angle = Math.toRadians((double) (Math.random() * 90.0));
        double cosY = Math.cos(angle) * distance;
        double sinX = Math.sin(angle) * distance;
        Line line1 = new Line(startX, startY, startX - sinX, startY - cosY);
        root.getChildren().add(line1);

        addBranch(startX - sinX, startY - cosY, distance);
        
        angle = Math.toRadians((double) (Math.random() * 90.0));
        cosY = Math.cos(angle) * distance;
        sinX = Math.sin(angle) * distance;
        Line line2 = new Line(startX, startY, startX + sinX, startY - cosY);
        root.getChildren().add(line2);

        addBranch(startX + sinX, startY - cosY, distance);
    }
}