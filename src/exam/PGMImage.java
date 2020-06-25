package exam;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PGMImage {

    private int width;
    private int height;
    public int[][] picture;

    public PGMImage(int width, int height) {
        this.width = width;
        this.height = height;
        picture = new int[height][width];
    }

    public void setPixel(int x, int y, int colour) {
        picture[y][x] = colour;
    }

    public void print() {
        for (int[] line : picture) {
            for (int x : line)
                System.out.print(x + " ");
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void saveTo(String filename) throws IOException {
        try (PrintStream out = new PrintStream(filename, StandardCharsets.UTF_8)) {
            out.println("P2");
            out.println(getWidth() + " " + getHeight());
            out.println(255);
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++)
                    out.print(this.picture[y][x] + " ");
            }

        }
    }
}
