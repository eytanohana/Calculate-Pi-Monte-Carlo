package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Controller {
    @FXML
    private Button btn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label piLabel;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Circle circle;


    private Timeline timeline;
    private Random random;
    private boolean go;

    private double leftRectBound;
    private double rightRectBound;
    private double topRectBound;
    private double bottomRectBound;

    public void initialize() {
        random = new Random();
        leftRectBound = rectangle.getLayoutX();
        rightRectBound = leftRectBound + rectangle.getWidth();
        topRectBound = rectangle.getLayoutY();
        bottomRectBound = topRectBound + rectangle.getHeight();
        System.out.println(leftRectBound);
        System.out.println(rightRectBound);
        System.out.println(topRectBound);
        System.out.println(bottomRectBound);
        go = false;

        timeline = new Timeline(new KeyFrame(Duration.ZERO, event1 -> {
            int x = (int) (Math.abs(random.nextInt()) % (rightRectBound - leftRectBound) + leftRectBound);
            int y = (int) (Math.abs(random.nextInt()) % (bottomRectBound - topRectBound) + topRectBound);

            anchorPane.getChildren().add(new Circle(x, y, 3));
        }), new KeyFrame(Duration.millis(100)));

        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    //    int x = (int) (Math.abs(random.nextInt()) % (rightRectBound - leftRectBound) + leftRectBound);
//    int y = (int) (Math.abs(random.nextInt()) % (bottomRectBound - topRectBound) + topRectBound);
    public void onClick(ActionEvent event) throws InterruptedException {
        if (!go) {
            go = true;
            timeline.play();
        } else {
            go = false;
            timeline.pause();
        }
    }
}
