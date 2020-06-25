package exam;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class PGMApp extends Application {
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
        Button loadImage = new Button("Select Some Image");
        Button loadPGMImage = new Button("Select PGM Image");
        ImageView userImage = new ImageView();

        loadImage.setOnAction(actionEvent -> {
            File picture = fileChooser.showOpenDialog(stage);
            try {
                FileInputStream choose = new FileInputStream(picture);
                Image photo = new Image(choose, 256, 256, false, false);
                userImage.setImage(photo);
                userImage.setFitWidth(256);
                userImage.setFitHeight(256);
                PixelReader pixRead = photo.getPixelReader();
                PGMImage fromUser = new PGMImage(256, 256);
                for (int x = 0; x < fromUser.getWidth(); x++)
                    for (int y = 0; y < fromUser.getHeight(); y++) {
                        Color photoColour = pixRead.getColor(x, y);
                        double greyness = 0.2126 * photoColour.getRed() +
                                0.7152 * photoColour.getGreen() + 0.0722 * photoColour.getBlue();
                        double grey = greyness * 255;
                        int gr = (int) grey;
                        fromUser.setPixel(x, y, gr);
                    }
                fromUser.saveTo(picture.getName() + ".pgm");
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageName.setText(picture.getName());
        });

        loadPGMImage.setOnAction(actionEvent -> {
            File picture = fileChooser.showOpenDialog(stage);
            try {
                Path myPath = Path.of(picture.getName());
                System.out.println(myPath);
                Scanner scanner = new Scanner(myPath, StandardCharsets.UTF_8);
                scanner.next();
                int imageWidth = Integer.parseInt(scanner.next());
                int imageHeight = Integer.parseInt(scanner.next());
                int maxGrey = Integer.parseInt(scanner.next());

                WritableImage fromPGM = new WritableImage(imageWidth, imageHeight);
                PixelWriter pw = fromPGM.getPixelWriter();
                for (int x = 0; x < imageWidth; x++) {
                    for (int y = 0; y < imageHeight; y++) {
                        double greyness = Integer.parseInt(scanner.next());
                        System.out.println(greyness/255);
                        pw.setColor(x, y, Color.gray(greyness/255));
                    }
                }
                File outputFile = new File("output.png");
                BufferedImage bImage = SwingFXUtils.fromFXImage(fromPGM, null);
                ImageIO.write(bImage, "png", outputFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            imageName.setText(picture.getName());
        });

        app.getChildren().addAll(imageName, userImage, loadImage, loadPGMImage);
        return app;
    }
}
