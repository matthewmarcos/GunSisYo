import java.util.*;

public class Screen {

    private final int MAP_SIZE = 64;
    private final int MAP_SIZE_MASK = MAP_SIZE - 1;
    private int width, height;
    public int[] pixels, tiles;

    private int time, counter;
    private Random random;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        tiles = new int[MAP_SIZE * MAP_SIZE];
        random = new Random();

        for(int i = 0; i<MAP_SIZE * MAP_SIZE; i++) {
            // Randomizes any color from black to white
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }

    public void render(int yOffset, int xOffset) {
        for(int y = 0; y < height; y++) {
            int yp = y - yOffset;
            if(yp < 0 || yp >= height) continue;
            for(int x = 0; x < width ; x++) {
                int xp = x - xOffset;
                if(xp < 0 || xp >= width) continue;
                pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
            }
        }
    }

    public void clear() {
        for(int i=0 ; i<pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
