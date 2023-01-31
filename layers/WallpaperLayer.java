package layers;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;

public class WallpaperLayer extends LayerUI<JComponent> {
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = c.getWidth();
        int h = c.getHeight();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.setPaint(new GradientPaint(0, 0, new Color(100, 0, 0), 0, h, new Color(100, 255, 255)));
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }
}
