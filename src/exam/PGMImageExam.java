package exam;
import java.io.IOException;
import java.util.Random;

public class PGMImageExam {
    public static void main(String[] args) throws IOException {
        //------ RandomPixels Image ------//
        Random random = new Random();
        PGMImage randomPixels = new PGMImage(80, 60);
        for (int y = 0; y < randomPixels.getHeight(); y++) {
            for (int x = 0; x < randomPixels.getWidth(); x++) {
                randomPixels.setPixel(x, y, random.nextInt(256));
            }
        }
        randomPixels.saveTo("randomPixels.pgm");

        //------ Gradient Image ------//
        PGMImage gradient = new PGMImage(80, 60);
        for (int y = 0; y < gradient.getHeight(); y++) {
            for (int x = 0; x < gradient.getWidth(); x++) {
                gradient.setPixel(x, y, (x+y)%256);
            }
        }
        gradient.saveTo("gradient.pgm");
    }

}
