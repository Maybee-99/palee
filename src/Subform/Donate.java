package Subform;

import Database.connectDB;
import Model.comboBoxHeight;
import Model.formatNumber;
import Model.message;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class Donate extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    DecimalFormat df = new DecimalFormat("#,###");
    DefaultTableModel model = new DefaultTableModel();
    message m;
    String sql = "";
    ArrayList unit = new ArrayList();

    public Donate() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        cmbUnit.setRenderer(new comboBoxHeight());
        showData();
        formatNumber.apply(txtMoney);
        dc.setDate(new Date());
        table.fixTable(jScrollPane1);
        
        jPanel1.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(15,15,15,15,new Color(212,230,249)),new FlatRoundBorder() {
        }));
        
    }

    public void retrieveUnit() {
        try {
            sql = "select * from unit";
            unit.clear();
            cmbUnit.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                unit.add(rs.getString("unitID"));
                cmbUnit.addItem(rs.getString("unit"));
            }
            cmbUnit.revalidate();
            cmbUnit.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtItem = new Swing.TextField();
        jPanel15 = new javax.swing.JPanel();
        txtQty = new Swing.TextField();
        jPanel17 = new javax.swing.JPanel();
        cmbUnit = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        txtMoney = new Swing.TextField();
        jPanel16 = new javax.swing.JPanel();
        dc = new com.toedter.calendar.JDateChooser();
        jPanel18 = new javax.swing.JPanel();
        Donator = new Swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setTitle("ຟອມຈັດການ ມອບ-ຮັບເຄື່ອງບໍລິຈາກ");
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

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 10, 20, new java.awt.Color(212, 230, 249)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1538, 300));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 30, 0));

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(212, 230, 249)));
        jPanel4.setLayout(new java.awt.GridLayout(3, 1));

        jPanel14.setBackground(new java.awt.Color(212, 230, 249));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຊື່ເຄື່ອງບໍລິຈາກ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel14.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel14.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtItem.setCornerRadius(12.0F);
        txtItem.setPlaceHolder("");
        txtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemKeyReleased(evt);
            }
        });
        jPanel14.add(txtItem);

        jPanel4.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(212, 230, 249));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຈຳນວນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel15.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtQty.setCornerRadius(12.0F);
        txtQty.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtQty.setPlaceHolder("");
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyKeyTyped(evt);
            }
        });
        jPanel15.add(txtQty);

        jPanel4.add(jPanel15);

        jPanel17.setBackground(new java.awt.Color(212, 230, 249));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຫົວໜ່ວຍ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel17.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel17.setLayout(new java.awt.BorderLayout(20, 0));

        cmbUnit.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbUnit.setMinimumSize(new java.awt.Dimension(50, 50));
        cmbUnit.setPreferredSize(new java.awt.Dimension(650, 50));
        jPanel17.add(cmbUnit, java.awt.BorderLayout.CENTER);

        btnAdd.setBackground(new java.awt.Color(0, 204, 51));
        btnAdd.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 30)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("+");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setMinimumSize(new java.awt.Dimension(50, 23));
        btnAdd.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel17.add(btnAdd, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel17);

        jPanel1.add(jPanel4);

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(212, 230, 249)));
        jPanel3.setLayout(new java.awt.GridLayout(3, 1));

        jPanel19.setBackground(new java.awt.Color(212, 230, 249));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(212, 230, 249)), "ຖືກເປັນເງິນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel19.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel19.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtMoney.setCornerRadius(12.0F);
        txtMoney.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtMoney.setPlaceHolder("");
        txtMoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMoneyKeyTyped(evt);
            }
        });
        jPanel19.add(txtMoney);

        jPanel3.add(jPanel19);

        jPanel16.setBackground(new java.awt.Color(212, 230, 249));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ວັນທີ່", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel16.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel16.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        dc.setDateFormatString("YYYY-dd-MM");
        dc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dc.setOpaque(false);
        jPanel16.add(dc);

        jPanel3.add(jPanel16);

        jPanel18.setBackground(new java.awt.Color(212, 230, 249));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ມອບໂດຍ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel18.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel18.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        Donator.setCornerRadius(12.0F);
        Donator.setPlaceHolder("");
        jPanel18.add(Donator);

        jPanel3.add(jPanel18);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 15, 15, 15, new java.awt.Color(242, 242, 242)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(1490, 60));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 15, 5, 15, new java.awt.Color(242, 242, 242)));
        jPanel7.setPreferredSize(new java.awt.Dimension(760, 60));
        jPanel7.setLayout(new java.awt.GridLayout());
        jPanel7.add(jPanel6);

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 25, 0, 0, new java.awt.Color(242, 242, 242)));
        jPanel8.setLayout(new net.miginfocom.swing.MigLayout());

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(176, 60));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel8.add(btnSave);

        btnEdit.setBackground(new java.awt.Color(0, 102, 255));
        btnEdit.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setPreferredSize(new java.awt.Dimension(176, 60));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel8.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລຶບ");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setPreferredSize(new java.awt.Dimension(176, 60));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel8.add(btnDelete);

        btnClear.setBackground(new java.awt.Color(255, 0, 255));
        btnClear.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("ເຄຼຍ");
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.setPreferredSize(new java.awt.Dimension(176, 60));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel8.add(btnClear);

        jPanel7.add(jPanel8);

        jPanel5.add(jPanel7);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(null);
        jScrollPane1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ລາຍການ", "ຈຳນວນ", "ຫົວໜ່ວຍ", "ຄິດໄລ່ເປັນເງິນ", "ວັນທີ່ມອບ", "ມອບໂດຍ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 15)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1550, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        btnSave.setEnabled(false);
        txtItem.setText(table.getValueAt(row, 1).toString());
        txtQty.setText(table.getValueAt(row, 2).toString());
        cmbUnit.setSelectedItem(table.getValueAt(row, 3));
        txtMoney.setText(table.getValueAt(row, 4).toString().replaceAll("ກີບ", "").trim());
        Date date = java.sql.Date.valueOf(table.getValueAt(row, 5).toString());
        dc.setDate(date);
        Donator.setText(table.getValueAt(row, 6).toString());

    }//GEN-LAST:event_tableMouseClicked

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased

    }//GEN-LAST:event_txtQtyKeyReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = table.getSelectedRow();
        if (row >= 0) {
            m = new message("ທ່ານແນ່ໃຈຈະລຶບແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                deleteData();
                showData();
            }
            Clear();
        } else {
            m = new message("ກະລຸນາເລືອກແຖວກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        retrieveUnit();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtItem.getText() != null && txtQty.getText() != null && txtMoney.getText() != null && Donator.getText() != null && dc.getDate() != null) {
            saveData();
            Clear();
        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບກ່ອນ!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (txtItem.getText() != null && txtQty.getText() != null && txtMoney.getText() != null && Donator.getText() != null && dc.getDate() != null) {
            editData();
            Clear();
        } else {
            m = new message("ຂໍ້ມູນບໍ່ສາມາດຫວ່າງເປົ່າໄດ້!");
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Clear();
    }//GEN-LAST:event_btnClearActionPerformed

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

    private void txtQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyTyped
        char c = evt.getKeyChar();
        String text = txtQty.getText();

        if (!Character.isDigit(c)) {
            evt.consume(); 
        } else {
            if (text.isEmpty() && c == '0') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtQtyKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Unit units = new Unit(this);
        units.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemKeyReleased
        if(evt.getKeyChar()=='\n')
        {
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtItemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.TextField Donator;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbUnit;
    private com.toedter.calendar.JDateChooser dc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JScrollPane jScrollPane1;
    private Model.Table table;
    private Swing.TextField txtItem;
    private Swing.TextField txtMoney;
    private Swing.TextField txtQty;
    // End of variables declaration//GEN-END:variables

    public void saveData() {
    try {
        sql = "call insertToDonate(?,?,?,?,?,?)";
        String u = unit.get(cmbUnit.getSelectedIndex()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dc.getDate());
        
        String moneyText = txtMoney.getText().replaceAll(",", "").trim();
        
        if (moneyText.isEmpty()) {
            moneyText = null;
        }

        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, txtItem.getText());
        cst.setString(2, txtQty.getText());
        cst.setString(3, u);
        
        if (moneyText != null) {
            cst.setString(4, moneyText);
        } else {
            cst.setNull(4, java.sql.Types.NULL);  
        }

        cst.setString(5, date);
        cst.setString(6, Donator.getText());
        cst.executeUpdate();
        showData();

    } catch (Exception e) {
        e.printStackTrace();
        m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
        JOptionPane.showMessageDialog(this, m);
    }
}

    private void ClearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    private void showData() {
    try {
        ClearTable();
        sql = "call selectDonate()";
        ResultSet rs = con.createStatement().executeQuery(sql);
        while (rs.next()) {
            Integer money = rs.getObject("estimatedValue") == null ? 0 : rs.getInt("estimatedValue");

            String formattedMoney = df.format(money) + " ກີບ";

            String[] data = {
                rs.getString("itemID"),
                rs.getString("itemName"),
                rs.getString("quantity"),
                rs.getString("unit"),
                formattedMoney,
                rs.getString("receivedDate"),
                rs.getString("donator"),
            };
            model.addRow(data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void editData() {
    try {
        int row = table.getSelectedRow();
        Object val = table.getValueAt(row, 0);
        int ind = Integer.parseInt(val.toString());

        // SQL for updating data
        sql = "call updateDonate(?,?,?,?,?,?,?)";
        String u = unit.get(cmbUnit.getSelectedIndex()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dc.getDate());

        String moneyText = txtMoney.getText().replaceAll(",", "").trim();

        if (moneyText.isEmpty()) {
            moneyText = null;
        }

        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, ind);  
        cst.setString(2, txtItem.getText());
        cst.setString(3, txtQty.getText());
        cst.setString(4, u);

        if (moneyText != null) {
            cst.setString(5, moneyText);
        } else {
            cst.setNull(5, java.sql.Types.NULL); 
        }

        cst.setString(6, date);
        cst.setString(7, Donator.getText());

        cst.executeUpdate();
        showData();  

    } catch (Exception e) {
        e.printStackTrace();
        m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
        JOptionPane.showMessageDialog(this, m);
    }
}


    private void deleteData() {
        int[] selectRows = table.getSelectedRows();
        try {
            sql = "call deleteDonate(?)";
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

    private void Clear() {
        txtItem.setText("");
        txtQty.setText("");
        txtMoney.setText("");
        Donator.setText("");
        cmbUnit.setSelectedIndex(0);
        dc.setDate(null);
        btnSave.setEnabled(true);
        txtItem.requestFocus();

    }
}
