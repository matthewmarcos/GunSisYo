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

public class Keyboard implements KeyListener{

    private boolean[] keys;
    public boolean up, down, left, right;

    public Keyboard() {
        keys = new boolean[120];
    }

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys [KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys [KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys [KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys [KeyEvent.VK_D];

        // for(int i=0; i<keys.length ; i++) {
        //     if(keys[i]) {
        //         System.out.println(i);
        //     }
        // }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
