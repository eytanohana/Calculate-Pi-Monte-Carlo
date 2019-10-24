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
    private AnchorPane anchorPane;
    @FXML
    private Label piLabel;
    @FXML
    private Button startStopBtn;
    @FXML
    private Rectangle rectangle;

    private LinkedList<Circle> dots;
    private Timeline timeline;
    private Random random;

    private double leftRectBound;
    private double rightRectBound;
    private double topRectBound;
    private double bottomRectBound;

    private double dotsInCircle;
    private double totalDots;

    private boolean go;


    public void initialize() {
        dots = new LinkedList<>();
        random = new Random();

        leftRectBound = rectangle.getLayoutX();
        rightRectBound = leftRectBound + rectangle.getWidth();
        topRectBound = rectangle.getLayoutY();
        bottomRectBound = topRectBound + rectangle.getHeight();

        go = false;
        dotsInCircle = 0;
        totalDots = 0;

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

    @FXML
    public void onClickStartStop() {
        if (!go) {
            go = true;
            startStopBtn.setText("Stop");
            timeline.play();
        } else {
            go = false;
            startStopBtn.setText("Start");
            timeline.pause();
        }
    }

    @FXML
    public void onClickReset() {
        timeline.stop();
        startStopBtn.setText("Start");
        go = false;
        totalDots = 0;
        dotsInCircle = 0;
        piLabel.setText("");
        anchorPane.getChildren().removeAll(dots);
        dots.clear();
    }

    private boolean pointInCircle(double x, double y) {
        return Math.pow(x - 200, 2) + Math.pow(y - 200, 2) < 22_500;
    }
}
