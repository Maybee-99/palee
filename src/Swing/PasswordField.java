package Swing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class PasswordField extends JPasswordField {
    private String hintText = "Password";
    private Color borderColor = new Color(200, 200, 200); // Default border color
    private Color hoverBorderColor = new Color(200, 200, 200); // Hover border color
    private Color activeBorderColor = new Color(54, 157, 248); // Active border color
    private float cornerRadius = 18f; // Smooth rounded corners
    private Color hintColor = new Color(153, 153, 153); // Placeholder color
    private boolean isHovered = false;
    private float borderThickness = 1.5f; // Uniform border thickness

    public PasswordField() {
        setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setOpaque(false); // Ensure custom painting works

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorderColor(activeBorderColor);
                borderThickness = 2.5f; // Increase border thickness when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorderColor(isHovered ? hoverBorderColor : new Color(204, 204, 204));
                borderThickness = 1.5f; // Reset border thickness when focus is lost
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

        // Fill the background with white color
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(borderThickness / 2, borderThickness / 2, 
                getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius));

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
        g2.setStroke(new BasicStroke(borderThickness)); // Use the adjusted border thickness
        g2.draw(new RoundRectangle2D.Float(borderThickness / 2, borderThickness / 2, 
                getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius));

        g2.dispose();
    }
}
