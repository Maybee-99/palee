package Component;

import Model.Model_Card;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class Card extends javax.swing.JPanel {

    private Color color1;
    private Color color2;

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    public void setData(Model_Card data) {
        lbTitle.setText(data.getTitle());
        lbValue.setText(data.getValues());
        lbDescription.setText(data.getDescription());
    }@Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float arc = 18f;
        RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setClip(roundRect);

        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(gradient);
        g2.fill(roundRect);

        g2.setColor(new Color(255, 255, 255, 50));
        int h = getHeight();
        g2.fillOval(getWidth() - (h / 2), 10, h, h);
        g2.fillOval(getWidth() - (h / 2) - 20, h / 2 + 20, h, h);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 0)); // Transparent
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
        g2.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbValue = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createMatteBorder(10, 15, 10, 0, new java.awt.Color(242, 242, 242)));
        setLayout(new java.awt.GridLayout(3, 1, 20, 0));

        lbTitle.setBackground(new java.awt.Color(142, 166, 180));
        lbTitle.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTitle.setText("title");
        lbTitle.setPreferredSize(new java.awt.Dimension(28, 20));
        add(lbTitle);

        lbValue.setBackground(new java.awt.Color(142, 166, 180));
        lbValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbValue.setForeground(new java.awt.Color(255, 255, 255));
        lbValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbValue.setText("content");
        add(lbValue);

        lbDescription.setBackground(new java.awt.Color(142, 166, 180));
        lbDescription.setFont(new java.awt.Font("Saysettha OT", 0, 15)); // NOI18N
        lbDescription.setForeground(new java.awt.Color(250, 250, 250));
        lbDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDescription.setText("description");
        add(lbDescription);
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValue;
    // End of variables declaration//GEN-END:variables
}
