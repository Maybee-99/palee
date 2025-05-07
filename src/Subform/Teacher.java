package Subform;

import Database.connectDB;
import Model.message;
import static Subform.EditTeacher.cmbGender;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Teacher extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    ArrayList gender = new ArrayList();
    DefaultTableModel model = new DefaultTableModel();
    message m;

    String state = "";
    EditTeacher edit = new EditTeacher(null, true, this);

    public Teacher() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        showData();
        table.fixTable(jScrollPane1);

    }

    public final void retrieveGender() {
        try {
            sql = "SELECT * FROM sex";
            gender.clear();
            cmbGender.removeAllItems();  // Clear the combo box before adding items
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                gender.add(rs.getString("sexID"));
                cmbGender.addItem(rs.getString("sex"));
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

    public void showData() {
        try {
            ClearTable();
            sql = "call selectTeacher()";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int status = rs.getInt("status");
                if (status == 1) {
                    state = "ກຳລັງສອນ";
                } else if (status == 0) {
                    state = "ໂຈະສອນ";
                }

                String[] data = {
                    rs.getString("teacherID"),
                    rs.getString("teacherName"),
                    rs.getString("Lastname"),
                    rs.getString("sex"),
                    rs.getString("Contact"),
                    state

                };
                model.addRow(data);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        searchIcon1 = new Model.searchIcon();
        txtSearch = new Swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setForeground(new java.awt.Color(0, 0, 0));
        setTitle("ຟອມຈັດການຂໍ້ມູນອາຈານ");
        setFrameIcon(null);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(213, 216, 219));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 1, 10, new java.awt.Color(213, 216, 219)));
        jPanel3.setPreferredSize(new java.awt.Dimension(716, 80));
        jPanel3.setLayout(new net.miginfocom.swing.MigLayout());

        jPanel4.setBackground(new java.awt.Color(213, 216, 219));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(searchIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 8, -1, -1));

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 38, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSearch.setCornerRadius(12.0F);
        txtSearch.setPlaceHolder("ຄົ້ນຫາຕາມລະຫັດ, ຊື່, ເພດ");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel4.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 48));

        jPanel3.add(jPanel4);

        jPanel2.setBackground(new java.awt.Color(213, 216, 219));
        jPanel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel2.setLayout(new net.miginfocom.swing.MigLayout());
        jPanel3.add(jPanel2);

        btnEdit.setBackground(new java.awt.Color(0, 102, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setIconTextGap(10);
        btnEdit.setPreferredSize(new java.awt.Dimension(200, 45));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setIconTextGap(10);
        btnDelete.setPreferredSize(new java.awt.Dimension(200, 45));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(213, 216, 219));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(213, 216, 219)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ເບີໂທຕິດຕໍ່", "ສະຖານະ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jPanel5.add(jScrollPane1);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setBounds(0, 0, 1550, 794);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    public void Search(String data) {
        ClearTable();
        try {
            sql = " SELECT *"
                    + " FROM teacher"
                    + " inner join sex on teacher.sexID=sex.sexID"
                    + " WHERE  teacherID LIKE '%" + data + "%' or teacherName LIKE '%" + data + "%'"
                    + " OR sex LIKE '%" + data + "%'"
                    + " ORDER BY teacherID DESC";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int status = rs.getInt("status");
                if (status == 1) {
                    state = "ສອນ";
                } else if (status == 0) {
                    state = "ໂຈະສອນ";
                }

                String[] info = {
                    rs.getString("teacherID"),
                    rs.getString("teacherName"),
                    rs.getString("Lastname"),
                    rs.getString("sex"),
                    rs.getString("Contact"),
                    state

                };
                model.addRow(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະແກ້ໄຂກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
            return;
        }
        edit.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        } else {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບລະຫັດຊໍ້ມູນນີ້ແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                showData();
            } else {
                showData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 1) {
            int row = table.getSelectedRow();

            String genderValue = table.getValueAt(row, 3).toString();

            if (genderValue != null && !genderValue.isEmpty()) {
                cmbGender.setSelectedItem(genderValue);
            }

            edit.txtID.setText(table.getValueAt(row, 0).toString());
            edit.txtName.setText(table.getValueAt(row, 1).toString());
            edit.txtLastname.setText(table.getValueAt(row, 2).toString());
            edit.txtContact.setText(table.getValueAt(row, 4).toString());

            String status = table.getValueAt(row, 5).toString().trim();
            if (status.equals("ກຳລັງສອນ")) {
                edit.rb1.setSelected(true);
            } else if (status.equals("ລາອອກ")) {
                edit.rb2.setSelected(true);
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        retrieveGender();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.searchIcon searchIcon1;
    public static Model.Table table;
    private Swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void deleteData() {
        int[] selectRow = table.getSelectedRows();
        try {
            sql = "call deleteTeacher(?)";
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
