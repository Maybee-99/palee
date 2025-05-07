
package Subform;
import Database.connectDB;
import Model.GenerateID;
import Model.message;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.comboBoxHeight;
import Model.formatNumber;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;

public class InternalSubject extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    DefaultTableModel model = new DefaultTableModel();
    message m;
    GenerateID id = new GenerateID();
    ArrayList<String> category = new ArrayList();
    DecimalFormat df = new DecimalFormat("#,###");
    ArrayList<String> level = new ArrayList();
    public InternalSubject() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        loadAllData();
        formatNumber.apply(txtCost);
        cmbLevel.setRenderer(new comboBoxHeight());
        cmbSub.setRenderer(new comboBoxHeight());
        table.fixTable(jScrollPane1);
        jPanel4.setBorder(BorderFactory.createCompoundBorder(new FlatRoundBorder(),new MatteBorder(15,15,15,15,new Color(212,230,249)) {
        }));
    }
    
     private void loadAllData() {
        SwingWorker<Void, Void> idWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                generateID();
                return null;
            }

            @Override
            protected void done() {
                retrieveSubType();
            }
        };
        idWorker.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                retrieveLevel();
                 showData();
                return null;
            }
        }.execute();

    }

    
       private void generateID() {
        String leveID = id.generateID("select max(subDetailID) as ID from subjectdetail ", "SDT", 3);
        lbID.setText(leveID);
    }

    private void retrieveSubType() {
        try {
            sql = "select * from subject";
            category.clear();
            cmbSub.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                category.add(rs.getString("subID"));
                cmbSub.addItem(rs.getString("subName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveLevel() {
        try {
            sql = "select * from level";
            level.clear();
            cmbLevel.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                level.add(rs.getString("levelID"));
                cmbLevel.addItem(rs.getString("level"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        try {
            clearTable();
            sql = "call selectSDAll()";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int cost = rs.getInt("cost");
                String[] data = {
                    rs.getString("subDetailID"),
                    rs.getString("subName"),
                    rs.getString("level"),
                    df.format(cost)
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            String s = category.get(cmbSub.getSelectedIndex());
            String l = level.get(cmbLevel.getSelectedIndex());
            sql = "call insertSD(?,?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbID.getText());
            cst.setString(2, s);
            cst.setString(3, l);
            cst.setString(4, txtCost.getText().replaceAll(",", "").trim());
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
            String s = category.get(cmbSub.getSelectedIndex());
            String l = level.get(cmbLevel.getSelectedIndex());
            sql = "call updateSD(?,?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbID.getText());
            cst.setString(2, s);
            cst.setString(3, l);
            cst.setString(4, txtCost.getText().replaceAll(",", "").trim());
            cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearData() {
        lbID.setText("");
        txtCost.setText("");
        cmbLevel.setSelectedIndex(0);
        cmbSub.setSelectedIndex(0);
        generateID();
    }

    private void deleteData() {
        int[] selectRows = table.getSelectedRows();
        try {
            sql = "call deleteSD(?)";
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


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lbID = new Swing.TextField();
        jPanel14 = new javax.swing.JPanel();
        cmbSub = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        cmbLevel = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        txtCost = new Swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 10, 20, new java.awt.Color(212, 230, 249)));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 230));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setPreferredSize(new java.awt.Dimension(190, 100));
        jPanel4.setLayout(new java.awt.GridLayout(2, 2, 10, 5));

        jPanel13.setBackground(new java.awt.Color(212, 230, 249));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel13.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        lbID.setEditable(false);
        lbID.setBackground(new java.awt.Color(255, 255, 255));
        lbID.setForeground(new java.awt.Color(0, 102, 255));
        lbID.setCornerRadius(12.0F);
        lbID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbID.setPlaceHolder("");
        lbID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbIDKeyReleased(evt);
            }
        });
        jPanel13.add(lbID);

        jPanel4.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(212, 230, 249));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ວິຊາ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel14.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        cmbSub.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbSub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(cmbSub);

        jPanel4.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(212, 230, 249));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະດັບ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel15.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        cmbLevel.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelActionPerformed(evt);
            }
        });
        jPanel15.add(cmbLevel);

        jPanel4.add(jPanel15);

        jPanel16.setBackground(new java.awt.Color(212, 230, 249));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຄ່າເທີມ/ວິຊາ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel16.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        txtCost.setCornerRadius(12.0F);
        txtCost.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCost.setPlaceHolder("");
        txtCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostKeyTyped(evt);
            }
        });
        jPanel16.add(txtCost);

        jPanel4.add(jPanel16);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 20, 20, 20, new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel3.setPreferredSize(new java.awt.Dimension(900, 60));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(620, 80));
        jPanel5.setLayout(new java.awt.GridLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new net.miginfocom.swing.MigLayout());

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທືກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(162, 45));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel7.add(btnSave);

        btnEdit.setBackground(new java.awt.Color(0, 102, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setPreferredSize(new java.awt.Dimension(162, 45));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel7.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setPreferredSize(new java.awt.Dimension(162, 45));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel7.add(btnDelete);

        jPanel5.add(jPanel7);

        jPanel3.add(jPanel5);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ວິຊາ", "ລະດັບ", "ຄ່າຮຽນ/ເທີມ"
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

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbIDKeyReleased

    private void cmbLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLevelActionPerformed
        txtCost.requestFocus();
    }//GEN-LAST:event_cmbLevelActionPerformed

    private void txtCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostKeyReleased

    }//GEN-LAST:event_txtCostKeyReleased

    private void txtCostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostKeyTyped
        char c = evt.getKeyChar();
        String text = txtCost.getText();

        if (!Character.isDigit(c)) {
            evt.consume(); 
        } else {
            if (text.isEmpty() && c == '0') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCostKeyTyped

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtCost.getText())) {
            boolean isDuplicate = IntStream.range(0, table.getRowCount())
            .anyMatch(i -> table.getValueAt(i, 1).toString().equals(cmbSub.getSelectedItem())
                && table.getValueAt(i, 2).toString().equals(cmbLevel.getSelectedItem()));
            if (isDuplicate) {
                JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຊ້ຳກັນແລ້ວ!"));
                return;
            }
            saveData();
            clearData();
            showData();

        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            editData();
            clearData();
            showData();
        } else {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະແກ້ໄຂກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row < 0) {
            m = new message("ກະລຸນາເລືອກລາຍການທີ່ທ່ານຕ້ອງການຈະລຶບກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        } else {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                showData();
            }
            clearData();

        }
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        lbID.setText(table.getValueAt(row, 0).toString());
        cmbSub.setSelectedItem(table.getValueAt(row, 1).toString());
        cmbLevel.setSelectedItem(table.getValueAt(row, 2).toString());
        txtCost.setText(table.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbLevel;
    private javax.swing.JComboBox<String> cmbSub;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.TextField lbID;
    private Model.Table table;
    private Swing.TextField txtCost;
    // End of variables declaration//GEN-END:variables
}
