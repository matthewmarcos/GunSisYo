import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// @SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
    public static final long serialVersionUID = 1L;

    // Constants
    public final static int scale = 3;
    // Constants that are not final
    private static int height;
    private static int width;
    private static boolean isRunning;

    // Threads
    private Thread gameThread;

    // Top level containers
    private JFrame frame;
    // Components

    public Game() {
        width = 300;
        height = width / 16 * 9;
        isRunning = false;
        setPreferredSize(new Dimension(width*scale, height*scale));

        frame = new JFrame("Matthew's Game");
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void run() {
        while(isRunning) {
            System.out.println("running!");
        }
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
