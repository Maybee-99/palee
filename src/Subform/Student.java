package Subform;

import Database.connectDB;
import Model.message;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class Student extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel();
    message m;
    Connection con = connectDB.getConnect();
    String sql;
    EditStudent edit = new EditStudent(null, true, this);
    ArrayList province = new ArrayList();
    ArrayList district = new ArrayList();
    ArrayList sex = new ArrayList();

    public Student() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        table.fixTable(jScrollPane1);
        new LoadStudentData().execute();
    }

    private class LoadStudentData extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            retrieveProvince();
            retrieveDistrict();
            retrieveGender();
            return null;
        }

        @Override
        protected void done() {
            showData();
        }
    }

    private void retrieveProvince() {
        try {
            sql = "select * from province";
            province.clear();
            edit.cmbProvince.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                province.add(rs.getString("provinceID"));
                edit.cmbProvince.addItem(rs.getString("provinceName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveDistrict() {
        try {
            sql = "select * from district";
            district.clear();
            edit.cmbDistrict.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                district.add(rs.getString("districtID"));
                edit.cmbDistrict.addItem(rs.getString("districtName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveGender() {
        try {
            sql = "select * from sex";
            sex.clear();
            edit.cmbSex.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                sex.add(rs.getString("sexID"));
                edit.cmbSex.addItem(rs.getString("sex"));
            }
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
        jPanel2 = new javax.swing.JPanel();
        searchIcon1 = new Model.searchIcon();
        txtSearch = new Swing.TextField();
        jPanel6 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnViewStudent = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.black);
        setTitle("ຟອມຈັດການຂໍ້ມູນນັກຮຽນ");
        setFont(new java.awt.Font("Noto Sans Lao", 0, 14)); // NOI18N
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(80, 40));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(213, 216, 219));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 0, 0, 0, new java.awt.Color(213, 216, 219)));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 90));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel4.setBackground(new java.awt.Color(213, 216, 219));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 5, 1, new java.awt.Color(213, 216, 219)));
        jPanel4.setLayout(new net.miginfocom.swing.MigLayout());

        jPanel2.setBackground(new java.awt.Color(213, 216, 219));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(searchIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 10, -1, 40));

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 50, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSearch.setCornerRadius(12.0F);
        txtSearch.setPlaceHolder("ຄົ້ນຫາຕາມລະຫັດ, ຊື່, ເພດ, ເບີບິດຕໍ່");
        txtSearch.setPreferredSize(new java.awt.Dimension(600, 60));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 0, 460, 51));

        jPanel4.add(jPanel2);

        jPanel6.setBackground(new java.awt.Color(213, 216, 219));
        jPanel6.setPreferredSize(new java.awt.Dimension(80, 100));
        jPanel4.add(jPanel6);

        btnEdit.setBackground(new java.awt.Color(0, 102, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setIconTextGap(10);
        btnEdit.setPreferredSize(new java.awt.Dimension(200, 50));
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
        btnDelete.setIconTextGap(10);
        btnDelete.setPreferredSize(new java.awt.Dimension(200, 50));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);

        btnViewStudent.setBackground(new java.awt.Color(0, 153, 102));
        btnViewStudent.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnViewStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnViewStudent.setText("ປ່ຽນວິຊາຮຽນ");
        btnViewStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewStudent.setIconTextGap(10);
        btnViewStudent.setPreferredSize(new java.awt.Dimension(200, 50));
        btnViewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStudentActionPerformed(evt);
            }
        });
        jPanel4.add(btnViewStudent);

        jPanel3.add(jPanel4);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(213, 216, 219));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(213, 216, 219)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ເບີຕິດຕໍ່", "ເບີຜູ້ປົກຄອງ", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ", "ທີ່ພັກ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        jPanel5.add(jScrollPane1);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        getAccessibleContext().setAccessibleParent(this);

        setBounds(0, 0, 1550, 798);
    }// </editor-fold>//GEN-END:initComponents

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
            } else {
                showData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະແກ້ໄຂກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
            return;
        }
        edit.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 1) {
            int row = table.getSelectedRow();
            edit.txtID.setText(table.getValueAt(row, 0).toString());
            edit.txtName.setText(table.getValueAt(row, 1).toString());
            edit.txtLastname.setText(table.getValueAt(row, 2).toString());

            String genderValue = table.getValueAt(row, 3).toString();
            if (!genderValue.isEmpty()) {
                edit.cmbSex.setSelectedItem(genderValue);
            }

            edit.txtPhoneNumber1.setText(table.getValueAt(row, 4).toString());
            edit.txtPhoneNumber2.setText(table.getValueAt(row, 5).toString());

            edit.txtSchool.setText(table.getValueAt(row, 6).toString());

            String dis = table.getValueAt(row, 7).toString();
            if (!dis.isEmpty()) {
                edit.cmbDistrict.setSelectedItem(dis);
            }

            String pro = table.getValueAt(row, 8).toString();
            if (!pro.isEmpty()) {
                edit.cmbProvince.setSelectedItem(pro);
            }
            edit.cmbStay.setSelectedItem(table.getValueAt(row, 9));

        }

    }//GEN-LAST:event_tableMouseClicked

    private void btnViewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStudentActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            String id = table.getValueAt(row, 0).toString();
            String name = table.getValueAt(row, 1).toString();
            String lastname = table.getValueAt(row, 2).toString();
            String fullName = name + " " + lastname;

            ViewDetail vs = new ViewDetail(null, true, id, fullName);
            vs.setVisible(true);
        } else {
            m = new message("ກະລຸນາເລືອກນັກຮຽນຈາກຕາຕະລາງ!!!");
            JOptionPane.showMessageDialog(this, m);
        }

    }//GEN-LAST:event_btnViewStudentActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    public void Search(String data) {
        ClearTable();
        try {
            sql = " SELECT *"
                    + " FROM student"
                    + " inner join sex on student.sexID=sex.sexID"
                    + " inner join district on student.districtID=district.districtID"
                    + " inner join province on district.provinceID=province.provinceID"
                    + " inner join stay on student.stay_id=stay.stay_id"
                    + " WHERE  stdID LIKE '%" + data + "%' OR stdName LIKE '%" + data + "%' OR sex LIKE '%" + data + "%' OR phoneNumber LIKE '%" + data + "%' "
                    + " ORDER BY stdID ASC";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {

                String[] info = {
                    rs.getString("stdID"),
                    rs.getString("stdName"),
                    rs.getString("Lastname"),
                    rs.getString("sex"),
                    rs.getString("phoneNumber"),
                    rs.getString("Contact"),
                    rs.getString("school"),
                    rs.getString("districtName"),
                    rs.getString("provinceName"),
                    rs.getString("stay"),};
                model.addRow(info);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnViewStudent;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.searchIcon searchIcon1;
    public static Model.Table table;
    private Swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void ClearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    public void showData() {
        try {
            ClearTable();
            sql = "call selectStudentCondition()";
            try (ResultSet rs = con.createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    String[] data = {
                        rs.getString("stdID"),
                        rs.getString("stdName"),
                        rs.getString("Lastname"),
                        rs.getString("sex"),
                        rs.getString("phoneNumber"),
                        rs.getString("Contact"),
                        rs.getString("school"),
                        rs.getString("districtName"),
                        rs.getString("provinceName"),
                        rs.getString("stay")

                    };
                    model.addRow(data);
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteData() {
        int[] selectRow = table.getSelectedRows();
        try {
            sql = "call deleteStudent(?)";
            CallableStatement cst = con.prepareCall(sql);
            for (int rows : selectRow) {
                String id = table.getValueAt(rows, 0).toString();
                cst.setString(1, id);
                cst.executeUpdate();
            }

            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
