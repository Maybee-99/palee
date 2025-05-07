package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class eye extends JLabel {

    private boolean showPassword = false;

    public eye() {
        setPreferredSize(new Dimension(40, 40));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPassword = !showPassword;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (showPassword) {
            drawEyeClosed(g2);
        } else {
            drawEyeOpen(g2);
        }
    }

    private void drawEyeOpen(Graphics2D g2) {
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawOval(10, 12, 20, 10);

        g2.setColor(new Color(70, 130, 180)); 
        g2.fillOval(17, 14, 6, 6);

        g2.setColor(Color.BLACK);
        g2.fillOval(19, 16, 2, 2);

        g2.setColor(Color.WHITE);
        g2.fillOval(20, 15, 1, 1);
    }

    private void drawEyeClosed(Graphics2D g2) {
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(10, 12, 20, 10);

        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.BLACK); 
        g2.drawLine(10, 12, 30, 22);

        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(150, 150, 150)); 
        g2.drawLine(12, 14, 14, 16);
        g2.drawLine(16, 13, 18, 15);
        g2.drawLine(22, 14, 24, 16);
        g2.drawLine(26, 13, 28, 15);
    }

    public boolean isShowPassword() {
        return showPassword;
    }
}
