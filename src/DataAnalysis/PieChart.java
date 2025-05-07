package DataAnalysis;

import Database.connectDB;
import Swing.RoundPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class PieChart extends RoundPanel {

    private final Connection con;
    int totalRegistrations = 0;

    public PieChart() {
        this.con = connectDB.getConnect();
        initComponents();
        this.setCornerRadius(18);
        loadChart();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(900, 520));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void loadChart() {
        JFreeChart pieChart = createPieChart();
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setOpaque(false);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setBorder(BorderFactory.createEmptyBorder());
        this.add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            String sql = """
                SELECT subject.subName AS sn, 
                       COUNT(regisdetail.subDetailID) AS registrationCount
                FROM subjectdetail
                LEFT JOIN subject ON subjectdetail.subID = subject.subID
                LEFT JOIN regisdetail ON regisdetail.subDetailID = subjectdetail.subDetailID
                GROUP BY subject.subName
                ORDER BY registrationCount DESC
                """;

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String subject = rs.getString("sn");
                int count = rs.getInt("registrationCount");
                dataset.setValue(subject, count);
                totalRegistrations += count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(255, 255, 255));

        Font titleFont = new Font("Saysettha OT", Font.BOLD, 18);
        TextTitle title = new TextTitle("ສັດສ່ວນວິຊາທີ່ນັກຮຽນເລືອກຮຽນ", titleFont);
        title.setPaint(new Color(40, 40, 40));
        title.setPadding(10, 0, 10, 0);
        chart.setTitle(title);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setInteriorGap(0.05);
        plot.setCircular(true);
        plot.setSimpleLabels(true);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{2}", new DecimalFormat("0"), new DecimalFormat("0.0%"));
        plot.setLabelGenerator(labelGenerator);
        plot.setLabelFont(new Font("Saysettha OT", Font.PLAIN, 16));
        plot.setLabelPaint(new Color(50, 50, 50));
        plot.setLabelBackgroundPaint(null);
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);

        chart.getLegend().setItemFont(new Font("Saysettha OT", Font.PLAIN, 14));
        chart.getLegend().setItemPaint(new Color(51,51,51));
        chart.getLegend().setBackgroundPaint(new Color(255, 255, 255));

        Color[] modernPalette = {
            new Color(33, 150, 243), // Blue
            new Color(156, 39, 176), // Purple
            new Color(76, 175, 80), // Green
            new Color(255, 193, 7), // Amber
            new Color(244, 67, 54) // Red
        };
        int i = 0;
        for (Object key : dataset.getKeys()) {
            plot.setSectionPaint((Comparable) key, modernPalette[i % modernPalette.length]);
            i++;
        }

        return chart;
    }
}
