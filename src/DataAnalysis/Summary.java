package DataAnalysis;
import Database.connectDB;
import Model.message;
import Swing.RoundPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.sql.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.title.TextTitle;

public class Summary extends RoundPanel {

    int totalStudents = getTotalStudents();
    int awardedStudents = getAwardedStudents();
    int currentYear = java.time.LocalDate.now().getYear();

    public Summary() {
        setLayout(new BorderLayout());
        this.setCornerRadius(18);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.white);

        int percentAwarded = (int) Math.round((double) awardedStudents / totalStudents * 100);
        int percentNotAwarded = 100 - percentAwarded;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ນັກຮຽນທີ່ໄດ້ມາດຕະຖານ", percentAwarded);
        dataset.setValue("ນັກຮຽນທີ່ຄວນພັດທະນາ", percentNotAwarded);

        CustomRingPlot customPlot = new CustomRingPlot(dataset, totalStudents);
        customPlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}%"));
        customPlot.setLabelGap(0.02);
        customPlot.setLabelFont(new Font("Saysettha OT", Font.PLAIN, 16));
        customPlot.setSectionDepth(0.3);
        
        customPlot.setBackgroundPaint(Color.WHITE);
        customPlot.setOutlineVisible(false);
        customPlot.setShadowPaint(null);
        customPlot.setInteriorGap(0.05);
        customPlot.setCircular(true);
        customPlot.setSimpleLabels(false);

        JFreeChart chart = new JFreeChart(customPlot);
        chart.setTitle(new TextTitle("ຜົນການຮຽນໂດຍລວມໃນປີ " + currentYear,
                new Font("Saysettha", Font.BOLD, 18)));
        chart.getLegend().setItemFont(new Font("Saysettha OT", Font.PLAIN, 16));
        chart.getLegend().setBackgroundPaint(Color.white);  
        chart.setBackgroundPaint(Color.WHITE);
        chart.getLegend().setItemPaint(new Color(27, 31, 58));


        


        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    private int getTotalStudents() {
        return getCount("SELECT COUNT(DISTINCT stdID) FROM registration");
    }

    private int getAwardedStudents() {
        return getCount("SELECT COUNT(DISTINCT stdID) FROM award");
    }

    private int getCount(String query) {
        int count = 0;
        Connection con = connectDB.getConnect();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, new message("ການເຊື່ອມຕໍ່ຖານຂໍ້ມູນຫຼົ້ມເລວ"));
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private static class CustomRingPlot extends RingPlot {

        private final int totalStudents;

        public CustomRingPlot(DefaultPieDataset dataset, int totalStudents) {
            super(dataset);
            this.totalStudents = totalStudents;
        }

        @Override
        public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D anchor, PlotState state, PlotRenderingInfo info) {
            super.draw(g2, plotArea, anchor, state, info);

            g2.setFont(new Font("Saysettha OT", Font.BOLD, 16));
            g2.setColor(Color.BLACK);

            String text = String.valueOf("ນັກຮຽນທັງໝົດ:"+totalStudents+" ຄົນ");
            FontMetrics metrics = g2.getFontMetrics();
            Rectangle2D textBounds = metrics.getStringBounds(text, g2);

            double x = plotArea.getCenterX() - textBounds.getWidth() / 2;
            double y = plotArea.getCenterY() + textBounds.getHeight() / 4;

            g2.drawString(text, (float) x, (float) y);
        }
   
    }
}
