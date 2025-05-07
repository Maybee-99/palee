package Subform;

import Database.connectDB;
import Model.message;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Unit extends javax.swing.JFrame {

    Connection con = connectDB.getConnect();
    String sql;
    DefaultTableModel model = new DefaultTableModel();
    message m;
    private Donate dn;

    public Unit(Donate dn) {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        this.dn = dn;
        showData();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dn.retrieveUnit();
            }

        });
        table.fixTable(jScrollPane1);
    }

    private void showData() {
        try {
            clearTable();
            sql = "call selectUnit()";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                String[] data = {
                    rs.getString("unitID"),
                    rs.getString("unit")
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    private void saveData() {
        try {
            sql = "call insertunit(?)";

            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtUnit.getText().trim());
            cst.executeUpdate();
        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

    private void editData() {
        try {
            int row = table.getSelectedRow();
            sql = "call updateUnit(?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, table.getValueAt(row, 0).toString());
            cst.setString(2, txtUnit.getText().trim());
            cst.executeUpdate();
        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }

    }

    private void deleteData() {
        int[] selectRow = table.getSelectedRows();
        try {
            sql = "call deleteUnit(?)";
            CallableStatement cst = con.prepareCall(sql);
            for (int rows : selectRow) {
                String id = table.getValueAt(rows, 0).toString();
                cst.setString(1, id);
                cst.executeUpdate();
            }

        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUnit = new Swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbClose = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 200));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUnit.setBackground(new java.awt.Color(102, 102, 102));
        txtUnit.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        txtUnit.setPlaceHolder("ປ້ອນຫົວໜ່ວຍ");
        jPanel2.add(txtUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 530, 50));

        jLabel1.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ຫົວໜ່ວຍ:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 50));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnSave.setBackground(new java.awt.Color(0, 204, 51));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setPreferredSize(new java.awt.Dimension(75, 45));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave);

        btnEdit.setBackground(new java.awt.Color(0, 102, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setPreferredSize(new java.awt.Dimension(75, 45));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel4.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setPreferredSize(new java.awt.Dimension(75, 45));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 530, 45));

        lbClose.setBackground(new java.awt.Color(102, 102, 102));
        lbClose.setFont(new java.awt.Font("Phetsarath OT", 0, 36)); // NOI18N
        lbClose.setForeground(new java.awt.Color(255, 255, 255));
        lbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbClose.setText("x");
        lbClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbClose.setOpaque(true);
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCloseMouseExited(evt);
            }
        });
        jPanel2.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 40, 40));

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(102, 102, 102)));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຫົວໜ່ວຍ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel3.add(jScrollPane1);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(700, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!");
            JOptionPane.showMessageDialog(null, m);
        } else {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບ " + txtUnit.getText() + " ແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                dn.retrieveUnit();
                showData();

            }
            txtUnit.setText("");
        }
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        txtUnit.setText(table.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະແກ້ໄຂກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);

        } else {
            editData();
            dn.retrieveUnit();
            txtUnit.setText("");
            showData();
        }
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtUnit.getText().trim())) {
            int row = table.getRowCount();
            String unit;

            for (int i = 0; i < row; i++) {
                unit = table.getValueAt(i, 1).toString();
                if (txtUnit.getText().equals(unit)) {
                    m = new message("ຂໍ້ມູນຊ້ຳກັນແລ້ວ");
                    JOptionPane.showMessageDialog(this, m);
                    return;
                }
            }

            saveData();
            dn.retrieveUnit();
            txtUnit.setText("");
            showData();

        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setBackground(Color.red);
        lbClose.setForeground(Color.BLACK);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setBackground(new Color(102, 102, 102));
        lbClose.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseExited

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        dn.retrieveUnit();
        this.dispose();

    }//GEN-LAST:event_lbCloseMouseClicked

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
            java.util.logging.Logger.getLogger(Unit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Unit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Unit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Unit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Unit(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbClose;
    private Model.Table table;
    private Swing.TextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
