package Subform;

import Database.connectDB;
import Model.message;
import static Subform.Student.table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class checkTeaching extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    DefaultTableModel model = new DefaultTableModel();

    public checkTeaching() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        table.fixTable(jScrollPane1);
        showData();
    }

    private void clearTB() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }

    }

    private void showData() {
        try {
            // Clear the existing data in the table
            clearTB();

            // SQL query to get the required data
            sql = "SELECT teach.teachDate as dt, "
                    + "subject.subName as sub, "
                    + "CONCAT(teacher.teacherName, ' ', teacher.Lastname) AS fullName, "
                    + "teacher.teacherID as id, "
                    + "teach.hour as h, "
                    + "level.level as l, "
                    + "CASE "
                    + "    WHEN teach.teachCondition = teach.teacherID THEN N'ສອນປົກກະຕິ' " // Adjust condition as per your logic
                    + "    ELSE N'ສອນແທນ' "
                    + "END AS status "
                    + "FROM teach "
                    + "INNER JOIN subjectdetail ON teach.subDetailID = subjectdetail.subDetailID "
                    + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
                    + "INNER JOIN level ON subjectdetail.levelID = level.levelID "
                    + "INNER JOIN teacher ON teach.teacherID = teacher.teacherID "
                    + "ORDER BY dt DESC";

            // Prepare and execute the SQL statement
            PreparedStatement psm = con.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();

            // Date formatting
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Iterate through the result set and populate the table model
            while (rs.next()) {
                // Format the date
                //String formattedDate = sdf.format(rs.getDate("dt")); // Format teachDate as yyyy-MM-dd

                // Handle null values for certain columns
                String teacherID = rs.getString("id") != null ? rs.getString("id") : "";
                String fullName = rs.getString("fullName") != null ? rs.getString("fullName") : "";
                String sub = rs.getString("sub") != null ? rs.getString("sub") : "";
                String level = rs.getString("l") != null ? rs.getString("l") : "";
                String hour = rs.getString("h") != null ? rs.getString("h") : "";

                // Prepare the data for the row
                String[] data = {
                    teacherID,
                    fullName,
                    sub,
                    level,
                    hour,
                    rs.getString("status"),
                    rs.getString("dt") // Insert the formatted date
                };

                // Add the row to the table model
                model.addRow(data);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }
    }

//    private void showData() {
//        try {
//            clearTB();
//            sql = "SELECT teach.teachDate as dt, "
//                    + "subject.subName as sub, "
//                    + "CONCAT(teacher.teacherName, ' ', teacher.Lastname) AS fullName, "
//                    + "teacher.teacherID as id, "
//                    + "teach.hour as h, "
//                    + "level.level as l "
//                    + "FROM teach "
//                    + "INNER JOIN subjectdetail ON teach.subDetailID = subjectdetail.subDetailID "
//                    + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
//                    + "INNER JOIN level ON subjectdetail.levelID = level.levelID "
//                    + "INNER JOIN teacher ON teach.teacherID = teacher.teacherID "
//                    + "ORDER BY dt DESC";
//            PreparedStatement psm = con.prepareStatement(sql);
//            ResultSet rs = psm.executeQuery();
//            while (rs.next()) {
//                String[] data = {
//                    rs.getString("id"),
//                    rs.getString("fullName"),
//                    rs.getString("sub"),
//                    rs.getString("l"),
//                    rs.getString("h"),
//                    rs.getString("dt")
//                };
//                model.addRow(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void Search(String data) {
        clearTB();
        try {
            sql = "SELECT teach.teachDate as dt, "
                    + "subject.subName as sub, "
                    + "CONCAT(teacher.teacherName, ' ', teacher.Lastname) AS fullName, "
                    + "teacher.teacherID as id, "
                    + "teach.hour as h, "
                    + "level.level as l, "
                    + "CASE "
                    + "    WHEN teach.teachCondition = teach.teacherID THEN N'ສອນປົກກະຕິ' "
                    + "    ELSE N'ສອນແທນ' "
                    + "END AS status "
                    + "FROM teach "
                    + "INNER JOIN subjectdetail ON teach.subDetailID = subjectdetail.subDetailID "
                    + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
                    + "INNER JOIN level ON subjectdetail.levelID = level.levelID "
                    + "INNER JOIN teacher ON teach.teacherID = teacher.teacherID "
                    + "WHERE teacher.teacherID=? OR teacher.teacherName LIKE ? "
                    + "ORDER BY dt DESC";

            PreparedStatement psm = con.prepareStatement(sql);
            psm.setString(1, data);
            psm.setString(2, "%" + data + "%");

            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                String[] info = {
                    rs.getString("id"),
                    rs.getString("fullName"),
                    rs.getString("sub"),
                    rs.getString("l"),
                    rs.getString("h"),
                    rs.getString("status"),
                    rs.getString("dt")
                };
                model.addRow(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchIcon1 = new Model.searchIcon();
        txtSearch = new Swing.TextField();
        btnDelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jLabel1.setBackground(new java.awt.Color(0, 102, 255));
        jLabel1.setFont(new java.awt.Font("Saysettha OT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ຄົ້ນຫາການສອນ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(37, 60));
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 10, 10, new java.awt.Color(242, 242, 242)));
        jPanel2.setPreferredSize(new java.awt.Dimension(1069, 80));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel3.setPreferredSize(new java.awt.Dimension(420, 55));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(searchIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 50, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSearch.setCornerRadius(12.0F);
        txtSearch.setPlaceHolder("ຄົ້ນຫາຕາມລະຫັດ, ຊື່ ອາຈານ");
        txtSearch.setPreferredSize(new java.awt.Dimension(600, 60));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel3.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 3, 390, 50));

        jPanel2.add(jPanel3);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setPreferredSize(new java.awt.Dimension(200, 45));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 20, 20, new java.awt.Color(242, 242, 242)));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດອາຈານ", "ອາຈານ", "ວິຊາ", "ລະດັບ", "ຈຳນວນຊົ່ວໂມງ", "ໝາຍເຫດ", "ວັນທີ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(table);

        jPanel4.add(jScrollPane1);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!"));
        } else {
            int x = JOptionPane.showConfirmDialog(this, new message("ທ່ານແນ່ໃຈຈະລຶບຂໍ້ມູນນີ້ແທ້ບໍ?"), "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
            } else {
                showData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.searchIcon searchIcon1;
    private Model.Table table;
    private Swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void deleteData() {
        int[] selectRow = table.getSelectedRows();
        try {
            sql = "delete from teach where teachDate=?";
            PreparedStatement cst = con.prepareStatement(sql);
            for (int rows : selectRow) {
                String id = table.getValueAt(rows, 6).toString();
                cst.setString(1, id);
                cst.executeUpdate();
            }

            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
