package Subform;

import Database.connectDB;
import Model.checkDupplicateValue;
import Model.message;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.comboBoxHeight;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

public class User extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    DefaultTableModel model = new DefaultTableModel();
    message m;

    public User() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        showData();
        cmbAuth.setRenderer(new comboBoxHeight());
        jPanel2.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,new Color(242,242,242)),new FlatRoundBorder() {
        }));
        table.fixTable(jScrollPane1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtUsername = new Swing.TextField();
        jPanel19 = new javax.swing.JPanel();
        txtPassword = new Swing.TextField();
        jPanel18 = new javax.swing.JPanel();
        cmbAuth = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setTitle("ຈັດການຜູ້ໃຊ້ລະບົບ");
        setFrameIcon(null);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(213, 216, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 1, 20, new java.awt.Color(213, 216, 219)));
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 300));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setBackground(new java.awt.Color(212, 230, 249));
        jPanel2.add(jPanel6);

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 15, 0, new java.awt.Color(212, 230, 249)));
        jPanel3.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel17.setBackground(new java.awt.Color(212, 230, 249));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ກຳນົດຊື່ຜູ້ໃຊ້", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel17.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.setCornerRadius(12.0F);
        txtUsername.setPlaceHolder("ປ້ອນຊື່ຜູ້ໃຊ້");
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });
        jPanel17.add(txtUsername);

        jPanel3.add(jPanel17);

        jPanel19.setBackground(new java.awt.Color(212, 230, 249));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດຜ່ານ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel19.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel19.setLayout(new java.awt.GridLayout(1, 0));

        txtPassword.setCornerRadius(12.0F);
        txtPassword.setPlaceHolder("ປ້ອນລະຫັດຜ່ານ");
        jPanel19.add(txtPassword);

        jPanel3.add(jPanel19);

        jPanel18.setBackground(new java.awt.Color(212, 230, 249));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ກຳນົດສິດທິ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel18.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        cmbAuth.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbAuth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ຜູ້ດູແລລະບົບ", "ອາຈານ" }));
        jPanel18.add(cmbAuth);

        jPanel3.add(jPanel18);

        jPanel2.add(jPanel3);

        jPanel5.setBackground(new java.awt.Color(212, 230, 249));
        jPanel2.add(jPanel5);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(213, 216, 219));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(213, 216, 219)));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(213, 216, 219));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 15, 1, new java.awt.Color(213, 216, 219)));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(213, 216, 219));
        jPanel8.add(jPanel10);

        jPanel4.setBackground(new java.awt.Color(213, 216, 219));
        jPanel4.setLayout(new java.awt.GridLayout(1, 3, 20, 10));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave);

        btnEdit.setBackground(new java.awt.Color(0, 51, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel4.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);

        jPanel8.add(jPanel4);

        jPanel9.setBackground(new java.awt.Color(213, 216, 219));
        jPanel8.add(jPanel9);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ຊື່ຜູ້ໃຊ້", "ລະຫັດຜ່ານ", "ສິດທິ "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setBounds(0, 0, 1550, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!");
            JOptionPane.showMessageDialog(null, m);
        } else {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບລະຫັດ " + txtUsername.getText() + " ນີ້ແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                showData();
            }
            clearData();
            btnSave.setEnabled(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            editData();
            showData();
            clearData();
            btnSave.setEnabled(true);

        } else {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ແກ້ໄຂກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtUsername.getText()) && !"".equals(txtPassword.getText())) {
            String name = txtUsername.getText();
            if (!checkDupplicateValue.isUnique(table, name, 1)) {
                m = new message("ກະລຸນາກຳນົດຊື່ຜູ້ໃຊ້ບໍ່ໃຫ້ຊ້ຳກັນ!");
                JOptionPane.showMessageDialog(this, m);
                txtUsername.requestFocus();
            } else {
                saveData();
                showData();
                clearData();
            }

        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        txtUsername.setText(table.getValueAt(row, 1).toString());
        txtPassword.setText(table.getValueAt(row, 2).toString());
        cmbAuth.setSelectedItem(table.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbAuth;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.Table table;
    private Swing.TextField txtPassword;
    private Swing.TextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        try {
            clearTable();
            sql = "call selectUser()";
            CallableStatement cst = con.prepareCall(sql);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString("User_ID"),
                    rs.getString("Username"),
                    rs.getString("password"),
                    rs.getString("auth")
                };
                model.addRow(data);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {

            sql = "call insertUser(?,?,?)";
            String Auth = cmbAuth.getSelectedItem().toString();
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtUsername.getText());
            cst.setString(2, txtPassword.getText());
            cst.setString(3, Auth);
            cst.executeUpdate();

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

    private void editData() {
        try {
            int row = table.getSelectedRow();
            String selectRow = table.getValueAt(row, 0).toString();
            sql = "call updateUser(?,?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, selectRow);
            cst.setString(2, txtUsername.getText());
            cst.setString(3, txtPassword.getText());
            cst.setString(4, cmbAuth.getSelectedItem().toString());

            cst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearData() {
        txtPassword.setText("");
        txtUsername.setText("");
        btnSave.setEnabled(true);
        txtUsername.requestFocus();
    }

    private void deleteData() {
        int[] selectRow = table.getSelectedRows();
        try {
            sql = "call deleteUser(?)";
            CallableStatement cst = con.prepareCall(sql);
            for (int rows : selectRow) {
                String ID = table.getValueAt(rows, 0).toString();
                cst.setString(1, ID);
                cst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
