import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class Spritesheet {

    private String path;
    private final int SIZE; //size of the spritesheet
    public int[] pixels;

    public Spritesheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch(IOException e) {
            System.out.println("IOEXCEPTION! @ SPRITESHEET");
        }

    }

    public int getSize() {
        return SIZE;
    }

}
