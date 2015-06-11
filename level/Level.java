package level;
import sprite.*;

public class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    public void update() {
    /*
        Update the movement of the entities
        Update everything @ 60UP/s gahh!
    */

    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {}
    protected void generateLevel() {}
    protected void loadLevel(String path) {}
}
