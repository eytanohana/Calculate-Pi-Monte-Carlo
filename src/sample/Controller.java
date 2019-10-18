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

import java.util.Map;
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
    private double dotsInCircle;
    private double totalDots;

    public void initialize() {
        random = new Random();

        leftRectBound = rectangle.getLayoutX();
        rightRectBound = leftRectBound + rectangle.getWidth();
        topRectBound = rectangle.getLayoutY();
        bottomRectBound = topRectBound + rectangle.getHeight();

        go = false;
        dotsInCircle = 0;
        totalDots = 0;

        btn.setText("Start");

        timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            int x = (int) (Math.abs(random.nextInt()) % (rightRectBound - leftRectBound) + leftRectBound);
            int y = (int) (Math.abs(random.nextInt()) % (bottomRectBound - topRectBound) + topRectBound);
            if (inCircle(x, y)) {
                dotsInCircle++;
            }
            totalDots++;
            anchorPane.getChildren().add(new Circle(x, y, 3));
            piLabel.setText(String.format("%.9f", 4 * dotsInCircle / totalDots));
        }), new KeyFrame(Duration.millis(10)));

        timeline.setCycleCount(Timeline.INDEFINITE);
    }


    public void onClick(ActionEvent event) {
        if (!go) {
            btn.setText("Stop");
            go = true;
            timeline.play();
        } else {
            btn.setText("Start");
            go = false;
            timeline.pause();
        }
    }


    private boolean inCircle(double x, double y) {
        return Math.pow(x - 200, 2) + Math.pow(y - 200, 2) < Math.pow(150, 2);
    }
}
