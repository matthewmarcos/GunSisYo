package sprites;

import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class Sprite {

    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private Spritesheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, Spritesheet.tiles);

    public Sprite(int size, int x, int y, Spritesheet sheet) {
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        this.x = x*size;
        this.y = y*size;

        load();
    }

    private void load() {
        for(int y = 0; y< SIZE; y++) {
            for(int x=0; x<SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
