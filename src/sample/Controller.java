package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

public class Controller {
    @FXML
    private Button startStopBtn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label piLabel;
    @FXML
    private Rectangle rectangle;


    private Timeline timeline;
    private Random random;
    private boolean go;

    private double leftRectBound;
    private double rightRectBound;
    private double topRectBound;
    private double bottomRectBound;
    private double dotsInCircle;
    private double totalDots;
    private LinkedList<Circle> dots;


    public void initialize() {
        random = new Random();
        dots = new LinkedList<>();

        leftRectBound = rectangle.getLayoutX();
        rightRectBound = leftRectBound + rectangle.getWidth();
        topRectBound = rectangle.getLayoutY();
        bottomRectBound = topRectBound + rectangle.getHeight();

        go = false;
        dotsInCircle = 0;
        totalDots = 0;

        startStopBtn.setText("Start");

        timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            int x = (int) (Math.abs(random.nextInt()) % (rightRectBound - leftRectBound) + leftRectBound);
            int y = (int) (Math.abs(random.nextInt()) % (bottomRectBound - topRectBound) + topRectBound);
            Circle dot = new Circle(x, y, 2);
            if (pointInCircle(x, y)) {
                dotsInCircle++;
                dot.setFill(Paint.valueOf("#2f416d"));
            } else {
                dot.setFill(Paint.valueOf("#f0134d"));
            }
            totalDots++;
            dots.add(dot);
            anchorPane.getChildren().add(dot);
            piLabel.setText(String.format("%.9f", 4 * dotsInCircle / totalDots));
        }), new KeyFrame(Duration.millis(5)));

        timeline.setCycleCount(Timeline.INDEFINITE);
    }


    private boolean pointInCircle(double x, double y) {
        return Math.pow(x - 200, 2) + Math.pow(y - 200, 2) < Math.pow(150, 2);
    }

    private double convert(double x) {
        return -9.95 * x + 1000;
    }

    public void onClickStartStop(ActionEvent event) {
        if (!go) {
            startStopBtn.setText("Stop");
            go = true;
            timeline.play();
        } else {
            startStopBtn.setText("Start");
            go = false;
            timeline.pause();
        }
    }

    public void onClickReset(ActionEvent event) {
        timeline.pause();
        startStopBtn.setText("Start");
        go = false;
        totalDots = 0;
        dotsInCircle = 0;
        piLabel.setText("");
        anchorPane.getChildren().removeAll(dots);
    }
}
