package Subform;

import Database.connectDB;
import Model.comboBoxHeight;
import Model.message;
import java.awt.Font;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViewDetail extends javax.swing.JDialog {

    Connection con = connectDB.getConnect();
    String sql;
    DecimalFormat df = new DecimalFormat("#,###");
    DefaultTableModel model = new DefaultTableModel();

    public ViewDetail(java.awt.Frame parent, boolean modal, String id, String fullName) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel) table.getModel();
        lbName.setText(fullName);
        lbID.setText(id);
        LoadData();
    }

    private void LoadData() {
        new LoadBill().execute();
    }

    private class LoadBill extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            loadBill();
            return null;
        }

        @Override
        protected void done() {
            new LoadpayID().execute();
        }
    }

    private class LoadpayID extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            loadpayID();
            return null;
        }

        @Override
        protected void done() {
            new loadSub().execute();
        }
    }

    private class loadSub extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            loadSubForStudent();
            return null;
        }

        @Override
        protected void done() {
            
        }
    }

    private void setComboBoxEditor(int columnIndex, Vector<String> items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Saysettha OT", 0, 14));
        comboBox.setRenderer(new comboBoxHeight());
        comboBox.setEditable(false); // prevent typing

        DefaultCellEditor editor = new DefaultCellEditor(comboBox) {
            @Override
            public boolean stopCellEditing() {
                int row = table.getEditingRow();
                Object selected = comboBox.getSelectedItem();
                if (row != -1 && selected != null) {
                    String fullText = selected.toString();
                    if (fullText.contains(" ")) {
                        int lastSpace = fullText.lastIndexOf(" ");
                        String subjectName = fullText.substring(0, lastSpace).trim();
                        String level = fullText.substring(lastSpace + 1).trim();
                        String costStr = getCostForSubject(subjectName, level);
                        try {
                            int cost = Integer.parseInt(costStr);
                            model.setValueAt(df.format(cost), row, 2);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            model.setValueAt(costStr, row, 2); // fallback to unformatted
                        }
                    }
                    btnUpdate.setEnabled(true);
                }
                return super.stopCellEditing();

            }
        };

        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        column.setCellEditor(editor);
    }

    private Map<String, String> subjectMap = new HashMap<>();
    private Map<String, String> scholarshipMap = new HashMap<>();

    private void loadSubjectMap() {
        subjectMap.clear();
        String query = "SELECT CONCAT(s.subName, ' ', l.level) AS sub, sd.subDetailID "
                + "FROM subjectdetail sd "
                + "INNER JOIN subject s ON sd.subID = s.subID "
                + "INNER JOIN level l ON sd.levelID = l.levelID"
                + " GROUP BY sd.subDetailID";
        try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                subjectMap.put(rs.getString("sub"), rs.getString("subDetailID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadScholarshipMap() {
        scholarshipMap.clear();
        String query = "SELECT scholarship, scholarship_id FROM scholarship";
        try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                scholarshipMap.put(rs.getString("scholarship"), rs.getString("scholarship_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSubForStudent() {
        try {
            String[] column = {"ລຳດັບ", "ວິຊາ", "ຄ່າຮຽນ", "ທຶນຮຽນ"};
            model = new DefaultTableModel(column, 0);
            table.setModel(model);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);

            // Load maps
            loadSubjectMap();
            loadScholarshipMap();

            Vector<String> subjects = new Vector<>(subjectMap.keySet());
            Vector<String> scholarships = new Vector<>(scholarshipMap.keySet());

            setComboBoxEditor(1, subjects);
            setComboBoxEditor(3, scholarships);
            sql = "SELECT rd.id as ID, CONCAT(s.subName, ' ', l.level) AS sub, sd.Cost, sc.scholarship "
                    + "FROM subjectdetail sd "
                    + "INNER JOIN subject s ON sd.subID = s.subID "
                    + "INNER JOIN level l ON sd.levelID = l.levelID "
                    + "INNER JOIN regisdetail rd ON rd.subDetailID = sd.subDetailID "
                    + "INNER JOIN registration r ON rd.regisID = r.regisID "
                    + "INNER JOIN scholarship sc ON rd.scholarship_id = sc.scholarship_id "
                    + "WHERE r.stdID = ?"
                    + " ORDER BY ID ASC";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ViewDetail.lbID.getText());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String scholarship = rs.getString("scholarship");
                int cost = rs.getInt("Cost");

                if ("ໄດ້ຮັບທຶນ".equals(scholarship)) {
                    cost = 0;
                }

                String[] data = {
                    rs.getString("ID"),
                    rs.getString("sub"),
                    df.format(cost),
                    scholarship
                };
                model.addRow(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCostForSubject(String subName, String level) {
        String cost = "0";
        String query = "SELECT sd.Cost FROM subjectdetail sd "
                + "INNER JOIN subject s ON sd.subID = s.subID "
                + "INNER JOIN level l ON sd.levelID = l.levelID "
                + "WHERE s.subName = ? AND l.level = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, subName);
            pst.setString(2, level);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cost = rs.getString("Cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cost;
    }

    private void updateAllData() {
        try {
            Set<String> uniqueSubIDs = new HashSet<>();

            // First Pass: Check for duplicates only
            for (int row = 0; row < model.getRowCount(); row++) {
                String subject = model.getValueAt(row, 1).toString().trim();
                String subID = subjectMap.get(subject);
                if (subID != null) {
                    subID = subID.trim();
                }

                if (subID == null) {
                    JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນວິຊາບໍ່ຖືກຕ້ອງ!"));
                    return;
                }

                if (uniqueSubIDs.contains(subID)) {
                    JOptionPane.showMessageDialog(this, new message("ວິຊາຊ້ຳກັນ! ກະລຸນາກວດສອບ."));
                    loadSubForStudent();
                    return;
                }
                uniqueSubIDs.add(subID);
            }

            // Second Pass: Update database
            for (int row = 0; row < model.getRowCount(); row++) {
                String id = model.getValueAt(row, 0).toString();
                String subject = model.getValueAt(row, 1).toString().trim();
                String costStr = model.getValueAt(row, 2).toString().replaceAll(",", "");
                String scholarship = model.getValueAt(row, 3).toString();

                if ("ໄດ້ຮັບທຶນ".equals(scholarship)) {
                    costStr = "0";
                }

                String subID = subjectMap.get(subject);
                String scholarshipID = scholarshipMap.get(scholarship);

                if (subID == null || scholarshipID == null) {
                    JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນບາງຢ່າງບໍ່ຖືກຕ້ອງ!"));
                    return;
                }

                sql = "UPDATE regisdetail SET subDetailID = ?, Cost = ?, scholarship_id = ? WHERE id = ?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, subID);
                    pst.setString(2, costStr);
                    pst.setString(3, scholarshipID);
                    pst.setString(4, id);

                    int updated = pst.executeUpdate();

                    if (updated <= 0) {
                        JOptionPane.showMessageDialog(this, new message("ແກ້ໄຂບໍ່ສຳເລັດ"));
                    }
                }
            }
            updatePayment();
            loadSubForStudent();
            this.dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, new message("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbName2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new Model.Table();
        lbName4 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        lbName3 = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        lbName5 = new javax.swing.JLabel();
        bill = new javax.swing.JLabel();
        payID = new javax.swing.JLabel();
        lbName6 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 330));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbName2.setBackground(new java.awt.Color(0, 102, 255));
        lbName2.setFont(new java.awt.Font("Saysettha OT", 1, 20)); // NOI18N
        lbName2.setForeground(new java.awt.Color(255, 255, 255));
        lbName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName2.setText("ຟອມປ່ຽນວິຊາຮຽນ");
        lbName2.setOpaque(true);
        jPanel1.add(lbName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 60));

        jScrollPane2.setBorder(null);
        jScrollPane2.setFocusable(false);

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setMinWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setMaxWidth(200);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 570, 220));

        lbName4.setBackground(new java.awt.Color(0, 102, 255));
        lbName4.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName4.setForeground(new java.awt.Color(255, 255, 255));
        lbName4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName4.setText("ລະຫັດນັກຮຽນ:");
        jPanel1.add(lbName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 110, 40));

        lbID.setBackground(new java.awt.Color(102, 102, 102));
        lbID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbID.setForeground(new java.awt.Color(255, 255, 255));
        lbID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbID.setText("ID");
        lbID.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 153, 255)));
        lbID.setOpaque(true);
        jPanel1.add(lbID, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 96, 125, -1));

        lbName3.setBackground(new java.awt.Color(0, 102, 255));
        lbName3.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName3.setForeground(new java.awt.Color(255, 255, 255));
        lbName3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName3.setText("ຊື່:");
        jPanel1.add(lbName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 40));

        lbName.setBackground(new java.awt.Color(0, 102, 255));
        lbName.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");
        lbName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 153, 255)));
        jPanel1.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 210, 40));

        btnUpdate.setBackground(new java.awt.Color(0, 153, 102));
        btnUpdate.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("ຢືນຢັນການປ່ຽນແປງ");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setEnabled(false);
        btnUpdate.setIconTextGap(10);
        btnUpdate.setPreferredSize(new java.awt.Dimension(200, 50));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, 50));

        lbName5.setBackground(new java.awt.Color(0, 102, 255));
        lbName5.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName5.setForeground(new java.awt.Color(255, 255, 255));
        lbName5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName5.setText("ລະຫັດບິນ:");
        jPanel1.add(lbName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 80, 40));

        bill.setBackground(new java.awt.Color(102, 102, 102));
        bill.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bill.setForeground(new java.awt.Color(255, 255, 255));
        bill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bill.setText("Bill");
        bill.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 153, 255)));
        bill.setOpaque(true);
        jPanel1.add(bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 80, 30));

        payID.setBackground(new java.awt.Color(0, 102, 255));
        payID.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        payID.setForeground(new java.awt.Color(255, 255, 255));
        payID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        payID.setText("No");
        payID.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        payID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 153, 255)));
        jPanel1.add(payID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 110, 30));

        lbName6.setBackground(new java.awt.Color(0, 102, 255));
        lbName6.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName6.setForeground(new java.awt.Color(255, 255, 255));
        lbName6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName6.setText("ເລກທີ່:");
        jPanel1.add(lbName6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 50, 40));

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("ຍົກເລີກ");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.setIconTextGap(10);
        btnCancel.setPreferredSize(new java.awt.Dimension(200, 50));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 50));

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(624, 533));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateAllData();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາເລືອກລາຍວິຊາທີ່ຈະລຶບກ່ອນ!"));
        } else {
            int x = JOptionPane.showConfirmDialog(this, new message("ທ່ານແນ່ໃຈຈະລຶບຂໍ້ມູນນີ້ແທ້ບໍ?"), "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
            } else {
                loadSubForStudent();
            }
        }
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(ViewDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewDetail dialog = new ViewDetail(new javax.swing.JFrame(), true, null, null);
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
    public static javax.swing.JLabel bill;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbName2;
    private javax.swing.JLabel lbName3;
    private javax.swing.JLabel lbName4;
    private javax.swing.JLabel lbName5;
    private javax.swing.JLabel lbName6;
    private javax.swing.JLabel payID;
    private Model.Table table;
    // End of variables declaration//GEN-END:variables

    private void loadBill() {
        try {
            sql = "select regisdetail.regisID as bill from regisdetail"
                    + " inner join registration on regisdetail.regisID=registration.regisID"
                    + " inner join student on registration.stdID=student.stdID"
                    + " where registration.stdID=?"
                    + " limit 1;";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lbID.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                bill.setText(rs.getString("bill"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, new message("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage()));
        }
    }

    private void loadpayID() {
        try {
            sql = "select payment.payID as id from payment"
                    + " inner join registration on payment.regisID=registration.regisID"
                    + " where registration.regisID=?"
                    + " limit 1;";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bill.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                payID.setText(rs.getString("id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, new message("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage()));
        }
    }

    private void updatePayment() {
        try {
            int dbCost = 0;
            int dbCost1 = 0;

            String amount = "select amount from payment where payID=?";

            PreparedStatement pstAmount = con.prepareStatement(amount);
            pstAmount.setString(1, payID.getText().trim());
            ResultSet rspay = pstAmount.executeQuery();
            while (rspay.next()) {
                dbCost = rspay.getInt("amount");
            }

            String currentCost = "select sum(Cost) as total from regisdetail WHERE regisID = ?";
            PreparedStatement pstCost = con.prepareStatement(currentCost);
            pstCost.setString(1, bill.getText().trim());
            ResultSet rsCost = pstCost.executeQuery();

            while (rsCost.next()) {
                dbCost1 = rsCost.getInt("total");
            }

            if (dbCost1 > dbCost) {
                sql = "UPDATE payment "
                        + "JOIN registration ON payment.regisID = registration.regisID "
                        + "SET payment.amount = ?, "
                        + "    payment.pend = ? - payment.pay "
                        + "WHERE registration.regisID = ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, dbCost1);
                pst.setInt(2, dbCost1);
                pst.setString(3, bill.getText());
                pst.executeUpdate();
                updateAllData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, new message("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage()));
        }
    }

    private void deleteData() {
        try {
            int[] selectedRows = table.getSelectedRows();
            int row = selectedRows[0];
            String id = table.getValueAt(row, 0).toString();
            sql = "delete from regisdetail where id=?";
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, id);
            pst.executeUpdate();

            loadSubForStudent();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, new message("ເກີດຂໍ້ຜິດພາດໃນການລຶບ"));
        }
    }

}
