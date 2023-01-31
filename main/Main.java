package main;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Main extends JFrame {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new Frame();
    }     
}
