package Swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import javax.swing.JPanel;


public class RoundPanel extends JPanel {

    private Color borderColor = Color.WHITE;
    private int borderThickness = 0;
    private float cornerRadius = 18f;
    public RoundPanel() {
        super();
        setOpaque(false);
    }
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }
    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.draw(new RoundRectangle2D.Float(borderThickness / 2.0f, borderThickness / 2.0f, getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius));
    }
     @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.draw(new RoundRectangle2D.Float(borderThickness / 2f, borderThickness / 2f, getWidth() - borderThickness,
                getHeight() - borderThickness, cornerRadius, cornerRadius));
        g2.dispose();
    }
    @Override
    public void updateUI() {
        super.updateUI();
        repaint();
    }
}