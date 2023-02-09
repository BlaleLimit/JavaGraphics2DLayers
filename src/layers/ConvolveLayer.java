package layers;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ConvolveLayer extends LayerUI<JComponent> {
    private BufferedImage mOffscreenImage;
    private final BufferedImageOp mOperation;

    public ConvolveLayer() {
        float[] blurKernel = {
                1, 0, -1,
                2, 0, -2,
                1, 0, -1
        };
        mOperation = new ConvolveOp(
                new Kernel(3, 3, blurKernel),
                ConvolveOp.EDGE_NO_OP, null);
    }

    @Override
    public void paint (Graphics g, JComponent c) {
        int w = c.getWidth();
        int h = c.getHeight();

        if (w == 0 || h == 0) {
            return;
        }

        if (mOffscreenImage == null || mOffscreenImage.getWidth() != w || mOffscreenImage.getHeight() != h) {
            mOffscreenImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        }

        Graphics2D ig2 = mOffscreenImage.createGraphics();
        ig2.setClip(g.getClip());
        super.paint(ig2, c);
        ig2.dispose();

        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(mOffscreenImage, mOperation, 0, 0);
    }
}