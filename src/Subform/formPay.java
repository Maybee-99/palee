package Subform;

import Database.connectDB;
import Model.PaymentTableCellRenderer;
import Model.message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class formPay extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel();
    message m;
    Connection con = connectDB.getConnect();
    String sql;
    DecimalFormat df = new DecimalFormat("#,###");

    public formPay() {
        super("pay");
        initComponents();
        model = (DefaultTableModel) table.getModel();
        showData();
        table.fixTable(sp);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        searchIcon1 = new Model.searchIcon();
        txtSearch = new Swing.TextField();
        jPanel7 = new javax.swing.JPanel();
        btnpay = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setTitle("ຟອມຈ່າຍຄ່າຮຽນ");
        setFrameIcon(null);

        jPanel1.setBackground(new java.awt.Color(213, 216, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(213, 216, 219)));
        jPanel1.setPreferredSize(new java.awt.Dimension(710, 80));
        jPanel1.setLayout(new net.miginfocom.swing.MigLayout());

        jPanel2.setBackground(new java.awt.Color(213, 216, 219));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 48));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(searchIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 30, -1));

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 38, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSearch.setCornerRadius(12.0F);
        txtSearch.setPlaceHolder("ຄົ້ນຫາຕາມເລກບິນ, ລະຫັດນັກຮຽນ, ຊື່ນັກຮຽນ");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 400, 48));

        jPanel1.add(jPanel2);

        jPanel7.setBackground(new java.awt.Color(213, 216, 219));
        jPanel7.setPreferredSize(new java.awt.Dimension(20, 60));
        jPanel1.add(jPanel7);

        btnpay.setBackground(new java.awt.Color(255, 51, 51));
        btnpay.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnpay.setForeground(new java.awt.Color(255, 255, 255));
        btnpay.setText("ຊຳລະເງິນ");
        btnpay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpay.setPreferredSize(new java.awt.Dimension(200, 50));
        btnpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpayActionPerformed(evt);
            }
        });
        jPanel1.add(btnpay);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(213, 216, 219));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 15, 15, 15, new java.awt.Color(213, 216, 219)));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ເລກບິນ", "ລະຫັດນັກຮຽນ", "ຊື່ນັກຮຽນ", "ຄ່າເທີມ", "ຈ່າຍແລ້ວ", "ຍັງບໍ່ຈ່າຍ", "ວັນທີ່"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        sp.setViewportView(table);

        jPanel6.add(sp);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1550, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpayActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            m = new message("ກະລຸນາເລືອກເລກບິນທີ່ຈະຊຳລະກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        } else {
            String pend = table.getValueAt(row, 6).toString().replaceAll(",", "").replaceAll("ກີບ", "").trim();
            if (Integer.parseInt(pend) > 0) {
                String billNo = table.getValueAt(row, 1).toString();
                String stdID = table.getValueAt(row, 2).toString();
                String fee = table.getValueAt(row, 6).toString();
                payment payDialog = new payment(this, new javax.swing.JFrame(), true, billNo, stdID, fee);
                payDialog.setVisible(true);
            } else {
                m = new message("ເລກບິນນີ້ຈ່າຍສຳເລັດແລ້ວ!");
                JOptionPane.showMessageDialog(this, m);
            }

        }
    }//GEN-LAST:event_btnpayActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private Model.searchIcon searchIcon1;
    private javax.swing.JScrollPane sp;
    private Model.Table table;
    private Swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables

    public void showData() {
        try {
            ClearTable();
            sql = "call selectpay()";
            try (ResultSet rs = con.createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    int amount = rs.getInt("amount");
                    int pay = rs.getInt("pay");
                    int pend = rs.getInt("pend");
                    String[] data = {
                        rs.getString("payID"),
                        rs.getString("regisID"),
                        rs.getString("stdID"),
                        rs.getString("stdName") + " " + rs.getString("Lastname"),
                        df.format(amount) + " ກີບ",
                        df.format(pay) + " ກີບ",
                        df.format(pend) + " ກີບ",
                        rs.getString("payDate")

                    };
                    model.addRow(data);

                    for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(new PaymentTableCellRenderer());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ClearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    public void Search(String para) {
        ClearTable();
        try {
            sql = "{CALL payBy(?)}";

            CallableStatement pst = con.prepareCall(sql);

            pst.setString(1, para);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int amount = rs.getInt("amount");
                int pay = rs.getInt("pay");
                int pend = rs.getInt("pend");
                String[] data = {
                    rs.getString("payID"),
                    rs.getString("regisID"),
                    rs.getString("stdID"),
                    rs.getString("stdName") + " " + rs.getString("Lastname"),
                    df.format(amount) + " ກີບ",
                    df.format(pay) + " ກີບ",
                    df.format(pend) + " ກີບ",
                    rs.getString("payDate")

                };
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
