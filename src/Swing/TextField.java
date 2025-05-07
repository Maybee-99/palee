package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class TextField extends JTextField {

    private String hintText = "Username";
    private Color borderColor = new Color(200, 200, 200); // Default border color
    private Color hoverBorderColor = new Color(200, 200, 200); // Hover border color
    private Color activeBorderColor = new Color(54, 157, 248); // Active border color
    private float cornerRadius = 15f; // Smooth rounded corners
    private Color hintColor = new Color(153, 153, 153); // Placeholder color
    private boolean isHovered = false;
    private float borderWidth = 1.5f; // Uniform border width

    public TextField() {
        setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setOpaque(false); // Ensure custom painting works

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorderColor(activeBorderColor);
                borderWidth = 2.5f; // Increase border width when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorderColor(isHovered ? hoverBorderColor : new Color(220, 220, 220));
                borderWidth = 1.5f; // Reset border width when focus is lost
            }
        });

    }

    public String getPlaceHolder() {
        return hintText;
    }

    public void setPlaceHolder(String hintText) {
        this.hintText = hintText;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public Color getHintColor() {
        return hintColor;
    }

    public void setHintColor(Color hintColor) {
        this.hintColor = hintColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Always set the background to white
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(borderWidth / 2, borderWidth / 2,
                getWidth() - borderWidth, getHeight() - borderWidth, cornerRadius, cornerRadius));

        // Placeholder text
        if (getText().length() == 0 && hintText != null) {
            Insets ins = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            g2.setColor(hintColor);
            g2.drawString(hintText, ins.left, getHeight() / 2 + fm.getAscent() / 2 - 2);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Border with hover or focus effect
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth)); // Use the adjusted border width
        g2.draw(new RoundRectangle2D.Float(borderWidth / 2, borderWidth / 2,
                getWidth() - borderWidth, getHeight() - borderWidth, cornerRadius, cornerRadius));

        g2.dispose();
    }
}
