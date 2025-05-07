package Report;

import Database.connectDB;
import Model.CurrentDate;
import Model.ExportData;
import Model.Table;
import Model.financial;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class finance extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    CurrentDate dn = new CurrentDate();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DecimalFormat df = new DecimalFormat("#,###");
    String year = dn.DateTimeNow("YYYY");

    public finance() {
        initComponents();
        model1 = (DefaultTableModel) table1.getModel();
        model2 = (DefaultTableModel) table2.getModel();
        totalExpense();
        totalIncome();
        totalProfit();
        showData();
        showData1();
    }

    private void clearTable(Table tb, DefaultTableModel m) {
        int row = tb.getRowCount() - 1;
        while (row >= 0) {
            m.removeRow(row--);
        }
    }

    private void showData() {
        try {
            clearTable(table1, model1);
            String sql = "select * from income where year(incomeDate)=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int m = rs.getInt("Amount");
                String[] data = {
                    rs.getString("incomeDate"),
                    df.format(m),
                    rs.getString("description")
                };
                model1.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showData1() {
        try {
            clearTable(table2, model2);
            String sql = "select * from expense where year(expenseDate)=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int m = rs.getInt("Amount");
                String[] data = {
                    rs.getString("expenseDate"),
                    df.format(m),
                    rs.getString("description")
                };
                model2.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void totalIncome() {
        int income = financial.getTotal(con, "income", "IncomeDate", year);
        lbIncome.setText(df.format(income) + " ₭");
    }

    private void totalExpense() {
        int income = financial.getTotal(con, "expense", "expenseDate", year);
        lbExpense.setText(df.format(income) + " ₭");
    }

    private void totalProfit() {
        try {
            int profit = 0;
            int income = Integer.parseInt(lbIncome.getText().replaceAll(",", "").replaceAll("₭", "").trim());
            int expense = Integer.parseInt(lbExpense.getText().replaceAll(",", "").replaceAll("₭", "").trim());
            profit = income - expense;
            lbProfit.setText(df.format(profit) + " ₭");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnexel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbIncome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbExpense = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbProfit = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new Model.Table();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnex1 = new javax.swing.JButton();
        btnIncome = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new Model.Table();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnex2 = new javax.swing.JButton();
        btnExpense = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(242, 242, 242)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(242, 242, 242)));
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 0, 0, new java.awt.Color(242, 242, 242)));
        jPanel14.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnexel.setBackground(new java.awt.Color(0, 153, 102));
        btnexel.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnexel.setForeground(new java.awt.Color(255, 255, 255));
        btnexel.setText("ບັນທຶກເປັນ Excel");
        btnexel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexelActionPerformed(evt);
            }
        });
        jPanel14.add(btnexel);

        btnPrint.setBackground(new java.awt.Color(0, 102, 255));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ປິ່ນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel14.add(btnPrint);

        jPanel8.add(jPanel14, java.awt.BorderLayout.LINE_END);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel6.setBackground(new java.awt.Color(0, 153, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new java.awt.BorderLayout());

        lbIncome.setBackground(new java.awt.Color(255, 255, 255));
        lbIncome.setFont(new java.awt.Font("Saysettha OT", 1, 20)); // NOI18N
        lbIncome.setForeground(new java.awt.Color(255, 255, 255));
        lbIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbIncome.setText("income");
        lbIncome.setPreferredSize(new java.awt.Dimension(260, 20));
        jPanel6.add(lbIncome, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ລາຍຮັບທັງໝົດ:");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 17));
        jPanel6.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jPanel15.add(jPanel6);

        jPanel4.setBackground(java.awt.Color.red);
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.BorderLayout());

        lbExpense.setFont(new java.awt.Font("Saysettha OT", 1, 20)); // NOI18N
        lbExpense.setForeground(new java.awt.Color(255, 255, 255));
        lbExpense.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbExpense.setText("expense");
        lbExpense.setPreferredSize(new java.awt.Dimension(79, 260));
        jPanel4.add(lbExpense, java.awt.BorderLayout.CENTER);

        jLabel7.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ລາຍຈ່າຍທັງໝົດ:");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 17));
        jPanel4.add(jLabel7, java.awt.BorderLayout.LINE_START);

        jPanel15.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 0, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.BorderLayout());

        lbProfit.setFont(new java.awt.Font("Saysettha OT", 1, 20)); // NOI18N
        lbProfit.setForeground(new java.awt.Color(255, 255, 255));
        lbProfit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbProfit.setText("income");
        lbProfit.setPreferredSize(new java.awt.Dimension(68, 260));
        jPanel5.add(lbProfit, java.awt.BorderLayout.CENTER);

        jLabel9.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("ຜົນກຳໄລທັງໝົດ:");
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 17));
        jPanel5.add(jLabel9, java.awt.BorderLayout.LINE_START);

        jPanel15.add(jPanel5);

        jPanel8.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(102, 102, 102)));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ລາຍຮັບ");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(242, 242, 242)));
        jLabel4.setPreferredSize(new java.awt.Dimension(51, 40));
        jPanel2.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jScrollPane3.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ວັນທີ່", "ຈຳນວນເງິນ", "ລາຍລະອຽດ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setMinWidth(150);
            table1.getColumnModel().getColumn(0).setPreferredWidth(150);
            table1.getColumnModel().getColumn(0).setMaxWidth(150);
            table1.getColumnModel().getColumn(1).setMinWidth(180);
            table1.getColumnModel().getColumn(1).setPreferredWidth(180);
            table1.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 1, 10, 1, new java.awt.Color(242, 242, 242)));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(350, 100));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnex1.setBackground(new java.awt.Color(0, 153, 102));
        btnex1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnex1.setForeground(new java.awt.Color(255, 255, 255));
        btnex1.setText("ບັນທຶກເປັນ Excel");
        btnex1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnex1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnex1);

        btnIncome.setBackground(new java.awt.Color(0, 102, 255));
        btnIncome.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnIncome.setForeground(new java.awt.Color(255, 255, 255));
        btnIncome.setText("ປິ່ນ");
        btnIncome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncomeActionPerformed(evt);
            }
        });
        jPanel12.add(btnIncome);

        jPanel10.add(jPanel12, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel9.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ລາຍຈ່າຍ");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(242, 242, 242)));
        jLabel5.setPreferredSize(new java.awt.Dimension(59, 40));
        jPanel3.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ວັນທີ່", "ຈຳນວນເງິນ", "ລາຍລະອຽດ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setMinWidth(150);
            table2.getColumnModel().getColumn(0).setPreferredWidth(150);
            table2.getColumnModel().getColumn(0).setMaxWidth(150);
            table2.getColumnModel().getColumn(1).setMinWidth(180);
            table2.getColumnModel().getColumn(1).setPreferredWidth(180);
            table2.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 1, 10, 1, new java.awt.Color(242, 242, 242)));
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(350, 100));
        jPanel13.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnex2.setBackground(new java.awt.Color(0, 153, 102));
        btnex2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnex2.setForeground(new java.awt.Color(255, 255, 255));
        btnex2.setText("ບັນທຶກເປັນ Excel");
        btnex2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnex2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnex2ActionPerformed(evt);
            }
        });
        jPanel13.add(btnex2);

        btnExpense.setBackground(new java.awt.Color(0, 102, 255));
        btnExpense.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnExpense.setForeground(new java.awt.Color(255, 255, 255));
        btnExpense.setText("ປິ່ນ");
        btnExpense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpenseActionPerformed(evt);
            }
        });
        jPanel13.add(btnExpense);

        jPanel11.add(jPanel13, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel11, java.awt.BorderLayout.PAGE_END);

        jPanel9.add(jPanel3);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setBounds(0, 0, 1540, 780);
    }// </editor-fold>//GEN-END:initComponents

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table2MouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        print();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnex2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnex2ActionPerformed
        int row = table2.getRowCount();
        if (row > 0) {
            ExportData.exportToCSV(table2);
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າ"));
        }
    }//GEN-LAST:event_btnex2ActionPerformed

    private void btnIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncomeActionPerformed
       int row = table1.getRowCount();
        if (row > 0) {
            print1();
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າບໍ່ພົບຂໍ້ມູນທີ່ຈະປິ່ນອອກ"));
        }
    }//GEN-LAST:event_btnIncomeActionPerformed

    private void btnex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnex1ActionPerformed
        int row = table1.getRowCount();
        if (row > 0) {
            ExportData.exportToCSV(table1);
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າ"));
        }
    }//GEN-LAST:event_btnex1ActionPerformed

    private void btnExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpenseActionPerformed
        int row = table2.getRowCount();
        if (row > 0) {
            print2();
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າບໍ່ພົບຂໍ້ມູນທີ່ຈະປິ່ນອອກ"));
        }
    }//GEN-LAST:event_btnExpenseActionPerformed

    private void btnexelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexelActionPerformed
        Table tb = new Table();
        DefaultTableModel mb = new DefaultTableModel();
        mb = (DefaultTableModel) tb.getModel();
        if(mb.getRowCount()==0)
        {
            mb.addColumn("ລາຍຮັບທັງໝົດ");
            mb.addColumn("ລາຍຈ່າຍທັງໝົດ");
            mb.addColumn("ຜົນກຳໄລທັງໝົດ");
        }
        String[] d = {lbIncome.getText(), lbExpense.getText(), lbProfit.getText()};
        mb.addRow(d);
         ExportData.exportToCSV(tb);

    }//GEN-LAST:event_btnexelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExpense;
    private javax.swing.JButton btnIncome;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnex1;
    private javax.swing.JButton btnex2;
    private javax.swing.JButton btnexel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbExpense;
    private javax.swing.JLabel lbIncome;
    private javax.swing.JLabel lbProfit;
    private Model.Table table1;
    private Model.Table table2;
    // End of variables declaration//GEN-END:variables

    private void print() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/finance.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("profit", lbProfit.getText());
            parameter.put("income", lbIncome.getText());
            parameter.put("expense", lbExpense.getText());

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, new JREmptyDataSource());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            view.setAlwaysOnTop(true);
            view.setVisible(true);
            view.addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowLostFocus(WindowEvent e) {
                    view.setAlwaysOnTop(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print1() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/income.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("total", lbIncome.getText());

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            view.setAlwaysOnTop(true);
            view.setVisible(true);
            view.addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowLostFocus(WindowEvent e) {
                    view.setAlwaysOnTop(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print2() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/expense.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("total", lbExpense.getText());

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            view.setAlwaysOnTop(true);
            view.setVisible(true);
            view.addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowLostFocus(WindowEvent e) {
                    view.setAlwaysOnTop(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
