/*
    Created by: Joseph Matthew R. Marcos
    Purpose: To kill time and learn good game development practices
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

// @SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
    public static final long serialVersionUID = 1L;

    // Constants
    public final static int scale = 3;
    private final double ns = (double)(1000000000 / 60);
    // Constants that are not final
    private static int height, width;
    private static boolean isRunning;
    private long lastTime;

    // Global Variables
    private double delta;
    private long now;
    private int time;
    private int iOffset;
    private int jOffset;

    // Threads
    private Thread gameThread;

    // Top level containers
    private JFrame frame;

    // Components
    private BufferedImage image; //draw image first before putting to bufferstrategy
    private int[] pixels;
    private Screen screen;

    public Game() {
        width = 300;
        height = width / 16 * 9;
        isRunning = false;
        setPreferredSize(new Dimension(width*scale, height*scale));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();//Raster of the image

        screen = new Screen(width, height);

        frame = new JFrame("Matthew's Game");
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void run() {
        initialize(); //initialize timers and globals
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1) {
                tick(); //update at 60 frames per second
                delta--;
                updates+=1;
            }
            tock(); //render at unlimited speed!
            frames++;
            if(System.currentTimeMillis() - timer >= 1000) {
                timer = System.currentTimeMillis();
                // System.out.println("updates: " + updates + " FPS: " + frames);
                frame.setTitle("Matthew's Game | FPS: " + frames + " | Updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void initialize() {
        lastTime = System.nanoTime();
        delta = 0d;
        time = 0;
    }

    private void tick() {
        // update
        iOffset++;
        // jOffset++;

    }

    private void tock () {
        // render
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        // screen.clear();
        screen.render(iOffset, jOffset);
        for(int i=0; i<pixels.length; i++) {
            pixels[i] = screen.pixels[i]; //BAD FUCKING PRACTICE WTF ARE YOU DOING????
        }

        // Getting graphics context of the BufferStrategy
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight()); //part of the Component class
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show(); // Memory clearing and Buffer swapping (blitting)

    }

    public void setVisible(boolean x) {
        frame.setVisible(x);
    }

    public synchronized void start() {
        gameThread = new Thread(this, "Display");
        isRunning = true;
        gameThread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try{
            gameThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at Game stop()");
        }
    }

    public static int getGameHeight() {
        return height;
    }

    public static int getGameWidth() {
        return width;
    }

    public static int getScale() {
        return scale;
    }
}
