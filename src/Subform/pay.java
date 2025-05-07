package Subform;

import Database.connectDB;
import Model.GenerateID;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class pay extends javax.swing.JDialog {

    GenerateID id = new GenerateID();
    String sql;
    Connection con = connectDB.getConnect();
    DecimalFormat df = new DecimalFormat("#,###");
    DefaultTableModel model = new DefaultTableModel();
    message m;
    String Month;
    public static CalculateSalary cs;

    public pay(java.awt.Frame parent, boolean modal, String id, String month, CalculateSalary cs) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel) table.getModel();
        generateID();
        this.cs = cs;
        lbTID.setText(id);
        this.Month = month;
        lbMonth.setText(Month);
        showData();
        total();

    }

    private void total() {
        int sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            String totalValue = table.getValueAt(i, 3).toString().replaceAll(",", "");
            sum += Integer.parseInt(totalValue);
        }
        lbTotal.setText("ລວມ: " + df.format(sum) + " ₭");
    }

    private void generateID() {
        String studentID = id.generateID("select Max(salaryID) as ID from salary", "SAL", 5);
        lbBID.setText(studentID);

    }

    private Map<String, String> loadSubID() throws SQLException {
        Map<String, String> getSubID = new HashMap<>();
        sql = "SELECT subjectdetail.subDetailID as id,"
                + " subject.subName as sub,"
                + " level.level as lv"
                + " FROM subjectdetail"
                + " INNER JOIN subject ON subjectdetail.subID = subject.subID"
                + " INNER JOIN level ON subjectdetail.levelID = level.levelID";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                String subID = rs.getString("id");
                String subName = rs.getString("sub") + " " + rs.getString("lv"); // Add space here
                getSubID.put(subName, subID);
            }
        }
        return getSubID;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbName4 = new javax.swing.JLabel();
        lbBID = new javax.swing.JLabel();
        lbName7 = new javax.swing.JLabel();
        lbMonth = new javax.swing.JLabel();
        lbName5 = new javax.swing.JLabel();
        lbTID = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel4 = new javax.swing.JPanel();
        btnVerify = new javax.swing.JButton();
        lbTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel12.setBackground(new java.awt.Color(0, 102, 255));
        jLabel12.setFont(new java.awt.Font("Saysettha OT", 1, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ຟອມຈ່າຍເງິນໃຫ້ພະນັກງານ(ອາຈານ)");
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(98, 60));
        jPanel1.add(jLabel12, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbName4.setBackground(new java.awt.Color(0, 102, 255));
        lbName4.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName4.setText("ເລກບິນ:");
        lbName4.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel3.add(lbName4);

        lbBID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbBID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbBID.setText("ID");
        lbBID.setOpaque(true);
        lbBID.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.add(lbBID);

        lbName7.setBackground(new java.awt.Color(0, 102, 255));
        lbName7.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbName7.setText("ເດືອນ:");
        lbName7.setPreferredSize(new java.awt.Dimension(55, 50));
        jPanel3.add(lbName7);

        lbMonth.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMonth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbMonth.setText("ID");
        lbMonth.setOpaque(true);
        lbMonth.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.add(lbMonth);

        lbName5.setBackground(new java.awt.Color(0, 102, 255));
        lbName5.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbName5.setText("ລະຫັດອາຈານ:");
        lbName5.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel3.add(lbName5);

        lbTID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTID.setText("ID");
        lbTID.setOpaque(true);
        lbTID.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.add(lbTID);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setBorder(null);
        jScrollPane2.setFocusable(false);

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ວິຊາ", "ລະດັບ", "ຈຳນວນຊົ່ວໂມງ", "ລວມເປັນເງິນ", "ໝາຍເຫດ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setEnabled(false);
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(table);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerify.setBackground(new java.awt.Color(0, 51, 255));
        btnVerify.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnVerify.setForeground(new java.awt.Color(255, 255, 255));
        btnVerify.setText("ພິມບິນ");
        btnVerify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerify.setIconTextGap(10);
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });
        jPanel4.add(btnVerify, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 50));

        lbTotal.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotal.setText("ID");
        lbTotal.setOpaque(true);
        jPanel4.add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 240, 50));

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(751, 464));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed

        try {
            Map<String, String> subjectID = loadSubID();
            sql = "call insertSalary(?,?,?)";
            try (CallableStatement cst = con.prepareCall(sql)) {
                cst.setString(1, lbBID.getText());
                cst.setString(2, lbTID.getText());
                cst.setString(3, lbMonth.getText());
                cst.executeUpdate();
            }
            for (int i = 0; i < table.getRowCount(); i++) {
                String condit = table.getValueAt(i, 4).toString();
                String hour = table.getValueAt(i, 2).toString();
                String total = table.getValueAt(i, 3).toString().replaceAll(",", "");
                String subName = table.getValueAt(i, 0).toString().trim();
                String level = table.getValueAt(i, 1).toString().trim();
                String subKey = subName + " " + level;
                String subID = subjectID.get(subKey);
                if (subID == null) {
                    throw new RuntimeException("Missing subDetailID for: " + subKey);
                }
                sql = "call insertSalDetail(?,?,?,?,?)";
                CallableStatement cst1 = con.prepareCall(sql);
                cst1.setString(1, lbBID.getText());
                cst1.setString(2, subID);
                cst1.setString(3, hour);
                cst1.setString(4, total);
                cst1.setString(5, condit);
                cst1.executeUpdate();

            }
            String amount = lbTotal.getText().replaceAll("ລວມ: ", "").replaceAll(",", "").replaceAll(" ₭", "");
            sql = "call insertExpense(?,?,?)";
            String des = "ເງິນເດືອນ" + lbTID.getText();
            String date = java.time.LocalDate.now().toString();
            try (CallableStatement cst2 = con.prepareCall(sql)) {
                cst2.setString(1, amount);
                cst2.setString(2, des);
                cst2.setString(3, date);
                cst2.executeUpdate();
            }
            cs.loadData();

            InputStream path = getClass().getResourceAsStream("/Report/bill.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("bill", lbBID.getText());
            parameter.put("month", lbMonth.getText());
            parameter.put("teacher", lbTID.getText());
            parameter.put("sum", lbTotal.getText());

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
        generateID();
        this.dispose();

    }//GEN-LAST:event_btnVerifyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pay dialog = new pay(new javax.swing.JFrame(), true, CalculateSalary.id, CalculateSalary.Month, cs);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lbBID;
    public static javax.swing.JLabel lbMonth;
    private javax.swing.JLabel lbName4;
    private javax.swing.JLabel lbName5;
    private javax.swing.JLabel lbName7;
    public static javax.swing.JLabel lbTID;
    public static javax.swing.JLabel lbTotal;
    private Model.Table table;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        int month;
        try {
            month = Integer.parseInt(Month);
            sql = "CALL selectSAL(?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbTID.getText());
            cst.setInt(2, month);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("TOTAL");
                String[] data = {
                    rs.getString("SUB"),
                    rs.getString("LEV"),
                    rs.getString("HO"),
                    df.format(total),
                    rs.getString("ST")
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
