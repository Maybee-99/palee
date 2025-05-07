package Subform;

import Database.connectDB;
import Model.CurrentDate;
import java.sql.*;
import Model.message;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.formatNumber;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

public abstract class Finance extends javax.swing.JInternalFrame {

    protected Connection con = connectDB.getConnect();
    protected DefaultTableModel model = new DefaultTableModel();
    protected message m;
    protected CurrentDate dn = new CurrentDate();
    protected DecimalFormat df = new DecimalFormat("#,###");
    private final String mode;

    public Finance(String mode) {
        initComponents();
        this.mode = mode;
        this.setTitle(mode);
        model = (DefaultTableModel) table.getModel();
        panel.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10, 10, 10, 10, new Color(242, 242, 242)), new FlatRoundBorder() {
        }));
        showData();
        formatNumber.apply(txtMoney);
        table.fixTable(jScrollPane1);
    }

    protected abstract String getSelectData();

    protected abstract String getInsertData();

    protected abstract String getUpdateData();

    protected abstract String getDeleteData();

    protected abstract String[] getRowData(ResultSet rs) throws SQLException;

    protected void showData() {
        try {
            clearTable();
            String sql = "CALL " + getSelectData();
            CallableStatement cst = con.prepareCall(sql);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                model.addRow(getRowData(rs));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void clearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    protected void saveData() {
        try {
            String sql = "call " + getInsertData() + "(?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtMoney.getText().replaceAll(",", "").trim());
            cst.setString(2, txtDescription.getText());
            cst.setString(3, dn.DateTimeNow("yyyy-MM-dd"));
            cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void editData() {
        try {
            int selectedRow = table.getSelectedRow();
            String sql = "call " + getUpdateData() + "(?,?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, table.getValueAt(selectedRow, 0).toString());
            cst.setString(2, txtMoney.getText().replaceAll(",", "").trim());
            cst.setString(3, txtDescription.getText());
            cst.setString(4, dn.DateTimeNow("yyyy-MM-dd"));
            cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void deleteData() {
        try {
            int[] selectedRow = table.getSelectedRows();

            String sql = "call " + getDeleteData() + "(?)";
            CallableStatement cst = con.prepareCall(sql);
            for (int rows : selectedRow) {
                String id = table.getValueAt(rows, 0).toString();
                cst.setString(1, id);
                cst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtMoney = new Swing.TextField();
        jPanel16 = new javax.swing.JPanel();
        txtDescription = new Swing.TextField();
        jPanel5 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBackground(new java.awt.Color(213, 216, 219));
        setBorder(null);
        setClosable(true);
        setTitle("ລາຍຈ່າຍ ແລະ ລາຍຮັບ");
        setAutoscrolls(true);
        setFrameIcon(null);

        panel.setBackground(new java.awt.Color(213, 216, 219));
        panel.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 0, 10, new java.awt.Color(213, 216, 219)));
        panel.setMinimumSize(new java.awt.Dimension(1500, 100));
        panel.setPreferredSize(new java.awt.Dimension(1500, 320));
        panel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        panel.add(jPanel1);

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 0, 20, 0, new java.awt.Color(212, 230, 249)));
        jPanel4.setMinimumSize(new java.awt.Dimension(600, 210));
        jPanel4.setPreferredSize(new java.awt.Dimension(600, 210));
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel17.setBackground(new java.awt.Color(212, 230, 249));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຈຳນວນເງິນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel17.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        txtMoney.setBackground(new java.awt.Color(212, 230, 249));
        txtMoney.setCornerRadius(12.0F);
        txtMoney.setPlaceHolder("ປ້ອນຈຳນວນເງິນ");
        txtMoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMoneyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMoneyKeyTyped(evt);
            }
        });
        jPanel17.add(txtMoney);

        jPanel4.add(jPanel17);

        jPanel16.setBackground(new java.awt.Color(212, 230, 249));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລາຍລະອຽດ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel16.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        txtDescription.setBackground(new java.awt.Color(212, 230, 249));
        txtDescription.setCornerRadius(12.0F);
        txtDescription.setPlaceHolder("ລາຍລະອຽດ");
        jPanel16.add(txtDescription);

        jPanel4.add(jPanel16);

        jPanel5.setBackground(new java.awt.Color(212, 230, 249));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 0, 10, 0, new java.awt.Color(212, 230, 249)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(200, 45));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel5.add(btnSave);

        btnedit.setBackground(new java.awt.Color(0, 51, 255));
        btnedit.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setText("ແກ້ໄຂ");
        btnedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit.setPreferredSize(new java.awt.Dimension(200, 45));
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel5.add(btnedit);

        btndelete.setBackground(new java.awt.Color(255, 0, 0));
        btndelete.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setText("ລຶບ");
        btndelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndelete.setPreferredSize(new java.awt.Dimension(200, 45));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btndelete);

        jPanel4.add(jPanel5);

        panel.add(jPanel4);

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel.add(jPanel3);

        getContentPane().add(panel, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(213, 216, 219));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(213, 216, 219)));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຈຳນວນເງິນ", "ວັນທີ່", "ລາຍລະອຽດ"
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(120);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1540, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMoneyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_txtMoneyKeyReleased

    private void txtMoneyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyTyped
        char c = evt.getKeyChar();
        String text = txtMoney.getText();

        if (!Character.isDigit(c)) {
            evt.consume(); 
        } else {
            if (text.isEmpty() && c == '0') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtMoneyKeyTyped

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        txtMoney.setText(table.getValueAt(row, 1).toString().replaceAll("ກີບ", ""));
        txtDescription.setText(table.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        } else {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                clearData();
                showData();
            }
            clearData();
            btnSave.setEnabled(true);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        if (!"".equals(txtMoney.getText())) {
            editData();
            clearData();
            showData();
        } else {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະແກ້ໄຂກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btneditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtMoney.getText()) && !"".equals(txtDescription.getText())) {
            saveData();
            clearData();
            showData();

        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private Model.Table table;
    private Swing.TextField txtDescription;
    private Swing.TextField txtMoney;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        txtMoney.setText("");
        txtDescription.setText("");
        txtMoney.requestFocus();
    }
}
