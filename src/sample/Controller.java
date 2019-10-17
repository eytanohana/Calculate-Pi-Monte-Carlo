package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

import java.util.Random;

public class Controller {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label piLabel;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Circle circle;

    private Random random;

    public void initialize() {
        random = new Random();
        double leftRectangleBound = rectangle.getLayoutX();
        double rightRectangleBound = leftRectangleBound + rectangle.getWidth();
        double topRectangleBound = rectangle.getLayoutY();
        double bottomRectangleBound = topRectangleBound + rectangle.getHeight();
        System.out.println(leftRectangleBound);
        System.out.println(rightRectangleBound);
        System.out.println(topRectangleBound);
        System.out.println(bottomRectangleBound);
    }
}
