
package Subform;

import Database.connectDB;
import Model.OnlyInputString;
import Model.GenerateID;
import Model.message;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.comboBoxHeight;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;
public class SubjectDialog extends javax.swing.JDialog {

    message m;
    String sql;
    Connection con = connectDB.getConnect();
    ArrayList category = new ArrayList();
    DefaultTableModel model = new DefaultTableModel();
    GenerateID id = new GenerateID();
    public SubjectDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel) table.getModel();
        generateID();
        showData();
        serviceAction();
        retrieveSubType();
        table.fixTable(jScrollPane1);
        jPanel5.setBorder(BorderFactory.createCompoundBorder(new FlatRoundBorder() ,new MatteBorder(15,15,15,15,new Color(212,230,249)){
        }));
    }
     private void generateID() {
        String subid = id.generateID("select max(subID) as ID from subject ", "SUB", 2);
        txtID.setText(subid);
    }

    private void serviceAction() {

        m = new message("ກະລຸນາປ້ອນຕົວອັກສອນເທົ່ານັ້ນ!");
        OnlyInputString ois = new OnlyInputString(m);
        txtName.addKeyListener(ois);

        cmbCate.setRenderer(new comboBoxHeight());
    }

    private void saveData() {
        try {
            String s = category.get(cmbCate.getSelectedIndex()).toString();
            sql = "call insertSubject(?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtID.getText());
            cst.setString(2, txtName.getText());
            cst.setString(3, s);
            cst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData() {
    try {
        ClearTable();
        sql = "SELECT subject.subID, subject.subName, subjecttype.subTypeName "
            + "FROM subject "
            + "INNER JOIN subjecttype ON subject.subTypeID = subjecttype.subTypeID";
        
        ResultSet rs = con.createStatement().executeQuery(sql);
        while (rs.next()) {
            String[] data = {
                rs.getString("subID"),
                rs.getString("subName"),
                rs.getString("subTypeName")
            };
            model.addRow(data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private void Clear() {
        txtName.setText("");
        cmbCate.setSelectedIndex(0);
        txtName.requestFocus();
        generateID();

    }

    private void retrieveSubType() {
        try {
            sql = "select * from subjecttype";
            category.clear();
            cmbCate.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                category.add(rs.getString("subTypeID"));
                cmbCate.addItem(rs.getString("subTypeName"));
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

    private void EditData() {
        try {
            sql = " call updateSubject(?,?,?)";
            String s = category.get(cmbCate.getSelectedIndex()).toString();
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtID.getText());
            cst.setString(2, txtName.getText());
            cst.setString(3, s);
            cst.executeUpdate();

        } catch (Exception e) {
        }
    }

    private void DeleteData() {
        int[] selectRows = table.getSelectedRows();
        try {
            sql = "call deleteSubject(?)";
            CallableStatement cst = con.prepareCall(sql);
            for (int rows : selectRows) {
                String ID = table.getValueAt(rows, 0).toString();
                cst.setString(1, ID);
                cst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        txtID = new Swing.TextField();
        jPanel14 = new javax.swing.JPanel();
        txtName = new Swing.TextField();
        jPanel15 = new javax.swing.JPanel();
        cmbCate = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subject");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(40, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 350));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(212, 230, 249));
        jPanel5.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel13.setBackground(new java.awt.Color(212, 230, 249));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel13.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel13.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setForeground(new java.awt.Color(0, 51, 255));
        txtID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtID.setCornerRadius(12.0F);
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID.setPlaceHolder("");
        jPanel13.add(txtID);

        jPanel5.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(212, 230, 249));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຊື່ວິຊາ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel14.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel14.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtName.setCornerRadius(12.0F);
        txtName.setPlaceHolder("ປ້ອນວິຊາ");
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        jPanel14.add(txtName);

        jPanel5.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(212, 230, 249));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ໝວດວິຊາ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel15.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        cmbCate.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel15.add(cmbCate);

        jPanel5.add(jPanel15);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 20, 20, 20, new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel3.setPreferredSize(new java.awt.Dimension(760, 50));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 3, 15, 15));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
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
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel4.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 51, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);

        jPanel3.add(jPanel4);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່ວິຊາ", "ໝວດ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(814, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased

    }//GEN-LAST:event_txtNameKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtName.getText())) {
            saveData();
            showData();
            Clear();
        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            EditData();
            showData();
            Clear();
            btnSave.setEnabled(true);
        } else {
            m = new message("ກະລຸນາເລືອກຂໍ້ມູນກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບ " + txtName.getText() + " ແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                DeleteData();
                showData();
            }
            Clear();
            btnSave.setEnabled(true);
        } else {
            m = new message("ກະລຸນາເລືອກຂໍ້ມູນກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        txtID.setText(table.getValueAt(row, 0).toString());
        txtName.setText(table.getValueAt(row, 1).toString());
        cmbCate.setSelectedItem(table.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(SubjectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SubjectDialog dialog = new SubjectDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbCate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private Model.Table table;
    private Swing.TextField txtID;
    private Swing.TextField txtName;
    // End of variables declaration//GEN-END:variables
}
