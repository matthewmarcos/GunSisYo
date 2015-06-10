/*
    Created by: Joseph Matthew R. Marcos
    Purpose: To kill time and learn good game development practices
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// @SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
    public static final long serialVersionUID = 1L;

    // Constants
    public final static int scale = 3;
    private final double ns = (double)(1000000000 / 60);
    private final int SPEED = 3;
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
    private Keyboard key;

    public Game() {
        width = 300;
        height = width / 16 * 9;
        isRunning = false;
        setPreferredSize(new Dimension(width*scale, height*scale));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();//Raster of the image

        screen = new Screen(width, height);
        key = new Keyboard();

        addKeyListener(key);

        frame = new JFrame("Matthew's Game");
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // setKeyBindings();
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
        key.update();
        double modifier = 1;


        if(key.sprint) {
            modifier = 2;
        } else {
            modifier = 1;
        }
        if(key.up) {
            iOffset-=SPEED * modifier;
        }
        if(key.down) {
            iOffset+=SPEED * modifier;
        }
        if(key.left) {
            jOffset-=SPEED * modifier;
        }
        if(key.right) {
            jOffset+=SPEED * modifier;
        }
        // iOffset++;
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

    public void setKeyBindings() {
        // this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "pause");
		// this.getActionMap().put("pause", new AbstractAction(){
		// 	@Override
		// 	public void actionPerformed(ActionEvent e){
		//
		// 	}
		// });

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
