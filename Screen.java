
public class Screen {

    private int width, height;
    public int[] pixels;

    private int time, counter;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
    }

    public void render() {
        counter++;
        if(counter%50==0) {
            time++;
        }
        pixels[time + time * width] = 0xff00ff;
        // for(int i = 0; i<height ; i++) {
        //     for(int j = 0; j<width ; j++) {
        //         pixels[40 + 30 * width] = 0xff00ff;
        //     }
        // }
    }

    public void clear() {
        for(int i=0 ; i<pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
