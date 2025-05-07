package Form;

import DataAnalysis.BarChart;
import DataAnalysis.Summary;
import DataAnalysis.PieChart;
import DataAnalysis.Result;
import DataAnalysis.Result1;
import Database.connectDB;
import Model.CurrentDate;
import Model.Model_Card;
import Model.financial;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Dashboard extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    CurrentDate dn = new CurrentDate();
    DecimalFormat df = new DecimalFormat("#,###");
    String year = dn.DateTimeNow("YYYY");
    BarChart bc;
    PieChart pc;
    Result rs;
    Result1 rs1;
    Summary rs2;
    JPopupMenu popupMenu;
    JLabel loadingLabel = new JLabel("ກຳລັງໂຫຼດຂໍ້ມູນ...", SwingConstants.CENTER);

    public Dashboard() {
        initComponents();
        BasicInternalFrameUI header = (BasicInternalFrameUI) this.getUI();
        if (header != null) {
            header.setNorthPane(null);
        }
        loadDashboard();

        popupMenu = new JPopupMenu();

        JMenuItem refreshItem = new JMenuItem("Refresh");
        refreshItem.addActionListener(e -> loadDashboard());

        popupMenu.add(refreshItem);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(Dashboard.this, e.getX(), e.getY());
                }
            }
        });

    }

    private void loadDashboard() {
        new LoadTotalStudent().execute();
    }

    private class LoadTotalStudent extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            totalStudent();
            return null;
        }

        @Override
        protected void done() {
            new LoadTotalTeacher().execute();
        }
    }

    private class LoadTotalTeacher extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            totalTeacher();
            return null;
        }

        @Override
        protected void done() {
            new LoadTotalIncome().execute();
        }
    }

    private class LoadTotalIncome extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            totalIncome();
            return null;
        }

        @Override
        protected void done() {
            new LoadTotalExpense().execute();
        }
    }

    private class LoadTotalExpense extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            totalExpense();
            return null;
        }

        @Override
        protected void done() {
            totalProfit();
            new LoadBarChart().execute();
        }
    }

    private class LoadBarChart extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            SwingUtilities.invokeLater(() -> {
                panel.removeAll();
                loadingLabel.setFont(new Font("Saysettha OT", 0, 16));
                loadingLabel.setForeground(Color.black);
                panel.add(loadingLabel);
                panel.revalidate();
                panel.repaint();
            });

            bc = new BarChart();
            return null;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                panel.add(bc);
                panel.revalidate();
                panel.repaint();
            });

            new LoadPieChart().execute();
        }
    }

    private class LoadPieChart extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            SwingUtilities.invokeLater(() -> {
                p1.removeAll();
                loadingLabel.setFont(new Font("Saysettha OT", 1, 16));
                loadingLabel.setForeground(Color.black);
                p1.add(loadingLabel);
                p1.revalidate();
                p1.repaint();
            });

            pc = new PieChart();
            return null;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                p1.add(pc);
                p1.revalidate();
                p1.repaint();
            });
            new LoadResult().execute();
        }
    }

    private class LoadResult extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            SwingUtilities.invokeLater(() -> {
                p2.removeAll();
                loadingLabel.setFont(new Font("Saysettha OT", 1, 16));
                loadingLabel.setForeground(Color.black);
                p2.add(loadingLabel);
                p2.revalidate();
                p2.repaint();
            });

            rs = new Result();
            return null;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                p2.add(rs);
                p2.revalidate();
                p2.repaint();
            });
            new LoadResult1().execute();
        }
    }

    private class LoadResult1 extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            SwingUtilities.invokeLater(() -> {
                p3.removeAll();
                loadingLabel.setFont(new Font("Saysettha OT", 1, 16));
                loadingLabel.setForeground(Color.black);
                p3.add(loadingLabel);
                p3.revalidate();
                p3.repaint();
            });

            rs1 = new Result1();
            return null;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                p3.add(rs1);
                p3.repaint();
                p3.revalidate();
            });
            new LoadResult2().execute();
        }
    }

    private class LoadResult2 extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            SwingUtilities.invokeLater(() -> {
                p4.removeAll();
                loadingLabel.setFont(new Font("Saysettha OT", 1, 16));
                loadingLabel.setForeground(Color.black);
                p4.add(loadingLabel);
                p4.revalidate();
                p4.repaint();
            });

            rs2 = new Summary();
            return null;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                p4.removeAll();
                p4.add(rs2);
                p4.repaint();
                p4.revalidate();
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        card1 = new Component.Card();
        card2 = new Component.Card();
        card3 = new Component.Card();
        card4 = new Component.Card();
        card5 = new Component.Card();
        jPanel4 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
        p3 = new javax.swing.JPanel();
        p4 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 102, 255));
        setBorder(null);
        setFrameIcon(null);
        setPreferredSize(new java.awt.Dimension(1550, 700));
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(new java.awt.Color(34, 56, 67));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 10, 20, new java.awt.Color(212, 230, 249)));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 180));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(0, 153, 255));
        card1.setColor2(new java.awt.Color(0, 153, 255));
        jPanel1.add(card1);

        card2.setColor1(new java.awt.Color(255, 119, 183));
        card2.setColor2(new java.awt.Color(255, 119, 183));
        jPanel1.add(card2);

        card3.setColor1(new java.awt.Color(0, 204, 51));
        card3.setColor2(new java.awt.Color(0, 204, 51));
        jPanel1.add(card3);

        card4.setColor1(new java.awt.Color(255, 0, 0));
        card4.setColor2(new java.awt.Color(255, 0, 0));
        jPanel1.add(card4);

        card5.setColor1(new java.awt.Color(255, 157, 35));
        card5.setColor2(new java.awt.Color(255, 157, 35));
        jPanel1.add(card5);

        jPanel3.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel.setBackground(new java.awt.Color(212, 230, 249));
        panel.setLayout(new java.awt.GridLayout(1, 0));
        jPanel4.add(panel);

        panel1.setBackground(new java.awt.Color(212, 230, 249));
        panel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        p1.setBackground(new java.awt.Color(212, 230, 249));
        p1.setLayout(new java.awt.GridLayout(1, 0));
        panel1.add(p1);

        p2.setBackground(new java.awt.Color(212, 230, 249));
        p2.setLayout(new java.awt.GridLayout(1, 0));
        panel1.add(p2);

        p3.setBackground(new java.awt.Color(212, 230, 249));
        p3.setLayout(new java.awt.GridLayout(1, 0));
        panel1.add(p3);

        p4.setBackground(new java.awt.Color(212, 230, 249));
        p4.setLayout(new java.awt.GridLayout(1, 0));
        panel1.add(p4);

        jPanel4.add(panel1);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3);

        setBounds(0, 0, 1550, 796);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Component.Card card1;
    private Component.Card card2;
    private Component.Card card3;
    private Component.Card card4;
    private Component.Card card5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables

    private void totalStudent() {
        try {
            int student = 0;
            sql = "select COUNT(DISTINCT registration.stdID) AS total from registration"
                    + " inner join student on registration.stdID=student.stdID"
                    + " where YEAR(registration.regisDate)=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                student = rs.getInt("total");
            }
            card1.setData(new Model_Card("ນັກຮຽນ", String.valueOf(student), "ຈຳນວນນັກຮຽນທັງໝົດ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void totalTeacher() {
        try {
            int teacher = 0;
            sql = "select COUNT(DISTINCT (teacherID)) AS total from teacher where status=1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                teacher = rs.getInt("total");
            }
            card2.setData(new Model_Card("ອາຈານ", String.valueOf(teacher), "ຈຳນວນອາຈານທັງໝົດ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void totalIncome() {
        int income = financial.getTotal(con, "income", "IncomeDate", year);
        card3.setData(new Model_Card("ລາຍຮັບ", df.format(income) + " ₭", "ລາຍຮັບທັງໝົດ"));
    }

    private void totalExpense() {
        int expense = financial.getTotal(con, "expense", "expenseDate", year);
        card4.setData(new Model_Card("ລາຍຈ່າຍ", df.format(expense) + " ₭", "ລາຍຈ່າຍທັງໝົດ"));
    }

    private void totalProfit() {
        try {
            int profit = 0;
            int income = financial.getTotal(con, "income", "IncomeDate", year);
            int expense = financial.getTotal(con, "expense", "expenseDate", year);
            profit = income - expense;
            card5.setData(new Model_Card( "ຜົນກຳໄລ", df.format(profit) + " ₭", "ຜົນກຳໄລທັງໝົດ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
