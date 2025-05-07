
package DataAnalysis;

import Database.connectDB;
import Swing.RoundPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public abstract class ResultBase extends RoundPanel {

    protected final Connection con;

    public ResultBase() {
        this.con = connectDB.getConnect();
        initComponents();
        this.setCornerRadius(18);
        loadCharts();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 600));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void loadCharts() {
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        JPanel subjectPanel = new JPanel(new BorderLayout());
        subjectPanel.setOpaque(false);

        JPanel awardPanel = new JPanel(new GridLayout(1, 2, 2, 0));
        awardPanel.setOpaque(false);
        for (String semesterName : getSemesterNames()) {
            awardPanel.add(createChartPanelForAwardPercentage(semesterName));
        }

        mainContainer.add(subjectPanel);
        mainContainer.add(awardPanel);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    protected abstract String[] getSemesterNames();

    private JPanel createChartPanelForAwardPercentage(String semesterName) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int totalRegistered = 0;
        int totalAwarded = 0;

        try {
            PreparedStatement pstTotal = con.prepareStatement("SELECT COUNT(DISTINCT stdID) AS total FROM registration");
            ResultSet rsTotal = pstTotal.executeQuery();
            if (rsTotal.next()) {
                totalRegistered = rsTotal.getInt("total");
            }

            String sqlAward = """
            SELECT COUNT(DISTINCT award.stdID) AS total
            FROM award
            INNER JOIN exam ON award.examID = exam.examID
            INNER JOIN semester ON exam.semID = semester.semID
            WHERE semester.semester = ?;
        """;

            PreparedStatement pstAward = con.prepareStatement(sqlAward);
            pstAward.setString(1, semesterName);
            ResultSet rsAward = pstAward.executeQuery();
            if (rsAward.next()) {
                totalAwarded = rsAward.getInt("total");
            }

            if (totalRegistered > 0) {
                double awardedPercent = (totalAwarded * 100.0) / totalRegistered;
                double notAwardedPercent = 100.0 - awardedPercent;
                dataset.setValue("ນັກຮຽນທີ່ໄດ້ມາດຕະຖານ", awardedPercent);
                dataset.setValue("ນັກຮຽນທີ່ຄວນພັດທະນາ", notAwardedPercent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return createDonutChartPanel("ຜົນການຮຽນ(" + semesterName + ")", dataset, totalRegistered);
    }

    private JPanel createDonutChartPanel(String title, DefaultPieDataset dataset, int totalStudents) {
        JFreeChart chart = createDonutChart(title, dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false);

        JLabel centerLabel = new JLabel("ນັກຮຽນທັງໝົດ:"+totalStudents + " ຄົນ", SwingConstants.CENTER);
        centerLabel.setFont(new Font("Saysettha OT", Font.BOLD, 16));
        centerLabel.setForeground(Color.BLACK);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));
        layeredPane.add(chartPanel, Integer.valueOf(0));
        layeredPane.add(centerLabel, Integer.valueOf(1));

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = layeredPane.getWidth();
                int height = layeredPane.getHeight();
                chartPanel.setBounds(0, 0, width, height);
                centerLabel.setBounds(0, height / 2 - 20, width, 40);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(layeredPane, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(600, 600));

        return panel;
    }

    private JFreeChart createDonutChart(String title, DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createRingChart(title, dataset, true, true, false);
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setSectionDepth(0.30);
        plot.setSectionPaint(0, new Color(244, 66, 66));
        plot.setSectionPaint(1, new Color(66, 134, 244));
        plot.setLabelGap(0.05);
        plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
        plot.setLabelFont(new Font("Saysettha OT", Font.PLAIN, 16));
        plot.setLabelPaint(Color.BLACK);
        chart.setTitle(new TextTitle(title, new Font("Saysettha", Font.BOLD, 18)));
        chart.getLegend().setItemFont(new Font("Saysettha OT", Font.PLAIN, 16));
        chart.getLegend().setItemPaint(new Color(27, 31, 58));
        
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setInteriorGap(0.05);
        plot.setCircular(true);
        plot.setSimpleLabels(false);
        
        return chart;
    }
}
