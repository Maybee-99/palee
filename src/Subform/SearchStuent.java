package Subform;

import Database.connectDB;
import Model.StudentSelectionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class SearchStuent extends javax.swing.JDialog {

    DefaultTableModel model = new DefaultTableModel();
    Connection con = connectDB.getConnect();
    String sql;
    public String stdID;
    public String stdName;
    public String Lastname;
    public String sex;
    String ph1;
    String ph2;
    String school;
    String dis;
    String pro;
    private final StudentSelectionListener listener;

    public SearchStuent(java.awt.Frame parent, boolean modal, StudentSelectionListener listener) {
        super(parent, modal);
        this.listener = listener;
        initComponents();
        model = (DefaultTableModel) table.getModel();
        table.fixTable(jScrollPane1);
        showData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel3 = new javax.swing.JPanel();
        searchIcon1 = new Model.searchIcon();
        txtSearch = new Swing.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(480, 700));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(213, 216, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(213, 216, 219)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດນັກຮຽນ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ເບີຕິດຕໍ່", "ເບີຜູ້ປົກຄອງ", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(0).setMinWidth(110);
            table.getColumnModel().getColumn(0).setPreferredWidth(110);
            table.getColumnModel().getColumn(0).setMaxWidth(110);
            table.getColumnModel().getColumn(1).setMinWidth(120);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            table.getColumnModel().getColumn(1).setMaxWidth(120);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(213, 216, 219));
        jPanel3.setPreferredSize(new java.awt.Dimension(1171, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(searchIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 38, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSearch.setCornerRadius(12.0F);
        txtSearch.setPlaceHolder("ຄົ້ນຫາ...");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel3.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 50));

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(1205, 687));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1 && evt.getClickCount() == 2) {
            stdID = table.getValueAt(selectedRow, 0).toString();
            stdName = table.getValueAt(selectedRow, 1).toString();
            Lastname = table.getValueAt(selectedRow, 2).toString();
            sex = table.getValueAt(selectedRow, 3).toString();
            ph1 = table.getValueAt(selectedRow, 4).toString();
            ph2 = table.getValueAt(selectedRow, 5).toString();
            school = table.getValueAt(selectedRow, 6).toString();
            dis = table.getValueAt(selectedRow, 7).toString();
            pro = table.getValueAt(selectedRow, 8).toString();
            listener.onStudentSelected(stdID, stdName, Lastname, sex, ph1, ph2, school, dis, pro);
            this.dispose();
        }

    }//GEN-LAST:event_tableMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if ("".equals(txtSearch.getText())) {
            Search("");
        } else {
            Search(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

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
            java.util.logging.Logger.getLogger(SearchStuent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchStuent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchStuent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchStuent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                var dialog = new SearchStuent(new javax.swing.JFrame(), true, new StudentSelectionListener() {
                    @Override
                    public void onStudentSelected(String id, String fname, String lname, String gender, String ph1, String ph2, String school, String dis, String pro) {

                    }
                });
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.searchIcon searchIcon1;
    private Model.Table table;
    private Swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void showData() {
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
                        rs.getString("provinceName")

                    };
                    model.addRow(data);
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

    public void Search(String data) {
        ClearTable();
        try {
            sql = " SELECT *"
                    + " FROM student"
                    + " inner join sex on student.sexID=sex.sexID"
                    + " inner join district on student.districtID=district.districtID"
                    + " inner join province on district.provinceID=province.provinceID"
                    + " WHERE  stdID LIKE '%" + data + "%' or stdName LIKE '%" + data + "%'"
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
                    rs.getString("provinceName")
                };
                model.addRow(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
