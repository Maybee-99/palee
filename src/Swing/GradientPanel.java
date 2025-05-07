package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    private Color startColor = Color.WHITE;  // Default start color
    private Color endColor = Color.LIGHT_GRAY; // Default end color

    // No-argument constructor for design mode
    public GradientPanel() {
        super();
        setPreferredSize(new Dimension(300, 300));
    }

    // Constructor with custom colors
    public GradientPanel(Color startColor, Color endColor) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
        setPreferredSize(new Dimension(300, 300));
    }

    // Getters and Setters for colors (useful for design mode)
    public Color getStartColor() {
        return startColor;
    }

    public void setStartColor(Color startColor) {
        this.startColor = startColor;
        repaint(); // Repaint the panel after updating the color
    }

    public Color getEndColor() {
        return endColor;
    }

    public void setEndColor(Color endColor) {
        this.endColor = endColor;
        repaint(); // Repaint the panel after updating the color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
