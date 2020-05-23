import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CircleApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(initInterface(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane initInterface() {
        GridPane gridPane = new GridPane();

        //------ Left Part ------//
        Label sliderText = new Label("Изменение радиуса круга");
        Slider radiusSlider = new Slider();
        radiusSlider.setMin(1);

        Label circleText = new Label("Изменение цвета круга");
        ColorPicker circleColour = new ColorPicker();
        circleColour.setValue(Color.BLACK);

        Label bgText = new Label("Изменение цвета фона");
        ColorPicker bgColour = new ColorPicker();

        //------ Right Part ------//
        Pane bg = new Pane();
        Circle cir = new Circle(100, 100, 1);
        cir.setRadius(50);
        bg.getChildren().add(cir);

        //------ GridPane Settings------//
        gridPane.add(sliderText, 0, 0);
        gridPane.add(radiusSlider, 0, 1);
        gridPane.add(circleText, 0, 2);
        gridPane.add(circleColour, 0, 3);
        gridPane.add(bgText, 0, 4);
        gridPane.add(bgColour, 0, 5);
        gridPane.add(bg, 1, 0, 1, 7);

        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints();
        gridPane.getColumnConstraints().addAll(column2, column1);

        RowConstraints row1 = new RowConstraints(0, 100, Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints();
        gridPane.getRowConstraints().addAll(row2, row2, row2, row2, row2, row2, row1);

        //------ Events and Binds ------//
        // bg.backgroundProperty().bind(bgColour.valueProperty());
        cir.radiusProperty().bind(radiusSlider.valueProperty());
        cir.fillProperty().bind(circleColour.valueProperty());

        cir.centerXProperty().bind(bg.widthProperty().divide(2));
        cir.centerYProperty().bind(bg.heightProperty().divide(2));

        radiusSlider.maxProperty().bind(Bindings.min
                (bg.heightProperty().divide(2), bg.widthProperty().divide(2)));

        return gridPane;
    }
}

