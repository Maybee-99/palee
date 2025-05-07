package Subform;

import java.awt.event.KeyEvent;
import Database.connectDB;
import Model.OnlyInputString;
import Model.message;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
import Model.PhoneNumberValidator;
import Model.comboBoxHeight;
import java.util.ArrayList;

public class EditStudent extends javax.swing.JDialog {

    Connection con = connectDB.getConnect();
    String sql;
    public static Student std;
    ArrayList stay = new ArrayList();

    public EditStudent(java.awt.Frame parent, boolean modal, Student std) {
        super(parent, modal);
        initComponents();
        EditStudent.std = std;
        cmbSex.setRenderer(new comboBoxHeight());
        cmbDistrict.setRenderer(new comboBoxHeight());
        cmbProvince.setRenderer(new comboBoxHeight());
         cmbStay.setRenderer(new comboBoxHeight());
        PhoneNumberValidator.Checkvalidate(txtPhoneNumber1);
        PhoneNumberValidator.Checkvalidate(txtPhoneNumber2);
        OnlyInputString ois = new OnlyInputString(new message("ກະລຸນາປ້ອນຕົວອັກສອນເທົ່ານັ້ນ!"));
        txtName.addKeyListener(ois);
        txtLastname.addKeyListener(ois);
    }
     private void retrieveStay() {
        try {
            sql = "select * from stay";
            stay.clear();
            cmbStay.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                stay.add(rs.getString("stay_id"));
                cmbStay.addItem(rs.getString("stay"));
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
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtID = new Swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new Swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastname = new Swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        cmbSex = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneNumber1 = new Swing.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtPhoneNumber2 = new Swing.TextField();
        jLabel8 = new javax.swing.JLabel();
        txtSchool = new Swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        cmbProvince = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbDistrict = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cmbStay = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "ແກ້ໄຂຂໍ້ມູນນັກຮຽນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new java.awt.GridLayout(10, 2, -200, 8));

        jLabel17.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("ລະຫັດ:");
        jLabel17.setPreferredSize(new java.awt.Dimension(55, 35));
        jPanel3.add(jLabel17);

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtID.setForeground(new java.awt.Color(0, 102, 255));
        txtID.setBorderColor(new java.awt.Color(204, 204, 204));
        txtID.setCornerRadius(12.0F);
        txtID.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID.setPlaceHolder("");
        jPanel3.add(txtID);

        jLabel2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("ຊື່:");
        jPanel3.add(jLabel2);

        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtName.setBorderColor(new java.awt.Color(204, 204, 204));
        txtName.setCornerRadius(12.0F);
        txtName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtName.setPlaceHolder("ປ້ອນຊື່");
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        jPanel3.add(txtName);

        jLabel3.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ນາມສະກຸນ:");
        jPanel3.add(jLabel3);

        txtLastname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtLastname.setBorderColor(new java.awt.Color(204, 204, 204));
        txtLastname.setCornerRadius(12.0F);
        txtLastname.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtLastname.setPlaceHolder("ປ້ອນນາມສະກຸນ");
        jPanel3.add(txtLastname);

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ເພດ:");
        jPanel3.add(jLabel4);

        cmbSex.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbSex.setPreferredSize(new java.awt.Dimension(72, 35));
        jPanel3.add(cmbSex);

        jLabel6.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("ເບີໂທ:");
        jPanel3.add(jLabel6);

        txtPhoneNumber1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtPhoneNumber1.setBorderColor(new java.awt.Color(204, 204, 204));
        txtPhoneNumber1.setCornerRadius(12.0F);
        txtPhoneNumber1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPhoneNumber1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber1.setPlaceHolder("020XXXXXXXX");
        txtPhoneNumber1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneNumber1FocusLost(evt);
            }
        });
        txtPhoneNumber1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumber1KeyReleased(evt);
            }
        });
        jPanel3.add(txtPhoneNumber1);

        jLabel7.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ເບີຜູ້ປົກຄອງ:");
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 17));
        jPanel3.add(jLabel7);

        txtPhoneNumber2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtPhoneNumber2.setBorderColor(new java.awt.Color(204, 204, 204));
        txtPhoneNumber2.setCornerRadius(12.0F);
        txtPhoneNumber2.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPhoneNumber2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber2.setPlaceHolder("020XXXXXXXX");
        txtPhoneNumber2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneNumber2FocusLost(evt);
            }
        });
        txtPhoneNumber2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumber2KeyReleased(evt);
            }
        });
        jPanel3.add(txtPhoneNumber2);

        jLabel8.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("ມາຈາກໂຮງຮຽນ:");
        jPanel3.add(jLabel8);

        txtSchool.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSchool.setBorderColor(new java.awt.Color(204, 204, 204));
        txtSchool.setCornerRadius(12.0F);
        txtSchool.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtSchool.setPlaceHolder("ປ້ອນໂຮງຮຽນ (EX: ມສ ສາທິດ)");
        jPanel3.add(txtSchool);

        jLabel10.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("ແຂວງ:");
        jPanel3.add(jLabel10);

        cmbProvince.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbProvince.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProvince.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbProvinceFocusGained(evt);
            }
        });
        jPanel3.add(cmbProvince);

        jLabel9.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("ເມືອງ:");
        jPanel3.add(jLabel9);

        cmbDistrict.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(cmbDistrict);

        jLabel21.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("ບ່ອນພັກ:");
        jLabel21.setPreferredSize(new java.awt.Dimension(55, 35));
        jPanel3.add(jLabel21);

        cmbStay.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbStay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(cmbStay);

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 530, 540));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setIconTextGap(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, 160, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 590, 670));

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(664, 770));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneNumber2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumber2KeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtSchool.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber2KeyReleased

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtLastname.requestFocus();
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void txtPhoneNumber1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumber1KeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtPhoneNumber2.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber1KeyReleased
    private void editData() {
        try {
            sql = "call updateStudent(?,?,?,?,?,?,?,?,?)";

            String s = std.sex.get(cmbSex.getSelectedIndex()).toString();
            String d = std.district.get(cmbDistrict.getSelectedIndex()).toString();
            String st = stay.get(cmbStay.getSelectedIndex()).toString();

            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtID.getText());
            cst.setString(2, txtName.getText());
            cst.setString(3, txtLastname.getText());
            cst.setString(4, s);
            cst.setString(5, txtPhoneNumber1.getText());
            cst.setString(6, txtPhoneNumber2.getText());
            cst.setString(7, txtSchool.getText());
            cst.setString(8, d);
            cst.setString(9, st);
            cst.executeUpdate();
            std.showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        editData();
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cmbProvinceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbProvinceFocusGained
        provinceChange();
    }//GEN-LAST:event_cmbProvinceFocusGained

    private void txtPhoneNumber1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneNumber1FocusLost
        int txt1 = txtPhoneNumber1.getText().length();
        if (!txtPhoneNumber1.getText().isEmpty() && txt1 < 11) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນໝາຍເລກໂທລະສັບໃຫ້ຄົບ"));
            txtPhoneNumber1.selectAll();
            txtPhoneNumber1.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber1FocusLost

    private void txtPhoneNumber2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneNumber2FocusLost
        int txt1 = txtPhoneNumber2.getText().length();
        if (!txtPhoneNumber2.getText().isEmpty() && txt1 < 11) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນໝາຍເລກໂທລະສັບໃຫ້ຄົບ"));
            txtPhoneNumber2.selectAll();
            txtPhoneNumber2.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber2FocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        retrieveStay();
    }//GEN-LAST:event_formWindowOpened

    private void provinceChange() {
        cmbProvince.addItemListener((ItemEvent e) -> {
            int index = cmbProvince.getSelectedIndex();
            if (index != -1) {
                try {
                    String provinceID = std.province.get(index).toString();
                    sql = "select * from district where provinceID=" + provinceID;
                    try (ResultSet rs1 = con.createStatement().executeQuery(sql)) {
                        std.district.clear();
                        cmbDistrict.removeAllItems();
                        while (rs1.next()) {
                            std.district.add(rs1.getString("districtID"));
                            cmbDistrict.addItem(rs1.getString("districtName"));
                        }
                        rs1.close();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

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
            java.util.logging.Logger.getLogger(EditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditStudent dialog = new EditStudent(new javax.swing.JFrame(), true, std);
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
    private javax.swing.JButton btnSave;
    public static javax.swing.JComboBox<String> cmbDistrict;
    public static javax.swing.JComboBox<String> cmbProvince;
    public static javax.swing.JComboBox<String> cmbSex;
    public static javax.swing.JComboBox<String> cmbStay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static Swing.TextField txtID;
    public static Swing.TextField txtLastname;
    public static Swing.TextField txtName;
    public static Swing.TextField txtPhoneNumber1;
    public static Swing.TextField txtPhoneNumber2;
    public static Swing.TextField txtSchool;
    // End of variables declaration//GEN-END:variables

}
