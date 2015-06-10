import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class Spritesheet {

    private String path;
    public final int SIZE; //size of the spritesheet
    public int[] pixels;

    public static Spritesheet tiles = new Spritesheet("assets/SpriteSheet.png", 256);

    public Spritesheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.load();
    }

    private void load() {
        try {
            // BufferedImage image = ImageIO.read(Spritesheet.class.getResource(this.path));
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(this.path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            System.out.println("Loaded Spritesheet " + this.path);
        } catch(IOException e) {
            System.out.println("IOEXCEPTION! @ SPRITESHEET");
        }

    }

    public int getSize() {
        return SIZE;
    }

}
