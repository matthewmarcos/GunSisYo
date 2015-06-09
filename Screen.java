import java.util.*;

public class Screen {

    private int width, height;
    public int[] pixels, tiles;

    private int time, counter;
    private Random random;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        tiles = new int[64 * 64];
        random = new Random();

        for(int i = 0; i<64 * 64; i++) {
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
                int tileIndex = (((ii>>4)) & 63) + ((jj>>4) & 63)*64;
                pixels[j + i * width] = tiles[tileIndex];
            }
        }
    }

    public void clear() {
        for(int i=0 ; i<pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
