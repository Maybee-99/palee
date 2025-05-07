package DataAnalysis;

import Database.connectDB;
import Swing.RoundPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.TextAnchor;

public class BarChart extends RoundPanel {

    Connection con = connectDB.getConnect();

    public BarChart() {
        initComponents();
        JFreeChart barChart = createBarChart();
        this.setCornerRadius(18);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 500));

        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(900, 520));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
    }

    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int totalRegistrations = 0;

        try {
            String sql = "SELECT "
                    + " subject.subName as sn, "
                    + " COUNT(regisdetail.subDetailID) AS registrationCount"
                    + " FROM subjectdetail"
                    + " LEFT JOIN subject ON subjectdetail.subID = subject.subID"
                    + " LEFT JOIN regisdetail ON regisdetail.subDetailID = subjectdetail.subDetailID"
                    + " GROUP BY subject.subName"
                    + " ORDER BY registrationCount DESC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String subject = rs.getString("sn");
                int count = rs.getInt("registrationCount");
                dataset.addValue(count, "Amount", subject);
                totalRegistrations += count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "ລາຍວິຊາທັງໝົດ",
                "ຈຳນວນທີ່ສະໝັກ",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        // Customize fonts
        Font titleFont = new Font("Saysettha", Font.BOLD, 18);
        Font axisFont = new Font("Saysettha OT", Font.PLAIN, 16);
        Font labelFont = new Font("Saysettha OT", Font.PLAIN, 14);

        barChart.setTitle(new TextTitle("ສະຖິຕິສຳລັບວິຊາທີ່ນັກຮຽນນິຍົມຮຽນ (" + totalRegistrations + ")", titleFont));

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(80, 80, 80));
        plot.setDomainGridlinesVisible(true);

        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelFont(labelFont);
        plot.getDomainAxis().setLabelFont(axisFont);
        plot.getRangeAxis().setLabelFont(axisFont);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(5));
        rangeAxis.setAutoRangeIncludesZero(true);

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                Color[] colors = new Color[]{
                    new Color(102, 178, 255),
                    new Color(102, 178, 255),
                    new Color(102, 178, 255),
                    new Color(102, 178, 255),
                    new Color(102, 178, 255),
                    new Color(102, 178, 255),
                    new Color(102, 178, 255)
                };
                return colors[column % colors.length];
            }
        };

        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
        renderer.setShadowVisible(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.PLAIN, 16));
        renderer.setItemMargin(0.15);
        renderer.setMaximumBarWidth(0.05);

// Add this:
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12,
                TextAnchor.BOTTOM_CENTER
        ));

        plot.setRenderer(renderer);

        return barChart;
    }

}
