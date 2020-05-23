import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColourApp extends Application {
    @Override
    public void start(Stage mainStage) {
        Scene scene = new Scene(initInterface(), 640, 640);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private VBox initInterface() {

        //------ Creating Boxes ------//
        VBox big = new VBox();
        HBox topViews = new HBox();
        HBox botViews = new HBox();

        //------ First Image - Green ------//

        WritableImage greenBox = new WritableImage(120, 100);
        PixelWriter pw1 = greenBox.getPixelWriter();
        for (int x = 0; x < 120; x++)
            for (int y = 0; y < 100; y++)
                pw1.setColor(x, y, Color.GREEN);
        ImageView topOne = new ImageView(greenBox);

        //------ Second Image Green-Blue Gradient ------//
        WritableImage greenBlueGradient = new WritableImage(256, 256);
        PixelWriter pw2 = greenBlueGradient.getPixelWriter();
        for (int x = 0; x < 256; x++)
            for (int y = 0; y < 256; y++)
                pw2.setColor(x, y, Color.rgb(0, x, y));

        ImageView topTwo = new ImageView(greenBlueGradient);

        //------ Third Image Blue-Yellow Gradient ------//
        WritableImage blueYellowGradient = new WritableImage(256, 256);
        PixelWriter pw3 = blueYellowGradient.getPixelWriter();
        for (int x = 0; x < 256; x++)
            for (int y = 0; y < 256; y++)
                pw3.setColor(x, y, Color.rgb(x, x, y));

        ImageView topThree = new ImageView(blueYellowGradient);

        //---- Fourth Image HSB Stripe ------//
        WritableImage ColourStripe = new WritableImage(360, 100);
        PixelWriter pw4 = ColourStripe.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 100; y++)
                pw4.setColor(x, y, Color.hsb(x, y/99, 1));

        ImageView fourth = new ImageView(ColourStripe);

        //------ Fifth Image LCH Box ------//
        WritableImage LCHBox = new WritableImage(360, 100);
        PixelWriter pw5 = LCHBox.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 100; y++)
                pw5.setColor(x, y, LCH.colorFromLCH(80, y, x));

        ImageView fifth = new ImageView(LCHBox);

        //------ Sixth Image Photo ------//
        Image photo = new Image
                ("https://upload.wikimedia.org/wikipedia/commons/0/0a/The_Great_Wave_off_Kanagawa.jpg");
        ImageView botSixth = new ImageView(photo);
        botSixth.setFitHeight(256);
        botSixth.setFitWidth(256);

        //------ Seventh Image B&W Photo ------//
        PixelReader pixRead = photo.getPixelReader();
        WritableImage blackWhite = new WritableImage(256, 256);
        PixelWriter pw6 = blackWhite.getPixelWriter();
        for (int x = 0; x < 256; x++)
            for (int y = 0; y < 256; y++) {
                Color photoColour = pixRead.getColor(x, y);
                double greyness = 0.2126*photoColour.getRed() +
                        0.7152*photoColour.getGreen() + 0.0722*photoColour.getBlue();
                pw6.setColor(x, y, Color.hsb(0, 0, greyness));
            }
        ImageView botSeventh = new ImageView(blackWhite);

        topViews.getChildren().addAll(topOne, topTwo, topThree);
        botViews.getChildren().addAll(botSixth, botSeventh);
        big.getChildren().addAll(topViews, fourth, fifth, botViews);

        return big;
    }
}
