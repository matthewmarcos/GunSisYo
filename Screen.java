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

    public void render(int iOffset, int jOffset) {
        // counter++;
        // if(counter%50==0) {
        //     time++;
        // }
        for(int i = 0; i<height ; i++) {
            int ii = i + iOffset;
            // if(ii<0 || ii>=height) break;
            for(int j = 0; j<width ; j++) {
                int jj = j+jOffset;
                // if(jj<0 || jj>=width) break;
                int tileIndex = (((ii>>4)) & MAP_SIZE_MASK) + ((jj>>4) & MAP_SIZE_MASK)*MAP_SIZE;
                // pixels[j + i * width] = tiles[tileIndex];
                pixels[j + i * width] = Sprite.grass.pixels[(jj&15) + (ii&15) * Sprite.grass.SIZE];
            }
        }
    }

    public void clear() {
        for(int i=0 ; i<pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
