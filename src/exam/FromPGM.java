package exam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FromPGM extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {
        Scene scene = new Scene(initInterface(mainStage), 640, 640);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private VBox initInterface(Stage stage) throws IOException {
        final FileChooser fileChooser = new FileChooser();

        VBox app = new VBox();
        Label imageName = new Label("some text");
        Button loadImage = new Button("Select Image");
        ImageView userImage = new ImageView();

        ImageView last = new ImageView();

        loadImage.setOnAction(actionEvent -> {
            File picture = fileChooser.showOpenDialog(stage);
            try {
                FileInputStream choose = new FileInputStream(picture);
                Image photo = new Image(choose, 256, 256, false, false);
                userImage.setImage(photo);
                userImage.setFitWidth(256);
                userImage.setFitHeight(256);


                PixelReader pixRead = photo.getPixelReader();
                for (int x = 0; x < photo.getWidth(); x++)
                    for (int y = 0; y < photo.getHeight(); y++) {
                        Color photoColour = pixRead.getColor(x, y);
                    }



            } catch (IOException e) {
                e.printStackTrace();
            }
            imageName.setText(picture.getName());
        });

        app.getChildren().addAll(imageName, userImage, loadImage, last);
        return app;
    }
}
