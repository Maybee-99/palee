package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class searchIcon extends JLabel {

    public searchIcon() {
        setPreferredSize(new Dimension(30, 30));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawSearchIcon(g2);
    }

    private void drawSearchIcon(Graphics2D g2) {
        g2.setColor(new Color(66, 66, 66));
        g2.setStroke(new BasicStroke(1.6f));
        g2.drawOval(7, 7, 12, 12);

        g2.setColor(new Color(100, 100, 100));
        g2.setStroke(new BasicStroke(1.8f));
        g2.drawLine(17, 17, 25, 25);

        g2.setStroke(new BasicStroke(0));
        g2.drawLine(17, 17, 10, 20);
    }
}
