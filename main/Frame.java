package main;

import layers.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.LayerUI;

import animation.GlassPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    Image mainIcon, subIcon;
    LayerUI<JComponent> layer = new BlurLayer();
    GlassPanel glassPanel;
    JPanel layerPanel = new JPanel();
    protected JButton[] jbutton = new JButton[6];
    String[] text = new String[] {"Blur", "Edge", "Spotlight",
                "Gradient", "Hybrid", "No Layer"};
    final BlurLayer bl = new BlurLayer();
    final ConvolveLayer cl = new ConvolveLayer();
    final HybridLayer hl = new HybridLayer();
    final SpotlightLayer sl = new SpotlightLayer();
    final WallpaperLayer wl = new WallpaperLayer();

    // Panels
    private JPanel createButtons(JButton[] jbutton, JPanel buttonPanel) {
        for (int i=0; i<jbutton.length; i++) {
            jbutton[i] = new JButton(text[i]);
            jbutton[i].setActionCommand(String.valueOf(i));
            jbutton[i].addActionListener(this);
            buttonPanel.add(jbutton[i]);
        }
        jbutton[5].setEnabled(false);
        return buttonPanel;
    }

    // Buttons
    private void setButtons(JButton[] jbutton, int index) {
        for (JButton button : jbutton) {
            button.setEnabled(true);
        }
        jbutton[index].setEnabled(false);
    }

    // Layer
    private JLayer<JComponent> setLayer(String action) {
        JLayer<JComponent> subLayer = new JLayer<>();
        switch (action) {
            case "0" -> subLayer = new JLayer<>(glassPanel, bl);
            case "1" -> subLayer = new JLayer<>(glassPanel, cl);
            case "2" -> subLayer = new JLayer<>(glassPanel, sl);
            case "3" -> subLayer = new JLayer<>(glassPanel, wl);
            case "4" -> subLayer = new JLayer<>(glassPanel, hl);
            case "5" -> subLayer = new JLayer<>(glassPanel);
        }
        return subLayer;
    }

    public Frame() {
        // Panels
        JPanel buttonPanel = createButtons(jbutton, new JPanel());
        
        // Filter Layer
        JFrame subFrame = new JFrame("Filter Layers");
        JLayer<JComponent> subLayer = new JLayer<>(glassPanel);
        layerPanel.setLayout(new BorderLayout());
        layerPanel.add(buttonPanel, BorderLayout.SOUTH);
        layerPanel.add(subLayer, BorderLayout.CENTER);
        subFrame.add(layerPanel);
        subFrame.pack();
        subFrame.setOpacity(0.95f);
        subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        subFrame.setLocation(600, 150);
        subFrame.setSize(600, 375+43);
        subFrame.setResizable(false);
        subFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JLayer<JComponent> subLayer = setLayer(action);
        setButtons(jbutton, Integer.parseInt(action));
        layerPanel.remove(layerPanel.getComponents()[1]);
        layerPanel.add(subLayer, BorderLayout.CENTER);
        // System.out.println(layerPanel.getComponentCount());
        layerPanel.revalidate();
        layerPanel.repaint();
    }
}




