import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private int[] pixels;
    private Spritesheet sheet;

    public Sprite(int size, Spritesheet sheet, int x, int y) {
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        this.x = x*size;
        this.y = y*size;

        load();
    }

    private void load() {
        for(int i=0; i<SIZE; i++) {
            for(int j=0; i<SIZE; j++) {
                pixels[i+j*SIZE] = sheet.pixels[(j + this.x) + (i + this.y) * sheet.getSize()];
            }
        }
    }
}
